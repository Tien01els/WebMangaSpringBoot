package com.example.webmanga.entities;

import com.example.webmanga.dtos.UserDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;
    private String fullname;
    private int age;
    private String gender;
    private String email;

    public User(UserDTO user) {
        this.id = user.getId();
        this.fullname = user.getName();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }

}
