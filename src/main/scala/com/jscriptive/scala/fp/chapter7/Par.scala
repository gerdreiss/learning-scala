package com.jscriptive.scala.fp.chapter7

import java.util.concurrent.{ExecutorService, Future}

import scala.concurrent.duration.TimeUnit

object Par {

  type Par[A] = ExecutorService => Future[A]

  private case class UnitFuture[A](get: A) extends Future[A] {
    override def isDone = true
    override def get(timeout: Long, units: TimeUnit): A = get
    override def isCancelled = false
    override def cancel(evenIfRunning: Boolean) = false
  }

  def unit[A](a: A): Par[A] = (_: ExecutorService) => UnitFuture(a)
  def delay[A](a: => Par[A]): Par[A] = es => a(es)
  def lazyUnit[A](a: => A): Par[A] = delay(unit(a))
  def asyncF[A, B](f: A => B): A => Par[B] = a => lazyUnit(f(a))

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] =
    (es: ExecutorService) => UnitFuture(f(a(es).get, b(es).get))

  def map[A, B](a: Par[A])(f: A => B): Par[B] =
    map2(a, unit(()))((a, _) => f(a))

  def sequence[A](as: List[Par[A]]): Par[List[A]] =
    as match {
      case Nil => unit(Nil)
      case h :: t => map2(h, delay(sequence(t)))(_ :: _)
    }

  def parMap[A, B](as: List[A])(f: A => B): Par[List[B]] =
    sequence(as.map(asyncF(f)))

  def parFilter[A](l: List[A])(f: A => Boolean): Par[List[A]] = {
    val pars: List[Par[List[A]]] =
      l.map(asyncF((a: A) => if (f(a)) List(a) else List()))
    map(sequence(pars))(_.flatten)
  }

  def run[A](s: ExecutorService)(a: Par[A]): Future[A] = a(s)

  def equal[A](es: ExecutorService)(a1: Par[A], a2: Par[A]): Boolean =
    a1(es).get == a2(es).get

  def main(args: Array[String]): Unit = {
    // Define Laws for our API
    // map(unit(x))(f) == unit(f(x))
    // map(unit(x))(id) == unit(id(x))
    // map(unit(x))(id) == unit(x)
    // map(y)(id) == y
  }
}


