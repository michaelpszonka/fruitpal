import client.FruitpalClient;
import picocli.CommandLine;

public class Main {
    public static void main(String... args) {
        int exitCode = new CommandLine(new FruitpalClient()).execute(args);
        System.exit(exitCode);
    }
}
