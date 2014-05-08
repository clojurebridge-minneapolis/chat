(ns awesome.handler
  (:use [compojure.core]
         [hiccup.bootstrap.middleware])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.page :as page]
            [hiccup.form :as form]
            [hiccup.bootstrap.page :as boot]))

(def messages (atom []))

(defn chat [name msg]
  (when-not (empty? msg)
    (swap! messages conj [name msg]))
  (page/html5
   [:head
    [:title "Chat"]
    (boot/include-bootstrap)]
   [:body
     [:h1 "Chat"]
     [:div.well
     (map (fn [message] [:div [:strong (first message)] " " (second message)]) @messages)]
     (form/form-to
      [:post "/"]
      [:div "Name:" (form/text-field "name" name) " Message:" (form/text-field "msg")]
      (form/submit-button "Submit"))]))

(defn iam [params]
  (page/html5
     (let [name (:name params)]
     [:div "You are " name "!"
      [:ul
       [:li (str params)]
       [:li "Your name is "
        (if (odd? (count name))
          "Odd"
          "Even")]
        (when (> (count name) 7)
            [:li "You should consider a nickname."])]])))

; this is a function
(defn who []
  (page/html5
     (form/form-to [:post "/iam"]
                   [:label "Who are you?"]
                   (form/text-field "name")
                   (form/submit-button {:name "submit"} "Submit"))))

(defroutes app-routes
  ;;(GET "/" [] (chat))
  (GET "/hello2" [] (page/html5 [:div "Hello from " [:b "hiccup"] "!"]))
  (GET "/who" [] (who))
  (POST "/iam" {params :params} (iam params))
  (ANY "/" {params :params} (chat (:name params) (:msg params)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site (wrap-bootstrap-resources app-routes)))
