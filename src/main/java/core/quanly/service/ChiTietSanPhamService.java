/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.service.impl.*;
import core.quanly.viewmodel.KMChiTietSanPhamResponse;
import domainmodels.ChiTietSP;
import domainmodels.SanPham;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public interface ChiTietSanPhamService {
    List<KMChiTietSanPhamResponse> GetAllResponse();
    
    List<KMChiTietSanPhamResponse>findLopHocByMaOrTen(String input);
    
    List<KMChiTietSanPhamResponse> GetSanPham();;
    
    String insert(ChiTietSP chiTietSP);
    
    String update(ChiTietSP chiTietSP);
}
