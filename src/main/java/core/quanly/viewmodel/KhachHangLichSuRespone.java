/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
    private Integer capBac;

    public Object[] toDaTaRow(int stt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new Object[]{stt, hoTen, sdt, gioiTinh == 0 ? "Nam" : "Nữ", ma, dateFormat.format(ngayThanhToan), thanhTien,
            trangThai == 0 ? "Chờ thanh toán" : (trangThai == 1 ? "Đã hủy" : (trangThai == 2 ? "Đã thanh toán" : (trangThai == 3 ? "Đang giao" : "Đã giao"))),
            capBac == 0 ? "Đồng" : (capBac == 1 ? "Bạc" : (capBac == 2 ? "Vàng" : "Kim cương"))};
    }

}
