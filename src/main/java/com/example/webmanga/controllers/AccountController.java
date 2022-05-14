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
    public ResponseEntity<ResponseObject> banAccount(@PathVariable String idUser) {
        return ResponseEntity.ok(accountService.banAccount(idUser));
    }

    @PostMapping("/provideAccount")
    public ResponseEntity<ResponseObject> provideAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @PutMapping("/editAccount")
    public ResponseEntity<ResponseObject> editAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.editAccount(accountDTO));
    }

    @GetMapping("/subComic/{id}/{idComic}")
    public ResponseEntity<ResponseObject> searchComics(@PathVariable String id, @PathVariable String idComic) {
        return ResponseEntity.ok(accountService.subComic(id, idComic));
    }

}
