package rxscalajs

class ObservableTests extends Helpers {

  "observable" should {
    "map" in {
      val o1 = Observable.just(1,2,3)

      val t = o1.map(_ + 1)

      toList(t) shouldBe List(2,3,4)
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
  }

}
