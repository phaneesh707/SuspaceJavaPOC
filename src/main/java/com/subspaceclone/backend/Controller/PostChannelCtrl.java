package com.subspaceclone.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.PostChannel;
import com.subspaceclone.backend.Service.PostChannelSrv;

@RestController
@RequestMapping("/api/v1/postChannel")
public class PostChannelCtrl {

    @Autowired
    private PostChannelSrv postChannelService;

    // get post channel by id
    @GetMapping("/{id}")
    public PostChannel getPostChannel(@PathVariable Integer id) {
        return postChannelService.getPostChannel(id);
    }

    // Get post channels by community
    @GetMapping("/community/{community}")
    public List<PostChannel> getPostChannelsByCommunity(@PathVariable Integer community) {
        return postChannelService.getPostChannelsByCommunity(community);
    }

    record PostChannelRequest(Integer communityId, String name, String entryFee, String logoURL,
            Boolean isAnnouncement) {

    }

    // create postchannel
    @PostMapping
    public PostChannel createPostChannel(@RequestBody PostChannelRequest request) {
        return postChannelService.createPostChannel(request.name, request.entryFee, request.logoURL,
                request.communityId, request.isAnnouncement);
    }

    // edit post channel by id
    @PostMapping("/{id}")
    public PostChannel editPostChannel(@PathVariable Integer id, @RequestBody PostChannelRequest request) {
        return postChannelService.editPostChannel(id, request.name, request.entryFee, request.logoURL,
                request.communityId, request.isAnnouncement);
    }

    // delete post channel by id
    @DeleteMapping("/{id}")
    public PostChannel deletePostChannel(@PathVariable Integer id) {
        return postChannelService.deletePostChannel(id);
    }
}
