package com.jscriptive.scala.exercises.fp

object HandlingErrorWithoutExceptions {

  case class Employee(name: String, department: String, manager: Option[String])

  def map2[A, B, C, E, EE >: E](a: Either[EE, A], b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    for {
      a1: A <- a
      b1: B <- b
    } yield f(a1, b1)

  def lookupByNameViaEither(name: String): Either[String, Employee] = name match {
    case "Joe" => Right(Employee("Joe", "Finances", Some("Julie")))
    case "Mary" => Right(Employee("Mary", "IT", None))
    case _ => Left("Employee not found")
  }

  def employeesShareDepartment(employeeA: Employee, employeeB: Employee): Boolean =
    employeeA.department == employeeB.department

  def main(args: Array[String]): Unit = {
    println(map2(lookupByNameViaEither("Joe"), lookupByNameViaEither("Mary"))(employeesShareDepartment))
    println(map2(lookupByNameViaEither("Mary"), lookupByNameViaEither("Izumi"))(employeesShareDepartment))
    println(map2(lookupByNameViaEither("Foo"), lookupByNameViaEither("Izumi"))(employeesShareDepartment))
  }

}
