package com.limhaekyu.boardproject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.limhaekyu.boardproject.dto.BoardDto;
import com.limhaekyu.boardproject.dto.Criteria;
import com.limhaekyu.boardproject.dto.PaginationDto;
import com.limhaekyu.boardproject.dto.ReplyBoardDto;

@Mapper
public interface BoardMapper {
	void writeBoard(BoardDto boardDto);

	List<BoardDto> selectBoardList(PaginationDto paginationDto);

	BoardDto selectBoardDetail(Long id);

	void deleteBoard(Long id);

	void editBoard(BoardDto boardDto);

	void editBoardPw(BoardDto boardDto);

	List<BoardDto> searchBoardList(@Param("criteria") Criteria criteria, @Param("paginationDto") PaginationDto paginationDtp);

	int getCount();

	BoardDto searchBoardById(Long id);

	List<BoardDto> selectTestBoardList(PaginationDto paginationDto);

	int getSearchCount(@Param("criteria") Criteria criteria);

	BoardDto getLatelyCreatedAt();

	String getPasswordById(Long id);

	void replyBoard(ReplyBoardDto replyBoardDto);

	ReplyBoardDto findParentInfo(Long id);

	void updateStepForReplyBoard(ReplyBoardDto replyBoardDto);

	void updateCommentCnt(Long boardId);

	List<BoardDto> searchBoardListNoPaging(@Param("type") String type, @Param("keyword") String keyword);

	List<BoardDto> selectBoardListNoPaging();
}
