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

    private String maSP;

    private String tenSP;

    private BigDecimal donGia;

    private int soLuong;

    private int soLuongTon;

    private BigDecimal giamGia;

    private BigDecimal giaBan;

    private int trangThai;

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, donGia, soLuong, giamGia, giaBan, new BigDecimal(soLuong).multiply(giaBan), trangThai == 0 ? "Được bán" : "Đã đổi trả"};
    }
}
