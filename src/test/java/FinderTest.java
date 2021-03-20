import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    @Test
    void find() throws FileNotFoundException {
        assertThrows(FileNotFoundException.class,
                () -> Finder.find("file.txt","notExists", true));

        assertEquals(Collections.EMPTY_LIST,
                Finder.find("notExists","files", false));

        assertEquals(new ArrayList<>(Collections.singleton("file.txt")),
                Finder.find("file.txt", "files", false));

        List<String> expected = new ArrayList<>();
        expected.add("dir/dir2/file.txt");
        expected.add("dir/file.txt");
        expected.add("file.txt");
        assertEquals(expected, Finder.find("file.txt","files", true));
        assertEquals(expected, Finder.find("file.*", "files", true));

        List<String> expected1 = new ArrayList<>();
        expected1.add("test.txt");
        expected1.add("test2.txt");
        assertEquals(expected1, Finder.find("test", "files", true));
    }
}