package ftw

import java.io.{File, FileNotFoundException}

object SFtw extends App {

  val alwaysTrue: File => Boolean = file => true
  val isFile: File => Boolean = file => file.isFile
  val isDirectory: File => Boolean = file => file.isDirectory
  val isJavaFile: File => Boolean = file => file.getAbsolutePath.endsWith(".java")
  val isScalaFile: File => Boolean = file => file.getAbsolutePath.endsWith(".scala")

  val defaultDir = "./src/main"
  val dir = if (args.length == 0) defaultDir else args(0)

  if (!new File(dir).exists)
    throw new FileNotFoundException("File or directory doesn't exist: " + dir)

  ftw(dir)(isScalaFile) foreach println

  def ftw(dir: String)(implicit predicate: File => Boolean): Seq[String] =
    ftw(new File(dir)).map(_.getPath)

  def ftw(dir: File)(implicit predicate: File => Boolean): Seq[File] = {

    val children = Option(dir.listFiles())
      .getOrElse(Array[File]())
      .toList
      .flatMap(ftw)

    if (predicate(dir))
      dir :: children
    else
      children
  }
}
