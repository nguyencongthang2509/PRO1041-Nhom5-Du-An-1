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
public class HdHoaDonChiTietResponse {

    private String id;
    private String maSP;
    private String tenSP;
    private Integer soLuong;
    private BigDecimal donGia;
    private BigDecimal giaBan;

    public Object[] toDataRow(){
        return new Object[]{id, maSP, tenSP, soLuong, donGia, giaBan, new BigDecimal(soLuong).multiply(giaBan)};
    }
}
