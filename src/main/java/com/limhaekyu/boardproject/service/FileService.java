package com.limhaekyu.boardproject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import com.limhaekyu.boardproject.dto.FileDto;
import com.limhaekyu.boardproject.mapper.FileMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	private final FileMapper fileMapper;
	
	public FileService (FileMapper fileMapper) {
		this.fileMapper = fileMapper;
	}
	
	public void uploadFile(MultipartFile[] files, Long boardId) {
		String uploadPath = "C:"+File.separator+"file";
		for (MultipartFile file : files) {
			String originalName = file.getOriginalFilename();
			
			String uuid = UUID.randomUUID().toString();
			
			String extension = originalName.substring(originalName.lastIndexOf("."));
			
			String savedName = uuid + extension;
			
			String savedPath = uploadPath+ File.separator+ savedName;
			
			Long fileSize = file.getSize();
			
			FileDto fileDto = new FileDto(originalName, savedName, savedPath, fileSize, boardId);
			
			Path savePath = Paths.get(savedPath);
		
			try {
				file.transferTo(savePath);
				saveFile(fileDto);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Transactional
	private void saveFile(FileDto file) {
		fileMapper.saveFile(file);
	}

	public List<FileDto> findFileByBoardId(Long boardId) {
		return fileMapper.findFileByBoardId(boardId);
	}
}
