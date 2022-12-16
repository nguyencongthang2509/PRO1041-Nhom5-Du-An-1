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
public class ThHoaDonChiTietResponse {

    private String idChiTietSP;

    private String idHDCT;

    private String idHoaDon;

    private String maCTSP;

    private String maSP;

    private String tenSP;

    private String hang;

    private String chatLieu;

    private String mauSac;

    private String size;

    private BigDecimal donGia;

    private int soLuong;
    
    private int soLuongTruocKhiTra;

    private int soLuongTon;

    private BigDecimal giamGia;

    private BigDecimal giaBan;

    public Object[] toDataRow() {
        return new Object[]{maCTSP, tenSP, donGia, soLuong, giamGia, giaBan, new BigDecimal(soLuong).multiply(giaBan), false};
    }
}
