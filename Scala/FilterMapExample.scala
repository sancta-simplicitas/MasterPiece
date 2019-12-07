import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object FilterMapExample {

    // This KtStd is written by @hzqd
    implicit class KtStd[T](a: T) {
        def let[R](f: T => R): R = f(a)
    }

    // This filterMap is written by @moeKiwiSAMA
    implicit class Functional[T](iterable: Iterator[T]) {
        def filterMap[R](functor: T => Option[R]): Iterator[R] = {
            val resultBuffer = ListBuffer[R]()
            iterable.foreach(iter => functor(iter) match {
                case None => ()
                case Some(value) => resultBuffer.append(value)
            })
            resultBuffer.iterator
        }
    }


    def main(args: Array[String]): Unit = {
        StdIn.readLine().split(" ").iterator.filterMap(_.toIntOption).sum.let(println)
    }
}
