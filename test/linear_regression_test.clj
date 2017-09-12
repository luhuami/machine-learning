(ns linear-regression-test
    (:require [clojure.test :refer :all]
              [clojure.string :refer :all]
              [clojure.core.matrix :as matrix]
              [linear-regression :as lr]
              [utils.feature-normalize :as fs]))


(def row-data (slurp "test/resource/linear/regression/multi-feature.txt"))

(def row-arr (split-lines row-data))

(def arr (map #(split % #",") row-arr))

(def arr1 (flatten arr))

(def arr2 (map #(Integer/parseInt %) arr1))
;(def arr2 (map #(Double/parseDouble %) arr1))

(def x-list (partition 2 3 arr2))
;(def x-list (partition 1 2 arr2))

(def X (matrix/matrix x-list))

(def y-list (map last (partition 3 arr2)))
;(def y-list (map last (partition 2 arr2)))

(def y (matrix/matrix (map vector y-list)))

;(lr/perform-batch-gradient-decent (fs/scale X) y 0.01 50)

(lr/perform-batch-gradient-decent (fs/normalize X) y 0.01 50)



