package com.broughty.ttlptwit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class JobLaunchController {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobLaunchController.class);

  final Job job;

  final JobLauncher jobLauncher;

  public JobLaunchController(@Qualifier("asyncJobLauncher") final JobLauncher jobLauncher, final Job job) {
    this.jobLauncher = jobLauncher;
    this.job         = job;
  }


  @PostMapping("/api/import")
  @ResponseBody
  public ResponseEntity<String> importTweetData(@RequestParam(required = false) String apiCallId) throws Exception {
    LOGGER.info("Request to import tweet data with id {}", apiCallId);
    JobParametersBuilder jobParametersbuilder = new JobParametersBuilder()
        .addString("apiCallId", apiCallId);
    Long jobId = jobLauncher.run(job, jobParametersbuilder.toJobParameters()).getJobId();
    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Twitter load Job requested. Job Id = " + jobId);
  }


}
