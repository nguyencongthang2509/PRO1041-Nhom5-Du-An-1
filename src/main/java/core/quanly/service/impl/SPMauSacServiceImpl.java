/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPMauSacRepository;
import core.quanly.service.SPMauSacService;
import core.quanly.viewmodel.SPMauSacResponse;
import domainmodels.MauSac;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPMauSacServiceImpl implements SPMauSacService{
    
private SPMauSacRepository spMauSacRepo;

    public SPMauSacServiceImpl() {
        spMauSacRepo = new SPMauSacRepository();
    }
    @Override
    public List<SPMauSacResponse> getAllViewModel() {
        return spMauSacRepo.getAllResponse();
    }

    @Override
    public MauSac getOne(String ma) {
        return spMauSacRepo.findById(ma);
    }

    @Override
    public String add(MauSac mauSac) {
    if (mauSac.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (mauSac.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        mauSac = spMauSacRepo.saveOrUpdate(mauSac);
        if (mauSac != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }}

    @Override
    public String update(MauSac mauSac) {
         MauSac sanPhamFindById = spMauSacRepo.findById(mauSac.getId());
        if (sanPhamFindById == null) {
            return "Không tìm thấy màu sắc";
        }
        if (mauSac.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (mauSac.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!mauSac.getMa().equals(sanPhamFindById.getMa())) {
            MauSac sanPhamFindByMa = spMauSacRepo.findByMa(mauSac.getMa());
            if (sanPhamFindByMa != null) {
                return "Mã không được trùng";
            } else {
                sanPhamFindById.setMa(mauSac.getMa());
            }
        }
        sanPhamFindById.setTen(mauSac.getTen());
        mauSac = spMauSacRepo.saveOrUpdate(sanPhamFindById);
        if (mauSac != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<MauSac> getAll() {
        return spMauSacRepo.getAll();
    }
    
}
