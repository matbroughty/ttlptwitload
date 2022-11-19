package com.broughty.ttlptwit.batch;

import static com.broughty.ttlptwit.batch.ListeningPartyUserReader.TWITTER_USER_IDS;
import static com.broughty.ttlptwit.database.jooq.data.Tables.LISTENING_PARTY_USER;
import static com.broughty.ttlptwit.database.jooq.data.Tables.MISSING_USER;

import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyUserRecord;
import com.broughty.ttlptwit.database.jooq.data.tables.records.MissingUserRecord;
import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import com.google.common.collect.Lists;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.user.User;
import io.github.redouane59.twitter.dto.user.UserV2;
import java.time.LocalDateTime;
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
          List<User> users = twitterClient.getUsersFromUserIds(userIds);
          if (!users.isEmpty()) {
            users.forEach(id -> LOGGER.info("Persisting user {} [{}]", id.getDisplayedName(), id.getId()));
          }
          repository.batchUpdateUsers(convert(users));
          // may have some missing users - record them, so we don't keep picking them up
          if (userIds.size() != users.size()) {
            List<String> twitterReportedIds = users.stream().map(User::getId).toList();
            LOGGER.info("removed twitter users = {}", userIds.removeAll(twitterReportedIds));
            repository.batchUpdateMissingUsers(convertToMissingUser(userIds));
          }

        }
    );
    return RepeatStatus.FINISHED;
  }

  private List<? extends ListeningPartyUserRecord> convert(final List<User> usersFromUserIds) {
    usersFromUserIds.forEach(ur -> LOGGER.info("in convert with User {} - id {}", ur.toString(), ur.getId()));
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
                                                                                                       user.getProfileImageUrl(),
                                                                                                       true,
                                                                                                       LocalDateTime.now()

                           )).peek(lpr -> lpr.changed(LISTENING_PARTY_USER.ID, false))
                           .collect(Collectors.toList());
  }

  private List<? extends MissingUserRecord> convertToMissingUser(final List<String> missingUserIds) {
    return missingUserIds.stream()
                         .filter(Objects::nonNull)
                         .map(user -> new MissingUserRecord(null, user, LocalDateTime.now(), true, LocalDateTime.now()
                         ))
                         .peek(lpr -> lpr.changed(MISSING_USER.ID, false))
                         .collect(Collectors.toList());
  }

}
