(ns sinusoid-test
  (:require [clojure.test :refer [deftest testing is]]
            notebooks.sinusoid))

(deftest solution1-radians-test
  (is (= (notebooks.sinusoid/solution1-rad 
          {:amplitude 13
           :trig-fn   "\\sin"
           :period    2
           :x-shift   -3
           :val       3})
         "\\begin{aligned}2x&=0.48+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{0.48+n\\cdot2\\pi}{2}=0.24+n\\cdot\\dfrac{2\\pi}{2}\\end{aligned}")))

(deftest solution1-degrees-test
  (is (= (notebooks.sinusoid/solution1-deg
          {:amplitude 8
           :trig-fn "\\cos"
           :period 12
           :x-shift 4
           :val -4})
         "\\begin{aligned}12x&=180.0^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{180.0^\\circ+n\\cdot360^\\circ}{12}=15.0^\\circ+n\\cdot30^\\circ\\end{aligned}")))

(deftest solution2-radians-sin-test
  (is (= (notebooks.sinusoid/solution2-sin-rad
          {:amplitude 13
           :trig-fn   "\\sin"
           :period    2
           :x-shift   -3
           :val       3})
         "\\begin{aligned}2x&=2.662+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{2.662+n\\cdot2\\pi}{2}=1.331+n\\cdot\\dfrac{2\\pi}{2}\\end{aligned}")))

(comment
  (clojure.test/run-tests)
  )