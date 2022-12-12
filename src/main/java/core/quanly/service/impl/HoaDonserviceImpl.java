/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.HoaDonRepository;
import core.quanly.service.HoaDonservice;
import core.quanly.viewmodel.HdHoaDonChiTietResponse1;
import core.quanly.viewmodel.HdHoaDonResponse1;
import core.quanly.viewmodel.HdHoaDonResponse2;
import domainmodels.HoaDon;
import java.util.Date;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class HoaDonserviceImpl implements HoaDonservice {

    private HoaDonRepository repo = new HoaDonRepository();

    @Override
    public List<HdHoaDonResponse1> getList(int htgh) {
        return repo.getListHoaDon(htgh);
    }

    @Override
    public List<HdHoaDonChiTietResponse1> getListHDCT(String idhd) {
        return repo.getListHDCT(idhd);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyText(int htgh, String input) {
        return repo.getListbyText(htgh, input);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyTrangThai(int trangthai, int htgh) {
        return repo.getListbyTrangThai(trangthai, htgh);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyHinhThucThanhToan(int hinhThuc, int htgh) {
        return repo.getListbyHinhThucThanhToan(hinhThuc, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2(int htgh) {
        return repo.getListHoaDon2(htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getListHD2byText(String input, int htgh) {
        return repo.getListHoaDon2byText(input, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byTrangThai(int trangthai, int htgh) {
        return repo.getList2byTrangThai(trangthai, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byHinhThucThanhToan(int hinhThuc, int htgh) {
        return repo.getList2byHinhThucThanhToan(hinhThuc, htgh);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyTime(Date time1, Date time2, int htgh) {
        return repo.getListbyTime(time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byTime(Date time1, Date time2, int htgh) {
        return repo.getList2byTime(time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyAllComboBox(int trangthai, int httt, Date time1, Date time2, int htgh) {
        return repo.getListbyAllComboBox(trangthai, httt, time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyAllTT(int trangthai, int httt, int htgh) {
        return repo.getListbyAllTT(trangthai, httt, htgh);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyTrangThaiandTime(int trangthai, Date time1, Date time2, int htgh) {
        return repo.getListbyTrangThaiandTime(trangthai, time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse1> getListbyHinhthucandTime(int httt, Date time1, Date time2, int htgh) {
        return repo.getListbyHinhthucandTime(httt, time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byAllComboBox(int trangthai, int httt, Date time1, Date time2, int htgh) {
        return repo.getList2byAllComboBox(trangthai, httt, time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byAllTT(int trangthai, int httt, int htgh) {
        return repo.getList2byAllTT(trangthai, httt, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byTrangThaiandTime(int trangthai, Date time1, Date time2, int htgh) {
        return repo.getList2byTrangThaiandTime(trangthai, time1, time2, htgh);
    }

    @Override
    public List<HdHoaDonResponse2> getList2byHinhthucandTime(int httt, Date time1, Date time2, int htgh) {
        return repo.getList2byHinhthucandTime(httt, time1, time2, htgh);
    }

    @Override
    public long getCountHoaDon() {
        return repo.getCountHoaDon();
    }

    @Override
    public boolean updateTTHoaDon(String id, String lydo) {
        return repo.updateTTHoaDon(id, lydo);
    }

    @Override
    public String findIdbyMa(String ma) {
        return repo.findIdByMa(ma);
    }

    @Override
    public HoaDon findHdByMa(String ma) {
        return repo.findHdByMa(ma);
    }

    @Override
    public boolean updateSoLuong(String id, Integer soLuong) {
        return repo.updateSoLuong(id, soLuong);
    }

    @Override
    public String findIdSPbyHDCT(String id) {
        return repo.findIdSPbyHDCT(id);
    }
}
