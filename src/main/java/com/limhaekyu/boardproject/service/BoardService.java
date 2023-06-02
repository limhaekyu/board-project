package com.limhaekyu.boardproject.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limhaekyu.boardproject.dto.BoardDto;
import com.limhaekyu.boardproject.dto.Criteria;
import com.limhaekyu.boardproject.dto.PaginationDto;
import com.limhaekyu.boardproject.dto.ReplyBoardDto;
import com.limhaekyu.boardproject.mapper.BoardMapper;

@Service
public class BoardService {
	private final BoardMapper boardMapper;

	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Transactional
	public void writeBoard(BoardDto boardDto) {
		try {
			boardMapper.writeBoard(boardDto);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardDto> selectBoardList(PaginationDto paginationDto) {
		return boardMapper.selectBoardList(paginationDto);
	}

	public BoardDto selectBoardDetail(Long id) {
		return boardMapper.selectBoardDetail(id);
	}

	@Transactional
	public void deleteBoard(Long id) {
		try {
			boardMapper.deleteBoard(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void editBoard(BoardDto boardDto) {
		try {
			if (boardDto.getPasswordAfter() != null) {
				boardMapper.editBoardPw(boardDto);
			} else {
				boardMapper.editBoard(boardDto);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardDto> searchBoardList(Criteria criteria, PaginationDto paginationDto) {
		return boardMapper.searchBoardList(criteria, paginationDto);
	}

	// 페이징을 위한 전체 데이터 개수 파악
	public int getCount() {
		return this.boardMapper.getCount();
	}

	public boolean checkPassword(String password, Long id) {
		BoardDto resultBoard = boardMapper.searchBoardById(id);
		if(password.equals(resultBoard.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateWord(String text) {
		String[] badWord = {"바보", "멍청이", "개자식", "미친", "새끼", "병신"};
		for (int i = 0; i<badWord.length; i++) {
			if (text.contains(badWord[i])) {
				return true;
			}
		}
		return false;
	}

	public List<BoardDto> selectTestBoardList(PaginationDto paginationDto) {
		return boardMapper.selectTestBoardList(paginationDto);
	}

	public int getSearchCount(Criteria criteria) {
		return this.boardMapper.getSearchCount(criteria);
	}

	public boolean chkCoolTimeWriteBoard() {
		LocalDateTime latelyCreatedAt = boardMapper.getLatelyCreatedAt().getCreatedAt();
		Duration duration = Duration.between(latelyCreatedAt.toLocalTime(), LocalDateTime.now().toLocalTime());
		long minute = duration.toMinutes();
		if (minute > 0) {
			return true;
		}else {
			return false;
		}
	}

	public boolean validPassword(BoardDto boardDto) {
		String currentPw = boardMapper.getPasswordById(boardDto.getId());
		if (currentPw.equals(boardDto.getPasswordBefore())) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public void replyBoard(Long id, ReplyBoardDto replyBoardDto) {
		ReplyBoardDto parentInfo = findParentInfo(id);
		setChildBoardInfo(parentInfo, replyBoardDto);
		boardMapper.updateStepForReplyBoard(replyBoardDto);
		boardMapper.replyBoard(replyBoardDto);
	}
	
	private ReplyBoardDto setChildBoardInfo(ReplyBoardDto parentInfo, ReplyBoardDto replayBoardDto) {
		replayBoardDto.setGroupNo(parentInfo.getGroupNo());
		replayBoardDto.setStep(parentInfo.getStep() + 1);
		replayBoardDto.setDepth(parentInfo.getDepth() + 1);
		return replayBoardDto;
	}
	
	private ReplyBoardDto findParentInfo(Long id) {
		return boardMapper.findParentInfo(id);
	}

	public List<BoardDto> searchBoardListNoPaging(String type, String keyword) {
		return boardMapper.searchBoardListNoPaging(type, keyword);
	}

	public List<BoardDto> selectBoardListNoPaging() {
		return boardMapper.selectBoardListNoPaging();
	}
	
	public boolean chkNullText(String text) {
		if (text == null || text == "" || text.isBlank() || text.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
}
