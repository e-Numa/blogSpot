package com.enuma.blogSpot.payload.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequest {

    Long id;
    String name;
    String email;
    String body;

}
