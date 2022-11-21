/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.KhachHangRespone;
import domainmodels.KhachHang;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public interface KhachHangService {

    List<KhachHangRespone> getAllResponse();

    String insert(KhachHang khachHang);

    String update(KhachHang khachHang);

//    List<KhachHang> getAll();
     List<KhachHangRespone> findKhachHangByMaOrTen(String input);
}
