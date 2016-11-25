import algorithms.TreeAlgorithms;
import io.LTGSout;
import io.LTGSoutReversed;
import io.TreeSout;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import io.TreeParser;
import tree.Tree;

import java.io.IOException;
import java.util.List;

public class Main {

  private static final String OPTION_INPUT_PATH = "i";
  private static final String OPTION_TASK_1 = "a";
  private static final String OPTION_TASK_2 = "b";
  private static final String OPTION_HELP = "h";

  private static final Options OPTIONS;


  static {
    OPTIONS = new Options();
    OPTIONS.addOption(OPTION_INPUT_PATH, true, "Pfad zur Wissensbassis");
    OPTIONS.addOption(OPTION_TASK_1, false, "Aufgabe 1: Baumhöhe");
    OPTIONS.addOption(OPTION_TASK_2, false, "Aufgabe 2: Grammatik");
    OPTIONS.addOption(OPTION_HELP, false, "Hilfe");
  }
/*
  public static void main(String[] args) throws Exception {
    CommandLineParser parser = new BasicParser();
    CommandLine cmd = parser.parse(OPTIONS, args);

    if (cmd.hasOption(OPTION_HELP)){
      printHelp();
      System.exit(0);
    }

    performSanityCheck(cmd);
    String path = cmd.getOptionValue(OPTION_INPUT_PATH);
    List<Tree> trees = new TreeParser().readTrees(path);

    if (cmd.hasOption(OPTION_TASK_1)){
      TreeAlgorithms.getDepths(trees).stream().forEach(System.out::println);
    }
    if (cmd.hasOption(OPTION_TASK_2)) {
      TreeAlgorithms.getGrammars(trees).stream().forEach(System.out::println);
    }
  }
*/
  /*
  public static void main(String[] args) throws IOException {
    String path = Main.class.getResource("testTree").getPath();
    List<Tree> trees = new TreeParser().readTrees(path);
    TreeSout sout = new TreeSout();
    System.out.println("\nBefore:\n");
    sout.writeTrees(trees);
    TreeAlgorithms.removeTrace(trees);
    System.out.println("\nAfter:\n");
    sout.writeTrees(trees);
  }*/
/*
  public static void main(String[] args) throws IOException {
    String path = Main.class.getResource("wsj_0100.tree").getPath();
    List<Tree> trees = new TreeParser().readTrees(path);
    TreeAlgorithms.removeTrace(trees);
    LTGSout sout = new LTGSout();
    sout.writeTrees(trees);
  }
*/

  public static void main(String[] args) throws IOException {
    String path = Main.class.getResource("wsj_0100.tree").getPath();
    List<Tree> trees = new TreeParser().readTrees(path);
    TreeAlgorithms.removeTrace(trees);
    LTGSoutReversed sout = new LTGSoutReversed();
    sout.writeTrees(trees);
  }

  private static void printHelp(){
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(Main.class.getName(), OPTIONS, false);
  }

  private static void performSanityCheck(final CommandLine cmd){
    if (!cmd.hasOption(OPTION_INPUT_PATH)){
      throw new IllegalArgumentException("Define input path (-i)");
    }
    if (!cmd.hasOption(OPTION_TASK_1) && !cmd.hasOption(OPTION_TASK_2)){
      throw new IllegalArgumentException("Define task: (-a) Task1, (-b) Task2");
    }
  }
}
