package com.limhaekyu.boardproject.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {

	// 페이지당 보여지는 게시물 수
	private int rowCount = 6; 
	// 한블럭에 보여줄 페이지 개수
	private int pageCount = 4; 
	// 총 게시물 수
	private int totalCount; 
	// 현재 페이지
	private int page; 

	// 한 블럭 시작 페이지
	private int startPage; 
	// 한 블럭 끝 페이지
	private int endPage; 
	
	// 총 페이지 개수
	private int totalPageCount;
	

	// 다음 페이지로 이동하는 버튼 유무
	private boolean isPrev = false; 

	// 이전 페이지로 이동하는 버튼 유무
	private boolean isNext = false;
	
	// 얼마나 끊을 것인지
	private int offset; 
	
	public PaginationDto(final int totalCount, final int page) {
		// 총 페이지 개수 구하기
		setTotalPageCount(totalCount, this.rowCount); 

		// 한 블럭의 첫 페이지 구하기
		setStartPage(page, this.pageCount);

		// 한 블럭의 끝 페이지 구하기
		setEndpage(this.startPage, this.pageCount, this.totalPageCount); 

		// 이전 블록 버튼 유무 판별하기
		isPrev(page, this.pageCount); 

		// 다음 블록 버튼 유무 판별하기
		isNext(this.endPage, this.totalPageCount); 

		// offset 구하기
		setOffset(page, this.rowCount); 
	}
	
	// 총 페이지 개수 구하기
    private void setTotalPageCount(final int totalCount, final int rowCount) {
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    }
    
    // 한 블럭의 첫 페이지 구하기
    private void setStartPage(final int page, final int pageCount) {
        this.startPage = 1 + (((page - 1) / pageCount) * pageCount);
    }


    // 한 블럭의 끝 페이지 구하기
    private void setEndpage(final int startPage, final int pageCount, final int totalPageCount) {
        this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
    }


    // 이전 블럭으로 이동할 버튼 생성 유무
    private void isPrev(final int page, final int pageCount) {
        this.isPrev = 1 < ((page * 1.0) / pageCount);
    }


    // 다음 블럭으로 이동할 버튼 생성 유무
    private void isNext(final int endPage, final int totalPageCount) {
        this.isNext = endPage < totalPageCount;
    }


    // offset 구하기 // 쿼리 select 시 끊어서 가져오기
    private void setOffset(final int page, final int rowCount) {
        this.offset = (page - 1) * rowCount;
    }

	public int getTotalCount() {
		return totalCount;
	}
	public int getPage() {
		return page;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public boolean getIsPrev() {
		return isPrev;
	}
	public boolean isNext() {
		return isNext;
	}
	public int getOffset() {
		return offset;
	}
}
