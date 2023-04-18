package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Message;
import com.subspaceclone.backend.Model.MessageChannel;
import com.subspaceclone.backend.Model.User;
import com.subspaceclone.backend.Repository.MessageRepo;

@Service
public class MessageSrv {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserSrv userSrv;

    @Autowired
    private MessageChannelSrv messageChannelSrv;

    // get message by id
    public Message getMessage(Integer id) {
        Optional<Message> message = messageRepo.findById(id);
        if (!message.isPresent()) {
            throw new CustomError(404, "Message not found");
        }
        return message.get();
    }

    // create Message
    public Message createMessage(String content, String imgUrl, Integer senderId, Integer channelId) {
        User sender = userSrv.findUserById(senderId);
        MessageChannel channel = messageChannelSrv.getMessageChannel(channelId);
        Message message = new Message();
        message.setContent(content);
        message.setImgUrl(imgUrl);
        message.setUser(sender);
        message.setMessageChannel(channel);
        return messageRepo.save(message);
    }

    // get messages in channel
    public List<Message> getMessagesInChannel(Integer channelId) {
        MessageChannel channel = messageChannelSrv.getMessageChannel(channelId);
        return messageRepo.findByMessageChannel(channel);
    }

    // delete message by id
    public Message deleteMessage(Integer id) {
        Message message = getMessage(id);
        messageRepo.delete(message);
        return message;
    }

}
