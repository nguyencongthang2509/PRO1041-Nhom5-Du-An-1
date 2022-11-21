package core.quanly.repository;

import config.HibernateUtil;
import core.quanly.viewmodel.BhChiTietSPResponse;
import domainmodels.ChiTietSP;
import java.util.ArrayList;
import java.util.List;
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
            String hql = "SELECT " + "new core.quanly.viewmodel.BhChiTietSPResponse(a.id, a.sanPham.ma, a.sanPham.ten, a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, b.loaiKhuyenMai, b.giaTri, a.giaBan, a.moTa, a.maVach)" + " FROM " + className + " a LEFT JOIN a.khuyenMai b";
            Query query = session.createQuery(hql);
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    public static void main(String[] args) {
        List<BhChiTietSPResponse> list = new BanHangRepository().getAllResponseCTSP();
        System.out.println(list.get(0));
    }
}
