import scala.io.Source

// NOTE: VALUES ARE ENCLOESD IN ' NOT "

trait csvParser {

  val categoriesStartAt = 6

  def grabNthCommaSeperatedElement(line: String, n: Int): String = {
    def innerGrabNth(line: String, fieldCount: Int): String = fieldCount match{
      case x if x == n => line.tail.takeWhile(_!='"')
      case _ => innerGrabNth(nextField(line), fieldCount + 1)
    }
    innerGrabNth(line, 1)
  }

  def nextField(line: String): String = {
    line.dropWhile(_!='"').tail.dropWhile(_!='"').tail.tail
  }

  def pullCategories(filePath: String): List[String] = {
    val theLines = Source.fromFile(filePath).getLines.toList
    theLines.map(x => grabNthCommaSeperatedElement(x, 15)).distinct
  }

  def cleanName(name: String): String = {
    def innerCleanName(name: String): String = name match {
      case x if x.contains("--") => innerCleanName(name.replaceAll("--", "-"))
      case _ => name
    }
    innerCleanName(name.replaceAll("[^a-zA-Z0-9]", "-"))
  }

  def formatForWpTerms(categories: List[String]): List[String] = {
    def innerFormat(categories: List[String], count: Int, acc: List[String]): List[String] = categories match {
      case Nil => acc
      case _ => innerFormat(categories.tail, count + 1, ("('"+ count + "','" + categories.head + "','" + cleanName(categories.head) + "','0')") +: acc)

    }
    innerFormat(categories, categoriesStartAt, List()).reverse
  }
}


object theMain extends App with csvParser {
  val filePath = "project-portion.csv"
  println(pullCategories(filePath).size)
  formatForWpTerms(pullCategories(filePath)).foreach(println(_))
}