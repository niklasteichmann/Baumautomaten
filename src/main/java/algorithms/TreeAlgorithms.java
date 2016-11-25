package algorithms;

import tree.Node;
import tree.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class TreeAlgorithms {

  public static List<Integer> getDepths(List<Tree> trees) {
    List<Integer> result = new ArrayList<>();
    for (Tree tree : trees) {
      result.add(getDepth(tree.getRoot()));
    }
    return result;
  }

  private static int getDepth(Node node) {
    if (node.getChildren().size() == 0) {
      return 0;
    } else {
      int depth = -1;
      for (Node child : node.getChildren()) {
        depth = Math.max(depth, getDepth(child));
      }
      return ++depth;
    }
  }

  public static List<String> getGrammars(List<Tree> trees) {
    Set<String> alreadySeen = new HashSet<>();
    List<String> grammar = new ArrayList<>();
    for (Tree tree : trees) {
      List<String> treeGrammar = getGrammar(tree);
      for(String rule : treeGrammar) {
        if(!alreadySeen.contains(rule)){
          alreadySeen.add(rule);
          grammar.add(rule);
        }
      }
    }
    return grammar;
  }

  private static List<String> getGrammar(Tree tree) {
    List<String> grammar = new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    stack.push(tree.getRoot());
    while (!stack.isEmpty()) {
      Node node = stack.pop();
      if (node.getChildren().size() == 0) {
        continue;
      }
      List<Node> children = node.getChildren();
      String rule = node.getData();
      rule += " ->";
      for (Node child : children) {
        rule += " " + child.getData();
        stack.push(child);
      }
      grammar.add(rule);
    }
    return grammar;
  }

  public static void removeTrace(List<Tree> trees) {
    for(Tree tree : trees) {
      removeTrace(tree);
    }
  }

  public static void removeTrace(Tree tree) {
    Stack<Node> stack = new Stack<>();
    stack.push(tree.getRoot());
    List<Node> toBeRemoved = new ArrayList<>();
    while (!stack.isEmpty()) {
      Node n = stack.pop();
      List<Node> children = n.getChildren();
      for (Node child : children) {
        if (child.getData().startsWith("*") && child.getChildren().size() == 0) {
          toBeRemoved.add(child);
        } else {
          stack.push(child);
        }
      }
    }
    for(Node remove : toBeRemoved) {
      while(remove.getChildren().size() == 0) {
        if(remove.getParent() != null) {
          remove.getParent().removeChild(remove);
          remove = remove.getParent();
        } else {
          break;
        }
      }
    }
  }

  private static void prune(Node node) {
    while(node.getChildren().size() == 0) {
      node.getParent().removeChild(node);
      node = node.getParent();
    }
  }
}
