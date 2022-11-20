/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.service.SanPhamService;
import core.quanly.viewmodel.SanPhamViewModel;
import domainmodels.SanPham;
import java.util.List;
import repository.impl.SanPhamRepository;

/**
 *
 * @author HP
 */
public class SanPhamserviceImpl implements SanPhamService{
    private SanPhamRepository sanPhamRepository;

    public SanPhamserviceImpl() {
        sanPhamRepository = new SanPhamRepository();
    }
    @Override
    public List<SanPhamViewModel> getAllViewModel() {
        return sanPhamRepository.getAllResponse();
    }

    @Override
    public SanPham getOne(String ma) {
        return sanPhamRepository.findByMa(ma);
    }

    @Override
    public String add(SanPham sanPham) {
        if (sanPham.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (sanPham.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        SanPham sanPhamFind = sanPhamRepository.findByMa(sanPham.getMa());
        if (sanPhamFind != null) {
            return "Mã không được trùng";
        }
        sanPham = sanPhamRepository.saveOrUpdate(sanPham);
        if (sanPham != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(SanPham sanPham) {
         SanPham sanPhamFindById = sanPhamRepository.findById(sanPham.getId());
        if (sanPhamFindById == null) {
            return "Không tìm thấy sản phẩm";
        }
        if (sanPham.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (sanPham.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!sanPham.getMa().equals(sanPhamFindById.getMa())) {
            SanPham sanPhamFindByMa = sanPhamRepository.findByMa(sanPham.getMa());
            if (sanPhamFindByMa != null) {
                return "Mã không được trùng";
            } else {
                sanPhamFindById.setMa(sanPham.getMa());
            }
        }
        sanPhamFindById.setTen(sanPham.getTen());
        sanPham = sanPhamRepository.saveOrUpdate(sanPhamFindById);
        if (sanPham != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }
    }

    
