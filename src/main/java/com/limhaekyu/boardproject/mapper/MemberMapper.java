package com.limhaekyu.boardproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.limhaekyu.boardproject.dto.MemberDto;

@Mapper
public interface MemberMapper {
	void signUp(MemberDto memberDto);

	MemberDto selectMemberByMemberId(String memberId);
}
