/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.NhanVienResponse;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import domainmodels.HoaDonChiTiet;
import java.util.List;

/**
 *
 * @author quynhncph26201
 */
public interface ThongKeHangHoaService {
    List<ThongKeHangHoaResponse> getAllResponse();
}
