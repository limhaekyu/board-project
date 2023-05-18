package com.limhaekyu.boardproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.limhaekyu.boardproject.dto.MemberDto;
import com.limhaekyu.boardproject.service.MemberService;

@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/sign-up")
	public String viewSignUp(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "page/signUp";
	}
	
	@PostMapping("/sign-up")
	public String signUp(MemberDto memberDto) {
		memberService.signUp(memberDto);
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String viewLogin(Model model) {
		model.addAttribute("memberDto", new MemberDto());
		return "page/login";
	}
	
	@PostMapping("/login")
	public String login(MemberDto memberDto, HttpSession session){
		return memberService.login(memberDto, session);
	}
}
