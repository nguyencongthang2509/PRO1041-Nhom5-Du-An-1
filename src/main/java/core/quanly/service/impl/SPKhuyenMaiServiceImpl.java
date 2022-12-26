/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;


import core.quanly.repository.SPKhuyenMaiRepository;
import core.quanly.service.SPKhuyenMaiService;
import core.quanly.viewmodel.SPKhuyenMaiResponse;
import domainmodels.KhuyenMai;
import java.util.List;

/**
 *
 * @author HP
 */
public class SPKhuyenMaiServiceImpl implements SPKhuyenMaiService{
    
    private SPKhuyenMaiRepository khuyenMaiRepository = new SPKhuyenMaiRepository();

    public SPKhuyenMaiServiceImpl() {
        khuyenMaiRepository = new SPKhuyenMaiRepository();
    }

    @Override
    public List<SPKhuyenMaiResponse> getAllViewModel() {
        return khuyenMaiRepository.getAllResponse();
    }

    @Override
    public KhuyenMai getOne(String ma) {
        return khuyenMaiRepository.findByMa(ma);
    }

    @Override
    public String add(KhuyenMai khuyenMai) {
    if (khuyenMai.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (khuyenMai.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        KhuyenMai HangFind = khuyenMaiRepository.findByMa(khuyenMai.getMa());
        if (HangFind != null) {
            return "Mã không được trùng";
        }
        khuyenMai = khuyenMaiRepository.saveOrUpdate(khuyenMai);
        if (khuyenMai != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(KhuyenMai khuyenMai) {
        KhuyenMai KmFindById = khuyenMaiRepository.findById(khuyenMai.getId());
        if (KmFindById == null) {
            return "Không tìm thấy sản phẩm";
        }
        if (khuyenMai.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (khuyenMai.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!khuyenMai.getMa().equals(KmFindById.getMa())) {
            KhuyenMai hangFindByMa = khuyenMaiRepository.findByMa(khuyenMai.getMa());
            if (hangFindByMa != null) {
                return "Mã không được trùng";
            } else {
                KmFindById.setMa(khuyenMai.getMa());
            }
        }
        KmFindById.setTen(khuyenMai.getTen());
        khuyenMai = khuyenMaiRepository.saveOrUpdate(KmFindById);
        if (khuyenMai != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }}

    @Override
    public List<KhuyenMai> getAll() {
        return khuyenMaiRepository.getAll();
    }
    
}
