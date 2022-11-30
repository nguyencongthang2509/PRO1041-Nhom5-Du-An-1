/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.SPHangRepository;
import core.quanly.viewmodel.SPHangResponse;
import domainmodels.Hang;
import java.util.List;
import core.quanly.service.SPHangService;

/**
 *
 * @author HP
 */
public class SPHangServiceImpl implements SPHangService {

    private SPHangRepository hangRepository = new SPHangRepository();

    public SPHangServiceImpl() {
        hangRepository = new SPHangRepository();
    }

    @Override
    public List<SPHangResponse> getAllViewModel() {
        return hangRepository.getAllResponse();
    }

    @Override
    public Hang getOne(String ma) {
        return hangRepository.findByMa(ma);
    }

    @Override
    public String add(Hang hang) {
        if (hang.getMa().trim().isEmpty()) {
            return "Mã không được trống";
        }
        if (hang.getTen().trim().isEmpty()) {
            return "Tên không được trống";
        }
        Hang HangFind = hangRepository.findByMa(hang.getMa());
        if (HangFind != null) {
            return "Mã không được trùng";
        }
        hang = hangRepository.saveOrUpdate(hang);
        if (hang != null) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(Hang hang) {
    Hang HangFindById = hangRepository.findById(hang.getId());
        if (HangFindById == null) {
            return "Không tìm thấy hãng";
        }
        if (hang.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (hang.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (!hang.getMa().equals(HangFindById.getMa())) {
            Hang hangFindByMa = hangRepository.findByMa(hang.getMa());
            if (hangFindByMa != null) {
                return "Mã không được trùng";
            } else {
                HangFindById.setMa(hang.getMa());
            }
        }
        HangFindById.setTen(hang.getTen());
        hang = hangRepository.saveOrUpdate(HangFindById);
        if (hang != null) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public List<Hang> getAll() {
        return hangRepository.getAll();
    }

    @Override
    public int genMaHangTuDong() {
        return hangRepository.genMaHang();
    }

}
