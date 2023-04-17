package com.subspaceclone.backend.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "CHANNEL_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Channel {

    private Integer id;
    private String name;
    private String entryFee;
    private String logoURL;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Community community;
}
