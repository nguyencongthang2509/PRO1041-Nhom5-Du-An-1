
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.KhachHangLichSuRespone;
import core.quanly.viewmodel.KhachHangRespone;
import core.view.ViewKhachHang;
import domainmodels.KhachHang;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import lombok.Synchronized;
import org.hibernate.Session;
import repository.CrudRepository;

/**
 *
 * @author NgocAnh
 */
public class KhachHangRespository extends CrudRepository<String, KhachHang, KhachHangRespone> {

    public KhachHangRespository() {
        className = KhachHang.class.getName();
        res = "new core.quanly.viewmodel.KhachHangRespone(a.id,a.ma,a.hoTen,a.gioiTinh,a.sdt,a.diaChi,a.email,a.ngaySinh,a.capBac)";
    }

    public List<KhachHangRespone> findKhachHangByMaOrTen(String input) {
        List<KhachHangRespone> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KhachHangRespone(a.id,a.ma,a.hoTen,a.gioiTinh,a.sdt,a.diaChi,a.email,a.ngaySinh, a.capBac) FROM KhachHang a WHERE a.ma LIKE CONCAT('%',:input,'%') OR a.hoTen LIKE CONCAT('%',:input,'%') ";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangRespone> getLoadCbbGioiTinh(int gioiTinh) {
        List<KhachHangRespone> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KhachHangRespone(a.id,a.ma,a.hoTen,a.gioiTinh,a.sdt,a.diaChi,a.email,a.ngaySinh,a.capBac) FROM KhachHang a"
                    + " WHERE a.gioiTinh = :gioiTinh";
            Query query = session.createQuery(hql);
            query.setParameter("gioiTinh", gioiTinh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhachHangLichSuRespone> getKhachHangByLichSu(String id) {
        List<KhachHangLichSuRespone> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KhachHangLichSuRespone(b.id,b.hoTen,b.sdt,b.gioiTinh,a.ma,a.ngayThanhToan,a.thanhTien,a.trangThai,b.capBac) "
                    + "FROM HoaDon a LEFT JOIN a.khachHang b WHERE b.ma =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<KhachHangLichSuRespone> list = new KhachHangRespository().getKhachHangByLichSu("KH001");
        System.out.println(list);
    }

    public List<KhachHangLichSuRespone> getKhachHangByCapBac(String id) {
        List<KhachHangLichSuRespone> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT new core.quanly.viewmodel.KhachHangLichSuRespone(b.id,b.hoTen,b.sdt,b.gioiTinh,a.ma,a.ngayThanhToan,a.thanhTien,a.trangThai, b.capBac) "
                    + "FROM HoaDon a LEFT JOIN a.khachHang b WHERE b.ma =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public BigDecimal getTongTienByIdKhachHang(String id) {
        BigDecimal money = new BigDecimal(0);
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT sum(a.thanhTien) FROM HoaDon a where a.khachHang.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            money = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return money;
    }

    public List<KhachHang> getAllKhacHangDangHoatDong() {
        List<KhachHang> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM " + className + " a where a.trangThaiXoa = 0 AND a.ma <> 'KH000'";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
