package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Model.Post;
import com.subspaceclone.backend.Model.PostChannel;
import com.subspaceclone.backend.Model.PostWrapper;
import com.subspaceclone.backend.Model.User;
import com.subspaceclone.backend.Repository.PostRepo;

@Service
public class PostSrv {

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private PostChannelSrv postChannelSrv;

    @Autowired
    private UserSrv userSrv;

    @Autowired
    private CommunitySrv communitySrv;

    public Post getPost(Integer postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent()) {
            throw new CustomError(404, "Post not found");
        }
        return optionalPost.get();
    }

    // get post by id with Replies
    public PostWrapper getPostWithReplies(Integer postId) {
        Post post = getPost(postId);
        List<Post> replies = postRepository.findByParentId(postId);
        return new PostWrapper(post, replies);
    }

    // create post
    public Post createPost(String title, String content, String imgURL, Integer communityId, Integer userId,
            Integer postChannelId) {
        Community community = communitySrv.getCommunity(communityId);
        User user = userSrv.findUserById(userId);
        PostChannel postChannel = postChannelSrv.getPostChannel(postChannelId);
        Post post = new Post();
        LocalDateTime currentDateTime = LocalDateTime.now();

        post.setTitle(title);
        post.setContent(content);
        post.setImgURL(imgURL);
        post.setCommunity(community);
        post.setUser(user);
        post.setPostChannel(postChannel);
        post.setCreatedAt(currentDateTime);
        return postRepository.save(post);
    }

    // create reply post
    public Post createReplyPost(String title, String content, String imgURL, Integer communityId, Integer userId,
            Integer postChannelId, Integer parentId) {
        Community community = communitySrv.getCommunity(communityId);
        User user = userSrv.findUserById(userId);
        PostChannel postChannel = postChannelSrv.getPostChannel(postChannelId);
        Post parentPost = getPost(parentId);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setImgURL(imgURL);
        post.setCommunity(community);
        post.setUser(user);
        post.setPostChannel(postChannel);
        post.setParent(parentPost);
        return postRepository.save(post);
    }

    // get posts by post channel
    public List<Post> getPostsByPostChannel(Integer postChannelId) {
        PostChannel postChannel = postChannelSrv.getPostChannel(postChannelId);
        return postRepository.findByPostChannelId(postChannelId);
    }

    // get posts in commuhnity
    public List<Post> getPostsByCommunity(Integer communityId) {
        Community community = communitySrv.getCommunity(communityId);
        return postRepository.findByCommunityId(communityId);
    }

    // delete post
    public Post deletePost(Integer postId) {
        Post post = getPost(postId);
        postRepository.delete(post);
        return post;
    }

}
