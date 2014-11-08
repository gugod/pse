(ns pse.index-plain-text
  (:use [clojure.java.io :as io])
  (:import
   (org.apache.lucene.util Version)
   (org.apache.lucene.store NIOFSDirectory)
   (org.apache.lucene.index IndexWriter IndexWriterConfig)
   (org.apache.lucene.analysis.standard StandardAnalyzer)
   (org.apache.lucene.document Document Field FieldType))
  (:gen-class))

(defn -main
  ""
  [index-dir-path & text-files]

  (let [index-dir-io (io/file index-dir-path)
        index-writer (IndexWriter.
                      (NIOFSDirectory/open index-dir-io)
                      (IndexWriterConfig. Version/LUCENE_CURRENT (StandardAnalyzer. Version/LUCENE_CURRENT)))
        stored-and-analyzed (doto (FieldType.) (.setStored true) (.setTokenized true))]

    (doall (map
            (fn [text-file]
              (.addDocument index-writer
                            (doto (Document.)
                              (.add (Field. "name"    text-file         stored-and-analyzed))
                              (.add (Field. "content" (slurp text-file) stored-and-analyzed)))))
            text-files))
    (.close index-writer)))
