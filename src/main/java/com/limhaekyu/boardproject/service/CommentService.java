package com.limhaekyu.boardproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limhaekyu.boardproject.dto.CommentDto;
import com.limhaekyu.boardproject.mapper.BoardMapper;
import com.limhaekyu.boardproject.mapper.CommentMapper;
import java.util.Map;
import java.util.HashMap;

@Service
public class CommentService {
	private final CommentMapper commentMapper;
	private final BoardMapper boardMapper;
	
	public CommentService(CommentMapper commentMapper, BoardMapper boardMapper) {
		this.commentMapper = commentMapper;
		this.boardMapper = boardMapper;
	}

	@Transactional
	public void addComment(Long boardId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		commentDto.setBoardId(boardId);
		commentMapper.addComment(commentDto);
		boardMapper.updateCommentCnt(boardId);
	}

	public List<CommentDto> findCommentByBoardId(Long boardId) {
		return commentMapper.findCommentByBoardId(boardId);
	}
	
	@Transactional
	public void deleteComment(Long boardId, Long commentId) {
		Map<String, Long> commentInfo = new HashMap<>();
		commentInfo.put("commentId", commentId);
		commentInfo.put("boardId", boardId);
		commentMapper.deleteComment(commentInfo);
		boardMapper.updateCommentCnt(boardId);
	}
	
	
}
