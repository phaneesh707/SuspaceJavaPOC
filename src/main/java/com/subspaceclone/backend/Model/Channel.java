package com.subspaceclone.backend.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
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

    @Id
    @SequenceGenerator(name = "channel_id_sequence", sequenceName = "channel_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_id_sequence")
    private Integer id;
    private String name;
    private String entryFee;
    private String logoURL;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Community community;
}
