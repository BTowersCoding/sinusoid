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
         "\\begin{aligned}2x&=0.48+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{0.48+n\\cdot2\\pi}{2}=0.24+n\\cdot\\dfrac{2\\pi}{2}\\end{aligned}"))
  (is (= (notebooks.sinusoid/solution1-rad
          {:amplitude 6
           :trig-fn   "\\sin"
           :period    20
           :x-shift   1
           :val       1})
         "\\begin{aligned}20x&=0.0+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{0.0+n\\cdot2\\pi}{20}=0.0+n\\cdot\\dfrac{2\\pi}{20}\\end{aligned}")))

(deftest solution1-degrees-test
  (testing "Cosine"
    (is (= (notebooks.sinusoid/solution1-deg
            {:amplitude 8
             :trig-fn "\\cos"
             :period 12
             :x-shift 4
             :val -4})
           "\\begin{aligned}12x&=180.0^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{180.0^\\circ+n\\cdot360^\\circ}{12}=15.0^\\circ+n\\cdot30^\\circ\\end{aligned}")))
  (testing "Sine"
    (is (= (notebooks.sinusoid/solution1-deg
            {:amplitude 2
             :trig-fn   "\\sin"
             :period    4
             :x-shift   6
             :val       5})
           "\\begin{aligned}4x&=-30.0^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{-30.0^\\circ+n\\cdot360^\\circ}{4}=-7.5^\\circ+n\\cdot90^\\circ\\end{aligned}"))))

(deftest solution2-sin-radians-test
  (is (= (notebooks.sinusoid/solution2-sin-rad
          {:amplitude 13
           :trig-fn   "\\sin"
           :period    2
           :x-shift   -3
           :val       3})
         "\\begin{aligned}2x&=2.662+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{2.662+n\\cdot2\\pi}{2}=1.331+n\\cdot\\dfrac{2\\pi}{2}\\end{aligned}"))
  (is (= (notebooks.sinusoid/solution2-sin-rad
          {:amplitude 6
          :trig-fn   "\\sin"
          :period    20
          :x-shift   1
          :val       1})
         "\\begin{aligned}20x&=3.142+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{3.142+n\\cdot2\\pi}{20}=\\dfrac{\\pi}{20}+n\\cdot\\dfrac{2\\pi}{20}\\end{aligned}")))

(deftest solution2-cos-radians-test
   (is (= (notebooks.sinusoid/solution2-cos-rad
           {:amplitude 16
            :trig-fn   "\\cos"
            :period    8
            :x-shift   -2
            :val       6})
          "\\begin{aligned}8x&=-\\dfrac{\\pi}{3}+n\\cdot2\\pi \\\\\\\\x&=\\dfrac{-\\dfrac{\\pi}{3}+n\\cdot2\\pi}{8}=-\\dfrac{\\pi}{24}+n\\cdot\\dfrac{2\\pi}{8}\\end{aligned}")))

(deftest solution2-sin-degrees-test
  (is (= (notebooks.sinusoid/solution2-sin-deg
          {:amplitude 2
           :trig-fn   "\\sin"
           :period    4
           :x-shift   6
           :val       5})
         "\\begin{aligned}4x&=-150.0^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{-150.0^\\circ+n\\cdot360^\\circ}{4}=-37.5^\\circ+n\\cdot90^\\circ\\end{aligned}")))

(deftest solution2-cos-degrees-test
  (is (= (notebooks.sinusoid/solution2-cos-deg
          {:amplitude 5
           :trig-fn   "\\cos"
           :period    6
           :x-shift   6
           :val       9})
         "\\begin{aligned}6x&=-53.13^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{-53.13^\\circ+n\\cdot360^\\circ}{6}=-8.86^\\circ+n\\cdot60^\\circ\\end{aligned}"))
  (is (= (notebooks.sinusoid/solution2-cos-deg
          {:amplitude 4
           :trig-fn   "\\cos"
           :period    10
           :x-shift   2
           :val       2})
         "\\begin{aligned}10x&=-90.0^\\circ+n\\cdot360^\\circ \\\\\\\\x&=\\dfrac{-90.0^\\circ+n\\cdot360^\\circ}{10}=-9.0^\\circ+n\\cdot36^\\circ\\end{aligned}")))

(comment
  (clojure.test/run-tests)
  )