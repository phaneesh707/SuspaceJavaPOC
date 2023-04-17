package com.subspaceclone.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByCommunityId(Integer communityId);

    List<Post> findByUserId(Integer userId);

    List<Post> findByParentId(Integer parentId);

    List<Post> findByPostChannelId(Integer postChannelId);
}
