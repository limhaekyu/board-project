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
public class ReplyBoardDto {
	private Long id;
	private Long RN;
	private String title;
	private String writer;
	private String contents;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	private LocalDateTime deletedAt;
	private boolean isDeleted;
	private String passwordBefore;
	private String passwordAfter;
	private Long groupNo;	// 원글 그룹 번호
	private Integer step;	// 원글 그룹에서의 순번 
	private Integer depth;	// 계층의 깊이 ( 원글 0, 답글 1, 답글에답글 2)
	
	public ReplyBoardDto(String title, String writer, String password, String contents) {
		this.title = title;
		this.writer = writer;
		this.password = password;
		this.contents = contents;
	}
}
