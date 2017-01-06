package com.jscriptive.scala.third.chapter28

import scala.xml.{Elem, Node, XML}

object XmlSerialization {

  abstract class CCTherm {
    val description: String
    val yearMade: Int
    val dateObtained: String
    val bookPrice: Int     // in US cents
    val purchasePrice: Int // in US cents
    val condition: Int     // 1 to 10

    override def toString: String = "CCTherm - " + description

    def toXML: Elem =
      <cctherm>
        <description>{description}</description>
        <yearMade>{yearMade}</yearMade>
        <dateObtained>{dateObtained}</dateObtained>
        <bookPrice>{bookPrice}</bookPrice>
        <purchasePrice>{purchasePrice}</purchasePrice>
        <condition>{condition}</condition>
        <methods>
          <toString>{toString}</toString>
        </methods>
      </cctherm>
  }

  object CCTherm {

    def fromXML(node: Node): CCTherm =
      new CCTherm {
        override val description: String = (node \"description").text
        override val yearMade: Int = (node \"yearMade").text.toInt
        override val dateObtained: String = (node \"dateObtained").text
        override val condition: Int = (node \"condition").text.toInt
        override val bookPrice: Int = (node \"bookPrice").text.toInt
        override val purchasePrice: Int = (node \"purchasePrice").text.toInt
      }

    def proc(node: Node): String =
      node match {
        case <description>{desc}</description> => "It's the description: " + desc
        case <bookPrice>{price}</bookPrice> => "It's the book price: " + price
        case <purchasePrice>{price}</purchasePrice> => "It's the purchase price: " + price
        case _ => "It's something else"
      }
  }

  def main(args: Array[String]): Unit = {
    val therm = new CCTherm {
      override val description: String = "hot dog #5"
      override val yearMade: Int = 1952
      override val dateObtained: String = "March 14, 2006"
      override val bookPrice: Int = 2199
      override val purchasePrice: Int = 500
      override val condition: Int = 9
    }

    println(therm.toXML)
    println(therm.toXML.text)
    println(therm.toXML \ "description")
    println(therm.toXML \\ "toString")
    println(CCTherm.fromXML(therm.toXML))

    val filename = "therm.xml"
    XML.save(filename, therm.toXML)
    val loaded = XML.load(filename)
    println("loaded from file: " + loaded)
    println("from loaded: " + CCTherm.fromXML(loaded))
    println(CCTherm.proc((therm.toXML \ "description").head))

    val joe: Elem = <employee name="Joe" rank="code monkey" serial="123"/>
    println(joe)
    println(joe \ "employee")
    println(joe \ "@name")
  }
}
