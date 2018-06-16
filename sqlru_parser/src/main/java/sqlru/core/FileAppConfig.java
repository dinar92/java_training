package sqlru.core;

import java.io.IOException;
import java.util.Properties;

/**
 * The file configuration class. Loads information from "config.properties" resource.
 */
public class FileAppConfig implements AppConfig {

    /**
     * Properties from the
     */
    private final Properties properties = new Properties();

    /**
     * The name of resource.
     */
    private static final String CONFIG_FILE_NAME = "config.properties";

    /**
     * Loads configuration.
     *
     * @throws IOException - if an error occurred when reading from the resource.
     */
    @Override
    public void loadConfig() throws IOException {
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
    }

    /**
     * Returns cron expression, that defines the application's working time.
     *
     * @return - cron expression.
     * @throws IOException - configuration is not load.
     */
    @Override
    public String getCronExpression() throws IOException {
        if (this.properties.isEmpty()) {
            throw new IOException("The configuration file is not load");
        }
        String expression;
        if (properties.getProperty("EVERY_MINUTE").equalsIgnoreCase("true")) {
            expression = String.format("%s * * * * ? *",
                    properties.getProperty("FIRE_SECOND"));
        } else if (properties.getProperty("EVERY_HOUR").equalsIgnoreCase("true")) {
            expression = String.format("%s %s * * * ? *",
                    properties.getProperty("FIRE_SECOND"),
                    properties.getProperty("FIRE_MINUTE"));
        } else if (properties.getProperty("EVERY_DAY").equalsIgnoreCase("true")) {
            expression = String.format("%s %s %s * * ? *",
                    properties.getProperty("FIRE_SECOND"),
                    properties.getProperty("FIRE_MINUTE"),
                    properties.getProperty("FIRE_HOUR"));
        } else if (properties.getProperty("EVERY_MONTH").equalsIgnoreCase("true")) {
            expression = String.format("%s %s %s %s * ? *",
                    properties.getProperty("FIRE_SECOND"),
                    properties.getProperty("FIRE_MINUTE"),
                    properties.getProperty("FIRE_HOUR"),
                    properties.getProperty("FIRE_DAY_OF_MONTH"));
        } else if (properties.getProperty("EVERY_WEEK").equalsIgnoreCase("true")) {
            expression = String.format("%s %s %s ? * %s *",
                    properties.getProperty("FIRE_SECOND"),
                    properties.getProperty("FIRE_MINUTE"),
                    properties.getProperty("FIRE_HOUR"),
                    properties.getProperty("FIRE_DAY_OF_WEEK"));
        } else {
            expression = "* * * * * ? *";
        }
        return expression;
    }

    /**
     * Returns the class for starting by schedule.
     *
     * @return - the class.
     * @throws IOException            -  configuration is not load.
     * @throws ClassNotFoundException - if the class cannot be located.
     * @throws InstantiationException - the specified class object cannot be
     *                                instantiated.
     * @throws IllegalAccessException - if does not have access to the definition of
     *                                the specified class, because the class is not an instance of Work.
     */
    @Override
    public Class getWorkClass() throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        if (this.properties.isEmpty()) {
            throw new IOException("The configuration file is not load");
        }
        Class cl = Class.forName(this.properties.getProperty("CLASS"));
        if (!(cl.newInstance() instanceof Work)) {
            throw new IllegalAccessException("Loaded class not an instance of Work");
        }
        return cl;
    }
}
