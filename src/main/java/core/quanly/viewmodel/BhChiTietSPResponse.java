package core.quanly.viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author thangncph26123
 */
@Getter
@ToString
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
    
    private String maKhuyeMai;

    private Integer loaiKhuyenMai;

    private Double khuyenMai;

    private BigDecimal donGia;

    private String moTa;

    private String maVach;

    public Object[] toDataRow() {
        return new Object[]{maSP, tenSP, mauSac, size, hang, soLuongTon, khuyenMai == null ? "0" : (loaiKhuyenMai == 0 ? khuyenMai + " %" : khuyenMai + " Vnđ"), donGia + " Vnđ", moTa == null ? "Không có" : moTa};
    }
}
