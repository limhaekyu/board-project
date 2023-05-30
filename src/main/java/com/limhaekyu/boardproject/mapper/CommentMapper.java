package com.limhaekyu.boardproject.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.limhaekyu.boardproject.dto.CommentDto;

@Mapper
public interface CommentMapper {

	void addComment(CommentDto commentDto);

	List<CommentDto> findCommentByBoardId(Long boardId);

	void deleteComment(Map<String, Long> commentInfo);

}
