package com.tdtech.scorecardapi.user.repositories;

import com.tdtech.scorecardapi.user.entities.UserDto;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableAutoConfiguration
public interface UserRepository extends ReactiveMongoRepository<UserDto, String> {
}
