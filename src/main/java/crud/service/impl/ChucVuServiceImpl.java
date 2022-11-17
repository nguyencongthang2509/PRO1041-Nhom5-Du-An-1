/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.ChucVuRepository;
import crud.service.ChucVuService;
import crud.viewmodel.ChucVuViewModel;
import domainmodels.ChucVu;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public class ChucVuServiceImpl implements ChucVuService {

    private ChucVuRepository repo = new ChucVuRepository();

    @Override
    public List<ChucVuViewModel> getList() {
        return repo.getList();
    }

    @Override
    public String insert(ChucVu c) {
        if (c.getMa().isEmpty()) {
            return "Mã ko đc trống";
        }
        if (repo.findbyMa(c.getMa()) != null) {
            return "Mã ko đc trùng";
        }
        if (c.getTen().isEmpty()) {
            return "Tên ko đc trống";
        }
        boolean check = repo.saveorUpdate(c);
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(ChucVu c) {
        if (c.getMa().isEmpty()) {
            return "Mã ko đc trống";
        }
        if (c.getTen().isEmpty()) {
            return "Tên ko đc trống";
        }
        boolean check = repo.saveorUpdate(c);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(String ma) {
        ChucVu c = repo.findbyMa(ma);
        boolean check = repo.delete(c);
        if (check) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }

    public static void main(String[] args) {
        ChucVuRepository rp = new ChucVuRepository();
        ChucVuService sv = new ChucVuServiceImpl();
        List<ChucVuViewModel> lst = sv.getList();
        for (ChucVuViewModel chucVuViewModel : lst) {
            System.out.println(chucVuViewModel.toString());
        }
    }
}
