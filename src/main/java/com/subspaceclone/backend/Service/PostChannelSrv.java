package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Model.PostChannel;
import com.subspaceclone.backend.Repository.PostChannelRepo;

@Service
public class PostChannelSrv {
    @Autowired
    private PostChannelRepo postChannelRepository;

    @Autowired
    private CommunitySrv communitySrv;

    // create post Channel
    public PostChannel createPostChannel(String name, String entryFee, String logoURL, Integer communityId,
            Boolean isAnnouncement) {
        Community community = communitySrv.getCommunity(communityId);
        PostChannel postChannel = new PostChannel();
        postChannel.setName(name);
        postChannel.setEntryFee(entryFee);
        postChannel.setLogoURL(logoURL);
        postChannel.setCommunity(community);
        postChannel.setAnnouncement(isAnnouncement);
        return postChannelRepository.save(postChannel);
    }

    // get post channel by id
    public PostChannel getPostChannel(Integer id) {
        Optional<PostChannel> optionalPostChannel = postChannelRepository.findById(id);
        if (!optionalPostChannel.isPresent()) {
            throw new CustomError(404, "Post Channel not found");
        }
        return optionalPostChannel.get();
    }

    // edit post channel by id
    public PostChannel editPostChannel(Integer id, String name, String entryFee, String logoURL, Integer communityId,
            Boolean isAnnouncement) {
        PostChannel postChannel = getPostChannel(id);
        postChannel.setName(name);
        postChannel.setEntryFee(entryFee);
        postChannel.setLogoURL(logoURL);
        postChannel.setAnnouncement(isAnnouncement);
        Community community = communitySrv.getCommunity(communityId);
        postChannel.setCommunity(community);
        return postChannelRepository.save(postChannel);
    }

    // delete post channel by id
    public PostChannel deletePostChannel(Integer id) {
        PostChannel postChannel = getPostChannel(id);
        postChannelRepository.delete(postChannel);
        return postChannel;
    }

    public List<PostChannel> getPostChannelsByCommunity(Integer communityId) {
        Community community = communitySrv.getCommunity(communityId);
        return postChannelRepository.findByCommunity(community);
    }
}
