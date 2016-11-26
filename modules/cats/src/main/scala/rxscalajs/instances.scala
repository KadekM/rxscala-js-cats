package rxscalajs

import cats._
import rxscalajs.Observable

import scala.annotation.tailrec

object instances {

  implicit val observableMonad: MonadCombine[Observable] = new MonadCombine[Observable] {

    // primitives:
    override final def flatMap[A, B](fa: Observable[A])(f: (A) => Observable[B]): Observable[B] =
      fa.flatMap(f)

    override final def tailRecM[A, B](a: A)(f: A => Observable[Either[A, B]]): Observable[B] = f(a).flatMap {
      case Left(e) => tailRecM(e)(f)
      case Right(x) => pure(x)
    }

    override final def pure[A](x: A): Observable[A] = Observable.just(x)

    override def empty[A]: Observable[A] = Observable.just[A]()

    override def combineK[A](x: Observable[A], y: Observable[A]): Observable[A] = x.merge(y)

    // existing:

    override final def map[A, B](fa: Observable[A])(f: (A) => B): Observable[B] = fa.map(f)

    override final def >>=[A, B](fa: Observable[A])(f: (A) => Observable[B]): Observable[B] = fa.flatMap(f)

    override final def flatten[A](ffa: Observable[Observable[A]]): Observable[A] = ffa.flatten
  }

}
