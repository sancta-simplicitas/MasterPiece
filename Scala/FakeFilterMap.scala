import scala.collection.mutable.ListBuffer
import scala.io.StdIn

object FakeFilterMap {
    // This KtStd is written by @hzqd
    implicit class KtStd[T](a: T) {
        def let[R](f: T => R): R = f(a)
    }

    implicit class FM[T](seq: Iterator[T]) {
        def filterMap[R](f: T => R): ListBuffer[Option[R]] = {
            val optionList = ListBuffer[Option[R]]()
            for (iter <- seq) {
                try {
                    optionList.append(Some(f(iter)))
                } catch {
                    case _: Exception => optionList.append(None)
                }
            }
            optionList
        }
    }

    implicit class OK[T](seq: ListBuffer[Option[T]]) {
        def ok(): ListBuffer[T] = {
            val resultList = ListBuffer[T]()
            for (iter <- seq) {
                if (iter.isDefined) {
                    resultList.append(iter.get)
                }
            }
            resultList
        }
    }

    def main(args: Array[String]): Unit = {
        StdIn.readLine().split(" ").iterator.filterMap(_.toInt).ok().sum.let(println)
    }
}
