package sqlru.core;

import java.io.IOException;

/**
 * Configuration data of the application.
 */
public interface AppConfig {

    /**
     * Loads configuration data.
     *
     * @throws IOException - error of loading.
     */
    void loadConfig() throws IOException;

    /**
     * Returns cron expression, that defines the application's working time.
     *
     * @return - cron expression.
     * @throws IOException - some error.
     */
    String getCronExpression() throws IOException;

    /**
     * Returns the class for starting by quartz.
     *
     * @return - the class.
     * @throws Exception -  some error of class getting.
     */
    Class getWorkClass() throws Exception;
}
