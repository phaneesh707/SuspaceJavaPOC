package com.subspaceclone.backend.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWrapper {
    private Post post;
    private List<Post> replies;

}
