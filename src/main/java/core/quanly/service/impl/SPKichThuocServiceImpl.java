/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPKichThuocRepository;
import core.quanly.service.SPKichThuocService;
import core.quanly.viewmodel.SPKichThuocResponse;
import domainmodels.KichThuoc;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPKichThuocServiceImpl implements SPKichThuocService{

    private SPKichThuocRepository kichThuocRepository = new SPKichThuocRepository();

    public SPKichThuocServiceImpl() {
        kichThuocRepository = new SPKichThuocRepository();
    }
    
    @Override
    public List<SPKichThuocResponse> getAllViewModel() {
        return kichThuocRepository.getAllResponse();
    }

    @Override
    public KichThuoc getOne(String ma) {
        return kichThuocRepository.findByMa(ma);
    }

    @Override
    public String add(KichThuoc kichThuoc) {
        if (kichThuoc.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (kichThuoc.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        KichThuoc kichThuocFind = kichThuocRepository.findByMa(kichThuoc.getMa());
        if (kichThuocFind != null) {
            return "Mã không được trùng";
        }
        kichThuoc = kichThuocRepository.saveOrUpdate(kichThuoc);
        if (kichThuoc != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KichThuoc kichThuoc) {
        KichThuoc KmFindById = kichThuocRepository.findById(kichThuoc.getId());
        if (KmFindById == null) {
            return "Không tìm thấy kích thước";
        }
        if (kichThuoc.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (kichThuoc.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!kichThuoc.getMa().equals(KmFindById.getMa())) {
            KichThuoc hangFindByMa = kichThuocRepository.findByMa(kichThuoc.getMa());
            if (hangFindByMa != null) {
                return "Mã không được trùng";
            } else {
                KmFindById.setMa(kichThuoc.getMa());
            }
        }
        KmFindById.setTen(kichThuoc.getTen());
        kichThuoc = kichThuocRepository.saveOrUpdate(KmFindById);
        if (kichThuoc != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<KichThuoc> getAll() {
        return kichThuocRepository.getAll();
    }
    
}
