/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.KMSanPhamRepository;
import core.quanly.repository.KhuyenMaiRepository;
import core.quanly.service.KhuyenMaiService;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
import domainmodels.KhuyenMai;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public class KhuyenMaiImpl implements core.quanly.service.KhuyenMaiService {

    private KhuyenMaiRepository khuyenMaiRepository;

    public KhuyenMaiImpl() {
        khuyenMaiRepository = new KhuyenMaiRepository();
    }



    @Override
    public List<KhuyenMaiResponse> GetAllResponse() {
        return khuyenMaiRepository.getAllResponse();
    }

    public static void main(String[] args) {
        KhuyenMaiService khuyenMaiService = new KhuyenMaiImpl();
        
        List<KhuyenMaiResponse> list = new ArrayList<>();
//        List<KMSanphamDangKmReponse> list1 = new ArrayList<>();
//        list1 = khuyenMaiService.GetAllSanPhamDangApDung("5cc8be99-a7bd-4079-9f3c-40a04469ae99");
        list = khuyenMaiService.GetAllResponse();
        System.out.println(list);
//        for (KMSanphamDangKmReponse kMSanphamDangKmReponse : list1) {
//            System.out.println(kMSanphamDangKmReponse);
//        }
//        for (KhuyenMaiResponse khuyenMaiResponse : list) {
//            System.out.println(khuyenMaiResponse);
//        }
    }

    @Override
    public List<KhuyenMaiResponse> findLopHocByMaOrTen(String input) {
        return khuyenMaiRepository.FindMaOrTenByInput(input);
    }

    @Override
    public boolean saveOrUpdateKM(KhuyenMai khuyenMai) {
        if(khuyenMai.getMa().isEmpty()){
//            return "ma khuyen mai khong duoc trong";
            return false;
        }
        if(khuyenMai.getTen().isEmpty()){
//            return "ma khuyen mai khong duoc trong";
            return false;
        }
        if(khuyenMai.getGiaTri().equals("")){
            return false;
        }
        if(khuyenMai.getNgayBatDau().equals("")){
            return false;
        }
        if(khuyenMai.getNgayKetThuc().equals("")){
            return false;
        }
        KhuyenMai khuyenMai1 = khuyenMaiRepository.saveOrUpdateKM(khuyenMai);
        if (khuyenMai1 != null) {           
            return true;
        }
        return false;
    }

    @Override
    public boolean updateKhuyenMaiChoSP(String idKhuyenMai, String idChiTietSP) {
        return khuyenMaiRepository.updateKhuyenMaiChoSP(idKhuyenMai, idChiTietSP);
    }

    @Override
    public List<KMSanphamDangKmReponse> GetAllSanPhamDangApDung(String idkhuyenmai) {
        return khuyenMaiRepository.GetAllSanPhamDangApDung(idkhuyenmai);
    }

    @Override
    public List<KhuyenMaiResponse> getAllResponseKhuyenMai() {
    return khuyenMaiRepository.getAllResponseKhuyenMai();
    }
}
