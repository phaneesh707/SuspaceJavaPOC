package com.subspaceclone.backend.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Community {
    @Id
    @SequenceGenerator(name = "community_id_sequence", sequenceName = "community_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "community_id_sequence")
    private Integer id;
    private String name;
    private String descr;
    private String logoURL;
    private String bannerURL;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;
}
