/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.KhachHangRespository;
import core.quanly.service.KhachHangService;
import core.quanly.viewmodel.KhachHangRespone;
import domainmodels.KhachHang;
import java.util.List;

/**
 *
 * @author NgocAnh
 */
public class KhachHangServiceImpl implements KhachHangService {

    private KhachHangRespository khachHangRe = new KhachHangRespository();

    @Override
    public List<KhachHangRespone> getAllResponse() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return khachHangRe.getAllResponse();
    }

    @Override
    public String insert(KhachHang khachHang) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        KhachHang khachHangFind = khachHangRe.findByMa(khachHang.getMa());
        if (khachHangFind != null) {
            return "Mã không được trùng";
        }
        if (khachHang.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (khachHang.getHoTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        if (khachHang.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (khachHang.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }

        if (khachHang.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được trống";
        }
        
         if (khachHang.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        

        khachHang = khachHangRe.saveOrUpdate(khachHang);
        if (khachHang != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KhachHang khachHang) {
        KhachHang khachHangFindById = khachHangRe.findById(khachHang.getId());
        if (khachHangFindById == null) {
            return "Không tìm thấy";
        }
        if (khachHang.getMa().isEmpty()) {
            return "Mã trống";
        }
        if (khachHang.getHoTen().isEmpty()) {
            return "Tên trống";
        }
        if (khachHang.getDiaChi().trim().isEmpty()) {
            return "Địa chỉ không được trống";
        }
        if (khachHang.getEmail().trim().isEmpty()) {
            return "Email không được trống";
        }

        if (khachHang.getSdt().trim().isEmpty()) {
            return "Số điện thoại không được trống";
        }
        if (!khachHang.getMa().equals(khachHangFindById.getMa())) {
            KhachHang khachHangFindByMa = khachHangRe.findByMa(khachHang.getMa());
            if (khachHangFindByMa != null) {
                return "Mã không được trùng";
            } else {
                khachHangFindById.setMa(khachHang.getMa());
            }
        }

        khachHangFindById.setHoTen(khachHang.getHoTen());
        khachHangFindById.setSdt(khachHang.getSdt());
        khachHangFindById.setNgaySinh(khachHang.getNgaySinh());
        khachHangFindById.setDiaChi(khachHang.getDiaChi());
        khachHangFindById.setGioiTinh(khachHang.getGioiTinh());
        
        khachHang = khachHangRe.saveOrUpdate(khachHangFindById);
        if (khachHang != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

//    @Override
//    public List<KhachHang> getAll() {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        return khachHangRe.getAll();
//    }
    @Override
    public List<KhachHangRespone> findKhachHangByMaOrTen(String input) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return khachHangRe.findKhachHangByMaOrTen(input);
    }

}
