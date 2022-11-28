/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author NgocAnh
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KhachHangLichSuRespone {

    private String id;
    private String hoTen;
    private String sdt;
    private Integer gioiTinh;
    private String ma;
    private Date ngayThanhToan;
    private BigDecimal thanhTien;
    private Integer trangThai;

    public Object[] toDaTaRow() {
        int stt = 1;
        return new Object[]{stt++, hoTen, sdt, gioiTinh, ma, ngayThanhToan, thanhTien, trangThai};
    }

}