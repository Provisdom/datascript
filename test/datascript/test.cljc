(ns datascript.test
  (:require
    #?(:cljs [cljs.test    :as t :refer-macros [is are deftest testing]]
       :clj  [clojure.test :as t :refer        [is are deftest testing]])
    #?(:clj [clojure.java.shell :as sh])
    datascript.test.core
   
    datascript.test.btset
    datascript.test.components
    datascript.test.conn
    datascript.test.db
    datascript.test.entity
    datascript.test.explode
    datascript.test.filter
    datascript.test.ident
    datascript.test.index
    datascript.test.listen
    datascript.test.lookup-refs
    datascript.test.lru
    datascript.test.parser
    datascript.test.parser-find
    datascript.test.parser-rules
    datascript.test.parser-query
    datascript.test.parser-where
    datascript.test.pull-api
    datascript.test.pull-parser
    datascript.test.query
    datascript.test.query-aggregates
    datascript.test.query-find-specs
    datascript.test.query-fns
    datascript.test.query-not
    datascript.test.query-pull
    datascript.test.query-rules
    datascript.test.query-v3
    datascript.test.serialization
    datascript.test.transact
    datascript.test.validation
    datascript.test.upsert))

(defn ^:export test-most []
  (datascript.test.core/wrap-res #(t/run-all-tests #"datascript\.test\.(?!btset).*")))

(defn ^:export test-btset []
  (datascript.test.core/wrap-res #(t/run-all-tests #"datascript\.test\.btset")))

(defn ^:export test-all []
  (datascript.test.core/wrap-res #(t/run-all-tests #"datascript\..*")))

#?(:clj
(defn test-node [& args]
  (let [res (apply sh/sh "node" "test_node.js" args)]
    (println (:out res))
    (binding [*out* *err*]
      (println (:err res)))
    (System/exit (:exit res)))))
