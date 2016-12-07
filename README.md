# Cats instances for Scala.js rx

[![Build Status](https://travis-ci.org/KadekM/rxscala-js-cats.svg?branch=dev)](https://travis-ci.org/KadekM/rxscala-js-cats)
[![Maven Central](https://img.shields.io/maven-central/v/com.marekkadek/rxscala-js-cats_sjs0.6_2.12.svg)](https://maven-badges.herokuapp.com/maven-central/com.marekkadek/rxscala-js-cats_sjs0.6_2.12)

## Install

```scala
libraryDependencies += "com.marekkadek" %%% "rxscala-js-cats" % "0.2.0-SNAPSHOT"
```

It doesn't come bundled with the underlying `rx.js` file, so you'll need to either add them manually or specify them as `jsDependencies`:

```scala
jsDependencies += "org.webjars.npm" % "rxjs" % "5.0.0-rc.4" / "bundles/Rx.min.js" commonJSName "Rx"
```

## Usage

```scala
import cats.implicits._
import rxscalajs.instances._

def foo[F[_]: Applicative](f: F[_]) = ???
...
foo(Observable.just(1,2,3))
```

It uses [scalajs rx facade](https://github.com/LukaJCB/rxscala-js)
