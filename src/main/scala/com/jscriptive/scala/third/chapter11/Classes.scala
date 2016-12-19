package com.jscriptive.scala.third.chapter11

object Classes {

  // value classes
  class Anchor(val value: String) extends AnyVal
  class Style(val value: String) extends AnyVal
  class Text(val value: String) extends AnyVal
  class Html(val value: String) extends AnyVal

  def title(text: Text, anchor: Anchor, style: Style): Html =
    new Html(s"<a id='${anchor.value}'><h1 class='${style.value}'>${text.value}</h1></a>")

  def main(args: Array[String]): Unit = {

    val html = title(
      new Text("Value Classes"),
      new Anchor("chap:vcls"),
      new Style("bold")
    )

    println(html.value)
  }
}
