package com.limhaekyu.boardproject.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.limhaekyu.boardproject.dto.PaginationDto;
import com.limhaekyu.boardproject.dto.ReplyBoardDto;
import com.limhaekyu.boardproject.dto.BoardDto;
import com.limhaekyu.boardproject.dto.CommentDto;
import com.limhaekyu.boardproject.dto.Criteria;
import com.limhaekyu.boardproject.service.BoardService;
import com.limhaekyu.boardproject.service.CommentService;

@Controller
public class BoardController {

	private final BoardService boardService;
	private final CommentService commentService;

	@Autowired
	public BoardController(BoardService boardService, CommentService commentService) {
		this.boardService = boardService;
		this.commentService = commentService;
	}

	@GetMapping(value = { "/", "/board" })
	public String viewBoardList(@RequestParam(value="type", required = false) String type, @RequestParam(value="keyword", required = false) String keyword, Model model, @RequestParam(value = "page", defaultValue = "1") final int page) {
		List<BoardDto> boardList = new ArrayList<>();
		if (keyword != null) {
			Criteria criteria = new Criteria(type, keyword);
			PaginationDto paginationDto = new PaginationDto(boardService.getSearchCount(criteria), page);

			boardList = boardService.searchBoardList(criteria, paginationDto);
			model.addAttribute("pageDto", paginationDto);
			model.addAttribute("criteria", criteria);
		} else {
			PaginationDto paginationDto = new PaginationDto(boardService.getCount(), page);
			boardList = boardService.selectBoardList(paginationDto);
			model.addAttribute("pageDto", paginationDto);
			model.addAttribute("criteria", new Criteria());	
		}
		model.addAttribute("boardList", boardList);
		return "/page/home";
	}

	@GetMapping("/board/write")
	public String viewAddBoard(Model model) {
		model.addAttribute("boardDto", new BoardDto());
		return "page/writeBoard";
	}
	
	@PostMapping(value = "/board/write", produces="application/json;charset=UTF-8")
	public String writeBoard(@RequestBody BoardDto boardDto, Model model, HttpServletResponse response) {
		
		Map<String, String> errors = new HashMap<>();
		
		if (boardService.validateWord(boardDto.getTitle())) {
			response.addHeader("bad-word-title", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}
		
		if (boardService.validateWord(boardDto.getWriter())) {
			response.addHeader("bad-word-writer", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}

		if (boardService.validateWord(boardDto.getContents())) {
			response.addHeader("bad-word-contents", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}
/*
		if (!boardService.chkCoolTimeWriteBoard()) {
			response.addHeader("cooltime", "1");
			errors.put("cooltime", "60초가 지나지 않았습니다.");
		} else {
			response.addHeader("cooltime", "0");
		}
*/
		// 검증에 실패하면 다시 입력 폼으로
		if (!errors.isEmpty()) {
			return "page/writeBoard";
		} else {
			// 성공 로직
			boardService.writeBoard(boardDto);
			return "redirect:/";
		}

	}
	@GetMapping("/board/{id}/reply")
	public String viewReplyBoard(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("id", id);
		return "/page/replyBoard";
	}

	@PostMapping(value = "/board/{id}/reply", produces="application/json;charset=UTF-8")
	public String replyBoard(@PathVariable(value = "id") Long id, @RequestBody ReplyBoardDto replyBoardDto, Model model, HttpServletResponse response) {
		
		Map<String, String> errors = new HashMap<>();
		
		if (boardService.validateWord(replyBoardDto.getTitle())) {
			response.addHeader("bad-word-title", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}
		
		if (boardService.validateWord(replyBoardDto.getWriter())) {
			response.addHeader("bad-word-writer", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}

		if (boardService.validateWord(replyBoardDto.getContents())) {
			response.addHeader("bad-word-contents", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}
/*
		if (!boardService.chkCoolTimeWriteBoard()) {
			response.addHeader("cooltime", "1");
			errors.put("cooltime", "60초가 지나지 않았습니다.");
		} else {
			response.addHeader("cooltime", "0");
		}
*/
		// 검증에 실패하면 다시 입력 폼으로
		if (!errors.isEmpty()) {
			return "page/writeBoard";
		} else {
			// 성공 로직
			boardService.replyBoard(id, replyBoardDto);
			return "redirect:/";
		}

	}
	
	@GetMapping("/board/{id}")
	public String selectBoardDetail(@PathVariable(value = "id") Long boardId, Model model) {
		BoardDto boardInfo = boardService.selectBoardDetail(boardId);
		List<CommentDto> commentList = commentService.findCommentByBoardId(boardId);

		model.addAttribute("boardInfo", boardInfo);
		model.addAttribute("commentList", commentList);
		return "page/detailBoard";
	}

	@PostMapping("/board/{id}")
	public RedirectView deleteBoard(@PathVariable(value = "id") Long id) {
		boardService.deleteBoard(id);
		return new RedirectView("/");
	}

	@GetMapping("/board/{id}/edit")
	public String viewEditBoard(@PathVariable(value = "id") Long id, Model model) {
		BoardDto boardInfo = boardService.selectBoardDetail(id);
		model.addAttribute("boardInfo", boardInfo);
		return "page/editBoard";
	}

	@PostMapping("/board/{id}/edit")
	public String editBoard(@PathVariable(value = "id") Long id, @RequestBody BoardDto boardDto, Model model, HttpServletResponse response) {
		// 검증 오류 결과를 보관
		Map<String, String> errors = new HashMap<>();
		boardDto.setId(id);
		// 단순 검증 로직
		if (boardService.validateWord(boardDto.getTitle())) {
			response.addHeader("bad-word-title", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}
		
		if (boardService.validateWord(boardDto.getWriter())) {
			response.addHeader("bad-word-writer", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}

		if (boardService.validateWord(boardDto.getContents())) {
			response.addHeader("bad-word-contents", "1");
			errors.put("contents", "비속어가 감지되었습니다.");
		}
		if (boardDto.getPasswordAfter() != null) {
			if (!boardService.validPassword(boardDto)) {
				response.addHeader("valid-pw", "1");
				errors.put("valid_pw", "비밀번호가 틀렸습니다.");
			}		
		}
		// 검증에 실패하면 다시 입력 폼으로
		if (!errors.isEmpty()) {
			model.addAttribute("boardInfo", boardDto);
			return "page/editBoard";
		} else {
			// 성공 로직
			boardService.editBoard(boardDto);
			return "redirect:/board/{id}";
		}
	}
	
	@PostMapping("/board/{id}/comment")
	public String addComment(@PathVariable(value = "id") Long id, @RequestBody CommentDto commentDto) {
		commentService.addComment(id, commentDto);
		return "redirect:/board/{id}";
	}
	
	@PostMapping("/board/{id}/comment/{commentId}")
	public String deleteComment(@PathVariable(value = "id") Long boardId, @PathVariable(value = "commentId") Long commentId) {
		commentService.deleteComment(boardId, commentId);
		return "redirect:/board/{id}";

	}
}
