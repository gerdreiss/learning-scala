class ProgramSuite extends munit.FunSuite:

  test("add") {
    val obtained = add(1, 1)
    val expected = 2
    assertEquals(obtained, expected)
  }

  test("fibonacci") {
    assertEquals(fibonacci(1), 1)
    assertEquals(fibonacci(2), 1)
    assertEquals(fibonacci(3), 2)
    assertEquals(fibonacci(4), 3)
    assertEquals(fibonacci(5), 5)
  }

end ProgramSuite
