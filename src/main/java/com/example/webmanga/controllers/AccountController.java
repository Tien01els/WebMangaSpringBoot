package com.example.webmanga.controllers;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.checkLogin(accountDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @PutMapping("/banAccount/{idUser}")
    public ResponseEntity<ResponseObject> banAccount(@PathVariable Long idUser) {
        return ResponseEntity.ok(accountService.banAccount(idUser));
    }

    @PostMapping("/provideAccount")
    public ResponseEntity<ResponseObject> provideAccount(@RequestBody AccountDTO accountDTO) {
        accountDTO.setRole(1);
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

}
