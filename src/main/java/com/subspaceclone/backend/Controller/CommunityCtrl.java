package com.subspaceclone.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.Community;
import com.subspaceclone.backend.Service.CommunitySrv;

@RestController
@RequestMapping("/api/v1/community")
public class CommunityCtrl {

    @Autowired
    private CommunitySrv communityService;

    // get communities by company Id
    @GetMapping("/company/{companyId}")
    public List<Community> getCommunitiesByCompanyId(@PathVariable Integer companyId) {
        return communityService.getCommunitiesByCompanyId(companyId);
    }

    // get community by Id
    @GetMapping("/{communityId}")
    public Community getCommunityById(@PathVariable Integer communityId) {
        return communityService.getCommunity(communityId);
    }

    // create community
    record CommunityRequest(Integer companyId, String name, String descr, String logoURL, String bannerURL) {
    }

    @PostMapping()
    public Community createCommunity(@RequestBody CommunityRequest communityRequest) {
        return communityService.createCommunity(communityRequest.companyId, communityRequest.name,
                communityRequest.descr, communityRequest.logoURL, communityRequest.bannerURL);
    }

    // update community
    record CommunityUpdateRequest(String name, String descr, String logoURL, String bannerURL) {
    }

    @PutMapping("/{communityId}")
    public Community updateCommunity(@PathVariable Integer communityId,
            @RequestBody CommunityUpdateRequest communityUpdateRequest) {
        return communityService.editCommunity(communityId, communityUpdateRequest.name, communityUpdateRequest.descr,
                communityUpdateRequest.logoURL, communityUpdateRequest.bannerURL);
    }

    // delete community
    @DeleteMapping("/{communityId}")
    public Community deleteCommunity(@PathVariable Integer communityId) {
        return communityService.deleteCommunity(communityId);
    }
}
