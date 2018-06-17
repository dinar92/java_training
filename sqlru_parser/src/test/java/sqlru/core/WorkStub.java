package sqlru.core;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WorkStub extends Work {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Some action");
    }
}
