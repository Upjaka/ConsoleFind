import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
    public static List<String> find(String fileName, String startDirectory, boolean subDir)
            throws FileNotFoundException {

        List<String> result = new ArrayList<>();
        File dir = new File(startDirectory);
        File[] files = dir.listFiles();
        if (files == null) {
            throw new FileNotFoundException();
        } else {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (subDir) {
                        String newStartDir = startDirectory + "/" + file.getName();
                        List<String> find = find(fileName, newStartDir, true);
                        for (String name : find) {
                            result.add(file.getName() + "/" + name);
                        }
                    }
                } else {
                    Pattern pattern = Pattern.compile(fileName);
                    Matcher matcher = pattern.matcher(file.getName());
                    if (matcher.find()) result.add(file.getName());
                }
            }
        }
        return result;
    }
}
