package com.limhaekyu.boardproject.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.limhaekyu.boardproject.dto.FileDto;

@Mapper
public interface FileMapper {
	void saveFile(FileDto file);

	List<FileDto> findFileByBoardId(Long boardId);

	String findSavedPathById(Long id);
}
