package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Model.Company;
import com.subspaceclone.backend.Repository.CommunityRepo;

@Service
public class CommunitySrv {

    @Autowired
    private CommunityRepo communityRepository;

    @Autowired
    private CompanySrv companySrv;

    // get community by id
    public Community getCommunity(Integer id) {
        Optional<Community> community = communityRepository.findById(id);
        if (!community.isPresent()) {
            throw new CustomError(404, "Community not found");
        }
        return community.get();
    }

    // get communities by companyId
    public List<Community> getCommunitiesByCompanyId(Integer companyId) {
        Company company = companySrv.getCompany(companyId);

        return communityRepository.findByCompanyId(companyId);
    }

    // create community
    public Community createCommunity(Integer companyId, String name, String descr, String logoURL, String bannerURL) {
        Company company = companySrv.getCompany(companyId);

        Community community = new Community();
        community.setCompany(company);
        community.setName(name);
        community.setDescr(descr);
        community.setLogoURL(logoURL);
        community.setBannerURL(bannerURL);
        return communityRepository.save(community);
    }

    // edit community by id
    public Community editCommunity(Integer id, String name, String descr, String logoURL, String bannerURL) {
        Community community = getCommunity(id);
        community.setName(name);
        community.setDescr(descr);
        community.setLogoURL(logoURL);
        community.setBannerURL(bannerURL);
        return communityRepository.save(community);
    }

    // delete community by id
    public Community deleteCommunity(Integer id) {
        Community community = getCommunity(id);

        communityRepository.delete(community);
        return community;
    }
}
