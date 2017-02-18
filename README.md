
Home page for Respo Project
----

Site: http://respo.site

### Develop

Workflow https://github.com/mvc-works/stack-workflow

To compile:

```bash
npm i
export boot_deps=`boot show -c`
boot build-advanced
webpack
page=all lumo -Kc $boot_deps:src/ -i tasks/render.cljs
# bash tasks/rsync.sh
```

### License

MIT
