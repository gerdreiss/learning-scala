package com.jscriptive.scala.third.chapter28

object Catalog extends App {

  val catalog =
    <catalog>
      <cctherm>
        <description>hot dog #5</description>
        <yearMade>1952</yearMade>
        <dateObtained>March 14, 2006</dateObtained>
        <bookPrice>2199</bookPrice>
        <purchasePrice>500</purchasePrice>
        <condition>9</condition>
      </cctherm>
      <cctherm>
        <description>hot dog #6</description>
        <yearMade>1953</yearMade>
        <dateObtained>March 24, 2006</dateObtained>
        <bookPrice>2399</bookPrice>
        <purchasePrice>600</purchasePrice>
        <condition>8</condition>
      </cctherm>
    </catalog>

  catalog match {
    case <catalog>{therms @ _*}</catalog> =>
      for (therm @ <cctherm>{_*}</cctherm> <- therms)
        println("processing: " + (therm \ "description").text)
  }
}
