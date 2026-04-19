package ru.report_generation_app.schedule;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ru.report_generation_app.configurations.CachedConfig;

public class FormatRefresher implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        CachedConfig config = (CachedConfig) jobExecutionContext.getJobDetail().getJobDataMap().get("Cache");
        config.setLastUpdate(0);
    }
}
