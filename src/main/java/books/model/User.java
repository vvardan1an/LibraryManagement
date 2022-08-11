package books.model;

import books.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;
    private String surName;
    private String email;
    private String password;
    private Role role;
}
