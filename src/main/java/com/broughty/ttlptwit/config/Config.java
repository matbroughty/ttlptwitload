package com.broughty.ttlptwit.config;


import com.broughty.ttlptwit.batch.JobCompletionNotificationListener;
import com.broughty.ttlptwit.batch.ListeningParty;
import com.broughty.ttlptwit.batch.ListeningPartyInserterCheck;
import com.broughty.ttlptwit.batch.ListeningPartyReader;
import com.broughty.ttlptwit.batch.ListeningPartyTweetWriter;
import com.broughty.ttlptwit.batch.ListeningPartyWriter;
import com.broughty.ttlptwit.database.jooq.data.tables.records.ListeningPartyRecord;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import java.net.MalformedURLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.RecordFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.UrlResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class Config {

  private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

  public final JobBuilderFactory jobBuilderFactory;

  public final StepBuilderFactory stepBuilderFactory;

  public final Environment env;

  @Value("${file.input}")
  private String fileInput;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public Config(final JobBuilderFactory jobBuilderFactory, final StepBuilderFactory stepBuilderFactory, final Environment env) {
    this.jobBuilderFactory  = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
    this.env                = env;
  }


  /**
   * Reads all the records from the csv file fileInput and creates a {@link ListeningParty} record for each
   *
   * @return List of {@link ListeningParty}
   */
  @Bean
  public FlatFileItemReader<ListeningParty> reader() {
    try {
      return new FlatFileItemReaderBuilder<ListeningParty>().name("listeningPartyItemReader")
                                                            .resource(new UrlResource(fileInput))
                                                            .delimited()
                                                            .names("date", "artist", "album", "announcement", "replay", "tweeters",
                                                                   "collection", "albumLink", "image",
                                                                   "image2", "releaseDate", "ttlpId", "image3")
                                                            .fieldSetMapper(new RecordFieldSetMapper<>(ListeningParty.class))
                                                            .build();
    } catch (MalformedURLException e) {
      LOGGER.error("Issue parsing {}", fileInput, e);
    }
    return null;
  }


  @Bean
  public Job importListeningPartyJob(JobCompletionNotificationListener listener,
                                     Step loadListeningParties, Step loadListeningPartyTweets) {
    return jobBuilderFactory.get("importListeningPartyJob")
                            .incrementer(new RunIdIncrementer())
                            .listener(listener)
                            .flow(loadListeningParties)
                            .next(loadListeningPartyTweets)
                            .end()
                            .build();
  }

  @Bean
  public Step loadListeningParties(ListeningPartyInserterCheck processor, ListeningPartyWriter writer) {
    return stepBuilderFactory.get("loadListeningParties")
                             .<ListeningParty, ListeningPartyRecord>chunk(100)
                             .reader(reader())
                             .processor(processor)
                             .writer(writer)
                             .build();
  }

  @Bean
  public Step loadListeningPartyTweets(ListeningPartyReader reader, ListeningPartyTweetWriter writer) {
    return stepBuilderFactory.get("loadListeningPartyTweets")
                             .<ListeningPartyRecord, ListeningPartyRecord>chunk(100)
                             .faultTolerant()
                             .retry(StringIndexOutOfBoundsException.class)
                             .retryLimit(5)
                             .reader(reader)
                             .writer(writer)
                             .build();
  }

  @Bean(name = "asyncJobLauncher")
  public JobLauncher simpleJobLauncher(JobRepository jobRepository) throws Exception {
    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setJobRepository(jobRepository);
    jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
    jobLauncher.afterPropertiesSet();
    return jobLauncher;
  }


  @Bean
  public TwitterClient twitterClient() {
    LOGGER.info("getting-twitter-client");
    return new TwitterClient(TwitterCredentials.builder()
                                               .accessToken(env.getProperty("twitter4j.accessToken"))
                                               .accessTokenSecret(env.getProperty("twitter4j.accessTokenSecret"))
                                               .apiKey(env.getProperty("twitter4j.consumerKey"))
                                               .apiSecretKey(env.getProperty("twitter4j.consumerSecret"))
                                               .build());
  }


}
