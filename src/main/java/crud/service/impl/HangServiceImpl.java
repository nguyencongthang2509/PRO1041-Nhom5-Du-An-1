/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.HangRepository;
import crud.service.HangService;
import domainmodels.Hang;
import java.util.List;

/**
 *
 * @author HP
 */
public class HangServiceImpl implements HangService {

    private HangRepository hangRepository = new HangRepository();

    @Override
    public List<Hang> getAll() {
        return hangRepository.getAll();
    }

    @Override
    public Hang getOne(String ma) {
        return hangRepository.getOne(ma);
    }

    @Override
    public String add(Hang hang) {
        if (hang.getMa().isEmpty()) {
            return "Mã ko đc trống";
        }
        if (hang.getTen().isEmpty()) {
            return "Tên ko đc trống";
        }
        boolean check = hangRepository.add(hang);
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(Hang hang) {
        if (hang.getMa().isEmpty()) {
            return "Mã ko đc trống";
        }
        if (hang.getTen().isEmpty()) {
            return "Tên ko đc trống";
        }
        boolean check = hangRepository.update(hang);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(Hang hang) {
        boolean deleteH = hangRepository.delete(hang);
        if (deleteH) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }
}
