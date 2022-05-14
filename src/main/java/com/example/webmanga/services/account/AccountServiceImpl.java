package com.example.webmanga.services.account;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.UserDTO;
import com.example.webmanga.entities.Account;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

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

        UserDTO user = new UserDTO();
        user.setName(accountDTO.getUserName());
        //accountDTO.setId(sequenceGenerator.generateSequence(AccountDTO.SEQUENCE_NAME));
        accountDTO.setUser(user);
        accountDTO.setActive(true);

        if (accountDTO.getRole() == 1) {
            accountDTO.setPassword("1");
            accountDTO.setRole(1);
        } else if (accountDTO.getRole() == 0) {
            accountDTO.setRole(0);
        }
        Account acc = new Account(accountDTO);

        Account res = accountRepository.save(acc);

        return new ResponseObject("Success", "Register successfully",
                new AccountDTO(res));
    }

    @Override
    public ResponseObject banAccount(Long id) {
        Account accountBanned = accountRepository.findById(id)
                .map(account -> {
                    account.setActive(false);
                    return accountRepository.save(account);
                }).orElseGet(() -> {
                    return null;
                });
        if (Objects.isNull(accountBanned))
        {
            return new ResponseObject("Fail", "Ban failure", "");
        }
        return new ResponseObject("Success", "Ban successfully", new AccountDTO(accountBanned));
    }
}
