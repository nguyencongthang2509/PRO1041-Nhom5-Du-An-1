
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
 * @author quynhncph26201
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongKeHangHoaResponse {

    private String id;
    private String maSP;
    private String tenSP;
    private String hang;
    private String mau;
    private String size;
    private long soLuong;
    private BigDecimal donGia;
    
    public Object[] toDataRow(int index){
        return new Object[]{index,maSP, tenSP,hang, mau, size, soLuong, donGia};
    }
}
