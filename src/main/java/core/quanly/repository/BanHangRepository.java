package core.quanly.repository;

import core.quanly.viewmodel.BhChiTietSPResponse;
import domainmodels.ChiTietSP;
import domainmodels.SanPham;
import java.util.List;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class BanHangRepository extends CrudRepository<String, ChiTietSP, BhChiTietSPResponse> {

    public BanHangRepository() {
        className = ChiTietSP.class.getName();
        res = "new core.quanly.viewmodel.BhChiTietSPResponse(a.id, a.sanPham.ma, a.sanPham.ten, a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, a.khuyenMai.giaTri, a.giaBan, a.moTa, a.maVach)";
    }

    public static void main(String[] args) {
        List<BhChiTietSPResponse> list = new BanHangRepository().getAllResponse();
        System.out.println(list.get(0).getKhuyenMai());
    }
}
