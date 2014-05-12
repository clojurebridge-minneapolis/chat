(defproject awesome "0.0.1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.3.4"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [javax.servlet/servlet-api "2.5"]
                 [hiccup "1.0.5"]
                 [hiccup-bootstrap "0.1.2"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler awesome.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]]}})
