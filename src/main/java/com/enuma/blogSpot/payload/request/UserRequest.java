package com.enuma.blogSpot.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "name must not be blank")
    String name;
    @Email(message = "Must be an email")
    String email;
    String gender;
    @Size(min = 5, message = "password must be more than 5 characters")
    String password;
}
