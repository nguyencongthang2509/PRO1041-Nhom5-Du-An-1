/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.ChiTietSanPhamRepository;
import core.quanly.service.ChiTietSanPhamService;
import core.quanly.viewmodel.KMChiTietSanPhamResponse;
import crud.service.SanPhamService;
import domainmodels.SanPham;
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
//    public static void main(String[] args) {
//        ChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamImpl();
//        List<ChiTietSanPhamResponse> list = new ArrayList<>();
//        list = chiTietSanPhamService.GetAllResponse();
//        for (KMChiTietSanPhamResponse chiTietSanPhamResponse : list) {
//            System.out.println(chiTietSanPhamResponse);
//        }
//    }
    
}
