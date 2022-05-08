package com.example.webmanga.controllers;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.UserDTO;
import com.example.webmanga.entities.ResponseObject;
import com.example.webmanga.entities.User;
import com.example.webmanga.services.SequenceGeneratorService;
import com.example.webmanga.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @GetMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.checkLogin(accountDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody AccountDTO accountDTO) {
        UserDTO user = new UserDTO();
        user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
        user.setName(accountDTO.getUserName());
        accountDTO.setId(sequenceGenerator.generateSequence(AccountDTO.SEQUENCE_NAME));
        accountDTO.setUser(user);
        accountDTO.setActive(true);
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

}
