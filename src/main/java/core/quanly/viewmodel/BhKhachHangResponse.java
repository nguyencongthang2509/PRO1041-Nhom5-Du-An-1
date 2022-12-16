package core.quanly.viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thangncph26123
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BhKhachHangResponse {

    private String id;

    private String ma;

    private String hoTen;

    private String sdt;

    private String email;

    private int gioiTinh;

    private String diaChi;
    
    private Integer capBac;

    public Object[] toDataRow() {
        return new Object[]{ma, hoTen, sdt, email, gioiTinh == 0 ? "Nam" : "Nữ", diaChi};
    }
}
