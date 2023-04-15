package com.subspaceclone.backend.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String photoURL;
    private String desc;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;

}
