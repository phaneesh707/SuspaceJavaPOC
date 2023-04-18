package com.subspaceclone.backend.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

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
public class Post {

    @Id
    @SequenceGenerator(name = "post_id_sequence", sequenceName = "post_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_sequence")
    private Integer id;

    private String title;
    private String content;
    private String imgURL;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post parent;

    @ManyToOne
    @JoinColumn(name = "post_channel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PostChannel postChannel;
}
