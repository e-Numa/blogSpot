package com.enuma.blogSpot.payload.response;

import com.enuma.blogSpot.payload.request.PostRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PostResponse {
    List<PostRequest> Content;
    int pageNo;
    int pageSize;
    Long totalElements;
    int totalPages;
    boolean last;
}
