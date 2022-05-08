package com.example.webmanga.entities;

import com.example.webmanga.dtos.AccountDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Account")
public class Account {
    @Transient
    public static final String SEQUENCE_NAME = "accounts_sequence";
    @Id
    private Long id;
    private String username;
    private String password;
    private boolean isActive;
    private User user;

    public Account(AccountDTO account) {
        this.id = account.getId();
        this.username = account.getUserName();
        this.password = account.getPassword();
        this.isActive = account.isActive();
        this.user = new User(account.getUser());
    }
}
