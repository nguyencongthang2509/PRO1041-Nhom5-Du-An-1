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
 * @author thangncph26123
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HdHoaDonResponse1 {

    private String id;
    private String ma;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Integer hinhThucThanhToan;
    private BigDecimal thanhTien;
    private BigDecimal tienKhachCK;
    private BigDecimal tienKhachTra;
    private BigDecimal tienThua;
    private String maNV;
    private String tenNV;
    private String tenKH;
    private String sdt;
    private String diaChi;
    private Integer trangThai;

    public String getNgayThanhToanStr() {
        if (ngayThanhToan == null) {
            return "..........";
        }
        return String.valueOf(ngayThanhToan);
    }

    public String getThanhTienStr() {
        if (thanhTien == null) {
            return "0VNĐ";
        }
        return String.valueOf(thanhTien) + "VNĐ";
    }

    public String getTienKhachCKStr() {
        if (tienKhachCK == null) {
            return "0VNĐ";
        }
        return String.valueOf(tienKhachCK) + "VNĐ";
    }

    public String getTienKhachTraStr() {
        if (tienKhachTra == null) {
            return "0VNĐ";
        }
        return String.valueOf(tienKhachTra) + "VNĐ";
    }

    public String getTienThuaStr() {
        if (tienThua == null) {
            return "0VNĐ";
        }
        return String.valueOf(tienThua) + "VNĐ";
    }

    public String getTenKHStr() {
        if (tenKH == null) {
            return "Khách bán lẻ";
        }
        return String.valueOf(tenKH);
    }

    public String getSDTStr() {
        if (sdt == null) {
            return "..........";
        }
        return String.valueOf(sdt);
    }

    public String getDiaChiStr() {
        if (diaChi == null) {
            return "..........";
        }
        return String.valueOf(diaChi);
    }

    public String getTT() {
        if (trangThai == 0) {
            return "Chờ thanh toán";
        } else if (trangThai == 1) {
            return "Đã hủy";
        } else if (trangThai == 2) {
            return "Đã thanh toán";
        } else if (trangThai == 3) {
            return "Chờ giao hàng";
        } else if (trangThai == 4) {
            return "Đang giao";
        }
        return "Đã giao";
    }

    public String getHinhThucTT() {
        return hinhThucThanhToan == 0 ? "Tiền mặt" : (hinhThucThanhToan == 1 ? "Chuyển khoản" : "Kết hợp");
    }

    public Object[] toDaTaRow() {
        return new Object[]{ma, ngayTao, ngayThanhToan == null ? "No" : ngayThanhToan, getThanhTienStr(), maNV, tenKH == null ? "Khách bán lẻ" : tenKH, getTT()};
    }
}
