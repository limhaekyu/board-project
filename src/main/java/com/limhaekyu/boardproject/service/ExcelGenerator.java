package com.limhaekyu.boardproject.service;

import com.limhaekyu.boardproject.dto.BoardDto;

import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator {
	public static Workbook generateExcel(List<BoardDto> boardList) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("BoardList");
		
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("번호");
		headerRow.createCell(1).setCellValue("제목");
		headerRow.createCell(2).setCellValue("작성자");
		headerRow.createCell(3).setCellValue("내용");
		headerRow.createCell(4).setCellValue("생성일");
		
		int rowNum = 1;
		for (BoardDto board : boardList) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(board.getRN());
			row.createCell(1).setCellValue(board.getTitle());
			row.createCell(2).setCellValue(board.getWriter());
			row.createCell(3).setCellValue(board.getContents());
			row.createCell(4).setCellValue(board.getCreatedAt());	
		}
		
		return workbook;
	}
}
