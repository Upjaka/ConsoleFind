import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {

    private final String startDir;
    private final boolean subDir;

    public Finder(final String startDirectory, final boolean subDir) {
        this.startDir = startDirectory;
        this.subDir = subDir;
    }

    public List<File> find(final String fileName) throws FileNotFoundException {
        List<File> result = new ArrayList<>();
        final File dir = new File(startDir);
        final File[] files = dir.listFiles();
        if (files == null) {
            throw new FileNotFoundException(); // Создать свое исключение
        }
        Pattern pattern = Pattern.compile(fileName);
        for (File file : files) {
            final Matcher matcher = pattern.matcher(file.getName());
            if (matcher.find()) result.add(file);
            if (file.isDirectory() && subDir) {
                final List<File> find = new Finder(startDir + "/" + file.getName(),
                        true).find(fileName);
                result.addAll(find);
            }
        }
        return result;
    }
}
