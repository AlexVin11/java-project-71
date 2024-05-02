package hexlet.code;

import com.google.common.collect.MapDifference;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    private static final String HOME = System.getProperty("user.home");

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    String format;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested = false;
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1") String firstFileName;
    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2") String secondFileName;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(firstFileName).toAbsolutePath().normalize();
        Path path2 = Paths.get(secondFileName).toAbsolutePath().normalize();
        var resultOfDiff = Differ.generate(path1, path2);
        System.out.println(resultOfDiff);
        return 0;
    }
}
