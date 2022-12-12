/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.SanPhamResponse;
import domainmodels.SanPham;
import java.util.List;
import javax.print.DocFlavor;

/**
 *
 * @author HP
 */
public interface SanPhamService {

    List<SanPhamResponse> getAllViewModel();
    
    List<SanPhamResponse> getAllResponse();

    SanPham getOne(String ma);

    String add(SanPham sanPham);

    String update(SanPham sanPham);

    List<SanPham> getAll();

    List<SanPhamResponse> findMaOrTen(String input);
    
    int genMaSanPhamTuDong();
}
