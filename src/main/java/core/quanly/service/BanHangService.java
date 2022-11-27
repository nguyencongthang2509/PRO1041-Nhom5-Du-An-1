package core.quanly.service;

import core.quanly.viewmodel.BhChiTietSPResponse;
import core.quanly.viewmodel.BhHoaDonChiTietResponse;
import core.quanly.viewmodel.BhHoaDonResponse;
import core.quanly.viewmodel.BhKhachHangResponse;
import domainmodels.HoaDon;
import domainmodels.HoaDonChiTiet;
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

    List<BhHoaDonResponse> getAllResponseHD(String idNhanVien);

    boolean insertHDCT(String idHoaDon, Map<String, BhHoaDonChiTietResponse> list);

    public boolean updateSoLuong(Map<String, BhHoaDonChiTietResponse> list);

    public boolean updateTrangThaiHoaDon(String id);

    boolean saveOrUpdateHDCT(HoaDonChiTiet entity);

    boolean deleteHDCT(HoaDonChiTiet hoaDonChiTiet);

    HoaDonChiTiet convertHoaDonChiTiet(BhHoaDonChiTietResponse bhHoaDonChiTietResponse);

    List<BhHoaDonChiTietResponse> getAllHDCTByIdHoaDon(String idHoaDon);

    Map<String, BhHoaDonChiTietResponse> convertToHoaDonChiTietResponse(List<BhHoaDonChiTietResponse> list);

    String findChiTietSPByMaVach(String maVach);
    
    boolean updateKhachHangInHoaDon(String idHoaDon, String idKhachHang);
    
    KhachHang findKhachHangById(String id);
    
    BhChiTietSPResponse findCTSPByMaVach(List<BhChiTietSPResponse> list, String maVach);
    
    HoaDon findByIdHoaDon(String id);
       
}
