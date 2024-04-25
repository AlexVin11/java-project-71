package hexlet.code;

import picocli.CommandLine;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.exit(new CommandLine(new Differ()).execute(args));
        //System.out.println("Hello World!");
    }
}
