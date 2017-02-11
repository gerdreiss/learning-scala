package com.jscriptive.scala.fp.chapter7

import java.util.concurrent.atomic.AtomicReference
import java.util.concurrent.{Callable, CountDownLatch, ExecutorService}

object Nonblocking {

  sealed trait Future[A] {
    private[Nonblocking] def apply(k: A => Unit): Unit
  }

  type Par[+A] = ExecutorService => Future[A]

  def unit[A](a: A): Par[A] = _ => (k: (A) => Unit) => k(a)

  def fork[A](a: => Par[A]): Par[A] =
    es => (k: (A) => Unit) => eval(es)(a(es)(k))

  def eval(es: ExecutorService)(r: => Unit): Unit =
    es.submit(new Callable[Unit] {
      def call: Unit = r
    })

  def choice[A](cond: Par[Boolean])(t: Par[A], f: Par[A]): Par[A] =
    es =>
      if (run(es)(cond).booleanValue()) t(es)
      else f(es)

  def map2[A, B, C](p1: Par[A], p2: Par[B])(f: (A, B) => C): Par[C] =
    es => (k: (C) => Unit) => {
      var ar: Option[A] = None
      var br: Option[B] = None

      val combiner = Actor[Either[A, B]](es) {
        case Left(a) => br match {
          case None => ar = Some(a)
          case Some(b) => eval(es)(k(f(a, b)))
        }
        case Right(b) => ar match {
          case None => br = Some(b)
          case Some(a) => eval(es)(k(f(a, b)))
        }
      }
      p1(es)(a => combiner ! Left(a))
      p2(es)(b => combiner ! Right(b))
    }

  def run[A](s: ExecutorService)(p: Par[A]): A = {
    val ref = new AtomicReference[A]
    val latch = new CountDownLatch(1)
    p(s) { a => ref.set(a); latch.countDown() }
    latch.await()
    ref.get
  }

}
