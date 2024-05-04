package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.concurrent.Callable;

@Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<Integer> {

    static final String HOME = System.getProperty("user.home");

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
        Path pathToFirstFile = Differ.generatePath(firstFileName);
        Path pathToSecondFile = Differ.generatePath(secondFileName);
        String firstFileContent = Differ.readFileContent(pathToFirstFile);
        String secondFileContent = Differ.readFileContent(pathToSecondFile);
        HashMap<String, Object> firstFileMap = Differ.generateHashMapFromFileContent(firstFileContent);
        HashMap<String, Object> secondFileMap = Differ.generateHashMapFromFileContent(secondFileContent);
        SortedMap<String, String> mapOfKeyStatus = Differ.generateKeyStatusHashMap(firstFileMap, secondFileMap);
        String resultOfDiff = Differ.generate(mapOfKeyStatus, firstFileMap, secondFileMap);
        System.out.println(resultOfDiff);
        return 0;
    }
}
