(ns pse.search
  (:use [clojure.java.io :as io])
  (:import
   (org.apache.lucene.util Version)
   (org.apache.lucene.index DirectoryReader)
   (org.apache.lucene.search IndexSearcher)
   (org.apache.lucene.store NIOFSDirectory)
   (org.apache.lucene.queryparser.simple SimpleQueryParser)
   (org.apache.lucene.analysis.standard StandardAnalyzer))
  (:gen-class))

(defn -main
  ""
  [index-dir-path query-string]
  (let [index-dir-io (NIOFSDirectory/open (io/file index-dir-path))
        index-reader (DirectoryReader/open index-dir-io)
        query-parser (SimpleQueryParser. (StandardAnalyzer. Version/LUCENE_CURRENT) "content")
        index-searcher (IndexSearcher. index-reader)
        query (.parse query-parser query-string)
        hits  (.search index-searcher query nil 1000)]
    (print (.totalHits hits))
    ;; (doall
    ;;  (map (fn [hit]
    ;;         (println (str (.get (.doc index-searcher (.doc hit)) "name")))
    ;;         ) hits))
    (.close index-reader)
    (.close index-dir-io)
    )
  )
