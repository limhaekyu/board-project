package com.limhaekyu.boardproject.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.limhaekyu.boardproject.config.SecurityConfig;
import com.limhaekyu.boardproject.dto.MemberDto;
import com.limhaekyu.boardproject.mapper.MemberMapper;

@Service
public class MemberService {
	
	private final MemberMapper memberMapper;
	private final SecurityConfig securityConfig;
	
	@Autowired
	public MemberService(MemberMapper memberMapper, SecurityConfig securityConfig) {
		this.memberMapper = memberMapper;
		this.securityConfig = securityConfig;
	}

	@Transactional
	public void signUp(MemberDto memberDto) {
		memberDto.setPassword(encodePassword(memberDto.getPassword()));
		memberMapper.signUp(memberDto);
	}
	
	private String encodePassword(String password) {
		return securityConfig.passwordEncoder().encode(password);
	}

	public String login(MemberDto memberDto, HttpSession session) {
		MemberDto memberInfo = memberMapper.selectMemberByMemberId(memberDto.getMemberId());
		if(validateLogin(memberDto.getPassword() ,memberInfo.getPassword())) {
			session.setAttribute("id", memberInfo.getId());
			session.setAttribute("memberId", memberInfo.getMemberId());
			session.setAttribute("name", memberInfo.getName());
			return "redirect:/";
		}
		else {
			return "redirect:/login";
		}
	}
	
	private boolean validateLogin(String password, String encodedPassword) {
		return securityConfig.passwordEncoder().matches(password, encodedPassword);
	}

	
	
	
}
