package com.example.batch_processing.job;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log =
           LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    @Override
    public void afterJob(@NonNull JobExecution jobExecution) {

        log.info("Job ended: {}", jobExecution.getJobInstance().getJobName());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("JOB FINISHED SUCCESSFULLY!");
        }else {
            log.info("JOB ERROR!");
        }
    }

    @Override
    public void beforeJob(@NonNull JobExecution jobExecution) {
        log.info("Job Started: {}", jobExecution.getJobInstance().getJobName());
    }
}