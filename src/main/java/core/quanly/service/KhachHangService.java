
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.KhachHangLichSuRespone;
import core.quanly.viewmodel.KhachHangRespone;
import domainmodels.KhachHang;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public interface KhachHangService {

    List<KhachHangRespone> getAllResponse();
    
    int genMaKhachHang();

    String insert(KhachHang khachHang);

    String update(KhachHang khachHang);
    
    boolean saveOrUpdateKH(KhachHang entity);

//    List<KhachHang> getAll();
    List<KhachHangRespone> findKhachHangByMaOrTen(String input);

    List<KhachHangRespone> getLoadCbbGioiTinh(int gioiTinh);

    List<KhachHangLichSuRespone> getKhachHangByLichSu(String id);
    
     List<KhachHangLichSuRespone> getKhachHangByCapBac(String id);
     
     BigDecimal getTongTienByIdKhachHang(String id);
}
