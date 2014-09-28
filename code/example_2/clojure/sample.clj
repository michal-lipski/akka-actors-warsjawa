;Haskell
;Clojure
(def counter (ref 0))
(println @counter)
(dosync (ref-set counter 1))
(println @counter)
