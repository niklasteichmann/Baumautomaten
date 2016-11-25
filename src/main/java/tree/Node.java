package tree;

import java.util.ArrayList;
import java.util.List;

public class Node {

  String data;
  Node parent;
  List<Node> children;

  public Node(String data) {
    this.data = data;
    this.children = new ArrayList<>();
  }

  public String getData() {
    return this.data;
  }

  public Node addChild(String child) {
    Node childNode = new Node(child);
    childNode.parent = this;
    this.children.add(childNode);
    return childNode;
  }

  public void removeChild(Node child) {
    children.remove(child);
  }

  public List<Node> getChildren() {
    return this.children;
  }

  public Node getParent() {
    return this.parent;
  }
}