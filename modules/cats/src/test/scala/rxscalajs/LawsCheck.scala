package rxscalajs

import cats._
import cats.implicits._
import cats.laws.discipline._
import org.scalacheck._
import instances._

/* TODO: once we bump cats to 0.8+
class LawsCheck extends Helpers {

  // observables can be potentially infinite, so we can't provide instance in library
  // for tests though we should be fine
  // this is sketchy at best

  implicit def observableEq[A](implicit listEq: Eq[List[A]]) : Eq[Observable[A]] = new Eq[Observable[A]] {
    override def eqv(x: Observable[A], y: Observable[A]): Boolean = {
      listEq.eqv(toList(x), toList(y))
    }
  }

  implicit def cogenForObservable[A](implicit A: Cogen[A]): Cogen[Observable[A]] = ??? // todo

  implicit def arbObs[A](implicit arbA: Arbitrary[A]): Arbitrary[Observable[A]] =
    Arbitrary(arbA.arbitrary.map(x => Observable.just(x)))

  CartesianTests[Observable].cartesian[Int, Int, Int].all.check()
  SerializableTests.serializable(CartesianTests[Observable]).all.check()

  MonadCombineTests[Observable].monadCombine[Int, Int, Int].all.check()
  SerializableTests.serializable(MonadCombineTests[Observable]).all.check()

  // CoflatMapTests[Observable].coflatMap[Int, Int, Int].all.check()
  // SerializableTests.serializable(CoflatMap[Stream]).all.check()
}
*/
