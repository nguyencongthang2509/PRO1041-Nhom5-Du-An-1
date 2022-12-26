
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import core.quanly.viewmodel.ThongKeTraHangResponse;
import domainmodels.NhanVien;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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
public class ExportNhanVienThongKeTra {

    public boolean ExportFileExcel(List<ThongKeTraHangResponse> lst, String path) {
        NhanVien nv = new NhanVien();
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh Sách Hàng Hoá Đã Trả Của Nhân Viên: " + nv.getTen());
            int rowNum = 0;
            Row firstRow = sheet.createRow(rowNum++);
            Cell firstCell1 = firstRow.createCell(0);
            Cell firstCell2 = firstRow.createCell(1);
            Cell firstCell3 = firstRow.createCell(2);
            Cell firstCell4 = firstRow.createCell(3);
            Cell firstCell5 = firstRow.createCell(4);
            Cell firstCell6 = firstRow.createCell(5);
            Cell firstCell7 = firstRow.createCell(6);
            Cell firstCell8 = firstRow.createCell(7);

            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Mã SP");
            firstCell3.setCellValue("Tên SP");
            firstCell4.setCellValue("Hãng");
            firstCell5.setCellValue("Màu Sắc");
            firstCell6.setCellValue("Size");
            firstCell7.setCellValue("Số Lượng");
            firstCell8.setCellValue("Đơn Giá");

            int index = 1;
            for (ThongKeTraHangResponse xx : lst) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(String.valueOf(index++));

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(xx.getMa());

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(xx.getTenSP());

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(xx.getHang());

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(xx.getMau());

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(xx.getKichThuoc());

                Cell cell7 = row.createCell(6);
                cell7.setCellValue(xx.getSoLuong());

                Cell cell8 = row.createCell(7);
                DecimalFormat df = new DecimalFormat("#,###");
                cell8.setCellValue(df.format(xx.getGiaBan()));

            }
            try {
                String pathFile = path + "\\" + "HangHoaHoanTraCuaNhanVien" + Calendar.getInstance().getTimeInMillis() + ".xlsx";
                File file = new File(pathFile);
                FileOutputStream outputStream = new FileOutputStream(pathFile);
                workbook.write(outputStream);
                workbook.close();
                if (!Desktop.isDesktopSupported()) {
                    return check;
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
            e.printStackTrace();
        }
        return check;
    }
}
