
(ns app.snippets )

(def component
  "(defcomp comp-child [prop-b]\n  (div {} (<> (str prop-b))))\n\n(defcomp comp-demo [prop-a prop-b]\n  (div {:class-name \"demo\" :style {} :on {}}\n    (<> (str prop-a))\n    (comp-child prop-b)))\n\n; respo.macros/div\n; respo.macros/<>\n; respo.macros/defcomp\n")

(def controller
  "(defn on-click [e dispatch! mutate!]\n  (dispatch! :confirm \"from button\")\n  (println \"clicked\"))\n\n(div {:style ui/button\n      :on-click on-click}})\n\n; respo-ui.style :as ui\n")

(def hot-swapping
  "; triggered by build tool\n(defn reload! []\n  ; remove caches of component tree\n  (clear-cache!)\n  ; now rendering process use no caches\n  (render-app!)\n  (println \"Code update.\"))\n\n; respo.core/clear-cache!\n")

(def inline-styles
  "(def style-page\n  {:background-color (hsl 240 80 80)\n   :font-size \"14px\"})\n\n(div {:style (merge ui/fullscreen ui/column style-page)})\n\n; hsl.core :as hsl\n; respo-ui.style :as ui\n")

(def model
  "; atom to define mutable store\n(defonce *store\n  (atom\n    {:tasks []\n     ; this is states\n     :states {}}))\n\n(defn updater [store op op-data]\n  (case op\n    :states (update store :states (mutate op-data))\n    :add-task (add-task store op-data)\n    store))\n\n(defn dispatch! [op op-data]\n  ; mutate store\n  (reset! *store (updater @*store op op-data)))\n\n; watch store changes\n(add-watch *store\n  (fn [] (println \"store is changed!\")))\n")

(def states
  "(defn on-input [e dispatch! mutate!]\n  ; send next state to store, internally using dispatch!\n  (mutate! (:value e)))\n\n(defcomp comp-field [states]\n  (let\n    ; states are inside store as a states tree\n    ; use `or` to specify initial state\n    [state (or (:data states) \"\")]\n    (div {}\n      (div {}\n        (<> span \"name\" nil))\n      (input {:placeholder \"name\"\n              :value state\n              :on-input on-input}))))\n\n(defcomp comp-parent [states]\n  (div {}\n    ; create a sub-cursor in states tree, pass down to it\n    (cursor-> :field comp-field states)))\n\n; respo.macros/cursor->\n")

(def view
  "(defcomp comp-container [store]\n  (div {}))\n\n(defonce *store (atom {:states {}}))\n\n(def mount-target (.querySelector js/document \".app\"))\n\n(defn render-app! []\n  (let [app (comp-container @*store)]\n    (render! mount-target app dispatch!)))\n\n; respo.macros/div\n; respo.core/create-comp\n; respo.core/render!\n")
