package sqlru.core;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FileAppConfigTest {

    private String pathToConf = "src/test/resources/config.properties";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void whenTryToUseUnloadConfigThenThrowException() throws IOException {
        AppConfig appConfig = new FileAppConfig();
        expectedException.expect(IOException.class);
        expectedException.expectMessage("The configuration file is not load");
        appConfig.getCronExpression();
    }

    @Test
    public void whenGetClassThenGetsClass() throws Exception {
        AppConfig appConfig = new FileAppConfig();
        appConfig.loadConfig();
        assertThat(appConfig.getWorkClass().getName(), is(WorkStub.class.getName()));
    }
}