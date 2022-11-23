/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.HdHoaDonChiTietResponse;
import core.quanly.viewmodel.HdHoaDonResponse;
import java.util.List;

/**
 *
 * @author longnhph26222
 */
public interface HoaDonservice {

    List<HdHoaDonResponse> getList();

    List<HdHoaDonResponse> getListbyMa(String input);

    List<HdHoaDonResponse> getListbyTrangThai(int trangthai);

    List<HdHoaDonResponse> getListbyHinhThucThanhToan(int hinhThuc);
    
        List<HdHoaDonResponse> getListbyThang(int thang, int nam);

    List<HdHoaDonChiTietResponse> getListHDCT(String idhd);
}
