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
    
    private String maCTSP;

    private String maSP;

    private String tenSP;

    private String mauSac;

    private String size;

    private String hang;

    private int soLuongTon;
    
    private BigDecimal donGia;

    private String moTa;

    private String maVach;
    
    public Object[] toDataRow() {
        return new Object[]{tenSP, mauSac, size, hang, soLuongTon, donGia + " Vnđ"};
    }
}
