import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    void find() throws FileNotFoundException {
        /*assertThrows(FileNotFoundException.class,
                () -> Finder.find("file.txt", "notExists", true));

        assertEquals(Collections.EMPTY_LIST,
                Finder.find("notExists", "files", false));

        assertEquals(new ArrayList<>(Collections.singleton("file.txt")),
                Finder.find("file.txt", "files", false));

        List<String> expected = Arrays.asList("dir/dir2/file.txt", "dir/file.txt", "file.txt");
        assertEquals(expected, Finder.find("file.txt", "files", true));
        assertEquals(expected, Finder.find("file.*", "files", true));

        List<String> expected1 = Arrays.asList("test.txt", "test2.txt");
        assertEquals(expected1, Finder.find("test", "files", true));*/
    }
}