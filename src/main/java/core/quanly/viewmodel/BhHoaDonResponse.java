package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.util.Date;
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
public class BhHoaDonResponse {

    private String id;

    private String maHD;

    private Date ngayTao;

    private String tenNhanVien;

    private String maKhachHang;

    private String tenKhachHang;

    public Object[] toDataRow() {
        return new Object[]{maHD, ngayTao, tenNhanVien, tenKhachHang};
    }
}
