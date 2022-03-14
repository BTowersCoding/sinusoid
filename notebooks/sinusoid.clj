^{:nextjournal.clerk/visibility #{:hide-ns}}
(ns notebooks.sinusoid
  (:require [nextjournal.clerk :as clerk]))

;; To render fractions we implement quick & dirty ratios,
;; so we can convert a decimal value to a TeX string:

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn ratios 
  "Builds a map of decimal values to their ratios as vectors
   using numerators and denominators from 1 to `end`."
  [end]
  (let [nums (for [n (range 1 end)
                   d (range 1 end)
                   :when (= 1 (gcd n d))]
               [n d])]
    (into {} (reverse (map (fn [[n d]] [(/ n d) [n d]])
                           nums)))))

(defn ratio 
  "Takes a decimal value and outputs a TeX string represesenting its ratio,
   or itself if no equivalent integer ratio exists."
  [dec]
  (let [[n d] (get (ratios 10) dec)]
    (if (and n d)
      (str "\\dfrac{" n "}{" d "}")
      dec)))

;; Now we can start the trig lesson:

(let [amplitude 7 trig-fn "\\cos" period 5 x-shift 9
      equals 12]
  (clerk/tex (str "\\begin{aligned}" amplitude trig-fn
                  "(" period "x)+" x-shift "&=" equals "\\\\\\\\ "
                  amplitude trig-fn "(" period "x)&=" (- equals x-shift) "\\\\\\\\ "
                  trig-fn "(" period "x)&=" (ratio (/ (- equals x-shift) amplitude)) "\\end{aligned}")))

^{:nextjournal.clerk/visibility #{:hide}}
(let [period 7]
  (clerk/tex (str "\\cos^{-1}\\left(\\dfrac{3}{7}\\right)=64.62^\\circ")))
