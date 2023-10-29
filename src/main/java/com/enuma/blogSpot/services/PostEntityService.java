package com.enuma.blogSpot.services;

import com.enuma.blogSpot.model.PostEntity;
import com.enuma.blogSpot.payload.request.PostRequest;
import com.enuma.blogSpot.payload.response.PostResponse;


import java.util.List;

public interface PostEntityService {

    PostRequest createPost(PostRequest postRequest);
    PostResponse updatePost(String email, Long id);
    List<PostEntity> getPosts();
    PostRequest updatePost(PostRequest postRequest, Long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostRequest getPostById(long id);

    PostRequest getPostById(Long id);

    void deletePostById(Long id);

    void deletePostById(long id);
}