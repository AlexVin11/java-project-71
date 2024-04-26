package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Object> {

    private static final String HOME = System.getProperty("user.home");

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", paramLabel = "format")
    String format;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    boolean helpRequested = false;
    @Option(names = {"-V", "--version"}, versionHelp = true, description = "Print version information and exit.")
    boolean versionInfoRequested = false;
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1") String pathToFirstFile;
    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2") String pathToSecondFile;

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
        //System.exit(exitCode);
    }

    @Override
    public Object call() throws Exception {
        Path path1 = Paths.get(pathToFirstFile).toAbsolutePath().normalize();
        Path path2 = Paths.get(pathToSecondFile).toAbsolutePath().normalize();
        String diff = Differ.generate(path1, path2);
        return null;
    }
}
