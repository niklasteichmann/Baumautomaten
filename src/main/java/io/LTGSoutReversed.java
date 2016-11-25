package io;

import tree.Node;
import tree.Tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by niklas on 21.11.16.
 */
public class LTGSoutReversed {
  Map<String, Map<String, Integer>> occurences;

  public void writeTrees(List<Tree> trees) {
    occurences = new HashMap<>();
    for (Tree tree : trees) {
      computeTreeGrammar(tree);
    }
    //System.out.println(occurences.keySet().size());
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
        if (occurences.containsKey(node.getData())) {
          Map<String, Integer> rules = occurences.get(node.getData());
          if (node.getParent() == null) {
            continue;
          }
          if (rules.containsKey(node.getParent().getData())) {
            rules.put(node.getParent().getData(),
              rules.get(node.getParent().getData()) + 1);
          } else {
            rules.put(node.getParent().getData(), 1);
          }
        } else {
          HashMap<String, Integer> map = new HashMap<>();
          map.put(node.getParent().getData(), 1);
          occurences.put(node.getData(), map);
        }
      } else {
        for (Node child : node.getChildren()) {
          stack.push(child);
        }
      }
    }
  }

  private void writeTreeGrammars() {
    for (Map.Entry<String, Map<String, Integer>> entry : occurences
      .entrySet()) {
      System.out.print(entry.getKey() + "\t");
      String line = "";
      for (Map.Entry<String, Integer> rule : entry.getValue().entrySet()) {
        line += rule.getKey() + " " + rule.getValue() + " ";
      }
      line = line.substring(0, line.length() - 1);
      System.out.println(line);
    }
  }
}
