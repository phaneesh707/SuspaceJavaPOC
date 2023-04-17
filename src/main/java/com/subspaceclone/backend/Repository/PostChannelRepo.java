package com.subspaceclone.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Model.PostChannel;

public interface PostChannelRepo extends JpaRepository<PostChannel, Integer> {
    List<PostChannel> findByCommunity(Community community);
}
