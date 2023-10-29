package com.enuma.blogSpot.utility;

import com.enuma.blogSpot.model.CommentEntity;
import com.enuma.blogSpot.model.UserEntity;
import com.enuma.blogSpot.enums.Gender;
import com.enuma.blogSpot.payload.request.CommentRequest;
import com.enuma.blogSpot.payload.request.UserRequest;
import com.enuma.blogSpot.payload.response.UserResponse;

public class DTOMapper {

    public static UserEntity requestToEntity(UserRequest request){
        return new UserEntity(
                request.getName(),
                request.getEmail(),
                Gender.valueOf(request.getGender().toUpperCase()),
                request.getPassword());
    }

    public static UserResponse entityToResponse(UserEntity entity){
        return new UserResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getGender().toString());
    }


    private CommentEntity mapToEntity(CommentRequest commentRequest) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentRequest.getId());
        commentEntity.setName(commentRequest.getName());
        commentEntity.setEmail(commentRequest.getEmail());
        commentEntity.setBody(commentRequest.getBody());

        return commentEntity;
    }


    public static  CommentRequest mapToDo(CommentEntity commentEntity) {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setId(commentEntity.getId());
        commentRequest.setName(commentRequest.getName());
        commentRequest.setEmail(commentRequest.getEmail());
        commentRequest.setBody(commentEntity.getBody());

        return commentRequest;
    }



}
