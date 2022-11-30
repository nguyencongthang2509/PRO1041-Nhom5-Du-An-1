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
    
    private SPMauSacRepository mausacRepository = new SPMauSacRepository();

    public SPMauSacServiceImpl() {
        mausacRepository = new SPMauSacRepository();
    }

    @Override
    public List<SPMauSacResponse> getAllViewModel() {
        return mausacRepository.getAllResponse();
    }

    @Override
    public MauSac getOne(String ma) {
        return mausacRepository.findByMa(ma);
    }

    @Override
    public String add(MauSac mauSac) {
        if (mauSac.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (mauSac.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        MauSac kichThuocFind = mausacRepository.findByMa(mauSac.getMa());
        if (kichThuocFind != null) {
            return "Mã không được trùng";
        }
        mauSac = mausacRepository.saveOrUpdate(mauSac);
        if (mauSac != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(MauSac mauSac) {
        MauSac KmFindById = mausacRepository.findById(mauSac.getId());
        if (KmFindById == null) {
            return "Không tìm thấy màu sắc";
        }
        if (mauSac.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (mauSac.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!mauSac.getMa().equals(KmFindById.getMa())) {
            MauSac hangFindByMa = mausacRepository.findByMa(mauSac.getMa());
            if (hangFindByMa != null) {
                return "Mã không được trùng";
            } else {
                KmFindById.setMa(mauSac.getMa());
            }
        }
        KmFindById.setTen(mauSac.getTen());
        mauSac = mausacRepository.saveOrUpdate(KmFindById);
        if (mauSac != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<MauSac> getAll() {
        return mausacRepository.getAll();
    }
    
}
