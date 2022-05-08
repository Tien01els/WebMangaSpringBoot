package com.example.webmanga.dtos;

import com.example.webmanga.entities.User;
import lombok.*;
import org.springframework.data.annotation.Transient;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String email;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getFullname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }
}



