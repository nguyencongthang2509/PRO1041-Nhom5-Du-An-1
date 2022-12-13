/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import core.quanly.viewmodel.ThongKeTraHangResponse;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.HoaDonTraHangChiTiet;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
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
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a Group by a.chiTietSPId.id, a.chiTietSPId.sanPham.ma, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia
                         """;
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<ThongKeHangHoaResponse> list = new ThongKeHangHoaRepository().getListHDCT();
        System.out.println(list);
    }
//lấy ra danh sách sản phẩm đã trả

    public List<ThongKeTraHangResponse> getListTraHang() {
        List<ThongKeTraHangResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeTraHangResponse(a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, sum(a.soLuongTra), a.giaBan) "
                    + "from HoaDonTraHangChiTiet a group by a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, a.giaBan";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

//Lấy ra doanh thu tất cả các năm
    public BigDecimal getDoanhThu() {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where a.trangThai = 2 OR a.trangThai = 5";
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
            String hql = "select sum(a.thanhTien) from HoaDon a where  (a.trangThai=2 or a.trangThai = 5) and year(a.ngayThanhToan)=:date1";
            Query query = session.createQuery(hql);
            query.setParameter("date1", nam);
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
//Lấy ra hóa đơn tất cả các năm

    public Long getHoaDon() {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.ngayThanhToan is not null";
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
            String hql = "select sum(a.thanhTien) from HoaDon a where year(a.ngayThanhToan)=:date1  AND (a.trangThai = 2 OR a.trangThai = 5)";
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
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.ngayThanhToan between :date1 and :date2) and (a.trangThai=2 OR a.trangThai = 5)";
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
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.trangThai = 2 OR a.trangThai = 5) AND month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2";
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
            String hql = "select sum(a.thanhTien) from HoaDon a where month(a.ngayThanhToan)=:date1 and year(a.ngayThanhToan)=:date2 and (a.trangThai=2 OR a.trangThai=5)";
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
            String hql = "select count(a.id) from HoaDon a where year(a.ngayThanhToan)=:date1";
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
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and year(a.ngayTao)=:date1";
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
            String dateStr = "01-01-" + date1;
            String dateStr1 = "31-12-" + date1;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Long time1 = sdf.parse(dateStr).getTime();
            Long time2 = sdf.parse(dateStr1).getTime();
            Session session = HibernateUtil.getSession();
            String hql = "select count(id) FROm KhachHang where ma <> 'KH000' AND createdDate > :time1 AND createdDate < :time2";
            Query query = session.createQuery(hql);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    public Long getKhachHangTungThang(int thang, int nam) {
        Long id = null;
        try {
            Long time1 = null;
            Long time2 = null;
            String dateStr = "01-" + thang + "-" + nam;
            String dateStr1 = "";
            if (thang == 1 || thang == 3 || thang == 5 || thang == 7 || thang == 8 || thang == 10 || thang == 12) {
                dateStr1 = "31-" + thang + "-" + nam;
            } else if (thang == 4 || thang == 6 || thang == 9 || thang == 11) {
                dateStr1 = "30-" + thang + "-" + nam;
            } else {
                dateStr1 = "28-" + thang + "-" + nam;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            time1 = sdf.parse(dateStr).getTime();
            time2 = sdf.parse(dateStr1).getTime();
            Session session = HibernateUtil.getSession();
            String hql = "select count(id) from KhachHang where ma <> 'KH000' AND createdDate > :time1 AND createdDate < :time2";
            Query query = session.createQuery(hql);
            query.setParameter("time1", time1);
            query.setParameter("time2", time2);
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
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and month(a.ngayTao)=:date1 and year(a.ngayTao)=:date2";
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
        if (id == null) {
            id = Long.valueOf("0");
        }
        return id;
    }

    //số hóa đơn hủy từng khoảng thời gian
    public Long getHoaDonHuyKhoang(Date date1, Date date2) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and (a.ngayTao between :date1 and :date2)";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1);
            query.setParameter("date2", date2);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (id == null) {
            id = Long.valueOf("0");
        }
        return id;
    }
//khách hàng theo khoảng thời gian

    public Long getKhachHangTungKhoangTG(Date date1, Date date2) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(id) from KhachHang where ma <> 'KH000' AND createdDate between :date1 AND :date2";
            Query query = session.createQuery(hql);
            query.setParameter("date1", date1.getTime());
            query.setParameter("date2", date2.getTime());
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (id == null) {
            id = Long.valueOf("0");
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
                    + "a.chiTietSPId.kichThuoc.ten, sum(a.soLuong), a.donGia) from HoaDonChiTiet a where a.soLuong=(select max(a.soLuong) from HoaDonChiTiet a) Group by a.id, a.chiTietSPId.id, a.chiTietSPId.maChiTietSP,a.chiTietSPId.sanPham.ma, \n" +
"                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,\n" +
"                         a.chiTietSPId.kichThuoc.ten,a.donGia";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //Lọc SP trả Nhiều
    public List<HoaDonTraHangChiTiet> getListMaxValueTra() {
        List<HoaDonTraHangChiTiet> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from HoaDonTraHangChiTiet a where a.soLuongTra=(select max(soLuongTra) from HoaDonTraHangChiTiet )";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //Lọc SP trả Ít
    public List<HoaDonTraHangChiTiet> getListMinValueTra() {
        List<HoaDonTraHangChiTiet> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from HoaDonTraHangChiTiet a where a.soLuongTra=(select min(a.soLuongTra) from HoaDonTraHangChiTiet a)";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
//SP bán chậm

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
                    + "(a.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, "
                    + "a.chiTietSPId.kichThuoc.ten, sum(a.soLuong), a.donGia) from HoaDonChiTiet a where a.chiTietSPId.sanPham.ten like CONCAT('%',:input,'%') or "
                    + "a.chiTietSPId.sanPham.ma like CONCAT('%',:input,'%') or a.chiTietSPId.mauSac.ten like CONCAT('%',:input,'%') or a.chiTietSPId.maChiTietSP like CONCAT('%',:input,'%') or a.chiTietSPId.kichThuoc.ten like CONCAT('%',:input,'%') or"
                    + " a.chiTietSPId.hang.ten like CONCAT('%',:input,'%') or a.donGia like CONCAT('%',:input,'%') Group by a.id, a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten,\n"
                    + "                         a.chiTietSPId.maChiTietSP, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,\n"
                    + "                         a.chiTietSPId.kichThuoc.ten,a.donGia";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //tìm kiếm hàng trả

    public List<HoaDonTraHangChiTiet> TimKiemHangHoaTra(String input) {
        List<HoaDonTraHangChiTiet> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from HoaDonTraHangChiTiet a where a.maChiTietSanPham like CONCAT('%',:input,'%') or "
                    + "a.tenSP like CONCAT('%',:input,'%') or a.tenHang like CONCAT('%',:input,'%') or a.kichThuoc like CONCAT('%',:input,'%') or"
                    + " a.mauSac like CONCAT('%',:input,'%') or a.giaBan like CONCAT('%',:input,'%') or a.soLuongTra like CONCAT('%',:input,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //doanh thu ngày
    public BigDecimal BaoCaoDT(Date dat1) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.trangThai=2 or a.trangThai = 5) and month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1)";
            Query query = session.createQuery(hql);
            query.setParameter("dat1", dat1);
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

    //hoá đơn ngày
    public Long BaoCaoHD(Date dat1) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(id) from HoaDon  where month(ngayThanhToan) = month(:dat1) "
                    + "and year(ngayThanhToan)= year(:dat1) and day(ngayThanhToan)= day(:dat1)";
            Query query = session.createQuery(hql);
            query.setParameter("dat1", dat1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    //hoá đơn huỷ ngày
    public Long BaoCaoHDhuy(Date dat1) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where a.trangThai=1 and month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1)";
            Query query = session.createQuery(hql);
            query.setParameter("dat1", dat1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }
    //khách hàng ngày

    public int BaoCaoKH(Date dat1) {
        int id = 0;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from khach_hang a inner join hoa_don b on a.id = b.id_khach_hang where(month(b.ngay_thanh_toan) = month(:dat1) \n"
                    + "  and year(b.ngay_thanh_toan)= year(:dat1) and day(b.ngay_thanh_toan)= day(:dat1)) ";
            Query query = session.createNativeQuery(hql);
            query.setParameter("dat1", dat1);
            id = (int) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return id;
    }

}
