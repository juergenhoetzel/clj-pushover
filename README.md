# clj-pushover

Clojure bindings to the Pushover.net service.

##Leiningen

###NOTE: this library is fully tested under Clojure 1.6

Just add the following to your project.clj file in the _dependencies_ section:

```
[clj-pushover "0.1.0"]
```


## Usage
```
(require  '[clj-pushover.core :as pushover])

(let [response (pushover/send-message! {:message "FooBar" :user "USERKEY" :token "APPTOKEN" })]
        (if (pushover/error-response? response)
          (println "Something went wrong" (:errors response))
          (println "Sent!")))
```
## License

Copyright © 2015 Jürgen Hötzel

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
