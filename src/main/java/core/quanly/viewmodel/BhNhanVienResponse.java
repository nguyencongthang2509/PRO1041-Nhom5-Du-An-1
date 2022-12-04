package core.quanly.viewmodel;

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
public class BhNhanVienResponse {

    private String id;

    private String ma;

    private String ten;

    private String sdt;

    private String email;

    private String diaChi;

    public Object[] toDataRow() {
        return new Object[]{ma, ten, sdt, email, diaChi};
    }
}
