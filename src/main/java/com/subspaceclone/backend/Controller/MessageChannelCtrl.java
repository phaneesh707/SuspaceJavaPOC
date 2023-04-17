package com.subspaceclone.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.MessageChannel;
import com.subspaceclone.backend.Service.MessageChannelSrv;

@RestController
@RequestMapping("/api/v1/messageChannel")
public class MessageChannelCtrl {

    @Autowired
    private MessageChannelSrv messageChannelSrv;

    @GetMapping("/{channelId}")
    public MessageChannel getMessageChannel(@PathVariable Integer channelId) {
        return messageChannelSrv.getMessageChannel(channelId);
    }

    record MessageChannelRequest(String name, String entryFee, String logoURL, Integer communityId, Boolean isDm) {
    }

    @PostMapping
    public MessageChannel createMessageChannel(@RequestBody MessageChannelRequest request) {
        return messageChannelSrv.createMessageChannel(request.name(), request.entryFee(), request.logoURL(),
                request.communityId(), request.isDm());
    }

    @PutMapping("/{channelId}")
    public MessageChannel editMessageChannel(@RequestBody MessageChannelRequest request,
            @PathVariable Integer channelId) {
        return messageChannelSrv.editMessageChannel(channelId, request.name(), request.entryFee(), request.logoURL(),
                request.communityId(), request.isDm());
    }

    @DeleteMapping("/{channelId}")
    public MessageChannel deleteMessageChannel(@PathVariable Integer channelId) {
        return messageChannelSrv.deleteMessageChannel(channelId);
    }

    @GetMapping("/community/{communityId}")
    public List<MessageChannel> getMessageChannelsByCommunity(@PathVariable Integer communityId) {
        return messageChannelSrv.getMessageChannelsInCommunity(communityId);
    }
}
