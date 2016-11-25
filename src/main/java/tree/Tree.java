package tree;


public class Tree {
  Node root;

  public Tree(String rootString) {
    this.root = new Node(rootString);
  }

  public Node getRoot() {
    return this.root;
  }

}
