package hexlet.code;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        System.exit(new CommandLine(new Differ()).execute(args));
        //System.out.println("Hello World!");
    }
}
