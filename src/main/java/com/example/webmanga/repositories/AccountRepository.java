package com.example.webmanga.repositories;

import com.example.webmanga.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {
    Account findAccountByUsername(String username);
}
