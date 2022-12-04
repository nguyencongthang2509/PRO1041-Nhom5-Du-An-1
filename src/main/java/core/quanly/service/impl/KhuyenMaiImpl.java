/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.KMSanPhamRepository;
import core.quanly.repository.KhuyenMaiRepository;
import core.quanly.service.KhuyenMaiService;
import core.quanly.viewmodel.KMChiTietSPResponse;
import core.quanly.viewmodel.KMSanphamDangKmReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import domainmodels.ChiTietSP;
import domainmodels.ChiTietSPKhuyenMai;
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
        List<KMSanphamDangKmReponse> list = new ArrayList<>();
        list = khuyenMaiService.GetAllSanPhamDangApDung("fb13ec87-ae97-40f4-9358-50700b730585");
        System.out.println(list);

    }

    @Override
    public String saveOrUpdateKM(KhuyenMai khuyenMai) {
        if (khuyenMai.getMa().trim().isEmpty()) {
            return "ma khuyen mai khong duoc trong";          
        }
        if (khuyenMai.getTen().trim().isEmpty()) {
            return "ma khuyen mai khong duoc trong";
        }
        if (khuyenMai.getGiaTri().equals("")) {
            return "ma khuyen mai khong duoc trong";
        }
        if (khuyenMai.getNgayBatDau().equals("")) {
            return "ma khuyen mai khong duoc trong";
        }
        if (khuyenMai.getNgayKetThuc().equals("")) {
            return "ma khuyen mai khong duoc trong";
        }
        KhuyenMai khuyenMai1 = khuyenMaiRepository.saveOrUpdateKM(khuyenMai);
        if (khuyenMai1 != null) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
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

    @Override
    public List<KMChiTietSPResponse> getAllChiTietSP() {
        List<KMChiTietSPResponse> list = khuyenMaiRepository.getAllChiTietSP();
        return list;
    }

    @Override
    public ChiTietSP findChiTietSpById(String id) {
        return khuyenMaiRepository.findChiTietSpById(id);
    }

    @Override
    public ChiTietSPKhuyenMai getChiTietSPKM(String idChiTietSP) {
        return khuyenMaiRepository.getChiTietSPKM(idChiTietSP);
    }

    @Override
    public ChiTietSPKhuyenMai saveOrUpdate(ChiTietSPKhuyenMai entity) {
        return khuyenMaiRepository.saveOrUpdate(entity);
    }

    @Override
    public List<KhuyenMaiResponse> GetKhuyenMaiDangDienRa() {
        return khuyenMaiRepository.GetKhuyenMaiDangDienRa();
    }

    @Override
    public List<KhuyenMaiResponse> GetKhuyenMaiSapDienRa() {
        return khuyenMaiRepository.GetKhuyenMaiSapDienRa();
    }

    @Override
    public List<KhuyenMaiResponse> GetKhuyenMaiDaDienRa() {
        return khuyenMaiRepository.GetKhuyenMaiDaDienRa();
    }

    @Override
    public List<KhuyenMaiResponse> FindMaOrTenByInputSapDienRa(String input) {
        return khuyenMaiRepository.FindMaOrTenByInputSapDienRa(input);
    }

    @Override
    public List<KhuyenMaiResponse> FindMaOrTenByInputDangDienRa(String input) {
        return khuyenMaiRepository.FindMaOrTenByInputDangDienRa(input);
    }

    @Override
    public List<KhuyenMaiResponse> FindMaOrTenByInputDaDienRa(String input) {
        return khuyenMaiRepository.FindMaOrTenByInputDaDienRa(input);
    }

    @Override
    public int GenMaKhuyenMai() {
    return khuyenMaiRepository.GenMaKhuyenMai();
    }

    @Override
    public List<KMChiTietSPResponse> FindSanPhamByTen(Object input) {
    return khuyenMaiRepository.FindSanPhamByTen(input);
    }

    @Override
    public List<String> SelectTenSanPham() {
    return khuyenMaiRepository.SelectTenSanPham();
    }

    @Override
    public boolean updateKhuyenMaiDangDienRa(String idKhuyenMai) {
        return khuyenMaiRepository.updateKhuyenMaiDangDienRa(idKhuyenMai);
    }

    @Override
    public List<KhuyenMaiResponse> GetKhuyenMaiKhongDienRa() {
        return khuyenMaiRepository.GetKhuyenMaiKhongDienRa();
    }

    @Override
    public boolean updateKhuyenMaiKhongDienRa(String idKhuyenMai) {
        return khuyenMaiRepository.updateKhuyenMaiKhongDienRa(idKhuyenMai);
    }
}
