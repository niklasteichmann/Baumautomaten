package io;

import tree.Node;
import tree.Tree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by niklas on 21.11.16.
 */
public class TreeSout {

  public void writeTrees(List<Tree> trees) throws IOException {
    for (Tree tree : trees) {
      writeTree(tree);
      System.out.println();
    }
  }

  public void writeTree(Tree tree) throws IOException {
    System.out.print("(");
    writeNode(tree.getRoot());
    System.out.print(" )");
  }

  private void writeNode(Node node) throws IOException {
    if(node.getChildren().size() > 0) {
      System.out.print(" (" + node.getData());
      for (Node child : node.getChildren()) {
        writeNode(child);
      }
      System.out.print(")");
    } else {
      System.out.print(" " + node.getData());
    }
  }
}
