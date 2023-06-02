package com.limhaekyu.boardproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Criteria {
    /* 검색 타입 */
	private String type;
	
	/* 검색 키워드 */
	private String keyword;

}
