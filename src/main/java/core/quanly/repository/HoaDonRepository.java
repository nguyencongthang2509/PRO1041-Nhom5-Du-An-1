
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

    public long getCountHoaDon() {
        long count = 0;
        try {
            session = HibernateUtil.getSession();
            String hql = "select count(*) from HoaDon where hinhThucGiaoHang = 0";
            Query query = session.createQuery(hql);
            count = (long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<HdHoaDonResponse1> getListHoaDon(int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
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
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b where a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where a.hinhThucGiaoHang = :htgh and (a.ma "
                    + "like CONCAT('%',:input, '%') or c.hoTen like CONCAT('%',:input, '%') or c.email like CONCAT('%',:input, '%') "
                    + "or c.ma like CONCAT('%',:input, '%')) order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c where (a.ma like CONCAT('%',:input, '%') or c.hoTen like CONCAT('%',:input, '%') or c.email like CONCAT('%',:input, '%')"
                    + "or c.ma like CONCAT('%',:input, '%')) and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b where a.trangThai = :trangthai and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where a.trangThai = :trangthai and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where a.hinhThucThanhToan = :hinhThuc and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b where a.hinhThucThanhToan = :hinhThuc and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where (a.ngayTao between :time1 and :time2) and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b where (a.ngayTao between :time1 and :time2) and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
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
                    + "(a.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia, a.giaBan, a.giamGiaKhuyenMai)"
                    + " from HoaDonChiTiet a where a.hoaDonId.ma = :mahd order by a.createdDate desc";
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
                    + "(a.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia, a.giaBan, a.giamGiaKhuyenMai)"
                    + " from HoaDonChiTiet a where a.hoaDonId.ma = :mahd order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("mahd", mahd);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyAllComboBox(int trangthai, int httt, Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where a.trangThai = :trangthai and a.hinhThucThanhToan = :httt"
                    + " and (a.ngayTao between :time1 and :time2)  and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            query.setParameter("httt", httt);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyTrangThaiandTime(int trangthai, Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where a.trangThai = :trangthai"
                    + " and (a.ngayTao between :time1 and :time2)  and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyHinhthucandTime(int httt, Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where a.hinhThucThanhToan = :httt"
                    + " and (a.ngayTao between :time1 and :time2)  and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("htgh", htgh);
            query.setParameter("httt", httt);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse1> getListbyAllTT(int trangthai, int httt, int htgh) {
        List<HdHoaDonResponse1> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse1"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "c.hoTen, c.sdt, c.diaChi, a.lyDo, a.trangThai, a.phamTramGiamGia, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b left join a.khachHang c"
                    + " where a.trangThai = :trangthai and a.hinhThucThanhToan = :httt"
                    + " and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            query.setParameter("httt", httt);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //list2
    public List<HdHoaDonResponse2> getList2byAllComboBox(int trangthai, int httt, Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b"
                    + " where a.trangThai = :trangthai and a.hinhThucThanhToan = :httt"
                    + " and (a.ngayTao between :time1 and :time2)  and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            query.setParameter("httt", httt);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getList2byTrangThaiandTime(int trangthai, Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b"
                    + " where a.trangThai = :trangthai"
                    + " and (a.ngayTao between :time1 and :time2)  and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getList2byHinhthucandTime(int httt, Date time1, Date time2, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b"
                    + " where a.hinhThucThanhToan = :httt"
                    + " and (a.ngayTao between :time1 and :time2)  and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("htgh", htgh);
            query.setParameter("httt", httt);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HdHoaDonResponse2> getList2byAllTT(int trangthai, int httt, int htgh) {
        List<HdHoaDonResponse2> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.HdHoaDonResponse2"
                    + "(a.id, a.ma, a.ngayTao, a.ngayThanhToan, a.hinhThucThanhToan, a.thanhTien, a.tienKhachChuyenKhoan ,a.tienKhachTra, a.tienThua, b.ma, b.ten,"
                    + "a.tenNguoiNhan, a.sdtNguoiNhan, a.diaChi, a.sdtNguoiShip, a.tenNguoiShip, a.tienShip, a.ngayShip, a.ngayNhan, a.lyDo, a.trangThai, a.phamTramGiamGia, a.ngayMongMuon, a.maGiaoDich) from HoaDon a left join "
                    + "a.nhanVien b"
                    + " where a.trangThai = :trangthai and a.hinhThucThanhToan = :httt"
                    + " and a.hinhThucGiaoHang = :htgh order by a.createdDate desc";
            Query query = session.createQuery(hql);
            query.setParameter("trangthai", trangthai);
            query.setParameter("htgh", htgh);
            query.setParameter("httt", httt);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateTTHoaDon(String id, String lydo) {
        boolean check = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            String hql = "update HoaDon set trang_thai = 1 where id = :id";
            String hql2 = "update HoaDon set ly_do = :lydo where id = :id";
            Query query = session.createQuery(hql);
            Query query2 = session.createQuery(hql2);
            query.setParameter("id", id);
            query2.setParameter("id", id);
            query2.setParameter("lydo", lydo);
            query.executeUpdate();
            query2.executeUpdate();
            trans.commit();
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public String findIdByMa(String ma) {
        String id = "";
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a.id FROM HoaDon a WHERE a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if (query.getSingleResult() != null) {
                id = (String) query.getSingleResult();
            }
        } catch (Exception e) {
        }
        return id;
    }

    public HoaDon findHdByMa(String ma) {
        HoaDon h = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "select a from HoaDon a where a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if (query.getSingleResult() != null) {
                h = (HoaDon) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }

    public String findIdSPbyHDCT(String id) {
        String idsp = "";
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a.chiTietSPId.id FROM HoaDonChiTiet a WHERE a.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (query.getSingleResult() != null) {
                idsp = (String) query.getSingleResult();
            }
        } catch (Exception e) {
        }
        return idsp;
    }

    public boolean updateSoLuong(String id, Integer soLuong) {
        boolean check = false;
        String sql = "UPDATE ChiTietSP SET soLuongTon = soLuongTon + :soLuongMua WHERE id = :id";
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            Query query = session.createQuery(sql);
            query.setParameter("soLuongMua", soLuong);
            query.setParameter("id", id);
            query.executeUpdate();
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
    }

//    public static void main(String[] args) {
//        String id = new HoaDonRepository().findIdByMa("HD7");
//        System.out.println(id);
//    }
}
