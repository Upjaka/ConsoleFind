import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    public static List<String> find(final String fileName, final String startDirectory,
                                    final boolean subDir) throws FileNotFoundException {
        List<String> result = new ArrayList<>();
        final File dir = new File(startDirectory);
        final File[] files = dir.listFiles();
        if (files == null) {
            throw new FileNotFoundException();
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (subDir) {
                        final String newStartDir = startDirectory + "/" + file.getName();
                        final List<String> find = find(fileName, newStartDir, true);
                        for (String name : find) {
                            result.add(file.getName() + "/" + name);
                        }
                    }
                } else {
                    final Pattern pattern = Pattern.compile(fileName);
                    final Matcher matcher = pattern.matcher(file.getName());
                    if (matcher.find()) result.add(file.getName());
                }
            }
        }
        return result;
    }
}
