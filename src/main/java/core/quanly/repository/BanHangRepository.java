package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import domainmodels.ChiTietSP;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
import domainmodels.KhachHang;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class BanHangRepository extends CrudRepository<String, ChiTietSP, BhChiTietSPResponse> {

    public BanHangRepository() {
        className = ChiTietSP.class.getName();
        res = "new core.quanly.viewmodel.BhChiTietSPResponse(a.id, a.sanPham.ma, a.sanPham.ten, a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, a.khuyenMai.loaiKhuyenMai, a.khuyenMai.giaTri, a.giaBan, a.moTa, a.maVach)";
    }

    public List<BhChiTietSPResponse> getAllResponseCTSP() {
        List<BhChiTietSPResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + "new core.quanly.viewmodel.BhChiTietSPResponse"
                    + "(a.id, a.sanPham.ma, a.sanPham.ten, "
                    + "a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, "
                    + "b.loaiKhuyenMai, b.giaTri, a.giaBan, a.moTa, a.maVach)"
                    + " FROM " + className + " a LEFT JOIN a.khuyenMai b";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public List<BhHoaDonResponse> getAllResponseHD() {
        List<BhHoaDonResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + "new core.quanly.viewmodel.BhHoaDonResponse"
                    + "(a.id, a.ma, a.ngayTao, b.ten, c.ma, c.hoTen)"
                    + " FROM HoaDon a LEFT JOIN a.nhanVien b LEFT JOIN a.khachHang c "
                    + "WHERE a.trangThai = 0 "
                    + "ORDER BY a.createdDate DESC";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public boolean updateSoLuong(Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = false;
        String sql = "UPDATE ChiTietSP SET soLuongTon = soLuongTon - :soLuongMua WHERE id = :id";
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : list.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();
                Query query = session.createQuery(sql);
                query.setParameter("soLuongMua", value.getSoLuong());
                query.setParameter("id", value.getIdChiTietSP());
                query.executeUpdate();
            }
            check = true;
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        return check;
    }

    public HoaDon saveOrUpdate(HoaDon entity) {
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.saveOrUpdate(entity);
            trans.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return entity;
    }

    public KhachHang findByMaKhachHang(String ma) {
        KhachHang khachHang = null;
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT a FROM KhachHang a WHERE a.ma = :ma";
            Query query = session.createQuery(hql);
            query.setParameter("ma", ma);
            if (query.getSingleResult() != null) {
                khachHang = (KhachHang) query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public static void main(String[] args) {
        System.out.println(new BanHangRepository().findByMaKhachHang("KH0001"));
    }

    public List<BhKhachHangResponse> getAllResponseKhachHang() {
        List<BhKhachHangResponse> list = new ArrayList<>();
        try {
            session = HibernateUtil.getSession();
            String hql = "SELECT " + "new core.quanly.viewmodel.BhKhachHangResponse"
                    + "(a.id, a.ma, a.hoTen, a.sdt, a.email, a.gioiTinh, a.diaChi)"
                    + " FROM KhachHang a";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public int genMaHoaDon() {
        String maStr = "";
        session = HibernateUtil.getSession();
        try {
            String nativeQuery = "SELECT MAX(CONVERT(INT, SUBSTRING(ma,3,10))) from hoa_don";
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

    public boolean insertHDCT(String idHoaDon, Map<String, BhHoaDonChiTietResponse> list) {
        boolean check = false;
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            for (Map.Entry<String, BhHoaDonChiTietResponse> entry : list.entrySet()) {
                BhHoaDonChiTietResponse value = entry.getValue();

                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setId(value.getIdChiTietSP());
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(idHoaDon);

                hoaDonChiTiet.setChiTietSPId(chiTietSP);
                hoaDonChiTiet.setHoaDonId(hoaDon);
                hoaDonChiTiet.setDonGia(value.getDonGia());
                hoaDonChiTiet.setGiaBan(value.getGiaBan());
                hoaDonChiTiet.setSoLuong(value.getSoLuong());
                hoaDonChiTiet.setTrangThai(0);

                session.saveOrUpdate(hoaDonChiTiet);
            }
            trans.commit();
            check = true;
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        }
        return check;
    }

    public boolean updateTrangThaiHoaDon(String id) {
        boolean check = false;
        session = HibernateUtil.getSession();
        trans = session.beginTransaction();
        try {
            Query query = session.createQuery("UPDATE HoaDon SET trang_thai = 2 WHERE id = :id");
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

}
