/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
import domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public interface KhuyenMaiService {
    List<KhuyenMaiResponse> GetAllResponse();
      
    List<KhuyenMaiResponse>findLopHocByMaOrTen(String input);
    
    boolean saveOrUpdateKM(KhuyenMai khuyenMai);
    
    boolean updateKhuyenMaiChoSP(String idKhuyenMai, String idChiTietSP);
    
    List<KMSanphamDangKmReponse> GetAllSanPhamDangApDung(String idkhuyenmai);
    
    List<KhuyenMaiResponse> getAllResponseKhuyenMai();
}
