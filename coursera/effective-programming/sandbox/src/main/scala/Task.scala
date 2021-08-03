final case class Task(name: String, duration: Int, requirements: List[Task])

val csSetup = Task("cs setup", 4, Nil)
val ide     = Task("IDE", 3, Nil)
val hack    = Task("hack", 8, List(csSetup, ide))
val deploy  = Task("deploy", 3, List(hack))

def maxTotalDuration(tasks: List[Task]): Int =
  if tasks.isEmpty then 0
  else math.max(totalDuration(tasks.head), maxTotalDuration(tasks.tail))

def totalDuration(task: Task): Int =
  task.duration + task.requirements.map(totalDuration).maxOption.getOrElse(0)
