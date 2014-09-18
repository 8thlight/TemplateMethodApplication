import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest {
    private Application app;
    private FakeReadWriter fakeReader;
    private FakeReadWriter fakeWriter;

    @Before
    public void setUp() {
        app = new Application();
        fakeReader = new FakeReadWriter();
        fakeWriter = new FakeReadWriter();
        app.setReader(fakeReader);
        app.setWriter(fakeWriter);
    }

    @Test
    public void ItReturnsRightCelciusForFarenheitOfFreezing() {
        double celsius = app.ConvertToCelsius(32);

        assertEquals(0, celsius, 0.001);
    }

    @Test
    public void ItReturnsRightCelciusForBoiling() {
        double celsius = app.ConvertToCelsius(212);

        assertEquals(100.0, celsius, 0.001);
    }

    @Test
    public void ItWritesNothingOnExecuteWhenReaderIsEmpty() throws Exception {
        app.execute();

        assertEquals("converter exit", fakeWriter.readLine());
    }

    @Test
    public void ItWritesOneConversionWhenTheReaderHasOneConversion() throws Exception {
        fakeReader.addLine("32");

        app.execute();

        assertEquals("F=32.0, C=0.0", fakeWriter.readLine());
        assertEquals("converter exit", fakeWriter.readLine());
    }

    @Test
    public void ItWritesManyConversionsFromTheReader() throws Exception {
        fakeReader.addLine("32");
        fakeReader.addLine("212");

        app.execute();

        assertEquals("F=32.0, C=0.0", fakeWriter.readLine());
        assertEquals("F=212.0, C=100.0", fakeWriter.readLine());
        assertEquals("converter exit", fakeWriter.readLine());
    }
}
