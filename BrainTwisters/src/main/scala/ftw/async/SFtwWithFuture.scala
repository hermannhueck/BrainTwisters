package ftw.async

import java.io.{File, FileNotFoundException}

import scala.concurrent.Future
import scala.util.{Failure, Success}

import scala.concurrent.ExecutionContext.Implicits.global

object SFtwWithFuture extends App {

  private def checkDir(dir: String) = {
    if (!new File(dir).exists) throw new FileNotFoundException("File or directory doesn't exist: " + dir)
    if (!new File(dir).isDirectory) throw new FileNotFoundException("File exists but is not a directory: " + dir)
  }

//  private val alwaysTrue: File => Boolean = file => true
//  private val isFile: File => Boolean = file => file.isFile
//  private val isDirectory: File => Boolean = file => file.isDirectory
//  private val isJavaFile: File => Boolean = file => file.getAbsolutePath.endsWith(".java")
  private val isScalaFile: File => Boolean = file => file.getAbsolutePath.endsWith(".scala")

  private val defaultDir = "./src/main"
  private val dir = if (args.length == 0) defaultDir else args(0)
  checkDir(dir)


  val future = ftw(dir)(isScalaFile)

  future onComplete {
    case Success(files: Seq[String]) => files foreach println
    case Failure(t: Throwable) => throw t
  }

  def ftw(dir: String)(implicit predicate: File => Boolean): Future[Seq[String]] =
    Future {
      ftw(new File(dir)).map(_.getPath)
    }

  def ftw(file: File)(implicit predicate: File => Boolean): Seq[File] = {

    if (!file.exists())
      throw new FileNotFoundException("File or directory doesn't exist: " + dir)

    val prefix: List[File] = if (predicate(file)) List(file) else List.empty

    val children: List[File] =
      if (file.isDirectory)
        file.listFiles().toList.flatMap(ftw)
      else
        List.empty

    prefix ::: children
  }
}
