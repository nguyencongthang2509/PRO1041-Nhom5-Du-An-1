/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;


import core.quanly.viewmodel.HdHoaDonChiTietResponse1;
import core.quanly.viewmodel.HdHoaDonResponse1;
import core.quanly.viewmodel.HdHoaDonResponse2;
import domainmodels.HoaDon;
import java.util.Date;
import java.util.List;

/**
 *
 * @author longnhph26222
 */
public interface HoaDonservice {

    long getCountHoaDon();

    List<HdHoaDonResponse1> getList(int htgh);

    List<HdHoaDonResponse2> getList2(int htgh);

    List<HdHoaDonResponse1> getListbyText(int htgh, String input);

    List<HdHoaDonResponse2> getListHD2byText(String input, int htgh);

    List<HdHoaDonResponse1> getListbyTrangThai(int trangthai, int htgh);

    List<HdHoaDonResponse2> getList2byTrangThai(int trangthai, int htgh);

    List<HdHoaDonResponse1> getListbyHinhThucThanhToan(int hinhThuc, int htgh);

    List<HdHoaDonResponse2> getList2byHinhThucThanhToan(int hinhThuc, int htgh);

    List<HdHoaDonResponse1> getListbyTime(Date time1, Date time2, int htgh);

    List<HdHoaDonResponse2> getList2byTime(Date time1, Date time2, int htgh);

    List<HdHoaDonChiTietResponse1> getListHDCT(String idhd);

    List<HdHoaDonResponse1> getListbyAllComboBox(int trangthai, int httt, Date time1, Date time2, int htgh);

    List<HdHoaDonResponse1> getListbyAllTT(int trangthai, int httt, int htgh);

    List<HdHoaDonResponse1> getListbyTrangThaiandTime(int trangthai, Date time1, Date time2, int htgh);

    List<HdHoaDonResponse1> getListbyHinhthucandTime(int httt, Date time1, Date time2, int htgh);

    List<HdHoaDonResponse2> getList2byAllComboBox(int trangthai, int httt, Date time1, Date time2, int htgh);

    List<HdHoaDonResponse2> getList2byAllTT(int trangthai, int httt, int htgh);

    List<HdHoaDonResponse2> getList2byTrangThaiandTime(int trangthai, Date time1, Date time2, int htgh);

    List<HdHoaDonResponse2> getList2byHinhthucandTime(int httt, Date time1, Date time2, int htgh);

    boolean updateTTHoaDon(String id, String lydo);

    String findIdbyMa(String ma);

    HoaDon findHdByMa(String ma);

    boolean updateSoLuong(String id, Integer soLuong);

    String findIdSPbyHDCT(String id);

    List<HdHoaDonResponse1> getListHDNV(int htgh, String id);

    List<HdHoaDonResponse2> getListHDNV2(int htgh, String id);

    List<HdHoaDonResponse1> getListbyTextNV(int htgh, String input, String id);

    List<HdHoaDonResponse2> getListHD2byTextNV(String input, int htgh, String id);

    List<HdHoaDonResponse1> getListbyTrangThaiNV(int trangthai, int htgh, String id);

    List<HdHoaDonResponse2> getList2byTrangThaiNV(int trangthai, int htgh, String id);

    List<HdHoaDonResponse1> getListbyHinhThucThanhToanNV(int hinhThuc, int htgh, String id);

    List<HdHoaDonResponse2> getList2byHinhThucThanhToanNV(int hinhThuc, int htgh, String id);

    List<HdHoaDonResponse1> getListbyTimeNV(Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse2> getList2byTimeNV(Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse1> getListbyAllComboBoxNV(int trangthai, int httt, Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse1> getListbyAllTTNV(int trangthai, int httt, int htgh, String id);

    List<HdHoaDonResponse1> getListbyTrangThaiandTimeNV(int trangthai, Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse1> getListbyHinhthucandTimeNV(int httt, Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse2> getList2byAllComboBoxNV(int trangthai, int httt, Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse2> getList2byAllTTNV(int trangthai, int httt, int htgh, String id);

    List<HdHoaDonResponse2> getList2byTrangThaiandTimeNV(int trangthai, Date time1, Date time2, int htgh, String id);

    List<HdHoaDonResponse2> getList2byHinhthucandTimeNV(int httt, Date time1, Date time2, int htgh, String id);
}
