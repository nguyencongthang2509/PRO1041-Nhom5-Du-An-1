
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import config.HibernateUtil;
import core.quanly.viewmodel.ThongKeHangHoaResponse;
import core.quanly.viewmodel.ThongKeTraHangResponse;
import domainmodels.HoaDonTraHangChiTiet;
import domainmodels.NhanVien;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author quynhncph26201
 */
public class NhanVienThongKeAllService {

    NhanVien nv = new NhanVien();

    //Lable Thong Ke : Start
    public BigDecimal BaoCaoDT(Date dat1, String id) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.nhanVien.id =:id)and(a.trangThai=2 or a.trangThai = 5) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
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

    public Long BaoCaoHD(Date dat1, String idnv) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a  where (a.nhanVien.id =:id) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("id", idnv);
            query.setParameter("dat1", dat1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    public Long BaoCaoHDhuy(Date dat1, String idnv) {
        Long id = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from HoaDon a where (a.nhanVien.id =:id)and(a.trangThai=1) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("id", idnv);
            query.setParameter("dat1", dat1);
            id = (Long) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    public int BaoCaoKH(Date dat1, String idnv) {
        int id = 0;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select count(a.id) from khach_hang a inner join hoa_don b on a.id = b.id_khach_hang where(month(b.ngay_thanh_toan) = month(:dat1) \n"
                    + "  and year(b.ngay_thanh_toan)= year(:dat1) and day(b.ngay_thanh_toan)= day(:dat1)) and (b.id_nhan_vien =:id) ";
            Query query = session.createNativeQuery(hql);
            query.setParameter("id", idnv);
            query.setParameter("dat1", dat1);
            id = (int) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
//            return null;

        }
        return id;
    }
//End
    //Table:Start

    public List<ThongKeHangHoaResponse> getListHDCT(String id) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a inner join HoaDon b on a.hoaDonId=b.id where b.nhanVien.id=:id Group by a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<ThongKeHangHoaResponse> getSoLuong(String id) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a inner join HoaDon b on a.hoaDonId=b.id where b.nhanVien.id=:id Group by a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia order by sum(a.soLuong) desc
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<ThongKeTraHangResponse> getListTraHang(String id) {
        List<ThongKeTraHangResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeTraHangResponse(a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, sum(a.soLuongTra), a.giaBan) "
                    + "from HoaDonTraHangChiTiet a inner join HoaDon b on a.hoaDonDoiTra.nhanVien.id=b.nhanVien.id where b.nhanVien.id=:id "
                    + "group by a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, a.giaBan";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //End

    //Find Table:Start
    public List<ThongKeHangHoaResponse> TimKiemHangHoa(String input, String id) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a inner join HoaDon b on a.hoaDonId= b.id where (b.nhanVien.id=:id) and (a.chiTietSPId.sanPham.ten like CONCAT('%',:input,'%') or 
                    a.chiTietSPId.sanPham.ma like CONCAT('%',:input,'%') or a.chiTietSPId.mauSac.ten like CONCAT('%',:input,'%') or a.chiTietSPId.maChiTietSP like CONCAT('%',:input,'%') 
                         or a.chiTietSPId.kichThuoc.ten like CONCAT('%',:input,'%') or 
                    a.chiTietSPId.hang.ten like CONCAT('%',:input,'%') or a.donGia like CONCAT('%',:input,'%')) Group by a.chiTietSPId.id, a.chiTietSPId.maChiTietSP, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDonTraHangChiTiet> TimKiemHangHoaTra(String input, String id) {
        List<HoaDonTraHangChiTiet> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select a from HoaDonTraHangChiTiet a inner join HoaDon b on a.hoaDonDoiTra.nhanVien.id=b.nhanVien.id where (b.nhanVien.id=:id) and "
                    + "(a.maChiTietSanPham like CONCAT('%',:input,'%') or "
                    + "a.tenSP like CONCAT('%',:input,'%') or a.tenHang like CONCAT('%',:input,'%') or a.kichThuoc like CONCAT('%',:input,'%') or"
                    + " a.mauSac like CONCAT('%',:input,'%') or a.giaBan like CONCAT('%',:input,'%') or a.soLuongTra like CONCAT('%',:input,'%'))";
            Query query = session.createQuery(hql);
            query.setParameter("input", input);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //Find: End

    //Find Max-Min: Start
    public List<ThongKeHangHoaResponse> getListMaxValue(String id, long soluong) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a inner join HoaDon b on a.hoaDonId=b.id where b.nhanVien.id=:id 
                         Group by a.chiTietSPId.id, a.chiTietSPId.sanPham.ma, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia having SUM(a.soLuong) = :soluong
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("soluong", soluong);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<ThongKeTraHangResponse> getSoLuongTra(String id) {
        List<ThongKeTraHangResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeTraHangResponse(a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, sum(a.soLuongTra), a.giaBan) "
                    + "from HoaDonTraHangChiTiet a inner join HoaDon b on a.hoaDonDoiTra.nhanVien.id=b.nhanVien.id where b.nhanVien.id=:id "
                    + "group by a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, a.giaBan order by sum(a.soLuongTra) desc";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //Lọc SP trả Nhiều
    public List<ThongKeTraHangResponse> getListMaxValueTra(String id, long soluong) {
        List<ThongKeTraHangResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeTraHangResponse(a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, sum(a.soLuongTra), a.giaBan) "
                    + "from HoaDonTraHangChiTiet a inner join HoaDon b on a.hoaDonDoiTra.nhanVien.id=b.nhanVien.id where b.nhanVien.id=:id "
                    + "group by a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, a.giaBan having sum(a.soLuongTra) = :soluong";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("soluong", soluong);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    //Lọc SP trả Ít
    public List<ThongKeTraHangResponse> getListMinValueTra(String id, long soluong) {
        List<ThongKeTraHangResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select new core.quanly.viewmodel.ThongKeTraHangResponse(a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, sum(a.soLuongTra), a.giaBan) "
                    + "from HoaDonTraHangChiTiet a inner join HoaDon b on a.hoaDonDoiTra.nhanVien.id=b.nhanVien.id where b.nhanVien.id=:id "
                    + "group by a.maChiTietSanPham, a.tenSP, a.tenHang, a.mauSac, a.kichThuoc, a.giaBan having sum(a.soLuongTra) = :soluong";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("soluong", soluong);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //SP bán chậm

    public List<ThongKeHangHoaResponse> getListMinValue(String id, long soluong) {
        List<ThongKeHangHoaResponse> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSession();
            String hql = """
                         select new core.quanly.viewmodel.ThongKeHangHoaResponse
                         (a.chiTietSPId.id, a.chiTietSPId.sanPham.ma, a.chiTietSPId.sanPham.ten,
                         a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten, 
                        a.chiTietSPId.kichThuoc.ten, SUM(a.soLuong), a.donGia)
                         from HoaDonChiTiet a inner join HoaDon b on a.hoaDonId=b.id where b.nhanVien.id=:id 
                         Group by a.chiTietSPId.id, a.chiTietSPId.sanPham.ma, 
                         a.chiTietSPId.sanPham.ten, a.chiTietSPId.hang.ten, a.chiTietSPId.mauSac.ten,
                         a.chiTietSPId.kichThuoc.ten,a.donGia having SUM(a.soLuong) = :soluong
                         """;
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("soluong", soluong);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    //Find Max-Min:End

    //Get Doanh thu Nhan Vien fill Bieu Do: Start
    public BigDecimal getDoanhThuNgayNhanVien(Date date1, String id) {
        BigDecimal nv = null;
        try {
            Session session = HibernateUtil.getSession();
            String hql = "select sum(a.thanhTien) from HoaDon a where (a.nhanVien.id =:id)and(a.trangThai=2 or a.trangThai = 5) and (month(a.ngayThanhToan) = month(:dat1) "
                    + "and year(a.ngayThanhToan)= year(:dat1) and day(a.ngayThanhToan)= day(:dat1))";
            Query query = session.createQuery(hql);
            query.setParameter("dat1", date1);
            query.setParameter("id", id);
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
    //Get Doanh thu Nhan Vien fill Bieu Do: Start

    public static void main(String[] args) throws ParseException {
        Date vv = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
//        Date dd = fm.parse(vv);
        int index = 0;
//        BigDecimal bg = new NhanVienThongKeAllService().BaoCaoDT(dd, "7bdf7e91-47ef-4b62-976a-dbc08cd64324");
        BigDecimal bc = new NhanVienThongKeAllService().getDoanhThuNgayNhanVien(vv, "7bdf7e91-47ef-4b62-976a-dbc08cd64324");
        List<ThongKeHangHoaResponse> lst = new NhanVienThongKeAllService().getListMaxValue("7bdf7e91-47ef-4b62-976a-dbc08cd64324", 5);
        for (ThongKeHangHoaResponse xx : lst) {
            System.out.println(lst.get(index).getSoLuong());
            index++;
        }
        System.out.println(bc);
    }
    //FreeChart:Start

    public JFreeChart createChartTheoThang(Date date, String id) throws Exception {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU CỦA CỬA HÀNG NGÀY " + date,
                "Ngày", "Doanh số",
                createDatasetTheoNgayNhanVien(date, nv.getId()), PlotOrientation.VERTICAL, true, true, true);
        return barChart;
    }

    public CategoryDataset createDatasetTheoNgayNhanVien(Date date, String id) throws Exception {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(new Double(getDoanhThuNgayNhanVien(date, nv.getId()).doubleValue()), "Doanh số", date);

        return dataset;
    }
    //FreeChart:End

}
