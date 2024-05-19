package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    static final String HOME = System.getProperty("user.home");

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = "output format [default: stylish]", paramLabel = "format")
    String format;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested = false;
    @Parameters(index = "0", description = "path to first file",
            paramLabel = "filepath1") String firstFileName;
    @Parameters(index = "1", description = "path to second file",
            paramLabel = "filepath2") String secondFileName;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        String resultOfDiff = Differ.generate(firstFileName, secondFileName, format);
        System.out.println(resultOfDiff);
        return 0;
    }
}
