package com.enuma.blogSpot.services.serviceImpl;

import com.enuma.blogSpot.exception.ResourceNotFoundException;
import com.enuma.blogSpot.model.PostEntity;
import com.enuma.blogSpot.payload.request.PostRequest;
import com.enuma.blogSpot.payload.response.PostResponse;
import com.enuma.blogSpot.respository.PostRepository;
import com.enuma.blogSpot.services.PostEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostEntityServiceImpl implements PostEntityService {

    private PostRepository postRepository;
    private ModelMapper mapper;


    public PostEntityServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostRequest createPost(PostRequest postRequest) {
        // Convert Dto to entity
        PostEntity postEntity = mapToEntity(postRequest);
        PostEntity newPost = postRepository.save(postEntity);

        // convert entity to dto
        PostRequest postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create pageable instance
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<PostEntity> posts = postRepository.findAll(pageable);

        // get content from page object
        List<PostEntity> listOfPosts = posts.getContent();
        List<PostRequest> content =
                listOfPosts.stream().map(this::mapToDTO).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pageNo);
        postResponse.setPageSize(pageSize);
        postResponse.setTotalElements(postResponse.getTotalElements());
        postResponse.setTotalPages(postResponse.getTotalPages());
        postResponse.setLast(postResponse.isLast());

        return postResponse;

    }

    @Override
    public PostRequest getPostById(Long id) {

        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapToDTO(post);
    }

    @Override
    public PostRequest updatePost(PostRequest postRequest, Long id) {
        // get post by Id in the dataBase
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id",
                id));
        postEntity.setTitle(postRequest.getTitle());
        postEntity.setDescription(postRequest.getDescription());
        postEntity.setContent(postRequest.getContent());

        PostEntity updatedpost = postRepository.save(postEntity);
        return mapToDTO(updatedpost);
    }

    @Override
    public void deletePostById(long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(postEntity);
    }

    // convert Entity into Dto
    private PostRequest mapToDTO(PostEntity postEntity) {
        PostRequest postRequest = mapper.map(postEntity, PostRequest.class);
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postRequest;
    }
    // convert Dto to entity
    private PostEntity mapToEntity(PostRequest postRequest) {
        PostEntity postEntity = mapper.map(postRequest, PostEntity.class);
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return postEntity;
    }
}
