/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.NhanVienResponse;
import domainmodels.NhanVien;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author quynhncph26201
 */
public class NhanVienRepository extends CrudRepository<String, NhanVien, NhanVienResponse> {

    public NhanVienRepository() {
        className = NhanVien.class.getName();
        res = "new core.quanly.viewmodel.NhanVienResponse(a.id,a.ma,a.ten,a.gioiTinh,"
                + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro,a.trangThaiXoa)";
    }

    public static void main(String[] args) {
        List<NhanVienResponse> lst = new NhanVienRepository().getAllResponse();
        System.out.println(lst);
    }

    public boolean updateMatKhau(String matKhau, String email) {
        int check = -1;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            String hql = "UPDATE nhan_vien SET mat_khau = :matKhau where email = :email";
            Query query = session.createNativeQuery(hql);
            query.setParameter("matKhau", matKhau);
            query.setParameter("email", email);
            check = query.executeUpdate();
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check > 0;
    }
    public List<NhanVienResponse> getAllResponseNhanVienNghi() {
        List<NhanVienResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.NhanVienResponse(a.id,a.ma,a.ten,a.gioiTinh,"
                    + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro,a.trangThaiXoa) FROM  NhanVien a where a.trangThaiXoa=1";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    public List<NhanVienResponse> getAllResponseNhanVienLam() {
        List<NhanVienResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.NhanVienResponse(a.id,a.ma,a.ten,a.gioiTinh,"
                    + "a.ngaySinh,a.diaChi,a.sdt,a.email,a.vaiTro,a.trangThaiXoa) FROM  NhanVien a where trangThaiXoa=0";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    public int genMaNhanVien() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,10))) from nhan_vien";
            NativeQuery query = session.createNativeQuery(nativeQuery);
            if (query.getSingleResult() != null) {
                maStr = query.getSingleResult().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }


}
