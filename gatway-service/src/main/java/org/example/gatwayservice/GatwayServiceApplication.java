package org.example.gatwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GatwayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatwayServiceApplication.class, args);
	}

	@Bean
	DiscoveryClientRouteDefinitionLocator locator(ReactiveDiscoveryClient rdc,
												  DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}


}
