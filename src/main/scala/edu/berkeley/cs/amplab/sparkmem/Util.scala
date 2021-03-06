package edu.berkeley.cs.amplab.sparkmem

object Util {
  private val UNITS = List(
    "T" -> 1024. * 1024. * 1024. * 1024.,
    "G" -> 1024. * 1024. * 1024.,
    "M" -> 1024. * 1024.,
    "K" -> 1024.
  )
  def bytesToString(bytes: Double): String = {
    val unit = UNITS.dropWhile(_._2 > 4. * bytes).headOption.getOrElse("" -> 1.)
    if (unit._1 == "") {
      return f"${bytes / unit._2}%.0f "
    } else {
      return f"${bytes / unit._2}%.2f ${unit._1}%s"
    }
  }

  def bytesToString(bytes: Long): String = bytesToString(bytes.toDouble)

  def stringToBytes(s: String): Long =  {
    val unitPart = s.takeRight(1)
    val numPart = s.dropRight(1)
    var unit = unitPart match {
      case ("b" | "B") => 1L
      case ("k" | "K") => 1024L
      case ("m" | "M") => 1024L * 1024L
      case ("g" | "G") => 1024L * 1024L * 1024L
      case ("t" | "T") => 1024L * 1024L * 1024L * 1024L
      case _ => throw new IllegalArgumentException(s"Unknown unit type $unitPart")
    }
    return (numPart.toDouble * unit).toLong
  }
}
