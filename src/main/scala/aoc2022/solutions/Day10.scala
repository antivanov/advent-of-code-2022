package aoc2022.solutions

object Day10:
  trait Instruction:
    def toOperations: List[Option[Int]]
  case class Add(value: Int) extends Instruction:
    override def toOperations: List[Option[Int]] =
      List(None, Some(value))
  object Noop extends Instruction:
    override def toString: String = "Noop"
    override def toOperations: List[Option[Int]] =
      List(None)

  def parse(input: String): Seq[Instruction] =
    input.split("\n").map(_.trim).filter(_.nonEmpty).map({
      case "noop" => Noop
      case s"addx $value" => Add(value.toInt)
    })

  def instructionsToOperations(instructions: Seq[Instruction]): List[Option[Int]] =
    val operations = instructions.toList.flatMap(_.toOperations)
    if (operations.flatten.isEmpty)
      operations
    else
      List(None) ++ operations

  private def registerSnapshots(currentRegisterValue: Int, operations: List[Option[Int]]): LazyList[Int] =
    val nextRegisterValue = operations.headOption.flatten match {
      case None => currentRegisterValue
      case Some(change) => currentRegisterValue + change
    }
    nextRegisterValue #:: registerSnapshots(nextRegisterValue, operations.tail)

  private val ScreenPixelNumber = 240
  private val ScreenWidth = 40

  def render(instructions: Seq[Instruction]): List[String] =
    val operations: List[Option[Int]] = instructionsToOperations(instructions)
    val initialRegisterValue = 1
    val registerValueStream = registerSnapshots(initialRegisterValue, operations)
    val pixels = (0 until ScreenPixelNumber).toList
    val registerValues = registerValueStream.take(ScreenPixelNumber).toList
    pixels.zip(registerValues).map({
      case (pixelIndex, registerValue) =>
        val pixelHorizontalPosition = pixelIndex % ScreenWidth
        if (pixelHorizontalPosition >= registerValue - 1 && pixelHorizontalPosition <= registerValue + 1)
          "#"
        else
          "."
    })

  def formatRendering(symbols: List[String]): String =
    symbols.grouped(ScreenWidth).map(_.mkString).mkString("\n")

  def registerAtCycle(cycleNumber: Int, instructions: Seq[Instruction]): Int =
    val operations: List[Option[Int]] = instructionsToOperations(instructions)
    val initialRegisterValue = 1
    val registerValueStream = registerSnapshots(initialRegisterValue, operations)
    registerValueStream.drop(cycleNumber - 1).head

  private val Part1Cycles = List(20, 60, 100, 140, 180, 220)

  def solutionPart1(instructions: Seq[Instruction]): Int =
    Part1Cycles.map(cycleCount =>
      cycleCount * registerAtCycle(cycleCount, instructions)
    ).sum

  def solutionPart2(instructions: Seq[Instruction]): String =
    formatRendering(render(instructions))

@main
def day10Main: Unit =
  import Day10._
  import Day10Input._
  val parsed = parse(input)
  println(solutionPart1(parsed))
  println(solutionPart2(parsed))
