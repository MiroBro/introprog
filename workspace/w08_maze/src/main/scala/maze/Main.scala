package maze

import cslib.window.SimpleWindow
import graphics.SimpleTurtle

object Main {
  val win = new SimpleWindow(800, 640, "A-Maze-Ing Race")
  val animationDelay: Int = 50
  def msg(s: String, y: Int = 100): Unit = { win.moveTo(10, y); win.writeText(s) }

  def drawMazeAndClickToWalk(maze: Maze): Unit = {
    maze.draw(win)
    win.setLineColor(java.awt.Color.red)
    win.setLineWidth(2)
    val mazeWalker = new MazeWalker(new SimpleTurtle(win))
    msg(s"Click to walk!", y = 50)
    win.waitForMouseClick()
    mazeWalker.walk(maze, animationDelay)
  }

  def drawMazesInDir(path: String): Unit = {
    for (mazeNbr <- 1 to 4) {
      val filename = s"maze$mazeNbr.txt"
      msg(s"Click to draw $path$filename")
      win.waitForMouseClick()
      win.clear()
      win.setLineColor(java.awt.Color.black)
      val maze = Maze.fromFile(path + filename)
      drawMazeAndClickToWalk(maze)
    }
  }

  def printMazesFromDir(path: String, n: Int): Unit = {
    for (mazeNbr <- 1 to n) {
      val filename = s"maze$mazeNbr.txt"
      val maze = Maze.fromFile(path + filename)
      println(maze)
    }
  }

  def getResourcePath : String = Paths.get(getClass.getResource("/").toURI).toString + "/"

  def main(args: Array[String]): Unit = {
    val path = getResourcePath
    println(s"Resource path detected: $path")
    printMazesFromDir(path, n = 4)
    msg(s"Mazes in $path have been printed to console.")
    msg("CLICK TO CONTINUE!", y = 200)
    win.waitForMouseClick()
    win.clear()
    drawMazesInDir(path)
    msg("GOODBYE! File -> Quit or Ctrl+Q to exit.", y = 80)
  }
}
