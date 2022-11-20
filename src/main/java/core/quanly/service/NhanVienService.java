/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.quanly.service;

import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author quynhncph26201
 */
public interface NhanVienService {

    List<NhanVienResponse> getAllResponse();

    String insert(NhanVien nhanVien);

    String update(NhanVien nhanVien);

    List<NhanVien> getAll();
}
