package core.quanly.service;

import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import domainmodels.HoaDon;
import domainmodels.KhachHang;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author thangncph26123
 */
public interface BanHangService {
    
    List<BhChiTietSPResponse> getAllChiTietSP();
    
    List<BhKhachHangResponse> getAllKhachHangResponse();
    
    int genMaHoaDonTuDong();
    
    String saveOrUpdate(HoaDon hoaDon);
    
    KhachHang findByMaKhachHang(String ma);
    
    List<BhHoaDonResponse> getAllResponseHD();
    
    boolean insertHDCT(String idHoaDon, Map<String, BhHoaDonChiTietResponse> list);
    
    public boolean updateSoLuong(Map<String, BhHoaDonChiTietResponse> list);
    
    public boolean updateTrangThaiHoaDon(String id);
}
