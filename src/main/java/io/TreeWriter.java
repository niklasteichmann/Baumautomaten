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
public class TreeWriter {

  private BufferedWriter writer;

  public TreeWriter(String path) throws IOException {
    this.writer = new BufferedWriter(new FileWriter(new File(path)));
  }

  public void writeTrees(List<Tree> trees) throws IOException {
    for (Tree tree : trees) {
      writeTree(tree);
      writer.newLine();
    }
  }

  public void writeTree(Tree tree) throws IOException {
    writer.write("(");
    writeNode(tree.getRoot());
    writer.write(" )");
    writer.flush();
  }

  private void writeNode(Node node) throws IOException {
    if(node.getChildren().size() > 0) {
      this.writer.write(" (" + node.getData());
      for (Node child : node.getChildren()) {
        writeNode(child);
      }
      this.writer.write(")");
    } else {
      writer.write(" " + node.getData());
    }
  }
}
