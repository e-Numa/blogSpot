package com.enuma.blogSpot.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserResponse {
    Long id;
    String name;
    String email;
    String gender;
}
