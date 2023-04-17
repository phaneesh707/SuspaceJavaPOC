package com.subspaceclone.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.Channel;
import com.subspaceclone.backend.Service.ChannelSrv;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelCtrl {

    @Autowired
    private ChannelSrv channelService;

    // Get channel by id
    @GetMapping("/{id}")
    public Channel getChannel(@PathVariable Integer id) {
        return channelService.getChannel(id);
    }

    // Get channels by community and type
    @GetMapping("/community/{community}/type/{type}")
    public List<Channel> getChannelsByCommunityAndType(@PathVariable Integer community, @PathVariable String type) {
        return channelService.findByChannelTypeAndCommunity(type, community);
    }

    // Get channels by community
    @GetMapping("/community/{community}")
    public List<Channel> getChannelsByCommunity(@PathVariable Integer community) {
        return channelService.getChannelsByCommunity(community);
    }

    // Edit channel by id
    @PutMapping("/{id}")
    public Channel editChannel(@PathVariable Integer id, String name, String entryFee, String logoURL) {
        return channelService.editChannel(id, name, entryFee, logoURL);
    }

    // Delete channel by id
    @DeleteMapping("/{id}")
    public Channel deleteChannel(@PathVariable Integer id) {
        return channelService.deleteChannel(id);
    }

}
