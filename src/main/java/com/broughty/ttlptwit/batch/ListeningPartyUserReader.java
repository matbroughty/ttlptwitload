package com.broughty.ttlptwit.batch;

import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ListeningPartyUserReader implements Tasklet, StepExecutionListener {

  static final         String                   TWITTER_USER_IDS = "TWITTER_USER_IDS";
  private static final Logger                   LOGGER           = LoggerFactory.getLogger(ListeningPartyUserReader.class);
  private final        ListeningPartyRepository repository;
  List<String> twitterUserIds;

  public ListeningPartyUserReader(final ListeningPartyRepository repository) {
    this.repository = repository;
  }

  @Override
  public void beforeStep(final StepExecution stepExecution) {
    LOGGER.info("beforeStep ListeningPartyUserReader");
    twitterUserIds = new ArrayList<>();

  }

  @Override
  public ExitStatus afterStep(final StepExecution stepExecution) {
    stepExecution.getJobExecution().getExecutionContext().put(TWITTER_USER_IDS, twitterUserIds);
    LOGGER.info("afterStep ListeningPartyUserReader with ids {} size", twitterUserIds);
    twitterUserIds.forEach(userId -> LOGGER.info("User Id {}", userId));
    return ExitStatus.COMPLETED;
  }

  @Override
  public RepeatStatus execute(final StepContribution stepContribution, final ChunkContext chunkContext) {
    twitterUserIds = repository.getMissingListeningPartyUsers();
    return RepeatStatus.FINISHED;
  }

}
