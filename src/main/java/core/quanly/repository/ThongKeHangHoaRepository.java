/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import domainmodels.HoaDon;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author quynhncph26201
 */
public class ThongKeHangHoaRepository {
//lấy ra danh sách sản phẩm bán được

    public List<ThongKeHangHoaResponse> getListHDCT() {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeHangHoaResponse"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia)"
                    + " from HoaDonChiTiet a ";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
//        String sDate1 = "01/01/2022";
//        String sDate2 = "30/12/2022";
//
//        SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
//
//        Date date11 = formatter1.parse(sDate1);
//        Date date21 = formatter2.parse(sDate2);
//        BigDecimal list = new ThongKeHangHoaRepository().getDoanhThuTheoKhoangThoiGian(date11, date21);
        List<ThongKeHangHoaResponse> list = new ThongKeHangHoaRepository().getListMaxValue();
        System.out.println(list);
    }
//Lấy ra doanh thu tất cả các năm

    public BigDecimal getDoanhThu() {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where  a.trangThai=2";
            Query query = session.createQuery(hql);
            nv = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nv;
    }

    //Doanh thu chi tiết từng năm
    public BigDecimal getDoanhThuNamChiTiet(int nam) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where  a.trangThai=2 and year(a.ngayThanhToan)=:date1";
            Query query = session.createQuery(hql);
            query.setParameter("date1", nam);
            nv = (BigDecimal) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return nv;
    }
//Lấy ra hóa đơn tất cả các năm

    public Long getHoaDon() {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a ";
            Query query = session.createQuery(hql);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
//số hóa đơn hủy tất cả các năm

    public Long getHoaDonHuy() {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1";
            Query query = session.createQuery(hql);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
//Lấy ra số khách hàng trong tất cả năm

    public Long getSoKhachHang() {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from KhachHang a where a.ma <> 'KH000'";
            Query query = session.createQuery(hql);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
//lấy ra doanh thu theo 1 ngày thanh ttoansf

    public BigDecimal getDoanhThuNam2(int date1) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where year(a.ngayThanhToan)=:date1 ";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            nv = (BigDecimal) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (nv == null) {
            nv = BigDecimal.valueOf(0.00);
        }
        return nv;
    }

    //lấy ra doanh thu với 2 mốc thời gian
    public BigDecimal getDoanhThuTheoKhoangThoiGian(Date date1, Date date2) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.ngayThanhToan between :date1 and :date2) and a.trangThai=2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            query.setParameter("date2", date2);
            nv = (BigDecimal) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (nv == null) {
            nv = BigDecimal.valueOf(0.00);
        }
        return nv;
    }
//Biểu đồ doanh thu Tháng

    public BigDecimal getDoanhThuThang(int thang, int nam) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", thang);
            query.setParameter("date2", nam);
            nv = (BigDecimal) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (nv == null) {
            nv = BigDecimal.valueOf(0.00);
        }
        return nv;
    }

    //doanh thu chi tiết tháng
    public BigDecimal getDoanhThuThangDoanhThuChiTiet(int thang, int nam) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2 and a.trangThai=2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", thang);
            query.setParameter("date2", nam);
            nv = (BigDecimal) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (nv == null) {
            nv = BigDecimal.valueOf(0.00);
        }
        return nv;
    }

    //Biểu đồ doanh thu các ngày trong 1 tháng
    public BigDecimal getDoanhThuNgayTrongThang(int ngay, int thang, int nam) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where day(a.ngayThanhToan)=:date and month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", thang);
            query.setParameter("date2", nam);
            query.setParameter("date", ngay);
            nv = (BigDecimal) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (nv == null) {
            nv = BigDecimal.valueOf(0.00);
        }
        return nv;
    }

    //số hóa đơn theo từng năm
    public Long getHoaDonTungNam(int date1) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where year(a.ngayThanhToan)=:date1 ";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    //số hóa đơn hủy từng năm
    public Long getHoaDonHuyTungNam(int date1) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and year(a.ngayThanhToan)=:date1";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
    //số khách hàng từng năm

    public Long getSoKhachHangTungNam(int date1) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.ma) from HoaDon a where year(a.ngayThanhToan)=:date1";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
//số khách hàng theo từng tháng

    public Long getKhachHangTungThang(int thang, int nam) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.ma) from HoaDon a where  month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2 ";
            Query query = session.createQuery(hql);
            query.setParameter("date1", thang);
            query.setParameter("date2", nam);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
    //số hóa đơn hủy từng tháng

    public Long getHoaDonHuyTungThang(int thang, int nam) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", thang);
            query.setParameter("date2", nam);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    //số hóa đơn theo từng tháng
    public Long getHoaDonTungThangTrongNam(int thang, int nam) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2 ";
            Query query = session.createQuery(hql);
            query.setParameter("date1", thang);
            query.setParameter("date2", nam);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    //số hóa đơn theo khoảng thời gian
    public Long getHoaDonTungKhoang(Date date1, Date date2) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.ngayThanhToan between :date1 and :date2 ";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            query.setParameter("date2", date2);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    //số hóa đơn hủy từng khoảng thời gian
    public Long getHoaDonHuyKhoang(Date date1, Date date2) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and (a.ngayThanhToan between :date1 and :date2)";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            query.setParameter("date2", date2);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
//khách hàng theo khoảng thời gian

    public Long getKhachHangTungKhoangTG(Date date1, Date date2) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.ma) from HoaDon a where a.ngayThanhToan between :date1 and :date2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            query.setParameter("date2", date2);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
//lọc sp bán chạy><không chạy

    public List<ThongKeHangHoaResponse> getListMaxValue() {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeHangHoaResponse"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia) from HoaDonChiTiet a where a.soLuong=(select max(a.soLuong) from HoaDonChiTiet a)";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<ThongKeHangHoaResponse> getListMinValue() {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeHangHoaResponse"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia) from HoaDonChiTiet a where a.soLuong=(select min(a.soLuong) from HoaDonChiTiet a)";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //tìm kiếm hàng hóa
    public List<ThongKeHangHoaResponse> TimKiemHangHoa(String input) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeHangHoaResponse"
                    + "(a.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, a.soLuong, a.donGia) from HoaDonChiTiet a where a.chiTietSPId.sanPham.ten like CONCAT('%',:input,'%') or "
                    + "a.chiTietSPId.sanPham.ma like CONCAT('%',:input,'%') or a.chiTietSPId.mauSac.ten like CONCAT('%',:input,'%') or a.chiTietSPId.kichThuoc.ten like CONCAT('%',:input,'%') or"
                    + " a.chiTietSPId.hang.ten like CONCAT('%',:input,'%') or a.donGia like CONCAT('%',:input,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
   

}
