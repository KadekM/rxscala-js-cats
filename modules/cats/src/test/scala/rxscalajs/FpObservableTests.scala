package rxscalajs

import cats._
import cats.implicits._

// Just some random tests jammed together to see usage of various operations
class FpObservableTests extends Helpers {
  import instances._

  "Functor" should {
    val F = implicitly[Functor[Observable]]

    "map" in {
      val o1 = Observable.just(1,2,3)
      val t = F.map(o1)(_ + 1)

      toList(t) shouldBe List(2,3,4)
    }
  }

  "Applicative" should {
    val F = implicitly[Applicative[Observable]]

    "replicate" in {
      val o1 = Observable.just(1,2)
      val t = F.replicateA(2, o1)

      toList(t) shouldBe List(List(1,1), List(1,2), List(2,1), List(2,2))
    }
  }

  "FlatMap" should {
    val F = implicitly[FlatMap[Observable]]

    {
      val o1 = Observable.just(1, 2)
      val fmap = (x: Int) =>  Observable.just((0 to x): _*)

      "flatMap" in {
        val t = F.flatMap(o1)(fmap)

        toList(t) shouldBe List(0, 1, 0, 1, 2)
      }

      ">>=" in {
        val t = F.>>=(o1)(fmap)

        toList(t) shouldBe List(0, 1, 0, 1, 2)
      }
    }

    "flatten" in {
      val o1 = Observable.just(Observable.just(1,2), Observable.just(3))
      val t = F.flatten(o1)

      toList(t) shouldBe List(1,2,3)
    }
  }

  "Monad" should {
    val F = implicitly[Monad[Observable]]
  }
}
