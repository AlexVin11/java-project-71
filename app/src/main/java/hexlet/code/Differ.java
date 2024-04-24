package hexlet.code;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class Differ implements Runnable{

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]") String format;
    @Option (names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested = false;
    @Option (names = {"-V", "--version"}, description = "Print version information and exit.") boolean version;
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1") String filepath1;
    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2") String filepath2;

    /*public static void generate() {
        System.out.println("generate created");
    }*/

    @Override
    public void run() {
        System.out.println("");
    }
}
