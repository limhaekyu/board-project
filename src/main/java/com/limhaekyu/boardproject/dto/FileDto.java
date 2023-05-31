package com.limhaekyu.boardproject.dto;

import java.time.LocalDateTime;

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getSavedName() {
		return savedName;
	}
	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}
	public String getSavedPath() {
		return savedPath;
	}
	public void setSavedPath(String savedPath) {
		this.savedPath = savedPath;
	}
	public Long getBoardId() {
		return boardId;
	}
	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}
	public LocalDateTime getSavedAt() {
		return savedAt;
	}
	public void setSavedAt(LocalDateTime savedAt) {
		this.savedAt = savedAt;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
