import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    private Reader reader;
    private Writer writer;

    public static void main(String [] args) throws Exception
    {
        Application app = new Application();
        app.execute();
    }

    public Application() {
        reader = new ReaderFromStdIn();
        writer = new WriterToStdOut();
    }

    public void execute() throws Exception
    {
        String line;
        while((line = reader.readLine()) != null) {
            double fahrenheit = Double.parseDouble(line);
            writer.writeLine("F=" + fahrenheit + ", C=" + ConvertToCelsius(fahrenheit));
        }
        writer.writeLine("converter exit");
    }

    public double ConvertToCelsius(double fahrenheit) {
        return 5.0 / 9.0 * (fahrenheit - 32);
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
