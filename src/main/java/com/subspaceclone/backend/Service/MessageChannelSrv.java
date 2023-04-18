package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Model.MessageChannel;
import com.subspaceclone.backend.Repository.MessageChannelRepo;

@Service
public class MessageChannelSrv {

    @Autowired
    private MessageChannelRepo messageChannelRepository;

    @Autowired
    private CommunitySrv communitySrv;

    // get message Channel by id
    public MessageChannel getMessageChannel(Integer id) {
        Optional<MessageChannel> messageChannel = messageChannelRepository.findById(id);
        if (!messageChannel.isPresent()) {
            throw new CustomError(404, "Message Channel not found");
        }

        return messageChannel.get();
    }

    // create Message Channel
    public MessageChannel createMessageChannel(String name, String entryFee, String logoURL, Integer communityId,
            Boolean isDm) {
        Community community = communitySrv.getCommunity(communityId);
        MessageChannel messageChannel = new MessageChannel();
        messageChannel.setName(name);
        messageChannel.setEntryFee(entryFee);
        messageChannel.setLogoURL(logoURL);
        messageChannel.setDm(isDm);
        messageChannel.setCommunity(community);
        return messageChannelRepository.save(messageChannel);
    }

    // edit Message Channel
    public MessageChannel editMessageChannel(Integer id, String name, String entryFee, String logoURL,
            Integer communityId, Boolean isDm) {
        Community community = communitySrv.getCommunity(communityId);
        MessageChannel messageChannel = getMessageChannel(id);
        messageChannel.setName(name);
        messageChannel.setEntryFee(entryFee);
        messageChannel.setLogoURL(logoURL);
        messageChannel.setDm(isDm);
        return messageChannelRepository.save(messageChannel);
    }

    // delete Message Channel
    public MessageChannel deleteMessageChannel(Integer id) {
        MessageChannel messageChannel = getMessageChannel(id);
        messageChannelRepository.delete(messageChannel);
        return messageChannel;
    }

    // get Message Channels in Community
    public List<MessageChannel> getMessageChannelsInCommunity(Integer communityId) {
        Community community = communitySrv.getCommunity(communityId);
        return messageChannelRepository.findByCommunity(community);
    }

}
