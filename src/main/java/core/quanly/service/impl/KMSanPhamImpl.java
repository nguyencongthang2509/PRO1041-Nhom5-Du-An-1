/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;


import core.quanly.repository.KMSanPhamRepository;
import core.quanly.viewmodel.KMSanphamReponse;
import core.quanly.viewmodel.KhuyenMaiResponse;
import java.util.ArrayList;
import java.util.List;
import core.quanly.service.KMSanPhamService;
import core.quanly.viewmodel.KMChiTietSPResponse;

/**
 *
 * @author thiennvtph26140
 */
public class KMSanPhamImpl implements core.quanly.service.KMSanPhamService {

    KMSanPhamRepository sanPhamRepository = new KMSanPhamRepository();

    public static void main(String[] args) {
        KMSanPhamService chiTietSanPhamService = new KMSanPhamImpl();
        List<KMChiTietSPResponse> list = new ArrayList<>();
        list = chiTietSanPhamService.GetAllResponse();
        for (KMChiTietSPResponse kMChiTietSPResponse : list) {
            System.out.println(kMChiTietSPResponse);
        }
    }

    @Override
    public List<KMChiTietSPResponse> GetAllResponse() {
        return sanPhamRepository.getAllResponse();
    }

    @Override
    public List<KMSanphamReponse> GetAllNoKhuyenMai() {
        return sanPhamRepository.getAllResponseNoKhuyenMai();
    }

}
