package com.example.webmanga.services.account;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    ResponseObject checkLogin(AccountDTO accountDTO);
    ResponseObject createAccount(AccountDTO accountDTO);
    ResponseObject banAccount(Long id);
}
