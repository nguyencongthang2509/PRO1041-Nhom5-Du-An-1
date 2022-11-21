/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.ChiTietSanPhamRepository;
import core.quanly.repository.KhuyenMaiRepository;
import core.quanly.service.KhuyenMaiService;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public class KhuyenMaiImpl implements core.quanly.service.KhuyenMaiService{
    private KhuyenMaiRepository khuyenMaiRepository;
    
    public KhuyenMaiImpl(){
        khuyenMaiRepository = new KhuyenMaiRepository();
    }

    @Override
    public String insert(KhuyenMai khuyenMai) {
     if (khuyenMai.getMa().isEmpty()) {
            return "ma khong duoc de trong";
        }
        if (khuyenMai.getTen().isEmpty()) {
            return "ten khong duoc de trong";
        }
        if (khuyenMai.getGiaTri().isEmpty()) {
            return "ten khong duoc de trong";
        }
//        if (khuyenMaiResponse.getNgayBatDau().isEmpty()) {
//            return "ten khong duoc de trong";
//        }
//        if (khuyenMaiResponse.getNgayKetThuc().isEmpty()) {
//            return "ten khong duoc de trong";
//        }
        
        khuyenMai = khuyenMaiRepository.saveOrUpdate(khuyenMai);
        if (khuyenMai != null) {
            return "them thanh cong";
        }
        return "them that bai";
    }

    @Override
    public List<KhuyenMaiResponse> GetAllResponse() {
    return khuyenMaiRepository.getAllResponse();
    }
     public static void main(String[] args) {
        KhuyenMaiService khuyenMaiService = new KhuyenMaiImpl();
        List<KhuyenMaiResponse> list = new ArrayList<>();
        list = khuyenMaiService.GetAllResponse();
         for (KhuyenMaiResponse khuyenMaiResponse : list) {
             System.out.println(khuyenMaiResponse);
         }
    }

    @Override
    public List<KhuyenMaiResponse> findLopHocByMaOrTen(String input) {
    return khuyenMaiRepository.FindMaOrTenByInput(input);
    }

    @Override
    public String update(KhuyenMai khuyenMai) {
    KhuyenMai findloaikhuyenmai = khuyenMaiRepository.findById(khuyenMai.getId());
    if (findloaikhuyenmai == null) {
            return "khuyen mai nay khong ton tai";
        }
    findloaikhuyenmai.setMa(khuyenMai.getMa());
    findloaikhuyenmai.setTen(khuyenMai.getTen());
    findloaikhuyenmai.setLoaiKhuyenMai(khuyenMai.getLoaiKhuyenMai());
    findloaikhuyenmai.setGiaTri(khuyenMai.getGiaTri());
    findloaikhuyenmai.setNgayBatDau(khuyenMai.getNgayBatDau());
    findloaikhuyenmai.setNgayKetThuc(khuyenMai.getNgayKetThuc());
    khuyenMai =  khuyenMaiRepository.saveOrUpdate(findloaikhuyenmai);
    if(khuyenMai != null){
        return "sua thanh cong";
    }else{
        return "sua that bai";
    }
    }
    
    
}
    

