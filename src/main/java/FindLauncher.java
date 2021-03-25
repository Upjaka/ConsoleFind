import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.FileNotFoundException;
import java.util.List;

public class FindLauncher {
    @Option(name = "-d", metaVar = "Directory", usage = "Search directory")
    private String startDirectory;

    @Option(name = "-r", metaVar = "Subdirectories", usage = "Including subdirectories")
    private boolean subdirectories;

    @Argument(required = true, metaVar = "FileName", usage = "Search file name")
    private String fileName;

    public static void main(String[] args) {
        new FindLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        try {
            final List<String> result = Finder.find(fileName, startDirectory, subdirectories);
            if (result.isEmpty()) {
                System.out.println("File wasn't found");
            } else {
                System.out.println("Found:");
                for (String line : result) {
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Directory not found");
        }
    }
}
