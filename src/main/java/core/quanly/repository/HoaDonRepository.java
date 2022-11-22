/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.service.HoaDonservice;
import core.quanly.service.impl.HoaDonserviceImpl;
import core.quanly.viewmodel.HdHoaDonChiTietResponse;
import core.quanly.viewmodel.HdHoaDonResponse;
import domainmodels.HoaDon;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class HoaDonRepository extends CrudRepository<String, HoaDon, HdHoaDonResponse> {

    public List<HdHoaDonResponse> getListHoaDon() {
        List<HdHoaDonResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse"
                    + "(a.id, a.ma, a.hinhThucThanhToan, a.ngayTao, a.ngayThanhToan, b.ma, b.ten,"
                    + "c.ma, c.hoTen, c.sdt, c.diaChi, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse> getListbyTextField(String input) {
        List<HdHoaDonResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse"
                    + "(a.id, a.ma, a.hinhThucThanhToan, a.ngayTao, a.ngayThanhToan, b.ma, b.ten,"
                    + "c.ma, c.hoTen, c.sdt, c.diaChi, a.trangThai) from HoaDon a "
                    + "left join a.nhanVien b left join a.khachHang c where a.ma "
                    + "like CONCAT('%',:input, '%') or c.hoTen like CONCAT('%',:input, '%') or "
                    + "c.sdt like CONCAT('%',:input, '%') or c.diaChi like CONCAT('%',:input, '%') "
                    + "or c.email like CONCAT('%',:input, '%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse> getListbyTrangThai(int trangthai) {
        List<HdHoaDonResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse"
                    + "(a.id, a.ma, a.hinhThucThanhToan, a.ngayTao, a.ngayThanhToan, b.ma, b.ten,"
                    + "c.ma, c.hoTen, c.sdt, c.diaChi, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.trangThai = :trangthai";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse> getListbyHinhThucThanhToan(int hinhThuc) {
        List<HdHoaDonResponse> list = new ArrayList<>();
        try {
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse"
                    + "(a.id, a.ma, a.hinhThucThanhToan, a.ngayTao, a.ngayThanhToan, b.ma, b.ten,"
                    + "c.ma, c.hoTen, c.sdt, c.diaChi, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.hinhThucThanhToan = :hinhThuc";
            Query query = session.createQuery(hql);
            query.setParameter("hinhThuc", hinhThuc);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse> getListbyThang(int thang, int nam) {
        List<HdHoaDonResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse"
                    + "(a.id, a.ma, a.hinhThucThanhToan, a.ngayTao, a.ngayThanhToan, b.ma, b.ten,"
                    + "c.ma, c.hoTen, c.sdt, c.diaChi, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c "
                    + "where MONTH(a.ngayTao) = :thang and YEAR(a.ngayTao) = :nam";
            Query query = session.createQuery(hql);
            query.setParameter("thang", thang);
            query.setParameter("nam", nam);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonChiTietResponse> getListHDCT(String mahd) {
        List<HdHoaDonChiTietResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonChiTietResponse"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.soLuong,"
                    + " a.chiTietSPId.giaBan, a.giaBan) from HoaDonChiTiet a where a.hoaDonId.ma = :mahd";
            Query query = session.createQuery(hql);
            query.setParameter("mahd", mahd);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public static void main(String[] args) {
//        List<HdHoaDonResponse> list = new HoaDonRepository().getListbyTrangThai(2);
//            System.out.println(list);
//        
//    }
}
