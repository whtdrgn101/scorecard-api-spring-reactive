package com.tdtech.scorecardapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tdtech.scorecardapi.user.controllers","com.tdtech.scorecardapi.user.services"})
@EntityScan({"com.tdtech.scorecardapi.user.entities"})
public class ScorecardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScorecardApiApplication.class, args);
	}

}
