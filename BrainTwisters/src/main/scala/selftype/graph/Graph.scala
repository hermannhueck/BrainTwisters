package selftype.graph

abstract class Graph {
  type Edge
  type Node <: NodeIntf
  abstract class NodeIntf {
    def connectWith(node: Node): Edge
  }
  def nodes: List[Node]
  def edges: List[Edge]
  def addNode: Node
}
