package com.example.webmanga.dtos;

import com.example.webmanga.entities.embedded.User;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private int age;
    private String gender;
    private String email;

    public UserDTO(User user) {
        this.name = user.getFullname();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }
}



