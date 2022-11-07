package com.broughty.ttlptwit.batch;

import static com.broughty.ttlptwit.batch.ListeningPartyUserReader.TWITTER_USER_IDS;
import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_USER;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyUserRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import com.google.common.collect.Lists;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.user.User;
import io.github.redouane59.twitter.dto.user.UserV2;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

@SuppressWarnings("NullableProblems")
@Component
public class ListeningPartyUserWriter implements Tasklet, StepExecutionListener {

  private static final Logger                   LOGGER = LoggerFactory.getLogger(ListeningPartyUserWriter.class);
  private final        ListeningPartyRepository repository;
  private final        TwitterClient            twitterClient;
  List<String> twitterUserIds;

  public ListeningPartyUserWriter(final ListeningPartyRepository repository, final TwitterClient twitterClient) {
    this.repository    = repository;
    this.twitterClient = twitterClient;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void beforeStep(final StepExecution stepExecution) {
    LOGGER.info("in beforeStep for ListeningPartyUserWriter");
    twitterUserIds = (List<String>)
        stepExecution.getJobExecution().getExecutionContext().get(TWITTER_USER_IDS);

  }

  @Override
  public ExitStatus afterStep(final StepExecution stepExecution) {
    LOGGER.info("completed writing {} users ", twitterUserIds.size());
    return ExitStatus.COMPLETED;
  }

  @Override
  public RepeatStatus execute(final StepContribution stepContribution, final ChunkContext chunkContext) {
    Lists.partition(twitterUserIds, 100).forEach(
        userIds -> {
          userIds.forEach(id -> LOGGER.info("Persisting user {}", id));
          repository.batchUpdateUsers(convert(twitterClient.getUsersFromUserIds(userIds)));
        }
    );
    return RepeatStatus.FINISHED;
  }

  private List<? extends ListeningPartyUserRecord> convert(final List<User> usersFromUserIds) {
    return usersFromUserIds.stream().filter(Objects::nonNull).map(user -> new ListeningPartyUserRecord(null,
                                                                                                       user.getId(),
                                                                                                       user.getName(),
                                                                                                       user.getDisplayedName(),
                                                                                                       user.getLocation(),
                                                                                                       user.getDescription(),
                                                                                                       user.getDateOfCreation(),
                                                                                                       user.getFollowersCount(),
                                                                                                       user.getFollowingCount(),
                                                                                                       user.getTweetCount(),
                                                                                                       user instanceof UserV2 ? null : user.getLang(),
                                                                                                       user.getUrl(),
                                                                                                       user.getProfileImageUrl()

                           )).peek(lpr -> lpr.changed(LISTENING_PARTY_USER.ID, false))
                           .collect(Collectors.toList());
  }
}
