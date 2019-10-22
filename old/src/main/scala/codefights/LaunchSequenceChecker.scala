package codefights

object LaunchSequenceChecker extends App {

  def launchSequenceChecker(systemNames: Array[String], stepNumbers: Array[Int]): Boolean = {
    systemNames.zip(stepNumbers)
      .groupBy(_._1)
      .map(tt => tt._2.map(_._2).toList)
      .forall(numbers => numbers.isEmpty || numbers.length == 1 || numbers.sliding(2).forall(ns => ns.head < ns.tail.head))
  }

  println(launchSequenceChecker(Array("stage_1", "stage_2", "dragon", "stage_1", "stage_2", "dragon"), Array(1, 10, 11, 2, 12, 111)))
  println(launchSequenceChecker(Array("stage_1", "stage_1", "stage_2", "dragon"), Array(2, 1, 12, 111)))
  println(launchSequenceChecker(Array("Dragon", "Falcon 9", "Dragon", "Falcon 9", "Falcon 9", "Dragon", "Dragon", "Dragon", "Falcon 9"), Array(1, 1, 3, 2, 4, 10, 20, 100, 4)))
}
