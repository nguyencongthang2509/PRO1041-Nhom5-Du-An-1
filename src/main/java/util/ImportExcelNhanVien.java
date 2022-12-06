/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author quynhncph26201
 */
import core.quanly.repository.NhanVienRepository;
import domainmodels.NhanVien;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelNhanVien {
    
    private NhanVienRepository nvr = new NhanVienRepository();
    
    public void ImportFile(String path) {
        List<NhanVien> lst = new ArrayList<>();
        
        try {
            List<NhanVien> listctsp = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            int mactsp = nvr.genMaNhanVien();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                NhanVien nv = new NhanVien();
                String ten = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
                String diachi = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
                String email = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
                String sdt = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
                String gioitinh = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
                String vaitro = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
                String trangthai = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
                String ngaysinh = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
                
                if (ten.isEmpty() && diachi.isEmpty() && email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }
                nv.setMa("NV" + mactsp++);
                nv.setTen(ten);
                nv.setDiaChi(diachi);
                nv.setEmail(email);
                nv.setSdt(sdt);
                nv.setGioiTinh(Integer.parseInt(gioitinh));
                nv.setNgaySinh(new Date(Long.parseLong(ngaysinh)));
                nv.setVaiTro(Integer.parseInt(vaitro));
                nv.setTrangThaiXoa(Integer.parseInt(trangthai));
                lst.add(nv);
                
            }
            
            nvr.saveAll(lst);
            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            workbook.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
