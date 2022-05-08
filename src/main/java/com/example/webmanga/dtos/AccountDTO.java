package com.example.webmanga.dtos;

import com.example.webmanga.entities.Account;
import lombok.*;
import org.springframework.data.annotation.Transient;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @Transient
    public static final String SEQUENCE_NAME = "accounts_sequence";
    private Long id;
    private String userName;
    private String password;
    private boolean isActive;
    private UserDTO user;
    private int role;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.userName = account.getUsername();
        this.password = account.getPassword();
        this.isActive = account.isActive();
        this.user = new UserDTO(account.getUser());
        this.role = account.getRole();
    }

}
