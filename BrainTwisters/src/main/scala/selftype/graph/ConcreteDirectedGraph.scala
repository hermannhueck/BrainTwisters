package selftype.graph

class ConcreteDirectedGraph extends DirectedGraph {
  type Edge = EdgeImpl
  type Node = NodeImpl
  protected def newNode: Node = new NodeImpl
  protected def newEdge(from: Node, to: Node): Edge =
    new EdgeImpl(from, to)
}
