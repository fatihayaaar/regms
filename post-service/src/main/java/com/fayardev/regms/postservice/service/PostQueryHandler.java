package com.fayardev.regms.postservice.service;

import com.fayardev.likeservice.like.LikeCountResponse;
import com.fayardev.likeservice.like.LikeResponse;
import com.fayardev.regms.postservice.client.LikeGrpcClient;
import com.fayardev.regms.postservice.client.UserGrpcClient;
import com.fayardev.regms.postservice.dto.PostDto;
import com.fayardev.regms.postservice.entity.Post;
import com.fayardev.regms.postservice.repository.PostRepository;
import com.fayardev.regms.postservice.service.abstracts.IPostQueryHandler;
import com.fayardev.regms.postservice.util.JwtUtils;
import com.fayardev.userservice.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryHandler implements IPostQueryHandler<PostDto> {

    private final PostRepository repository;
    private final UserGrpcClient userClient;
    private final LikeGrpcClient likeGrpcClient;
    private final ModelMapper modelMapper;

    @Override
    public PostDto get(String id) {
        Post post = repository.findById(id).orElseThrow(IllegalAccessError::new);
        UserResponse user = userClient.getUserByUuid(post.getUserId());
        LikeResponse like = likeGrpcClient.getIsLike(JwtUtils.getUserUUID(), post.getId());
        LikeCountResponse likeCountResponse = likeGrpcClient.getLikesCount(post.getId());

        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setUsername(user.getUid());
        postDto.setAvatar(user.getAvatar());
        postDto.setLike(like.getIsLike());
        postDto.setLikeCount(likeCountResponse.getLikeCount());

        return postDto;
    }

    @Override
    public List<PostDto> getMyPosts(String id) {
        Slice<Post> posts = repository.getPostsByUserId(id, PageRequest.of(0, 10));
        UserResponse user = userClient.getUserByUuid(id);

        return getPostDtos(user, posts);
    }

    @Override
    public List<PostDto> getPostsByUsername(String username) {
        UserResponse user = userClient.getUserByUsername(username);

        Slice<Post> posts = repository.getPostsByUserId(user.getUuid(), PageRequest.of(0, 10));
        return getPostDtos(user, posts);
    }

    @Override
    public List<PostDto> getAll() {
        Slice<Post> posts = repository.getPosts(PageRequest.of(0, 20));

        return posts.getContent().stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .map(post -> {
                    PostDto postDto = modelMapper.map(post, PostDto.class);
                    UserResponse user = userClient.getUserByUuid(post.getUserId());
                    LikeResponse like = likeGrpcClient.getIsLike(JwtUtils.getUserUUID(), post.getId());
                    LikeCountResponse likeCountResponse = likeGrpcClient.getLikesCount(post.getId());
                    postDto.setUsername(user.getUid());
                    postDto.setAvatar(user.getAvatar());
                    postDto.setLike(like.getIsLike());
                    postDto.setLikeCount(likeCountResponse.getLikeCount());
                    return postDto;
                }).toList();
    }

    private List<PostDto> getPostDtos(UserResponse user, Slice<Post> posts) {
        return posts.getContent().stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .map(post -> {
                    LikeResponse like = likeGrpcClient.getIsLike(JwtUtils.getUserUUID(), post.getId());
                    LikeCountResponse likeCountResponse = likeGrpcClient.getLikesCount(post.getId());
                    PostDto postDto = modelMapper.map(post, PostDto.class);
                    postDto.setUsername(user.getUid());
                    postDto.setAvatar(user.getAvatar());
                    postDto.setLike(like.getIsLike());
                    postDto.setLikeCount(likeCountResponse.getLikeCount());
                    return postDto;
                }).toList();
    }
}