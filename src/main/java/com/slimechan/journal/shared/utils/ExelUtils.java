package com.slimechan.journal.shared.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExelUtils {

	public static List<XSSFSheet> parseFile(String path) throws InvalidFormatException, IOException{
		File file = new File(path);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		List<XSSFSheet> sheets = new ArrayList<>();
		for(int id =0; id< wb.getNumberOfSheets(); id++) {
			sheets.add(wb.getSheetAt(id));
		}
		return sheets;
	}
}
