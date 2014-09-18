import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureConverterTest {
    private TemperatureConverter temperatureConverter;
    private ApplicationRunner applicationRunner;

    private FakeReadWriter fakeReader;
    private FakeReadWriter fakeWriter;

    @Before
    public void setUp() {
        temperatureConverter = new TemperatureConverter();
        fakeReader = new FakeReadWriter();
        fakeWriter = new FakeReadWriter();
        temperatureConverter.setReader(fakeReader);
        temperatureConverter.setWriter(fakeWriter);

        applicationRunner = new ApplicationRunner(temperatureConverter);
    }

    @Test
    public void ItReturnsRightCelciusForFarenheitOfFreezing() {
        double celsius = temperatureConverter.ConvertToCelsius(32);

        assertEquals(0, celsius, 0.001);
    }

    @Test
    public void ItReturnsRightCelciusForBoiling() {
        double celsius = temperatureConverter.ConvertToCelsius(212);

        assertEquals(100.0, celsius, 0.001);
    }

    @Test
    public void ItWritesNothingOnExecuteWhenReaderIsEmpty() throws Exception {
        applicationRunner.execute();

        assertEquals("converter exit", fakeWriter.readLine());
    }

    @Test
    public void ItWritesOneConversionWhenTheReaderHasOneConversion() throws Exception {
        fakeReader.addLine("32");

        applicationRunner.execute();

        assertEquals("F=32.0, C=0.0", fakeWriter.readLine());
        assertEquals("converter exit", fakeWriter.readLine());
    }

    @Test
    public void ItWritesManyConversionsFromTheReader() throws Exception {
        fakeReader.addLine("32");
        fakeReader.addLine("212");

        applicationRunner.execute();

        assertEquals("F=32.0, C=0.0", fakeWriter.readLine());
        assertEquals("F=212.0, C=100.0", fakeWriter.readLine());
        assertEquals("converter exit", fakeWriter.readLine());
    }
}
