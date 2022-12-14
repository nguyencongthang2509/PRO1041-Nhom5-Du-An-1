/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.KMChiTietSPResponse;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
import domainmodels.KhuyenMai;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public interface KhuyenMaiService {
    
    ChiTietSPKhuyenMai saveOrUpdate(ChiTietSPKhuyenMai entity);

    List<KMChiTietSPResponse> getAllChiTietSP();
    
    ChiTietSP findChiTietSpById(String id);
    
    ChiTietSPKhuyenMai getChiTietSPKM(String idChiTietSP);

    List<KhuyenMaiResponse> GetAllResponse();

    List<KhuyenMaiResponse> FindMaOrTenByInputSapDienRa(String input);
    
    List<KhuyenMaiResponse> FindMaOrTenByInputDangDienRa(String input);
    
    List<KhuyenMaiResponse> FindMaOrTenByInputDaDienRa(String input);

    String saveOrUpdateKM(KhuyenMai khuyenMai);

    boolean updateKhuyenMaiChoSP(String idKhuyenMai, String idChiTietSP);

    List<KMSanphamDangKmReponse> GetAllSanPhamDangApDung(String idkhuyenmai);

    List<KhuyenMaiResponse> getAllResponseKhuyenMai();
    
    List<KhuyenMaiResponse> GetKhuyenMaiDangDienRa(); 
    
    List<KhuyenMai> GetAllKhuyenMaiDangDienRa(); 
    
    List<KhuyenMaiResponse> GetKhuyenMaiSapDienRa(); 
    
    List<KhuyenMaiResponse> GetKhuyenMaiDaDienRa(); 
    
    int GenMaKhuyenMai();
    
    List<KMChiTietSPResponse> FindSanPhamByTen(Object input);
    
    List<String> SelectTenSanPham();
    
    boolean updateKhuyenMaiDangDienRa(String idKhuyenMai);
    
    List<KhuyenMaiResponse> GetKhuyenMaiKhongDienRa();
    
    List<KhuyenMai> GetAllKhuyenMaiKhongDienRa();
    
    boolean updateKhuyenMaiKhongDienRa(String idKhuyenMai);
    
    boolean HuyKhuyenMai(String idctspkm);
    
    List<KMChiTietSPResponse> getAllChiTietSPCoTheApDung(Date ngayBatDau, Date ngayKetThuc, int loaikhuyenmai, Double giatri);
    
    ChiTietSPKhuyenMai getChiTietSPKM2(String idkhuyenmai);
}
