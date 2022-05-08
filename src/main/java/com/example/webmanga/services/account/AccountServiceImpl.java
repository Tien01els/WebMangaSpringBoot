package com.example.webmanga.services.account;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.entities.Account;
import com.example.webmanga.entities.ResponseObject;
import com.example.webmanga.repositories.AccountRepository;
import com.example.webmanga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseObject checkLogin(AccountDTO accountDTO) {
        Account account = accountRepository.findAccountByUsername(accountDTO.getUserName());
        if (Objects.isNull(account)) {
            return new ResponseObject("Fail", "Account invalid", "");
        }
        else if (!accountDTO.getPassword().equals(account.getPassword()))
        {
            return new ResponseObject("Fail", "Password invalid", "");
        }
        return new ResponseObject("Success", "Logged in successfully", new AccountDTO(account));
    }

    @Override
    public ResponseObject createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findAccountByUsername(accountDTO.getUserName());
        if (!Objects.isNull(account)) {
            return new ResponseObject("Fail", "Account already exists", "");
        }
        return new ResponseObject("Success", "Register successfully", new AccountDTO(accountRepository.save(account)));
    }
}
