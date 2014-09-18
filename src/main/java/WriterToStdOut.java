public class WriterToStdOut implements Writer {

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
