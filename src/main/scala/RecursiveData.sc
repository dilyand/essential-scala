// A List of Methods

sealed trait IntList {
  def tail: IntList

  def length: Int = {
    this match {
      case End => 0
      case p: Pair => 1 + p.tail.length
    }
  }

  def product: Int ={
    this match {
      case End => 1
      case p: Pair => p.head * p.tail.product
    }
  }

  def double: IntList = {
    this match {
      case End => this
      case p: Pair => Pair(p.head * 2, p.tail.double)
    }
  }
}

final case object End extends IntList {def tail = throw new RuntimeException("The tail is a lie.")}
final case class Pair(head: Int, tail: IntList) extends IntList

val example = Pair(1, Pair(2, Pair(3, End)))

example.length
example.tail.length
example.tail.tail.length
End.length

example.product
example.tail.product
example.tail.tail.product
End.product

example.double
example.tail.double
example.tail.tail.double
End.double



// The Forest of Trees

sealed trait Tree {
  def sum: Int = {
    this match {
      case Leaf(i) => i
      case Node(l, r) => l.sum + r.sum
    }
  }

  def double: Tree = {
    this match {
      case Leaf(i) => Leaf(i * 2)
      case Node(l, r) => Node(l.double, r.double)
    }
  }
}

final case class Node(l: Tree, r: Tree) extends Tree
final case class Leaf(i: Int) extends Tree

val tree = Node(Leaf(3), Leaf(4))
tree.sum
tree.double
val tree2 = Node(Node(Leaf(1), Leaf(2)), Leaf(3))
tree2.sum
tree2.double


sealed trait AltTree {
  def sum: Int
  def double: AltTree
}

final case class AltNode(l: AltTree, r: AltTree) extends AltTree {
  def sum = l.sum + r.sum
  def double = AltNode(l.double, r.double)
}

final case class AltLeaf(i: Int) extends AltTree {
  def sum = i
  def double = AltLeaf(i * 2)
}

val altTree = AltNode(AltLeaf(3), AltLeaf(4))
altTree.sum
altTree.double
val altTree2 = AltNode(AltNode(AltLeaf(1), AltLeaf(2)), AltLeaf(3))
altTree2.sum
altTree2.double
