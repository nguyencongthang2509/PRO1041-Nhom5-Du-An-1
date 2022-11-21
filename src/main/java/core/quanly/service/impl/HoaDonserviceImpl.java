/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.HoaDonRepository;
import core.quanly.service.HoaDonservice;
import core.quanly.viewmodel.HdHoaDonChiTietResponse;
import core.quanly.viewmodel.HdHoaDonResponse;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class HoaDonserviceImpl implements HoaDonservice {

    private HoaDonRepository repo = new HoaDonRepository();

    @Override
    public List<HdHoaDonResponse> getList() {
        return repo.getListHoaDon();
    }

    @Override
    public List<HdHoaDonChiTietResponse> getListHDCT(String idhd) {
        return repo.getListHDCT(idhd);
    }

    @Override
    public List<HdHoaDonResponse> getListbyTextField(String input) {
        return repo.getListbyTextField(input);
    }

    @Override
    public List<HdHoaDonResponse> getListbyTrangThai(int trangthai) {
        return repo.getListbyTrangThai(trangthai);
    }

    @Override
    public List<HdHoaDonResponse> getListbyHinhThucThanhToan(int hinhThuc) {
        return repo.getListbyHinhThucThanhToan(hinhThuc);
    }

    @Override
    public List<HdHoaDonResponse> getListbyThang(int thang, int nam) {
        return repo.getListbyThang(thang, nam);
    }

}
