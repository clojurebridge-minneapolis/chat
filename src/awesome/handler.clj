(ns awesome.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.page :as page]))

(defroutes app-routes
  (GET "/" [] "HelloName")
  (GET "/hello2" [] (page/html5 [:div "Hello from " [:b "hiccup"] "!"]))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
