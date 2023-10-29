package com.enuma.blogSpot.services.serviceImpl;

import com.enuma.blogSpot.model.UserEntity;
import com.enuma.blogSpot.payload.request.UserRequest;
import com.enuma.blogSpot.payload.response.UserResponse;
import com.enuma.blogSpot.respository.UserRepository;
import com.enuma.blogSpot.services.UserEntityService;
import com.enuma.blogSpot.utility.DTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserEntityServiceImpl implements UserEntityService {

//    private final UserRepository userRepository;
     UserRepository userRepository; //same as above but with annotations

    @Override
    public UserResponse createUser(UserRequest request) {
        UserEntity entity = DTOMapper.requestToEntity(request);
        UserEntity entityFromTheDB = userRepository.save(entity);
        return DTOMapper.entityToResponse(entityFromTheDB);
    }

    @Override
    @Transactional
    public UserResponse updateUser(String email, Long id) {

           UserEntity entity = userRepository.findById(id)
                    .orElseThrow(()-> new IllegalStateException(
                            "user with id " + id + " does not exist"
                    ));

            if (email != null && email.length() > 0 &&
                    !Objects.equals(entity.getEmail(), email)) {
                Optional<UserEntity> entityOptional = userRepository
                        .findByEmail(email);
                if (entityOptional.isPresent()){
                    throw new IllegalStateException("email taken");
                }
                entity.setEmail(email);
            }
        return null;
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse getUserByID(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "user with id " + id + "does not exist"
                ));

        if (id > 0 && !Objects.equals(entity.getId(), id)) {
            Optional<UserEntity> entityOptional = userRepository
                    .findById(id);
            if (entityOptional.isPresent()){
                return DTOMapper.entityToResponse(entity);
            }
        }
        return null;
    }

//        for (UserEntity entity : getUsers().toArray(new UserEntity[0])) {
//                    if (Objects.equals(entity.getId(), id)) {
//                        return DTOMapper.entityToResponse(entity);
//                    }
//                }
//        return null;} // User with the specified ID not found.


    @Override
    public UserResponse deleteUser(Long id) {
            boolean exists = userRepository.existsById(id);
            if (!exists) {
                throw new IllegalStateException(
                        "user with id " + id + " does not exist");
            }
            userRepository.deleteById(id);
        return null;
    }

    }
