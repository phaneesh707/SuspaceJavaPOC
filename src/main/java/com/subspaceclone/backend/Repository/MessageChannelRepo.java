package com.subspaceclone.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Model.MessageChannel;

public interface MessageChannelRepo extends JpaRepository<MessageChannel, Integer> {
    List<MessageChannel> findByCommunity(Community community);
}
