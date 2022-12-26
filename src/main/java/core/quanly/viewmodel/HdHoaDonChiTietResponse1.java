
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author longnhph26222
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HdHoaDonChiTietResponse1 {

    private String id;
    private String maSP;
    private String tenSP;
    private String hang;
    private String mau;
    private String size;
    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal giaBan;
    private BigDecimal giaTriKM;
    
    public Object[] toDataRow(){
        return new Object[]{maSP, tenSP,hang, mau, size, soLuong, donGia, giaBan, (giaTriKM == null ? "Không có" : giaTriKM), new BigDecimal(soLuong).multiply(giaBan)};
    }
}
