package com.limhaekyu.boardproject.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {
	private Long id;
	private String writer;
	private String password;
	private String contents;
	private boolean is_deleted;
	private LocalDateTime createdAt;
	private LocalDateTime deletedAt;
	private Long boardId;

}
