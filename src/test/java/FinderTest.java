import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {
    final File dir = new File("files/dir");
    final File dir2 = new File("files/dir/dir2");
    final File file = new File("files/file.txt");
    final File png = new File("files/image.png");
    final File test = new File("files/test.txt");
    final File test1 = new File("files/test1.txt");
    final File dirFile = new File("files/dir/file.txt");
    final File dirDir2File = new File("files/dir/dir2/file.txt");
    final List<File> text = Arrays.asList(dirDir2File, dirFile, file, test, test1);

    @Test
    void findException() {
        assertThrows(IllegalDirectoryName.class,
                () -> new Finder("notExists", true).find("file.txt"));
    }

    @Test
    void find() throws IllegalDirectoryName {
        Finder finder = new Finder("files", false);
        assertEquals(Collections.EMPTY_LIST, finder.find("notExists"));
        assertEquals(Collections.singletonList(file), finder.find("file.txt"));
        assertEquals(Arrays.asList(dir, file, png, test, test1), finder.find(""));
    }

    @Test
    void findRecursion() throws IllegalDirectoryName {
        Finder finder = new Finder("files", true);
        List<File> expected = Arrays.asList(dirDir2File, dirFile, file);
        assertEquals(expected, finder.find("file"));

        List<File> expected1 = Arrays.asList(dir, dir2);
        assertEquals(expected1, finder.find("dir"));
    }

    @Test
    void findRegex() throws IllegalDirectoryName {
        Finder finder = new Finder("files", true);
        assertEquals(text, finder.find(".txt"));
        assertEquals(text, finder.find("t.*t"));
        assertEquals(Arrays.asList(dir2, test1), finder.find("[0-9]"));
        assertEquals(Arrays.asList(test, test1), finder.find("^t"));
        assertEquals(text, finder.find("t$"));
        assertEquals(Arrays.asList(dir, dir2, dirDir2File, dirFile, file), finder.find("file|dir"));
        assertEquals(Collections.singletonList(png), finder.find("[a-z]{5}"));
    }
}