package com.subspaceclone.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.Post;
import com.subspaceclone.backend.Model.PostWrapper;
import com.subspaceclone.backend.Service.PostSrv;

@RestController
@RequestMapping("/api/v1/post")
public class PostCtrl {

    @Autowired
    private PostSrv postSrv;

    @GetMapping("/{postId}")
    public PostWrapper getPost(@PathVariable Integer postId) {
        return postSrv.getPostWithReplies(postId);
    }

    record PostRequest(String title, String content, String imgURL, Integer communityId, Integer userId,
            Integer postChannelId) {
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody PostRequest postRequest) {
        return postSrv.createPost(postRequest.title, postRequest.content, postRequest.imgURL, postRequest.communityId,
                postRequest.userId, postRequest.postChannelId);
    }

    @PostMapping("/create/{postId}/reply")
    public Post createReplyPost(@RequestBody PostRequest postReplyRequest, @PathVariable Integer postId) {
        return postSrv.createReplyPost(postReplyRequest.title, postReplyRequest.content, postReplyRequest.imgURL,
                postReplyRequest.communityId, postReplyRequest.userId, postReplyRequest.postChannelId,
                postId);
    }

    @GetMapping("/community/{communityId}")
    public List<Post> getPostsByCommunity(@PathVariable Integer communityId) {
        return postSrv.getPostsByCommunity(communityId);
    }

    @GetMapping("/channel/{postChannelId}")
    public List<Post> getPostsByChannel(@PathVariable Integer postChannelId) {
        return postSrv.getPostsByPostChannel(postChannelId);
    }

    // handle like post
}
