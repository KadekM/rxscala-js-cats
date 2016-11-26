package rxscalajs

import org.scalatest.{Matchers, WordSpec}

import scala.collection.mutable

trait Helpers extends WordSpec with Matchers {
  def toList[A](o: Observable[A]): List[A] = {
    val xs = mutable.ArrayBuffer[A]()

    o.subscribe(x => xs.append(x))

    xs.toList
  }
}
