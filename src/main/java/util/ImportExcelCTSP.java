
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import core.quanly.repository.CTSanPhamRepository;
import domainmodels.ChatLieu;
import domainmodels.ChiTietSP;
import domainmodels.Hang;
import domainmodels.KichThuoc;
import domainmodels.MauSac;
import domainmodels.SanPham;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author HP
 */
public class ImportExcelCTSP {

    private CTSanPhamRepository cTSanPhamRepository;

    private ConcurrentMap<String, ChatLieu> mapCL = new ConcurrentHashMap<>();
    private ConcurrentMap<String, MauSac> mapMS = new ConcurrentHashMap<>();
    private ConcurrentMap<String, KichThuoc> mapKT = new ConcurrentHashMap<>();
    private ConcurrentMap<String, SanPham> mapSP = new ConcurrentHashMap<>();
    private ConcurrentMap<String, Hang> mapHang = new ConcurrentHashMap<>();

    public ImportExcelCTSP() {
        cTSanPhamRepository = new CTSanPhamRepository();
    }

    public void ImportFile(String path) {
        try {
            List<ChiTietSP> listctsp = new ArrayList<>();
            FileInputStream excelFile = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            int mactsp = cTSanPhamRepository.genMaCTSP();
            addDataInMapCL(mapCL);
            addDataInMapHANG(mapHang);
            addDataInMapKT(mapKT);
            addDataInMapMS(mapMS);
            addDataInMapSP(mapSP);
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                String sanPhamStr = String.valueOf(getCellValue(currentRow.getCell(1))).trim();
                String mauSacStr = String.valueOf(getCellValue(currentRow.getCell(2))).trim();
                String tenkichThuoc = String.valueOf(getCellValue(currentRow.getCell(3))).trim();
                String hangStr = String.valueOf(getCellValue(currentRow.getCell(4))).trim();
                String chatLieuStr = String.valueOf(getCellValue(currentRow.getCell(5))).trim();
                String moTa = String.valueOf(getCellValue(currentRow.getCell(6))).trim();
                String soLuongTon = String.valueOf(getCellValue(currentRow.getCell(7))).trim();
                String giaBan = String.valueOf(getCellValue(currentRow.getCell(8))).trim();
                String maVach = String.valueOf(getCellValue(currentRow.getCell(9))).trim();
                if (mauSacStr.isEmpty() && sanPhamStr.isEmpty() && tenkichThuoc.isEmpty() && hangStr.isEmpty()
                        && chatLieuStr.isEmpty() && moTa.isEmpty() && soLuongTon.isEmpty() && giaBan.isEmpty() && maVach.isEmpty()) {
                    continue;
                }
                if (mauSacStr.isEmpty() || sanPhamStr.isEmpty() || tenkichThuoc.isEmpty() || hangStr.isEmpty()
                        || chatLieuStr.isEmpty() || moTa.isEmpty() || soLuongTon.isEmpty() || giaBan.isEmpty() || maVach.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không để trống");
                    return;
                }
                try {
                    double soLuongTonSo = 0;
                    soLuongTonSo = Double.parseDouble(soLuongTon);
                    if (soLuongTonSo <= 0) {
                        JOptionPane.showMessageDialog(null, "Số lượng tồn lớn hơn 0");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Số lượng tồn phải là số");
                    return;
                }
                try {
                    double giaBanSo = 0;
                    giaBanSo = Double.parseDouble(giaBan);
                    if (giaBanSo <= 0) {
                        JOptionPane.showMessageDialog(null, "Giá bán lớn hơn 0");
                        return;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Giá bán phải là số");
                    return;
                }
                
                SanPham sanPham = new SanPham();
                Hang hang = new Hang();
                MauSac mauSac = new MauSac();
                ChatLieu chatLieu = new ChatLieu();
                KichThuoc kichThuoc1 = new KichThuoc();
                
                if(mapSP.get(sanPhamStr) == null){
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
                    return;
                }else{
                    sanPham = mapSP.get(sanPhamStr);
                }
                
                if(mapHang.get(hangStr) == null){
                    JOptionPane.showMessageDialog(null, "Không tìm thấy hãng");
                    return;
                }else{
                    hang = mapHang.get(hangStr);
                }
                
                if(mapMS.get(mauSacStr) == null){
                    JOptionPane.showMessageDialog(null, "Không tìm thấy màu sắc");
                    return;
                }else{
                    mauSac = mapMS.get(mauSacStr);
                }
                
                if(mapKT.get(String.valueOf((int) Double.parseDouble(tenkichThuoc))) == null){
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kích thước");
                    return;
                }else{
                    kichThuoc1 = mapKT.get(String.valueOf((int) Double.parseDouble(tenkichThuoc)));
                }
                
                if(mapCL.get(chatLieuStr) == null){
                    JOptionPane.showMessageDialog(null, "Không tìm thấy chất liệu");
                    return;
                }else{
                    chatLieu = mapCL.get(chatLieuStr);
                }
                    
                    
                ChiTietSP chiTietSP = new ChiTietSP();
                ChiTietSP chiTietSPcheck = cTSanPhamRepository.findByCBB(sanPham.getTen(), hang.getTen(), mauSac.getTen(), kichThuoc1.getTen(), chatLieu.getTen());
                if (chiTietSPcheck.getMaChiTietSP() == null) {
                    chiTietSP.setMaChiTietSP("CTSP" + mactsp++);
                    chiTietSP.setSanPham(sanPham);
                    chiTietSP.setMauSac(mauSac);
                    chiTietSP.setKichThuoc(kichThuoc1);
                    chiTietSP.setHang(hang);
                    chiTietSP.setChatLieu(chatLieu);
                    chiTietSP.setMoTa(moTa);
                    chiTietSP.setSoLuongTon((int) Double.parseDouble(soLuongTon));
                    chiTietSP.setGiaBan(new BigDecimal(giaBan));
                    chiTietSP.setMaVach(String.valueOf((int) Double.parseDouble(maVach)));
                    listctsp.add(chiTietSP);
//                    cTSanPhamRepository.saveOrUpdate(chiTietSP);
                } else {
                    chiTietSPcheck.setMaVach(String.valueOf((int) Double.parseDouble(maVach)));
                    chiTietSPcheck.setGiaBan(new BigDecimal(giaBan));
                    chiTietSPcheck.setMoTa(moTa);
                    if (chiTietSPcheck.getSoLuongTon() != null) {
                        chiTietSPcheck.setSoLuongTon((int) Double.parseDouble(soLuongTon) + chiTietSPcheck.getSoLuongTon());
                    } else {
                        chiTietSPcheck.setSoLuongTon((int) Double.parseDouble(soLuongTon));
                    }
                    listctsp.add(chiTietSPcheck);
//                    cTSanPhamRepository.saveOrUpdate(chiTietSPcheck);
                }
            }
            cTSanPhamRepository.saveAll(listctsp);
            JOptionPane.showMessageDialog(null, "Import file excel thành công");
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addDataInMapSP(ConcurrentMap<String, SanPham> mapSimple){
        List<SanPham> listSP = cTSanPhamRepository.getALLSP();
        getALlPutMapCheckSP(mapSimple, listSP);
    }
    
    public void addDataInMapMS(ConcurrentMap<String, MauSac> mapSimple){
        List<MauSac> listSP = cTSanPhamRepository.getALLMS();
        getALlPutMapCheckMS(mapSimple, listSP);
    }
    
    public void addDataInMapKT(ConcurrentMap<String, KichThuoc> mapSimple){
        List<KichThuoc> listSP = cTSanPhamRepository.getALLKichThuoc();
        getALlPutMapCheckKT(mapSimple, listSP);
    }
    
    public void addDataInMapHANG(ConcurrentMap<String, Hang> mapSimple){
        List<Hang> listSP = cTSanPhamRepository.getALLHang();
        getALlPutMapCheckHang(mapSimple, listSP);
    }
    
    public void addDataInMapCL(ConcurrentMap<String, ChatLieu> mapSimple){
        List<ChatLieu> listSP = cTSanPhamRepository.getALLChatLieu();
        getALlPutMapCheckCL(mapSimple, listSP);
    }

    public void getALlPutMapCheckSP(ConcurrentMap<String, SanPham> mapSimple, List<SanPham> list) {
        for (SanPham xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }
    
    public void getALlPutMapCheckHang(ConcurrentMap<String, Hang> mapSimple, List<Hang> list) {
        for (Hang xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }
    
    public void getALlPutMapCheckKT(ConcurrentMap<String, KichThuoc> mapSimple, List<KichThuoc> list) {
        for (KichThuoc xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }
    
    public void getALlPutMapCheckMS(ConcurrentMap<String, MauSac> mapSimple, List<MauSac> list) {
        for (MauSac xx : list) {
            mapSimple.put(xx.getTen(), xx);
        }
    }
    
    public void getALlPutMapCheckCL(ConcurrentMap<String, ChatLieu> mapSimple, List<ChatLieu> list) {
        for (ChatLieu xx : list) {
            mapSimple.put(xx.getTen(), xx);
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
