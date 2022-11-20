/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.CTSanPhamRepository;
import core.quanly.service.CTSanPhamService;
import core.quanly.viewmodel.CTSanPhamViewModel;
import domainmodels.ChiTietSP;
import java.util.List;

/**
 *
 * @author HP
 */
public class CTSanPhamServiceImpl implements CTSanPhamService{

    private CTSanPhamRepository CTSanPhamRepository;
    
    @Override
    public List<CTSanPhamViewModel> getAllViewModel() {
        return CTSanPhamRepository.getAllResponse();
    }

    @Override
    public ChiTietSP getOne(String ma) {
        return CTSanPhamRepository.findByMa(ma);
    }

    @Override
    public String add(ChiTietSP ctsanPham) {
        if (ctsanPham.getMaChiTietSP().trim().isEmpty()) {
            return "Mã không được trống";
        }
        ChiTietSP sanPhamFind = CTSanPhamRepository.findByMa(ctsanPham.getMaChiTietSP());
        if (sanPhamFind != null) {
            return "Mã không được trùng";
        }
        ctsanPham = CTSanPhamRepository.saveOrUpdate(ctsanPham);
        if (ctsanPham != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(ChiTietSP ctsanPham) {
         ChiTietSP sanPhamFindById = CTSanPhamRepository.findById(ctsanPham.getId());
        if (sanPhamFindById == null) {
            return "Không tìm thấy sản phẩm";
        }
        if (ctsanPham.getMaChiTietSP().isEmpty()) {
            return "Mã không được để trống";
        }
        if (!ctsanPham.getMaChiTietSP().equals(sanPhamFindById.getMaChiTietSP())) {
            ChiTietSP sanPhamFindByMa = CTSanPhamRepository.findByMa(ctsanPham.getMaChiTietSP());
            if (sanPhamFindByMa != null) {
                return "Mã không được trùng";
            } else {
                sanPhamFindById.setMaChiTietSP(ctsanPham.getMaChiTietSP());
            }
        }
        ctsanPham = CTSanPhamRepository.saveOrUpdate(sanPhamFindById);
        if (ctsanPham != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<ChiTietSP> getAll() {
        return CTSanPhamRepository.getAll();
    }
    
}
