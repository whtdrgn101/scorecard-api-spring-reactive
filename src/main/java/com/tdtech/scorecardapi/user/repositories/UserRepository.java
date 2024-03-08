package com.tdtech.scorecardapi.user.repositories;

import com.tdtech.scorecardapi.user.entities.UserDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDto, String> {
}
