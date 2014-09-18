import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ReaderFromStdIn implements Reader {
    private BufferedReader reader;

    public ReaderFromStdIn() {
        InputStreamReader isr = new InputStreamReader(System.in);
        reader = new BufferedReader(isr);
    }

    @Override
    public String readLine() throws Exception {
        return reader.readLine();
    }
}
