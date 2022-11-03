package com.broughty.ttlptwit.controller;

import static java.lang.Integer.parseInt;

import com.broughty.ttlptwit.aggregation.ListeningPartyTweetDto;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@SuppressWarnings("unused")
@Controller
public class ListeningPartyController {

  private static final Logger                   LOGGER = LoggerFactory.getLogger(ListeningPartyController.class);
  final                Job                      job;
  final                JobLauncher              jobLauncher;
  private final        ListeningPartyRepository repository;

  public ListeningPartyController(final ListeningPartyRepository repository, final Job job, final JobLauncher jobLauncher) {
    this.repository  = repository;
    this.job         = job;
    this.jobLauncher = jobLauncher;
  }

  @QueryMapping(name = "listeningParties")
  List<ListeningPartyRecord> listeningParties() {
    return repository.getListeningPartyList(0, 2000);
  }

  @QueryMapping(name = "listeningPartyById")
  ListeningPartyRecord listeningPartyById(@Argument String ttlpNo) {
    return repository.selectByTtlpId(parseInt(ttlpNo));
  }

  @QueryMapping(name = "listeningPartyTweetsById")
  List<ListeningPartyTweetDto> listeningPartyTweetsById(@Argument String ttlpNo) {
    return repository.getListeningPartyTweets(parseInt(ttlpNo));
  }

  @BatchMapping(typeName = "ListeningParty")
  Map<ListeningPartyRecord, List<ListeningPartyTweetDto>> listeningPartyTweets(List<ListeningPartyRecord> listeningParties) {
    LOGGER.info("getting tweets for {} parties", listeningParties.size());
    return repository.getListeningPartiesTweets(listeningParties);
  }

  @MutationMapping(name = "importTweetData")
  Integer importTweetData(@Argument String apiCallId) throws Exception {
    LOGGER.info("importing tweet data {} for api call id ", apiCallId);
    JobParametersBuilder jobParametersbuilder = new JobParametersBuilder()
        .addString("apiCallId", apiCallId);
    return jobLauncher.run(job, jobParametersbuilder.toJobParameters()).getJobId().intValue();

  }


}