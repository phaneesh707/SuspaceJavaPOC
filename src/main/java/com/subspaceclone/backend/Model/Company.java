package com.subspaceclone.backend.Model;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @SequenceGenerator(name = "company_id_sequence", sequenceName = "company_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_sequence")
    private Integer id;
    private String name;
    private String companyURL;
    private String contactEmail;
    private String contactPhone;
    private String logoURL;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private String token = UUID.randomUUID().toString();

}
