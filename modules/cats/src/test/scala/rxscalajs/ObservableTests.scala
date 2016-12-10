package rxscalajs

import scala.concurrent.duration._

class ObservableTests extends Helpers {

  "observable" should {
    "map" in {
      val o1 = Observable.just(1,2,3)

      val t = o1.map(_ + 1)

      toList(t) shouldBe List(2,3,4)
    }

    "collect" in {
      val o1 = Observable.just(1,2,3,4,5)

      val t = o1.collect {
        case x if x > 2 => x * 2
      }

      toList(t) shouldBe List(6,8,10)
    }

    "zip" in {
      val o1 = Observable.just(1,2,3)
      val o2 = Observable.just('a','b','c')
      val t = o1.zipWith(o2)((a,b) => (a,b))

      toList(t) shouldBe List((1,'a'), (2,'b'), (3,'c'))
    }

    "zip with index" in {
      val o1 = Observable.just(1,2,3)
      val t = o1.zipWithIndex

      toList(t) shouldBe List((1,0), (2,1), (3,2))
    }

    "flatten" in {

      val o1 = Observable.just(1,2,3)
      val o2 = Observable.just('a','b','c')

      val t = o1.map(x => o2.map(y => (x, y))).flatten

      toList(t) shouldBe List((1,'a'), (1,'b'), (1,'c'), (2,'a'), (2,'b'), (2,'c'), (3,'a'), (3,'b'), (3,'c'))
    }

    "factory combine latest" in {
      val xs = Vector(1,2,3,4).map(x => Observable.just(x))
      val obs = Observable.combineLatest(xs)

      toList(obs) shouldBe List(List(1,2,3,4))
    }
  }

}
