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

import com.subspaceclone.backend.Model.Company;
import com.subspaceclone.backend.Service.CompanySrv;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyCtrl {

    @Autowired
    private CompanySrv companyService;

    // get company by id

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Integer id) {
        return companyService.getCompany(id);
    }

    record CompanyBody(String name, String companyURL, String contactPhone, String logoURL, String contactEmail) {

    }

    // create company
    @PostMapping
    public Company createCompany(@RequestBody CompanyBody companyBody) {
        return companyService.createCompany(companyBody.name, companyBody.companyURL, companyBody.contactPhone,
                companyBody.logoURL, companyBody.contactEmail);
    }

    // edit company
    @PutMapping("/{id}")
    public Company editCompany(@PathVariable Integer id, @RequestBody CompanyBody companyBody) {
        return companyService.editCompany(id, companyBody.name, companyBody.companyURL, companyBody.contactPhone,
                companyBody.logoURL, companyBody.contactEmail);
    }

    // delete company
    @DeleteMapping("/{id}")
    public Company deleteCompany(@PathVariable Integer id) {
        return companyService.deleteCompany(id);
    }

}
