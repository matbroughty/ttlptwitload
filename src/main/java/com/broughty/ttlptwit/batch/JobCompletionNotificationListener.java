package com.broughty.ttlptwit.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);


  @Override
  public void afterJob(JobExecution jobExecution) {
    jobExecution.getStepExecutions().forEach(jobE -> LOGGER.info("Job Execution -> {}", jobE.toString()));
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      LOGGER.info("Job finished ok");
    } else {
      LOGGER.warn("Issue running Job - status = {}", jobExecution.getStatus());
    }
  }
}