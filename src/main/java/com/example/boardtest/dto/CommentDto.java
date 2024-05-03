package com.example.boardtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String content;
    private Long boardId;
    private String username;
}
