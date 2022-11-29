/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.service.HoaDonservice;
import core.quanly.service.impl.HoaDonserviceImpl;
import core.quanly.viewmodel.HdHoaDonChiTietResponse1;
import core.quanly.viewmodel.HdHoaDonResponse1;
import core.quanly.viewmodel.HdHoaDonResponse2;
import domainmodels.HoaDon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class HoaDonRepository extends CrudRepository<String, HoaDon, HdHoaDonResponse1> {

    public List<HdHoaDonResponse1> getListHoaDon(int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.hinhThucGiaoHang = :htgh"
                    + " order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getListHoaDon2(int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyText(int htgh, String input) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, "
                    + "a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.trangThai) from HoaDon a left join a.nhanVien b left join a.khachHang c where "
                    + "a.hinhThucGiaoHang = :htgh and (a.ma "
                    + "like CONCAT('%',:input, '%') or c.hoTen like CONCAT('%',:input, '%') or c.email like CONCAT('%',:input, '%') "
                    + "or c.ma like CONCAT('%',:input, '%'))";
            Query query = session.createQuery(hql);
            query.setParameter("htgh", htgh);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getListHoaDon2byText(String input, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where (a.ma like CONCAT('%',:input, '%') or c.hoTen like CONCAT('%',:input, '%') or c.email like CONCAT('%',:input, '%')"
                    + "or c.ma like CONCAT('%',:input, '%')) and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getList2byTrangThai(int trangthai, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.trangThai = :trangthai and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyTrangThai(int trangthai, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, "
                    + "a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.trangThai) from HoaDon a "
                    + "left join a.nhanVien b left join a.khachHang c"
                    + " where a.trangThai = :trangthai and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyHinhThucThanhToan(int hinhThuc, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, "
                    + "a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.trangThai) from HoaDon a "
                    + "left join a.nhanVien b left join a.khachHang c"
                    + " where a.hinhThucThanhToan = :hinhThuc and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("hinhThuc", hinhThuc);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getList2byHinhThucThanhToan(int hinhThuc, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.hinhThucThanhToan = :hinhThuc and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("hinhThuc", hinhThuc);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyTime(Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, "
                    + "a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.trangThai) from HoaDon a "
                    + "left join a.nhanVien b left join a.khachHang c"
                    + " where (a.ngayTao between :time1 and :time2) and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getList2byTime(Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.sdt, c.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.trangThai) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where (a.ngayTao between :time1 and :time2) and a.hinhThucGiaoHang = :htgh";
            Query query = session.createQuery(hql);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            query.setParameter("htgh", htgh);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonChiTietResponse1> getListHDCT(String mahd) {
        List<HdHoaDonChiTietResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonChiTietResponse1"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia, a.giaBan, a.giamGiaKhuyenMai)"
                    + " from HoaDonChiTiet a where a.hoaDonId.ma = :mahd";
            Query query = session.createQuery(hql);
            query.setParameter("mahd", mahd);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonChiTietResponse1> getListHDCT2(String mahd) {
        List<HdHoaDonChiTietResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonChiTietResponse1"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia, a.giaBan, a.giamGiaKhuyenMai)"
                    + " from HoaDonChiTiet a where a.hoaDonId.ma = :mahd";
            Query query = session.createQuery(hql);
            query.setParameter("mahd", mahd);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<HdHoaDonChiTietResponse1> list = new HoaDonRepository().getListHDCT("HD02");
        System.out.println(list);
    }
}
