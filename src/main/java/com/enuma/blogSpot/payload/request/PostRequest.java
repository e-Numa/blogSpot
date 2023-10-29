package com.enuma.blogSpot.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest {

    Long id;

    @NotEmpty
    @Size(min = 2, message = "post title should not be empty")
    String title;

    @NotEmpty
    @Size(min = 10, message = "post description should have at least 10 characters")
    String description;

    @NotEmpty
    String content;
    Set<CommentRequest> comments;
}
