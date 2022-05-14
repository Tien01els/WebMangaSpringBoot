package com.example.webmanga.dtos;

import com.example.webmanga.entities.Account;
import lombok.*;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @Transient
    public static final String SEQUENCE_NAME = "accounts_sequence";
    private String id;
    private String userName;
    private String password;
    private boolean isActive;
    private UserDTO user;
    private int role;
    private List<Long> subscribeComicList;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.userName = account.getUsername();
        this.password = account.getPassword();
        this.isActive = account.isActive();
        this.user = new UserDTO(account.getUser());
        this.role = account.getRole();
        if(account.getSubscribeComicList() != null)
            account.getSubscribeComicList().forEach(subscribeComic -> {
                if (this.subscribeComicList == null) {
                    this.subscribeComicList = new ArrayList<>();
                }
                this.subscribeComicList.add(subscribeComic);
            });
    }

}
