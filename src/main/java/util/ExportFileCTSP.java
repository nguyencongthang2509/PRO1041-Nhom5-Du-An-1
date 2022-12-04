/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import core.quanly.viewmodel.CTSanPhamResponse;
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
 * @author HP
 */
public class ExportFileCTSP {

    public boolean ExportFileExcel(List<CTSanPhamResponse> lst) {
        boolean check = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Quản Lý Sản Phẩm");
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
            Cell firstCell9 = firstRow.createCell(8);
            Cell firstCell10 = firstRow.createCell(9);
            Cell firstCell11 = firstRow.createCell(10);
            Cell firstCell12 = firstRow.createCell(11);

            firstCell1.setCellValue("STT");
            firstCell2.setCellValue("Mã CTSP");
            firstCell3.setCellValue("Mã SP");
            firstCell4.setCellValue("Màu sắc");
            firstCell5.setCellValue("Kích thước");
            firstCell6.setCellValue("Hãng");
            firstCell7.setCellValue("Chất liệu");
            firstCell8.setCellValue("Mô tả");
            firstCell9.setCellValue("Số lượng tồn");
            firstCell10.setCellValue("Giá bán");
            firstCell11.setCellValue("Mã vạch");
            firstCell12.setCellValue("Trạng thái");
            int index = 1;
            for (CTSanPhamResponse xx : lst) {
                Row row = sheet.createRow(rowNum++);
                Cell cell1 = row.createCell(0);
                cell1.setCellValue(index++);

                Cell cell2 = row.createCell(1);
                cell2.setCellValue(xx.getMactsp());

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(xx.getMasp());

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(xx.getMauSac());

                Cell cell5 = row.createCell(4);
                cell5.setCellValue(xx.getKichThuoc());

                Cell cell6 = row.createCell(5);
                cell6.setCellValue(xx.getHang());

                Cell cell7 = row.createCell(6);
                cell7.setCellValue(xx.getChatLieu());

                Cell cell8 = row.createCell(7);
                cell8.setCellValue(xx.getMoTa());

                Cell cell9 = row.createCell(8);
                cell9.setCellValue(xx.getSoLuongTon());

                Cell cell10 = row.createCell(9);
                DecimalFormat df = new DecimalFormat("#,###");
                cell10.setCellValue(df.format(xx.getGiaBan()));

                Cell cell11 = row.createCell(10);
                cell11.setCellValue(xx.getMaVach());

                Cell cell12 = row.createCell(11);
                cell12.setCellValue(xx.getTrangThai() == 0 ? "Đang hoạt động" : "Dừng hoạt động");

            }
            try {
                FileOutputStream outputStream = new FileOutputStream("ChiTietSanPham" + Calendar.getInstance().getTimeInMillis() + ".xlsx");
                workbook.write(outputStream);
                workbook.close();
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
