public class IllegalDirectoryName extends Throwable {

    public IllegalDirectoryName(String dirName) {
        super(String.format("Directory \"%1$s\" does not exist", dirName));
    }
}
