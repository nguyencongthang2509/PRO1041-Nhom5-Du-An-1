/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import core.quanly.repository.NhanVienRepository;
import core.quanly.service.NhanVienService;
import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import infrastructure.constant.VaiTro;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author quynhncph26201
 */
public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository nhanVienRepos;

    public NhanVienServiceImpl() {
        nhanVienRepos = new NhanVienRepository();
    }

    @Override
    public List<NhanVienResponse> getAllResponse() {
        return nhanVienRepos.getAllResponse();
    }

    @Override
    public String insert(NhanVien nhanVien) {
        if (nhanVien.getMa().isEmpty()) {
            return "Ma khong de trong";
        }
        NhanVien FindNhanVien = nhanVienRepos.findByMa(nhanVien.getMa());
        if (FindNhanVien != null) {
            return "Trung ma nhan vien";
        }
        if (nhanVien.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (nhanVien.getDiaChi().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        if (nhanVien.getSdt().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (nhanVien.getEmail().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (nhanVien.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        if (nhanVienRepos.saveOrUpdate(nhanVien) == null) {
            return "Create failded";
        } else {
            return "Thêm thành công";
        }

    }

    @Override
    public String update(NhanVien nhanVien) {
        NhanVien nhanVienFindById = nhanVienRepos.findByMa(nhanVien.getId());
        if (nhanVienFindById == null) {
            return "Nhân viên không tồn tại";
        }
        if (nhanVien.getMa().isEmpty()) {
            return "Mã không được để trống";
        }
        if (!nhanVien.getMa().equals(nhanVienFindById.getMa())) {
            NhanVien nhanVienFindByMa = nhanVienRepos.findByMa(nhanVien.getMa());
            if (nhanVienFindByMa != null) {
                return "Trùng mã nhân viên";
            } else {
                nhanVienFindById.setMa(nhanVien.getMa());
            }
        }
        if (nhanVien.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (nhanVien.getDiaChi().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        if (nhanVien.getSdt().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (nhanVien.getEmail().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (nhanVien.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        nhanVienFindById.setTen(nhanVien.getTen());
        nhanVienFindById.setGioiTinh(nhanVien.getGioiTinh());
        nhanVienFindById.setNgaySinh(nhanVien.getNgaySinh());
        nhanVienFindById.setDiaChi(nhanVien.getDiaChi());
        nhanVienFindById.setSdt(nhanVien.getSdt());
        nhanVienFindById.setEmail(nhanVien.getEmail());
        nhanVienFindById.setMatKhau(nhanVien.getVaiTro().toString());
        if (nhanVienRepos.saveOrUpdate(nhanVienFindById) == null) {
            return "Lỗi. Sửa thất bại";
        } else {
            return "Sửa thành công";
        }

    }

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepos.getAll();
    }
    public static void main(String[] args) {
        NhanVienRepository rs  =new NhanVienRepository();
        NhanVienService cc =  new NhanVienServiceImpl();
        NhanVien nv = new NhanVien();
        
        
        nv.setMa("qq");
        nv.setTen("ww");
        nv.setSdt("ewrwe");
        nv.setDiaChi("sfsa");
        nv.setGioiTinh("wqeq");
        nv.setEmail("gdfd");
        nv.setNgaySinh(new java.util.Date());
        nv.setVaiTro(VaiTro.QUAN_LY);
        System.out.println(cc.insert(nv));
    }

}
