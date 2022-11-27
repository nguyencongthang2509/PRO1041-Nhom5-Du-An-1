package core.quanly.viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thangncph26123
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BhHoaDonChiTietResponse {

    private String idChiTietSP;
    
    private String idHDCT;
    
    private String idHoaDon;

    private String maSP;

    private String tenSP;
    
    private String hang;
    
    private String mauSac;
    
    private String size;

    private BigDecimal donGia;

    private int soLuong;

    private int soLuongTon;
    
    private String maKhuyenMai;
    
    private Integer loaiKhuyenMai;

    private Double giamGia;

    private BigDecimal giaBan;

    private int trangThai;

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, hang, mauSac, size, donGia, soLuong, maKhuyenMai == null ? 0 : (loaiKhuyenMai == 0 ? giamGia + " %" : giamGia + " Vnđ"), giaBan, new BigDecimal(soLuong).multiply(giaBan)};
    }
}
