import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.List;

public class FindLauncher {
    @Option(name = "-d", metaVar = "Directory", usage = "Search directory")
    private String startDirectory = ".";

    @Option(name = "-r", metaVar = "Subdirectories", usage = "Including subdirectories")
    private boolean subdirectories;

    @Argument(required = true, metaVar = "FileName", usage = "Search file name")
    private String fileName;

    public static void main(String[] args) throws IllegalDirectoryName {
        new FindLauncher().launch(args);
    }

    private void launch(String[] args) throws IllegalDirectoryName {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        if (new File(startDirectory).listFiles() == null) {
            System.err.printf("Directory \"%1$s\" was not found%n", startDirectory);
            return;
        }
        final List<File> result = new Finder(startDirectory, subdirectories).find(fileName);
        if (result.isEmpty()) {
            System.out.println("File wasn't found");
            return;
        }
        System.out.println("Found:");
        for (File file : result) {
            System.out.println(file.getPath());
        }
    }
}
