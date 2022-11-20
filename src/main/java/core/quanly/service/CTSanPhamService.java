/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.CTSanPhamViewModel;
import domainmodels.ChiTietSP;
import java.util.List;

/**
 *
 * @author HP
 */
public interface CTSanPhamService {
    List<CTSanPhamViewModel> getAllViewModel();

    ChiTietSP getOne(String ma);

    String add(ChiTietSP ctsanPham);

    String update(ChiTietSP ctsanPham);

    List<ChiTietSP> getAll();
}
