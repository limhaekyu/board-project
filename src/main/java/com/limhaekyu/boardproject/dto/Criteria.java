package com.limhaekyu.boardproject.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
    /* 검색 타입 */
	private String type;
	
	/* 검색 키워드 */
	private String keyword;
	
	
	public Criteria(String type, String keyword) {
		this.type = type;
		this.keyword = keyword;
	}

	public Criteria() {
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
