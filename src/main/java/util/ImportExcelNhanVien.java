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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Array;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcelNhanVien {

    private NhanVienRepository nvr = new NhanVienRepository();

    public void ImportFile(String path) throws ParseException, NoSuchAlgorithmException {
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
//                String trangthai = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
                String ngaysinh = String.valueOf(getCellValue(currentRow.getCell(3))).trim();

                if (ten.isEmpty() && diachi.isEmpty() && email.isEmpty() && email.isEmpty()
                        && sdt.isEmpty() && gioitinh.isEmpty() && vaitro.isEmpty() && ngaysinh.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date strDate = formatter.parse(ngaysinh);
                nv.setMa("NV" + mactsp++);
                nv.setTen(ten);
                nv.setDiaChi(diachi);
                nv.setEmail(email);
                nv.setSdt(sdt);
                nv.setGioiTinh(gioitinh.equals("Nam") ? 0 : 1);
                nv.setNgaySinh(strDate);
                nv.setVaiTro(vaitro.equals("Nhân Viên") ? 1 : 0);
//                nv.setTrangThaiXoa(trangthai.equals("Đang Làm")?0:1);
                lst.add(nv);
//                System.out.println(myHash);

            }
            nvr.saveAll(lst);
            String[] array = new String[lst.size()];
            for (int i = 0; i < lst.size(); i++) {
                array[i] = lst.get(i).getEmail();
            }
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(array[i]);
            }
            new SendToPassWord().emailSender(array, "Thông báo mật khẩu mới:", "");

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
