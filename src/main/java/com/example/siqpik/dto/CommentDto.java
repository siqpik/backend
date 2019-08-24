package com.example.siqpik.dto;

import com.example.siqpik.domain.Comment;

public class CommentDto {

    private String userName;
    private String comment;

    public CommentDto(Comment comment) {
        userName = comment.getUser().getUserName();
        this.comment = comment.getCommentary();
    }

    public String getUserName() {
        return userName;
    }

    public String getComment() {
        return comment;
    }
}
