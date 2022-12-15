/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import core.quanly.viewmodel.NhanVienResponse;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author quynhncph26201
 */
public class ExportFileExcelNhanVien {

    public boolean ExportExcel(List<NhanVienResponse> list, String path) {
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Nhân viên");
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell = firstRow.createCell(0);
            Cell firstCell1 = firstRow.createCell(1);
            Cell firstCell2 = firstRow.createCell(2);
            Cell firstCell3 = firstRow.createCell(3);
            Cell firstCell4 = firstRow.createCell(4);
            Cell firstCell5 = firstRow.createCell(5);
            Cell firstCell6 = firstRow.createCell(6);
            Cell firstCell7 = firstRow.createCell(7);
            Cell firstCell8 = firstRow.createCell(8);
            Cell firstCell9 = firstRow.createCell(9);
            firstCell.setCellValue("STT");
            firstCell1.setCellValue("Mã Nhân Viên");
            firstCell2.setCellValue("Họ Tên");
            firstCell3.setCellValue("Giới Tính");
            firstCell4.setCellValue("Ngày Sinh");
            firstCell5.setCellValue("Địa Chỉ");
            firstCell6.setCellValue("Số Điện Thoại");
            firstCell7.setCellValue("Email");
            firstCell8.setCellValue("Vai Trò");
            firstCell9.setCellValue("Trạng Thái");
            int index = 1;
            for (NhanVienResponse xx : list) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                Cell cell2 = row.createCell(1);
                Cell cell3 = row.createCell(2);
                Cell cell4 = row.createCell(3);
                Cell cell5 = row.createCell(4);
                Cell cell6 = row.createCell(5);
                Cell cell7 = row.createCell(6);
                Cell cell8 = row.createCell(7);
                Cell cell9 = row.createCell(8);
                Cell cell10 = row.createCell(9);
                cell1.setCellValue(String.valueOf(index++));
                cell2.setCellValue(xx.getMa());
                cell3.setCellValue(xx.getTen());
                cell4.setCellValue(xx.getGioitinh() == 0 ? "Nam" : "Nữ");
                cell5.setCellValue(xx.getnngaysinh());
                cell6.setCellValue(xx.getDiachi());
                cell7.setCellValue(xx.getSdt());
                cell8.setCellValue(xx.getEmail());
                cell9.setCellValue(xx.getVaitro() == 0 ? "Quản lý" : "Nhân viên");
                cell10.setCellValue(xx.getTrangthaixoa() == 0 ? "Đang làm" : "Nghỉ làm");
            }
            try {
                String pathFile = path + "\\" + "NhanVienExcel" + Calendar.getInstance().getTimeInMillis() + ".xlsx";
                File file = new File(pathFile);
                FileOutputStream outputStream = new FileOutputStream(pathFile);
                workbook.write(outputStream);
                workbook.close();
                if (!Desktop.isDesktopSupported()) {
                    return false;
                }
                Desktop desktop = Desktop.getDesktop();
                if (file.exists()) {
                    desktop.open(file);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
}
