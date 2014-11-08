(defproject pse "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "CC0 1.0 Universal"
            :url "http://creativecommons.org/publicdomain/zero/1.0/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.apache.lucene/lucene-core "4.10.2"]
                 [org.apache.lucene/lucene-analyzers-common "4.10.2"]
                 [org.apache.lucene/lucene-queries "4.10.2"]
                 [org.apache.lucene/lucene-queryparser "4.10.2"]]
  :main ^:skip-aot pse.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
