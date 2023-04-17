package com.subspaceclone.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subspaceclone.backend.Model.Community;
import java.util.List;

public interface CommunityRepo extends JpaRepository<Community, Integer> {
    List<Community> findByCompanyId(Integer companyId);
}
