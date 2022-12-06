package aoc2022.solutions

import aoc2022.solutions._

class DaysSpec extends munit.FunSuite:

  test("Day 1") {
    import Day1._
    import Day1Input._
    val parsed = parse(input)
    assertEquals(solutionPart1(parsed), 24000)
    assertEquals(solutionPart2(parsed), 45000)
  }

  test("Day 2") {
    import Day2._
    import Day2Input._
    val parsed = parse(input)
    assertEquals(solutionPart1(parsed), 15)
    assertEquals(solutionPart2(parsed), 12)
  }

  test("Day 3") {
    import Day3._
    import Day3Input._
    val parsed = parse(input)
    assertEquals(solutionPart1(parsed), 157)
    assertEquals(solutionPart2(parsed), 70)
  }

  test("Day 4") {
    import Day4._
    import Day4Input._
    val parsed = parse(input)
    assertEquals(solutionPart1(parsed), 2)
    assertEquals(solutionPart2(parsed), 4)
  }

  test("Day 5") {
    import Day5._
    import Day5Input._
    val parsed = parse(input)
    assertEquals(solutionPart1(parsed), "CMZ")
    assertEquals(solutionPart2(parsed), "MCD")
  }

  test("Day 6") {
    import Day6._
    import Day6Input._
    val parsed = parse(input)
    assertEquals(solutionPart1(parsed), 7)
    assertEquals(solutionPart2(parsed), 19)
  }