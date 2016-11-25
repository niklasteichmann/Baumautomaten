package io;

import tree.Node;
import tree.Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TreeParser {

  public List<Tree> readTrees(String path) throws IOException {
    File file = new File(path);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line;

    List<Tree> trees = new ArrayList<>();
    while ((line = reader.readLine()) != null) {
      if(line.equals("")) {
        continue;
      }
      Tree tree;
      String[] tokens = line.split(" ");

      tree = new Tree(tokens[1].replace("(",""));
      Node current = tree.getRoot();
      for (int i = 2; i < tokens.length; i++) {

        if (tokens[i].contains("(")){
          current = current.addChild(tokens[i].replace("(",""));
        }

        if (tokens[i].contains(")")) {
          current.addChild(tokens[i].replace(")",""));
          while(tokens[i].contains(")")) {
            tokens[i] = tokens[i].replaceFirst("\\)", "");
            current = current.getParent();
          }
          if(current == null) {
            break;
          }
        }
      }
      trees.add(tree);
    }
    return trees;
  }
}
