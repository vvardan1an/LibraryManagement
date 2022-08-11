package books.model;

import books.enums.AuthorGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String name;
    private String surName;
    private String email;
    private AuthorGender gender;

}
