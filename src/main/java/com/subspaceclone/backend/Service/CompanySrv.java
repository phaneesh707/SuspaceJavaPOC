package com.subspaceclone.backend.Service;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Company;
import com.subspaceclone.backend.Repository.CompanyRepo;

@Service
public class CompanySrv {
    @Autowired
    private CompanyRepo companyRepository;

    public Company createCompany(String name, String companyURL, String contactPhone, String logoURL,
            String contactEmail) {
        Company newCompany = Company.builder()
                .name(name)
                .companyURL(companyURL)
                .contactPhone(contactPhone)
                .contactEmail(contactEmail)
                .logoURL(logoURL)
                .build();
        return companyRepository.save(newCompany);
    }

    public Company getCompany(Integer id) {

        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            throw new CustomError(404, "Company not found");
        }
        return company.get();
    }

    // edit Company details
    public Company editCompany(Integer id, String name, String companyURL, String contactPhone, String logoURL,
            String contactEmail) {
        Company company = getCompany(id);
        company.setName(name);
        company.setCompanyURL(companyURL);
        company.setContactPhone(contactPhone);
        company.setLogoURL(logoURL);
        company.setContactEmail(contactEmail);
        return companyRepository.save(company);
    }

    // delete company by id
    public Company deleteCompany(Integer id) {
        Company company = getCompany(id);

        companyRepository.delete(company);
        return company;
    }

    // get all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
