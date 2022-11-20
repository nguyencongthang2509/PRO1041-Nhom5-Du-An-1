
package core.quanly.repository;

import core.quanly.viewmodel.BhChiTietSPResponse;
import domainmodels.ChiTietSP;
import repository.CrudRepository;

/**
 *
 * @author thangncph26123
 */
public class BanHangRepository extends CrudRepository<String, ChiTietSP, BhChiTietSPResponse>{

    public BanHangRepository() {
        className = ChiTietSP.class.getName();
        res = "new core.quanly.viewmodel(a.id, a.sanPham.ma, a.sanPham.ten, a.mauSac.ten, a.kichThuoc.ten, a.hang.ten, a.soLuongTon, a.khuyenMai.giaTri, a.donGia, a.moTa, a.maVach)";
    }
    
}
