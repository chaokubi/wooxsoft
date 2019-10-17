package com.mip.software.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	/**
	 * 通过poi从excel中获取List<LinkedHashMap<String, String>>
	 * 
	 * @param InputStream
	 *            文件流
	 * @param startRow
	 *            从多少行开始 默认传-1 保证每行都能读取到
	 * @param startCol
	 *            从多少列开始 默认传-1 保证每列都能读取到
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static List<LinkedHashMap<String, String>> readExcel(
			InputStream inputStream, int startRow, int startCol,int sheetno)
			throws FileNotFoundException, IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		System.out.println("开始读excel:" + simpleDateFormat.format(new Date()));
		List<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		// excel表头
		LinkedHashMap<Short, String> tableMap = new LinkedHashMap<Short, String>();
		org.apache.poi.ss.usermodel.Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(inputStream);
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		}

		// 创建工作表
		org.apache.poi.ss.usermodel.Sheet sheet = workBook.getSheetAt(sheetno);
		int rows = sheet.getPhysicalNumberOfRows(); // 获得行数
		sheet.getMargin(HSSFSheet.TopMargin);
		int columncount = sheet.getRow(0).getLastCellNum(); // 表头的列数
		for (int j = 0; j < rows; j++) {// 行循环
			org.apache.poi.ss.usermodel.Row row = sheet.getRow(j);
			if (j == 0) {
				if (row != null && row.getCell(0) != null
						&& row.getCell(0).toString().length() > 0) {
					// int cells = row.getLastCellNum();// 获得列数
					for (short k = 0; k < columncount; k++) {
						tableMap.put(k, row.getCell(k).toString().trim());
					}
				}
			}
			if ((j != startRow) && (j != 0)) {
				LinkedHashMap<String, String> linkmap = new LinkedHashMap<String, String>();
//				if (row != null && row.getCell(1) != null
//						&& row.getCell(1).toString().length() > 0) {
				if (row != null) {
					// int cells = row.getLastCellNum();// 获得列数
					for (short k = 0; k < columncount; k++) { // 列循环
						if (k != startCol) {
							org.apache.poi.ss.usermodel.Cell cell = row
									.getCell(k);
							// /////////////////////
							String value = "";
							if (cell != null && cell.toString().length() > 0) {
								switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
									if (HSSFDateUtil.isCellDateFormatted(cell)) {
										// 如果是date类型则 ，获取该cell的date值
										value = HSSFDateUtil.getJavaDate(
												cell.getNumericCellValue())
												.toString();
									} else {// 纯数字
										value = String.valueOf(cell
												.getNumericCellValue());
										if (cell.getNumericCellValue() > (long) cell
												.getNumericCellValue()) {
											value = String.valueOf(cell
													.getNumericCellValue());
										} else {
											value = String.valueOf((long) cell
													.getNumericCellValue());
										}
									}
									break;
								/* 此行表示单元格的内容为string类型 */
								case HSSFCell.CELL_TYPE_STRING: // 字符串型
									value = cell.getRichStringCellValue()
											.toString();
									break;
								case HSSFCell.CELL_TYPE_FORMULA:// 公式型
									// 读公式计算值
									value = String.valueOf(cell
											.getNumericCellValue());
									if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串

										value = cell.getRichStringCellValue()
												.toString();
									}
									// cell.getCellFormula();读公式
									break;
								case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
									value = " " + cell.getBooleanCellValue();
									break;
								/* 此行表示该单元格值为空 */
								case HSSFCell.CELL_TYPE_BLANK: // 空值
									value = "";
									break;
								case HSSFCell.CELL_TYPE_ERROR: // 故障
									value = "";
									break;
								default:
									value = cell.getRichStringCellValue()
											.toString();
								}
							} else {
							}
							linkmap.put(tableMap.get(k).trim(), value.trim());
						}
					}
					list.add(linkmap);
				}
			}
		}
		System.out.println("读excel结束:" + simpleDateFormat.format(new Date()));
		return list;
	}
	
	/**
	 * 判断是否增加多个sheet
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	public static void sheets(HttpServletRequest request,HttpServletResponse response,List<LinkedHashMap<String, Object>> sheetList, String username,
			String fileName, String herderinfo, List<String> colsList,
			List<String> colsListWidth) throws Exception {
		WritableWorkbook wwb = null;
		Workbook workbook = null;
		String path = request.getSession().getServletContext().getRealPath(
				"//resources//template//exporttemplate.xls");
		File file = new File(path);
		String targetPath=request.getSession().getServletContext().getRealPath("//resources//template//target//");
		File targetFile = new File(targetPath+"//"+fileName+".xls"); 
		if(targetFile.exists()){
			targetFile.delete();
		}
		// 源文件读入
		workbook = Workbook.getWorkbook(file);  
	    //目标文件先引入template中的内容，并将以targetFile导出  
	    wwb = Workbook.createWorkbook(targetFile, workbook);  
		// 创建多个sheet
		Integer pagecount = 0;
		if (sheetList.size() % 40000 == 0) {
			pagecount = sheetList.size() / 40000;
		} else {
			pagecount = sheetList.size() / 40000 + 1;
		}
		if (sheetList != null) {
			if (sheetList.size() > 0) {
				for (int i = 0; i < pagecount; i++) {
					if (i != (pagecount - 1)) {
						// 创建sheet的sheetList
						List<LinkedHashMap<String, Object>> list = sheetList
								.subList(40000 * i, 40000 * (i + 1));
						Sheet sheetOfCreat =getSheet(request, response,
								list, username, i + 1, wwb, herderinfo,
								colsList, colsListWidth);
					} else {
						// 最后一页
						List<LinkedHashMap<String, Object>> list = sheetList
								.subList(40000 * i, sheetList.size());
						Sheet sheetOfCreat =getSheet(request, response,
								list, username, i + 1, wwb, herderinfo,
								colsList, colsListWidth);
					}
				}
			} else {
				// 没有数据
				Sheet sheetOfCreat =getSheet(request, response,
						sheetList, username, 1, wwb, herderinfo, colsList,
						colsListWidth);
			}
		}
		wwb.write();
		wwb.close();
		workbook.close();
		FileUtils.downloadFile(targetPath, fileName+".xls", request, response);
	}
	
	/**
	 * 创建文本框sheet
	 */
	public static Sheet getSheet(HttpServletRequest request,
			HttpServletResponse response,
			List<LinkedHashMap<String, Object>> sheetList, String username,
			int pageSize, WritableWorkbook workbook, String herderinfo,
			List<String> colsList, List<String> colsListWidth)
			throws RowsExceededException, WriteException {
		// 单元格样式
		WritableCellFormat headerFormat = new WritableCellFormat();
		// 水平居中对齐
		headerFormat.setAlignment(Alignment.CENTRE);
		// 设置标题信息
		WritableSheet sheet = workbook.createSheet("第" + pageSize + "页",
				pageSize-1);
		Label herder = new Label(0, 0, herderinfo, getHeader());
		// 合并单元格
		sheet.mergeCells(0, 0, colsList.size() - 1, 0);
		sheet.mergeCells(0, 1, colsList.size() - 1, 1).getBottomRight();
		for (int i = 0; i < colsList.size(); i++) {
			sheet.mergeCells(i, 2, i, 2);
		}
		sheet.mergeCells(0, sheetList.size() + 3, colsList.size() - 1,
				sheetList.size() + 3);
		// 单元格内容的设置
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Label ls = new Label(0, 1, "用户:" + username + "                   "+ "导出日期:" + s.format(new Date()), getFont());
		sheet.addCell(herder);
		sheet.addCell(ls);
		Label lb = null;
		for (int i = 0; i < colsList.size(); i++) {
			lb = new Label(i, 2, colsList.get(i), getFont());
			sheet.addCell(lb);
		}
		Label zj = new Label(0, sheetList.size() + 3, "总计：" + sheetList.size()
				+ "条记录", getFont());
		sheet.addCell(zj);

		// 单元格列宽的设置
		for (int i = 0; i < colsListWidth.size(); i++) {
			sheet.setColumnView(i, Integer.parseInt(colsListWidth.get(i)));
		}
		// 动态的将信息填写到表中
		Object value = "";
		for (int i = 0; i < sheetList.size(); i++) {
			if (sheetList.get(i) != null) {
				LinkedHashMap<String, Object> map = sheetList.get(i);
				Set<String> keySet = sheetList.get(i).keySet();
				int j = 0;
				for (String str : keySet) {
					Label label = null;
					if(null!=map.get(str)){
						value = String.valueOf(map.get(str));
					}else {
						value = "";
					}
					label = new Label(j, i + 3, String.valueOf(value),getFont());
					sheet.addCell(label);
					j++;
				}
			}
		}
		return sheet;
	}

	/**
	 * 设置标题的样式
	 */
	public static CellFormat getHeader() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 16,
				WritableFont.BOLD);
		try {
			font.setColour(Colour.BLACK);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		WritableCellFormat wcf = new WritableCellFormat(font);
		try {
			wcf.setAlignment(Alignment.CENTRE);// 左右居中
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 上下居中
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
			wcf.setBackground(Colour.WHITE);// 白色背景
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return wcf;

	}

	/**
	 * 设置单元格字体样式
	 */
	private static CellFormat getFont() {
		WritableFont wf = new WritableFont(WritableFont.TIMES, 14);// 字体大小
		try {
			wf.setColour(Colour.BLACK);
		} catch (WriteException e) {
			e.printStackTrace();
		}
		WritableCellFormat wcf = new WritableCellFormat(wf);
		try {
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			wcf.setBackground(Colour.WHITE);
		} catch (WriteException e) {
			e.printStackTrace();
		}// 左右居中
		return wcf;
	}
}
