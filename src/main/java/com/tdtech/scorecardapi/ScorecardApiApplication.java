package com.tdtech.scorecardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.tdtech.scorecardapi.exceptions", "com.tdtech.scorecardapi.user.controllers","com.tdtech.scorecardapi.user.services", "com.tdtech.scorecardapi.round.controllers","com.tdtech.scorecardapi.round.services"})
@EntityScan({"com.tdtech.scorecardapi.user.entities","com.tdtech.scorecardapi.round.entities","com.tdtech.scorecardapi.bow.entities"})
public class ScorecardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScorecardApiApplication.class, args);
	}

}
