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
public class FileDto {
	private Long id;
	private String originalName;
	private String savedName;
	private String savedPath;
	private Long fileSize;
	private Long boardId;
	private LocalDateTime savedAt;
	
	public FileDto(String originalName, String savedName, String savedPath, Long fileSize, Long boardId) {
		this.originalName = originalName;
		this.savedName = savedName;
		this.savedPath = savedPath;
		this.fileSize = fileSize;
		this.boardId = boardId;
	}
}
