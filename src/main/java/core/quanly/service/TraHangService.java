package core.quanly.service;

import core.quanly.viewmodel.ThHoaDonResponse;
import domainmodels.HoaDon;
import domainmodels.HoaDonTraHang;
import domainmodels.HoaDonTraHangChiTiet;
import java.util.List;

/**
 *
 * @author thangncph26123
 */
public interface TraHangService {
    
    List<ThHoaDonResponse> getAllResponseHD();
    
    List<HoaDonTraHang> getAllHoaDonTraHang();
    
    HoaDon getHoaDonByMa(String ma);
    
    HoaDonTraHang findHoaDonTraHang(String idMoi, String idCu);
    
    List<HoaDonTraHangChiTiet> getAllHoaDonTraHangChiTiet(String id);
    
    List<ThHoaDonResponse> getAllResponseHDTraHang();
    
    List<ThHoaDonResponse> findHoaDon(String input);
    
    boolean saveOrUpdateHoaDonTraHang(HoaDonTraHang entity);
    
    boolean saveOrUpdateHoaDonTraHangChiTiet(HoaDonTraHangChiTiet entity);
}
