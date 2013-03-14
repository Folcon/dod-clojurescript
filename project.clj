(defproject dod "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ;; clojure source code pathname
  :source-paths ["src/clj"]

  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [hiccups "0.2.0"]]

  :plugins [;; cljsbuild plugin
            [lein-cljsbuild "0.3.0"]
            [lein-ring "0.8.3"]]

  ;; ring tasks configuration
  :ring {:handler dod.core/handler}

  ;; cljsbuild tasks configuration
  :cljsbuild {:repl-listen-port 9000
              :repl-launch-commands
              ;; Launch command for connecting the page of choice to
              ;; the REPL. Only works if the page at URL automatically
              ;; connects to the REPL, like
              ;; http://localhost:3000/repl-demo does.
              ;; $ lein trampoline cljsbuild repl-launch firefox <URL>
              {"firefox" ["firefox"
                          :stdout ".repl-firefox-out"
                          :stderr ".repl-firefox-err"]
               ;; Launch command for interacting with your
               ;; ClojureScript at a REPL, without browsing to the app
               ;; (a static HTML file is used).
               ;; $ lein trampoline cljsbuild repl-launch firefox-naked
               "firefox-naked" ["firefox"
                                "resources/private/html/naked.html"
                                :stdout ".repl-firefox-naked-out"
                                :stderr ".repl-firefox-naked-err"]
               ;; This is similar to "firefox" except it uses
               ;;     PhantomJS. $ lein trampoline cljsbuild
               ;;     repl-launch phantom <URL>
               "phantom" ["phantomjs"
                          "phantom/repl.js"
                          :stdout ".repl-phantom-out"
                          :stderr ".repl-phantom-err"]
               ;; This is similar to "firefox-naked" except it uses
               ;;     PhantomJS.
               ;; $ lein trampoline cljsbuild repl-launch phantom-naked
               "phantom-naked" ["phantomjs"
                                "phantom/repl.js"
                                "resources/private/html/naked.html"
                                :stdout ".repl-phantom-naked-out"
                                :stderr ".repl-phantom-naked-err"]}
              :test-commands
              ;; Test command for running the unit tests in
              ;;     "test-cljs" (see below). $ lein cljsbuild test
              {"unit" ["phantomjs"
                       "phantom/unit-test.js"
                       "resources/private/html/unit-test.html"]}

              :builds
              [{;; clojurescript source code path
                :source-paths ["src/cljs"]
                
                ;; Google Closure Compiler options
                :compiler {;; the name of the emitted JS file
                           :output-to "resources/public/js/dod.js"
                           
                           ;; minimum optimization
                           :optimizations :whitespace
                           
                           ;; prettyfying emitted JS
                           :pretty-print true}}]})
