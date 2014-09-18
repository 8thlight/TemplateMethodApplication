interface ApplicationInterface {
    public void idle(ApplicationRunner applicationRunner) throws Exception;
    public void cleanup();
}

class ApplicationRunner {
    private boolean isDone;
    private ApplicationInterface app;

    public ApplicationRunner(ApplicationInterface app) {
        this.app = app;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }
    public void setToDone() {
        isDone = true;
    }

    public void execute() throws Exception {
        while (!isDone())
            app.idle(this);
        app.cleanup();
    }
}

public class TemperatureConverter implements ApplicationInterface {

    private Reader reader;
    private Writer writer;

    public static void main(String [] args) throws Exception
    {
        TemperatureConverter tcvr = new TemperatureConverter();
        ApplicationRunner app = new ApplicationRunner(tcvr);

        app.execute();
    }

    public TemperatureConverter() {
        reader = new ReaderFromStdIn();
        writer = new WriterToStdOut();
    }

    @Override
    public void idle(ApplicationRunner runner) throws Exception
    {
        String line = reader.readLine();
        if (line == null)
            runner.setToDone();
        else {
            double fahrenheit = Double.parseDouble(line);
            writer.writeLine("F=" + fahrenheit + ", C=" + ConvertToCelsius(fahrenheit));
        }
    }

    @Override
    public void cleanup() {
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
