abstract class ApplicationRunner {
    private boolean isDone;

    public ApplicationRunner() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setToDone() {
        isDone = true;
    }
}

public class Application extends ApplicationRunner {

    private Reader reader;
    private Writer writer;

    public static void main(String [] args) throws Exception
    {
        Application app = new Application();
        app.execute();
    }

    public Application() {
        super();
        reader = new ReaderFromStdIn();
        writer = new WriterToStdOut();
    }

    public void execute() throws Exception
    {
        String line;
        while(!isDone()) {
            line = reader.readLine();
            if (line == null)
                setToDone();
            else {
                double fahrenheit = Double.parseDouble(line);
                writer.writeLine("F=" + fahrenheit + ", C=" + ConvertToCelsius(fahrenheit));
            }
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
