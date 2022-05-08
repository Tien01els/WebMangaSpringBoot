package com.example.webmanga.services.account;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.entities.Account;
import com.example.webmanga.entities.ResponseObject;
import com.example.webmanga.repositories.AccountRepository;
import com.example.webmanga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (accountDTO.getRole() == 1)
            accountDTO.setPassword("1");
        return new ResponseObject("Success", "Register successfully",
                new AccountDTO(accountRepository.save(new Account(accountDTO))));
    }

    @Override
    public ResponseObject banAccount(Long id) {
        Account account = accountRepository.findById(id)
                .map(accountBanned -> {
                    accountBanned.setActive(false);
                    return accountRepository.save(accountBanned);
                }).orElseGet(() -> {
                    return null;
                });
        if (Objects.isNull(account))
        {
            return new ResponseObject("Fail", "Ban failure", "");
        }
        else if (!account.isActive()) {
            return new ResponseObject("Fail", "Account is banned", "");
        }
        return new ResponseObject("Success", "Register successfully", new AccountDTO(account));
    }
}
