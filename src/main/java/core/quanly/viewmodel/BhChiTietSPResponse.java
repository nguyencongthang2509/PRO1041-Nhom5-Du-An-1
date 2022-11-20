package core.quanly.viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author thangncph26123
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BhChiTietSPResponse {

    private String id;
    
    private String maSP;

    private String tenSP;

    private String mauSac;

    private String size;

    private String hang;

    private int soLuongTon;
    
    private Integer khuyenMai;

    private BigDecimal donGia;

    private String moTa;

    private String maVach;

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, mauSac, size, hang, soLuongTon, khuyenMai, donGia, moTa};
    }
}
