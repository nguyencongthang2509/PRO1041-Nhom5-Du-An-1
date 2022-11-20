/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.ChiTietSanPhamRepository;
import core.quanly.service.KhuyenMaiService;
import core.quanly.viewmodel.KhuyenMaiResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiennvtph26140
 */
public class KhuyenMaiImpl implements core.quanly.service.KhuyenMaiService{
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    @Override
    public List<KhuyenMaiResponse> GetKhuyenMai() {
    return chiTietSanPhamRepository.getAllKhuyenMai();
    }
    
     public static void main(String[] args) {
         KhuyenMaiService khuyenMaiService = new KhuyenMaiImpl();
        List<KhuyenMaiResponse> list = new ArrayList<>();
        list = khuyenMaiService.GetKhuyenMai();
         for (KhuyenMaiResponse x: list) {
             System.out.println(x);
         }
    }
}
