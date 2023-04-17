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

import com.subspaceclone.backend.Model.Message;
import com.subspaceclone.backend.Service.MessageSrv;

@RestController
@RequestMapping("/api/v1/message")
public class MessageCtrl {

    @Autowired
    private MessageSrv messageSrv;

    @GetMapping("/channel/{messageChannelId}")
    public List<Message> getMessagesInChannel(@PathVariable Integer messageChannelId) {
        return messageSrv.getMessagesInChannel(messageChannelId);
    }

    record MessageRequest(String content, String imgUrl, Integer senderId, Integer messageChannelId) {
    }

    @PostMapping
    public Message createMessage(@RequestBody MessageRequest messageRequest) {
        return messageSrv.createMessage(messageRequest.content,
                messageRequest.imgUrl, messageRequest.senderId,
                messageRequest.messageChannelId);
    }

    @DeleteMapping("/{messageId}")
    public Message deleteMessage(@PathVariable Integer messageId) {
        return messageSrv.deleteMessage(messageId);
    }

}
