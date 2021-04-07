public class IllegalDirectoryName extends Throwable {
    private final String message;

    public IllegalDirectoryName(String dirName) {
        message = String.format("Directory \"%1$s\" does not exist", dirName);
    }

    @Override
    public String toString() {
        return message;
    }
}
