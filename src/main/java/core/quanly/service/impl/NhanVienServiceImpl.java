/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.service.impl;

import config.HibernateUtil;
import core.quanly.repository.NhanVienRepository;
import core.quanly.service.NhanVienService;
import core.quanly.viewmodel.NhanVienNghiResponse;
import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import java.util.ArrayList;
//import infrastructure.constant.VaiTro;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author quynhncph26201
 */
public class NhanVienServiceImpl implements NhanVienService {

    private NhanVienRepository nhanVienRepos;
    protected Session session;

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
        NhanVien FindNhanVienByMa = nhanVienRepos.findByMa(nhanVien.getMa());
        if (FindNhanVienByMa != null) {
            return "Trung ma nhan vien";
        }

        if (nhanVien.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (nhanVien.getTen().length() > 100) {
            return "Tên không quá 100 ký tự";
        }
        if (nhanVien.getTen().matches("[a-zA-Z][a-zA-Z ]*")) {
            return "Tên phải là chữ";
        }
        if (nhanVien.getDiaChi().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        if (nhanVien.getDiaChi().length() > 125) {
            return "Địa Chỉ không được quá 125 ký tự";
        }
        if (nhanVien.getSdt().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (nhanVien.getEmail().isEmpty()) {
            return "Email không được để trống";
        }
        if (nhanVien.getEmail().length()>50) {
            return "Email không được quá 50 ký tự";
        }
        if (nhanVien.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        if (!nhanVien.getEmail().matches("\\w+@{1}\\w+.+\\w")) {
            return "Sai định dạng email";
        }
        if (nhanVienRepos.saveOrUpdate(nhanVien) == null) {
            return "Create failded";
        } else {
            return "Thêm thành công";
        }

    }

    @Override
    public String update(NhanVien nhanVien) {
        NhanVien nhanVienFindById = nhanVienRepos.findById(nhanVien.getId());

        System.out.println(nhanVien.getTrangThaiXoa() + "ahshduihasuhdiuashidasd");
        if (nhanVienFindById == null) {
            return "Nhân viên không tồn tại";
        }
        if (nhanVien.getTen().isEmpty()) {
            return "Tên không được để trống";
        }
        if (nhanVien.getTen().length() > 100) {
            return "Tên không quá 100 ký tự";
        }
        if (nhanVien.getTen().matches("[a-zA-Z][a-zA-Z ]*")) {
            return "Tên phải là chữ";
        }
        if (nhanVien.getDiaChi().isEmpty()) {
            return "Địa chỉ không được để trống";
        }
        if (nhanVien.getDiaChi().length() > 125) {
            return "Địa Chỉ không được quá 125 ký tự";
        }
        if (nhanVien.getSdt().isEmpty()) {
            return "Số điện thoại không được để trống";
        }
        if (nhanVien.getEmail().isEmpty()) {
            return "Email không được để trống";
        }
        if (nhanVien.getEmail().length()>50) {
            return "Email không được quá 50 ký tự";
        }
        if (nhanVien.getSdt().matches("^0{1}\\d{10}$")) {
            return "Số điện thoại phải là số và gồm 10 ký tự";
        }
        if (!nhanVien.getEmail().matches("\\w+@{1}\\w+.+\\w")) {
            return "Sai định dạng email";
        }
        nhanVienFindById.setTen(nhanVien.getTen());
        nhanVienFindById.setGioiTinh(nhanVien.getGioiTinh());
        nhanVienFindById.setNgaySinh(nhanVien.getNgaySinh());
        nhanVienFindById.setDiaChi(nhanVien.getDiaChi());
        nhanVienFindById.setSdt(nhanVien.getSdt());
        nhanVienFindById.setEmail(nhanVien.getEmail());
        nhanVienFindById.setVaiTro(nhanVien.getVaiTro());
        nhanVienFindById.setTrangThaiXoa(nhanVien.getTrangThaiXoa());

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

    @Override
    public List<NhanVienResponse> GetAllByMa(String ma) {

        return getListbyMaorTen(ma);

    }

    public List<NhanVienResponse> getListbyMaorTen(String input) {
        List<NhanVienResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.NhanVienResponse(a.id,a.ma,a.ten,a.gioiTinh,"
                    + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro,a.trangThaiXoa) from NhanVien a where a.ten like CONCAT('%',:input,'%') or "
                    + "a.ma like CONCAT('%',:input,'%') or a.email like CONCAT('%',:input,'%') or a.diaChi like CONCAT('%',:input,'%') or"
                    + " a.sdt like CONCAT('%',:input,'%') or a.ngaySinh like CONCAT('%',:input,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<NhanVienNghiResponse> getListbyAll(String input) {
        List<NhanVienNghiResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.NhanVienNghiResponse(a.id,a.ma,a.ten,a.gioiTinh,"
                    + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro,a.trangThaiXoa) from NhanVien a where (a.ten like CONCAT('%',:input,'%') or "
                    + "a.ma like CONCAT('%',:input,'%') or a.email like CONCAT('%',:input,'%') or a.diaChi like CONCAT('%',:input,'%') or"
                    + " a.sdt like CONCAT('%',:input,'%') or a.ngaySinh like CONCAT('%',:input,'%')) and a.trangThaiXoa=1";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getNhanVienByEmail(String input) {
        String id = "";
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a.id from NhanVien a where a.email = :input";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            id = (String) query.getSingleResult();
        } catch (Exception e) {
        }
        return id;
    }
    public NhanVien getNhanVienByEmail2(String input) {
        NhanVien list = new NhanVien();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from NhanVien a where a.email = :input";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = (NhanVien) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String updateMatKhau(String matKhau, String email) {
        boolean check = nhanVienRepos.updateMatKhau(matKhau, email);
        if (check) {
            return "Đổi mật khẩu thành công";
        }
        return "Đổi mật khẩu thất bại";
    }

    @Override
    public List<NhanVienNghiResponse> getAllResponseNghi() {
        return nhanVienRepos.getAllResponseNhanVienNghi();
    }

    @Override
    public List<NhanVienResponse> getAllResponseLam() {
        return nhanVienRepos.getAllResponseNhanVienLam();
    }

    public List<NhanVienResponse> getAllR() {
        List<NhanVienResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.NhanVienResponse(a.id,a.ma,a.ten,a.gioiTinh,"
                    + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro,a.trangThaiXoa) FROM NhanVien a order by a.createdDate desc";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<NhanVienNghiResponse> xx = new NhanVienServiceImpl().getListbyAll("N");
        System.out.println(xx);
    }

}
