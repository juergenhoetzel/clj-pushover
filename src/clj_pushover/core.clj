(ns ^{:doc "Pushover Message API Clojure wrapper"
      :author "Jürgen Hötzel"}
  clj-pushover.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(def ^:dynamic *rest-api* "https://api.pushover.net/1/messages.json")

(defn error-response? [{:keys [status]}]
  "Return true if the response is an error"
  (zero? status))

(defn send-message! [{:keys [token user message device title url url_title priority timespan sound html] :as request}]
  {:pre [(every? string? [token user message])]}
  "Takes a map of Pushover parameters and sends a Pushover message.

  The following keys are mandatory:
  * :user
  * :token
  * :message

  These optional parameters may be included:
  * :device
  * :title
  * :url
  * :url_title
  * :priority
  * :timespan
  * :sound
  * :html

  Returns the response map consisting of :status and :request.
  if (error-response? response) is true the response map also
  contains :errors and the the invalid parameters

  See the the Pushover docs for further information: https://pushover.net/api"
  (-> (try
        (client/post *rest-api* {:form-params request})
        (catch clojure.lang.ExceptionInfo e (.getData e)))
      :body
      (json/read-str :key-fn keyword)))


