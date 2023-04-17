package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Channel;
import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Repository.ChannelRepo;

@Service
public class ChannelSrv {

    @Autowired
    private ChannelRepo channelRepository;

    @Autowired
    private CommunitySrv communitySrv;

    // find channels by type and community
    public List<Channel> findByChannelTypeAndCommunity(String channelType, Integer community) {
        return channelRepository.findByChannelTypeAndCommunityId(channelType, community);
    }

    // get Channel By id
    public Channel getChannel(Integer id) {
        Optional<Channel> optionalChannel = channelRepository.findById(id);
        if (!optionalChannel.isPresent()) {
            throw new CustomError(404, "Channel not found");
        }
        return optionalChannel.get();
    }

    // edit channel by id
    public Channel editChannel(Integer id, String name, String entryFee, String logoURL) {
        Channel channel = getChannel(id);
        channel.setName(name);
        channel.setEntryFee(entryFee);
        channel.setLogoURL(logoURL);
        return channelRepository.save(channel);
    }

    // delete channel by id
    public Channel deleteChannel(Integer id) {
        Channel channel = getChannel(id);
        channelRepository.delete(channel);
        return channel;
    }

    // get channels by community
    public List<Channel> getChannelsByCommunity(Integer community) {
        Community communit = communitySrv.getCommunity(community);
        return channelRepository.findByCommunityId(community);
    }
}
