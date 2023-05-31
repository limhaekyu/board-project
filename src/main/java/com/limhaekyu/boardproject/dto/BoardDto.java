package com.limhaekyu.boardproject.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
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
	private Integer commentCnt;
	private MultipartFile[] files;
	
	public BoardDto(String title, String writer, String password, String contents) {
		this.title = title;
		this.writer = writer;
		this.password = password;
		this.contents = contents;
	}

	public BoardDto() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return this.title;
	}

	public String getWriter() {
		return this.writer;
	}

	public String getContents() {
		return this.contents;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRN() {
		return RN;
	}

	public void setRN(Long rN) {
		RN = rN;
	}

	public String getPasswordBefore() {
		return passwordBefore;
	}

	public void setPasswordBefore(String passwordBefore) {
		this.passwordBefore = passwordBefore;
	}

	public String getPasswordAfter() {
		return passwordAfter;
	}

	public void setPasswordAfter(String passwordAfter) {
		this.passwordAfter = passwordAfter;
	}
	public Long getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Integer getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(Integer commentCnt) {
		this.commentCnt = commentCnt;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
}
