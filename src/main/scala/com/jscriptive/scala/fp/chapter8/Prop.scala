package com.jscriptive.scala.fp.chapter8

trait Prop {
  def check: Boolean
  def &&(p: Prop): Prop = new Prop {
    override def check: Boolean = super.check && p.check
  }
}
