package com.broughty.ttlptwit.config;

import com.broughty.ttlptwit.repository.ListeningPartyRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphConfig {

  @Bean
  RuntimeWiringConfigurer runtimeWiringConfigurer(ListeningPartyRepository repository) {
    return builder -> builder.type("Query", wiring -> wiring
        .dataFetcher("listeningPartyById", environment -> repository.selectByTtlpId(Integer.parseInt(environment.getArgument("ttlpNo"))))
        .dataFetcher("listeningPartyTweetsById",
                     environment -> repository.getListeningPartyTweets(Integer.parseInt(environment.getArgument("ttlpNo"))))
        .dataFetcher("listeningParties", environment -> repository.getListeningPartyList(0, 2000)));

  }

}
