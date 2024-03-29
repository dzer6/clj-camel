(ns repl
  (:require [cemerick.pomegranate :as pomegranate]
            [cemerick.pomegranate.aether :as aether]
            [clojure.java.io]
            [clojure.test]
            [clojure.tools.logging :as log]
            [clojure.tools.namespace.repl :as tn]))

(def aliases
  {'io  'clojure.java.io
   'log 'clojure.tools.logging})

(defn deinit-aliases []
  (doseq [[from _] aliases]
    (ns-unalias 'repl from)))

(defn init-aliases []
  (deinit-aliases)
  (doseq [[from to] aliases]
    (alias from to)))

(defn add-dependency [coordinates]
  (pomegranate/add-dependencies
    :coordinates [coordinates]
    :repositories (merge aether/maven-central
                         {"clojars" "https://clojars.org/repo"})))

;;

;; Lifecycle
(defn init []
  (try
    (in-ns 'repl)
    (deinit-aliases)
    (let [result (tn/refresh-all)]
      (when (instance? Throwable result)
        (throw result))
      (init-aliases)
      :done)
    (catch Throwable e
      (log/error e "Unable to init app"))))

(defn reset []
  (try
    (deinit-aliases)
    (tn/refresh)
    (init-aliases)
    :done
    (catch Throwable e
      (log/error e "Unable to reset app"))))

(defn keyword->state [kw]
  (get {} kw))

(defn run-tests
  ([]
   (run-tests #"clj-camel.*?\-test"))
  ([regular-expression]
   (clojure.test/run-all-tests regular-expression)))
