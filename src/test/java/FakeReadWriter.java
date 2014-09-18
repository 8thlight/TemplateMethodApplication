import java.util.LinkedList;
import java.util.Queue;

class FakeReadWriter implements Reader, Writer {
    private Queue<String> lines;

    public FakeReadWriter() {
        lines = new LinkedList<String>();
    }

    public void addLine(String line) {
        lines.add(line);
    }

    @Override
    public String readLine() {
        return lines.poll();
    }

    @Override
    public void writeLine(String line) {
        addLine(line);
    }
}
