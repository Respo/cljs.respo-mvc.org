
(ns client.writer
  (:require [clojure.string :as string]))

(def fs (js/require "fs"))
(def gaze (js/require "gaze"))
(def path (js/require "path"))

(defn slurp [x]
  (.readFileSync fs x "utf8"))

(defn spit [x y]
  (.writeFileSync fs x y))

(def ref-docs (atom {}))
(def ref-guide (atom {}))
(def ref-snippets (atom {}))

(defn read-path [filepath]
  (let [piece (.relative path (.join path js/process.env.PWD "md-files") filepath)
        [category filename] (string/split piece "/")]
    [category filename]))

(defn watch! []
  (gaze
    (clj->js ["md-files/docs/*.md"
              "md-files/guide/*.md"
              "md-files/snippets/*.cljs"])
    (fn [err watcher]
      (.on watcher "changed" (fn [filepath]
                                 (let [[category filename] (read-path filepath)
                                       content (slurp filepath)]
                                    (case category
                                      "docs" (swap! ref-docs assoc filename content)
                                      "guide" (swap! ref-guide assoc filename content)
                                      "snippets" (swap! ref-snippets assoc filename content)
                                      nil))))))
  (println "Watching files..."))

(defn write-as-file! [file-data file-id]
  (let [file-name (.join path js/process.env.PWD "polyfill/client" (str file-id ".cljs"))
        header-part (str "(ns client." file-id ")")
        defs (->> file-data
                  (sort-by first)
                  (map (fn [entry]
                          (let [[k v] entry]
                            (str "(def " k " " (pr-str v) ")")))))
        content (string/join "\n\n" (cons header-part defs))]
    (println "Writing file:" file-id)
    (spit file-name (str "\n" content "\n"))))

(defn watch-files! []
  (watch!)
  (add-watch ref-docs :update (fn [] (write-as-file! @ref-docs "docs")))
  (add-watch ref-guide :update (fn [] (write-as-file! @ref-guide "guide")))
  (add-watch ref-snippets :update (fn [] (write-as-file! @ref-snippets "snippets"))))

(defn load-content! [folder-path ref-target]
  (let [files (.readdirSync fs folder-path)]
    (doseq [file files]
      (let [file-path (.join path folder-path file)]
        (swap! ref-target assoc file (slurp file-path))))))

(defn provide-files! []
  (load-content! "md-files/docs" ref-docs)
  (load-content! "md-files/guide" ref-guide)
  (load-content! "md-files/snippets" ref-snippets))

(defn perform-write! []
  (write-as-file! @ref-docs "docs")
  (write-as-file! @ref-guide "guide")
  (write-as-file! @ref-snippets "snippets"))

(provide-files!)

(if (= js/process.env.op "compile")
  (perform-write!)
  (watch-files!))
