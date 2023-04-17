package com.subspaceclone.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.subspaceclone.backend.Model.Channel;

public interface ChannelRepo extends JpaRepository<Channel, Integer> {
    @Query("SELECT c FROM Channel c WHERE TYPE(c)=:channelType AND c.community.id=:communityId")
    List<Channel> findByChannelTypeAndCommunityId(@Param("channelType") String channelType,
            @Param("community") Integer communityId);

    List<Channel> findByCommunityId(Integer communityId);
}