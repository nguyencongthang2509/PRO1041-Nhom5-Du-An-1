/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud.service.impl;

import crud.repository.SanPhamRepository;
import crud.service.SanPhamService;
import domainmodels.SanPham;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class SanPhamServiceImpl implements SanPhamService {

    private SanPhamRepository sanPhamReposirory = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return sanPhamReposirory.getAll();
    }

    @Override
    public SanPham getOne(String ma) {
        return sanPhamReposirory.getOne(ma);
    }

    @Override
    public String add(SanPham sanPham) {
        if (sanPham.getMa().isEmpty()) {
            return "Mã ko đc trống";
        }
        if (sanPham.getTen().isEmpty()) {
            return "Tên ko đc trống";
        }
        boolean check = sanPhamReposirory.add(sanPham);
        if (check) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(SanPham sanPham) {
        if (sanPham.getMa().isEmpty()) {
            return "Mã ko đc trống";
        }
        if (sanPham.getTen().isEmpty()) {
            return "Tên ko đc trống";
        }
        boolean check = sanPhamReposirory.update(sanPham);
        if (check) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(SanPham sanPham) {
        boolean deleteSp = sanPhamReposirory.delete(sanPham);
        if (deleteSp) {
            return "Xóa thành công";
        }
        return "Xóa thất bại";
    }
}
