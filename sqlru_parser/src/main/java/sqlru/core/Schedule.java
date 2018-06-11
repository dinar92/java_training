package sqlru.core;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Start class for parsing process.
 */
public class Schedule {

    /**
     * The errors logger.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(Schedule.class);

    /**
     * Configuration of application from the file 'config.properties'.
     */
    private static AppConfig appConfig = new FileAppConfig();

    /**
     * Starts a class from configuration with cron time parameters from the same file.
     *
     * @param args - input string arguments.
     */
    public static void main(String[] args) {
        try {
            appConfig.loadConfig();
            Class parserClass = appConfig.getWorkClass();
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            JobDetail job = JobBuilder.newJob(parserClass)
                    .withIdentity("job1", "group1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(appConfig.getCronExpression()))
                    .forJob(job)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
