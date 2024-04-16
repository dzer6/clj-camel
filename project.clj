(def camel-version "4.5.0")

(defproject com.dzer6/clj-camel "3.0.0"
  :description "Clojure wrapper for Apache Camel"
  :url "https://github.com/dzer6/clj-camel"
  :license {:name "Apache License Version 2.0"
            :url  "https://www.apache.org/licenses/LICENSE-2.0.txt"}

  :target-path "target/%s"

  :source-paths ["src/main/clj"]
  :test-paths ["src/test/clj" "src/test/resources"]

  :dependencies [[malabarba/lazy-map "1.3"]
                 [defun "0.4.0"]]

  :deploy-repositories [["clojars" {:url           "https://clojars.org/repo"
                                    :username      :env/clojars_user ; CLOJARS_USER
                                    :password      :env/clojars_pass ; CLOJARS_PASS
                                    :sign-releases false}]]

  :profiles {:dev {:source-paths   ["dev"
                                    "src/main/clj"]

                   :resource-paths ["src/test/resources"]

                   :repl-options   {:port    4010
                                    :init-ns user}

                   :dependencies   [[org.clojure/clojure "1.11.1"]
                                    [org.clojure/tools.logging "1.0.0"]
                                    [org.clojure/tools.nrepl "0.2.13"]
                                    [org.clojure/tools.namespace "0.2.11"]
                                    [ch.qos.logback/logback-classic "1.2.3"]

                                    [camel-snake-kebab "0.4.1"]
                                    [metosin/jsonista "0.2.6"]
                                    [clj-time "0.15.2"]

                                    [com.cemerick/pomegranate "1.1.0"]
                                    [com.rpl/specter "1.1.3"]

                                    [org.apache.camel/camel-core ~camel-version]
                                    [org.apache.camel/camel-api ~camel-version]
                                    [org.apache.camel/camel-sql ~camel-version]
                                    [org.apache.camel/camel-jcache ~camel-version]
                                    [org.apache.camel/camel-ehcache ~camel-version]
                                    [org.apache.camel/camel-http ~camel-version]
                                    [org.apache.camel/camel-jsonpath ~camel-version]
                                    [org.apache.camel/camel-management ~camel-version]]

                   :plugins        [[test2junit "1.4.2"]]}})