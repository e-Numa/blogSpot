package com.enuma.blogSpot.services.serviceImpl;


import com.enuma.blogSpot.exception.BlogAPIException;
import com.enuma.blogSpot.exception.ResourceNotFoundException;
import com.enuma.blogSpot.model.CommentEntity;
import com.enuma.blogSpot.model.PostEntity;
import com.enuma.blogSpot.payload.request.CommentRequest;
import com.enuma.blogSpot.respository.CommentRepository;
import com.enuma.blogSpot.respository.PostRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.enuma.blogSpot.utility.DTOMapper.mapToDo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentServiceImpl implements com.enuma.blogSpot.services.CommentServiceImpl {

    CommentRepository commentRepository;
    PostRepository postRepository;
    ModelMapper mapper;


    @Override
    public CommentRequest createComment(Long postId, CommentRequest commentRequest) {
        CommentEntity commentEntity = mapToEntity(commentRequest);
        PostEntity postEntity = postRepository.findById(postId).orElseThrow
                (() -> new ResourceNotFoundException("Post", "id", postId));
        commentEntity.setPostEntity(postEntity);
        CommentEntity newComment = commentRepository.save(commentEntity);
        return mapToDo(newComment);
    }

    @Override
    public List<CommentRequest> getCommentsByPostId(Long postId) {
        List<CommentEntity> commentEntities = commentRepository.findByPostId(postId);
        return commentEntities.stream().map(this::mapToDo).collect(Collectors.toList());
    }

    @Override
    public CommentRequest getCommentById(Long postId, Long commentId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow
                (() -> new ResourceNotFoundException("Post", "id", postId));
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return mapToDo(commentEntity);
    }

    @Override
    public CommentRequest updateComment(Long postId, Long commentId, CommentRequest commentRequest) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow
                (() -> new ResourceNotFoundException("Post", "id", postId));
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        commentEntity.setName(commentRequest.getName());
        commentEntity.setEmail(commentRequest.getEmail());
        commentEntity.setBody(commentRequest.getBody());

        CommentEntity updatedComment = commentRepository.save(commentEntity);
        return mapToDo(updatedComment);

    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if (!commentEntity.getPostEntity().getId().equals(postEntity.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
        }
        commentRepository.delete(commentEntity);
    }

}