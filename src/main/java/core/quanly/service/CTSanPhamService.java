/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.repository.CTSanPhamRepository;
import core.quanly.viewmodel.CTSanPhamResponse;
import domainmodels.ChiTietSP;
import java.util.List;

/**
 *
 * @author HP
 */
public interface CTSanPhamService {
    List<CTSanPhamResponse> getAllViewModel();

    ChiTietSP getOne(String ma);

    String add(ChiTietSP ctsanPham);

    String update(ChiTietSP ctsanPham);

    List<ChiTietSP> getAll();
    
    List<CTSanPhamResponse> findByMaOrTen(String input);
    
    List<CTSanPhamResponse> getCbbListHang(String hang);
    
    List<CTSanPhamResponse> getFormCTSP(String ma);
    
    List<CTSanPhamResponse> findTrangThai(Integer ma);
    
}
