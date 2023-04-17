package com.subspaceclone.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.Message;
import com.subspaceclone.backend.Model.MessageChannel;

public interface MessageRepo extends JpaRepository<Message, Integer> {
    List<Message> findByMessageChannel(MessageChannel channel);

}
