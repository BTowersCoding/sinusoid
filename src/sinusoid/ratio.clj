(ns sinusoid.ratio)

;; To render fractions we implement quick & dirty ratios,
;; so we can convert a decimal value to a TeX string.
;; There is `clojure.core/rationalize`, but we want it to work in cljs too. 

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(defn ratios
  "Builds a map of decimal values to their ratios as vectors
   using integers from 1 to `end` as numerators and denominators."
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
  (let [[n d] (get (ratios 100) dec)]
    (if (and n d)
      (str "\\dfrac{" n "}{" d "}")
      dec)))

;; we may want to be even more flexible:

(defn very-close? [n1 n2]
  (> 0.000001 (Math/abs (- n1 n2))))

(defn rat [n]
  (or (first (filter #(very-close? % n)
                     (keys (ratios 100))))
      n))

(comment
  ((juxt numerator denominator)
   (rat (/ 3.0 7))))