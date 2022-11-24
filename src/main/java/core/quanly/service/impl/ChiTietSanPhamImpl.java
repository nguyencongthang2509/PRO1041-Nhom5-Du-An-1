/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.ChiTietSanPhamRepository;
import core.quanly.service.ChiTietSanPhamService;
import core.quanly.viewmodel.KMChiTietSanPhamResponse;
import crud.service.SanPhamService;
import domainmodels.ChiTietSP;
import domainmodels.SanPham;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public class ChiTietSanPhamImpl implements core.quanly.service.ChiTietSanPhamService{
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();

    @Override
    public List<KMChiTietSanPhamResponse> GetAllResponse() {
    return chiTietSanPhamRepository.getAllResponse();
    }
    
    public static void main(String[] args) {
        ChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamImpl();
        List<KMChiTietSanPhamResponse> list = new ArrayList<>();
        list = chiTietSanPhamService.GetAllResponse();
        for (KMChiTietSanPhamResponse chiTietSanPhamResponse : list) {
            System.out.println(chiTietSanPhamResponse);
        }
    }

    @Override
    public List<KMChiTietSanPhamResponse> findLopHocByMaOrTen(String input) {
    return chiTietSanPhamRepository.FindMaOrTenByInput(input);
    }

    @Override
    public String insert(ChiTietSP chiTietSP) {
    if (chiTietSP.getKhuyenMai().getMa().isEmpty()) {
            return "ma khong duoc de trong";
        }
        if (chiTietSP.getKhuyenMai().getTen().isEmpty()) {
            return "ten khong duoc de trong";
        }
        if (chiTietSP.getKhuyenMai().getGiaTri().equals("")) {
            return "gia tri khong duoc de trong";
        }
//        if (khuyenMaiResponse.getNgayBatDau().isEmpty()) {
//            return "ten khong duoc de trong";
//        }
//        if (khuyenMaiResponse.getNgayKetThuc().isEmpty()) {
//            return "ten khong duoc de trong";
//        }
        
        chiTietSP = chiTietSanPhamRepository.saveOrUpdate(chiTietSP);
        if (chiTietSP != null) {
            return "them thanh cong";
        }
        return "them that bai";
    }

    @Override
    public String update(ChiTietSP chiTietSP) {
        ChiTietSP findchitietsanpham = chiTietSanPhamRepository.findById(chiTietSP.getId());
    if (findchitietsanpham == null) {
            return "khuyen mai nay khong ton tai";
        }
//    findchitietsanpham.setKhuyenMai.(chiTietSP.getKhuyenMai().getMa());
//    findchitietsanpham.setTen(chiTietSP.getKhuyenMai().getTen());
//    findchitietsanpham.setLoaiKhuyenMai(chiTietSP.getKhuyenMai().getLoaiKhuyenMai());
//    findchitietsanpham.setGiaTri(chiTietSP.getKhuyenMai().getGiaTri());
//    findchitietsanpham.set(chiTietSP.getKhuyenMai().getGiaTri());
//    findchitietsanpham.setNgayBatDau(chiTietSP.getKhuyenMai().getNgayBatDau());
//    findchitietsanpham.setNgayKetThuc(chiTietSP.getKhuyenMai().getNgayKetThuc());
    chiTietSP =  chiTietSanPhamRepository.saveOrUpdate(findchitietsanpham);
    if(chiTietSP != null){
        return "sua thanh cong";
    }else{
        return "sua that bai";
    }
    }

    @Override
    public List<KMChiTietSanPhamResponse> GetSanPham() {
    return chiTietSanPhamRepository.GetSanPham();
    }
    
}
