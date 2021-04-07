import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    private final Path startDir;
    private final boolean subDir;

    public Finder(final String startDirectory, final boolean subDir) {
        this.startDir = Paths.get(startDirectory);
        this.subDir = subDir;
    }

    public List<File> find(final String fileName) throws IllegalDirectoryName {
        List<File> result = new ArrayList<>();
        final File dir = startDir.toFile();
        final File[] files = dir.listFiles();
        if (files == null) {
            throw new IllegalDirectoryName(startDir.toString());
        }
        Pattern pattern = Pattern.compile(fileName);
        for (File file : files) {
            final Matcher matcher = pattern.matcher(file.getName());
            if (matcher.find()) result.add(file);
            if (file.isDirectory() && subDir) {
                final List<File> find = new Finder(file.getPath(), true).find(fileName);
                result.addAll(find);
            }
        }
        return result;
    }
}
