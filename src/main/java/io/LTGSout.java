package io;

import tree.Node;
import tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Created by niklas on 21.11.16.
 */
public class LTGSout {

  Map<String, Integer> occurences;

  public void writeTrees(List<Tree> trees) {
    occurences = new HashMap<>();
    for (Tree tree : trees) {
      computeTreeGrammar(tree);
    }
    writeTreeGrammars();
  }

  public void writeTree(Tree tree) {
    occurences = new HashMap<>();
    computeTreeGrammar(tree);
    writeTreeGrammars();
  }

  private void computeTreeGrammar(Tree tree) {
    Stack<Node> stack = new Stack<>();
    stack.push(tree.getRoot());
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (node.getChildren().size() == 0) {
        continue;
      }
      List<Node> children = node.getChildren();
      String rule = node.getData();
      for (Node child : children) {
        rule += " " + child.getData();
        stack.push(child);
      }
      if(occurences.containsKey(rule)) {
        occurences.put(rule, occurences.get(rule)+1);
      } else {
        occurences.put(rule, 1);
      }
    }
  }

  private void writeTreeGrammars() {
    for(Map.Entry entry : occurences.entrySet()) {
      System.out.println(entry.getValue() + " " + entry.getKey());
    }
  }
}
