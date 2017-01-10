package com.jscriptive.scala.third.chapter32

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

object SimpleFutures {

  def main(args: Array[String]): Unit = {
    val future1 = Future {
      Thread.sleep(10000)
      21 + 21
    }

    println(future1.value)
    println(future1.map(x => x + 1).value)

    val future2 = Future {
      Thread.sleep(10000)
      23 + 23
    }

    // this one takes about 10 seconds to complete, 'coz parallel
    val result1 = for {
      x <- future1
      y <- future2
    } yield x + y
    println(result1.value)

    // this takes at least 20 seconds to complete, 'coz sequential
    val result2 = for {
      x <- Future { Thread.sleep(10000); 21 + 21}
      y <- Future { Thread.sleep(10000); 23 + 23}
    } yield x + y
    println(result2.value)

    println(Future.successful(21 + 21))
    println(Future.failed(new Exception("bummer!")))
    println(Future.fromTry(Success { Thread.sleep(1000); 21 + 21 }))


    val pro = Promise[Int]
    val fut = pro.future
    println(fut.value)
    println(pro.success(42))
    println(fut.value)


    val res = Future { 42 / 1 }
      .transform(
        s => s * 2,
        f => new Exception("see cause", f))
    println(res)

    val fail = Future.failed(new Exception("oy vey!"))
    val nonNegative = fail.transform {
      case Success(r) => Success(r.toString)
      case Failure(_) => Success("fail")
    }
    println(nonNegative)
  }
}
