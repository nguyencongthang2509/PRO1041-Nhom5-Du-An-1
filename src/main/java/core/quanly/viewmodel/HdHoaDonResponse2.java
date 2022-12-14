/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
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
public class HdHoaDonResponse2 {

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
    private String tenNguoiNhan;
    private String sdt;
    private String diaChi;
    private String sdtship;
    private String tennguoiship;
    private BigDecimal tienship;
    private Date ngayship;
    private Date ngaynhan;
    private String lydo;
    private Integer trangThai;
    private Double phanTramGiamGia;
    private Date ngayMongMuon;
    private String maGiaoDich;

    public String getNgayThanhToanStr() {
        if (ngayThanhToan == null) {
            return "..........";
        }
        return String.valueOf(ngayThanhToan);
    }

    public String getGiamGiaStr() {
        if (phanTramGiamGia == null) {
            return "0%";
        }
        return String.valueOf(phanTramGiamGia) + "%";
    }

    public String getNgayMongMuonStr() {
        if (ngayMongMuon == null) {
            return "..........";
        }
        return String.valueOf(ngayMongMuon);
    }

    public String getThanhTienStr() {
        DecimalFormat df = new DecimalFormat("#,###");
        if (thanhTien == null) {
            return "0VNĐ";
        }
        return String.valueOf(df.format(thanhTien)) + "VNĐ";
    }

    public String getTienKhachCKStr() {
        DecimalFormat df = new DecimalFormat("#,###");
        if (tienKhachCK == null) {
            return "0VNĐ";
        }
        return String.valueOf(df.format(tienKhachCK)) + "VNĐ";
    }

    public String getTienKhachTraStr() {
        DecimalFormat df = new DecimalFormat("#,###");
        if (tienKhachTra == null) {
            return "0VNĐ";
        }
        return String.valueOf(df.format(tienKhachTra)) + "VNĐ";
    }

    public String getTienThuaStr() {
        DecimalFormat df = new DecimalFormat("#,###");
        if (tienThua == null) {
            return "0VNĐ";
        }
        return String.valueOf(df.format(tienThua)) + "VNĐ";
    }

    public String getTenKHStr() {
        if (tenNguoiNhan == null) {
            return "..........";
        }
        return String.valueOf(tenNguoiNhan);
    }

    public String getSDTStr() {
        if (sdt == null) {
            return "..........";
        }
        return sdt;
    }

    public String getDiaChiStr() {
        if (diaChi == null) {
            return "..........";
        }
        return diaChi;
    }

    public String getTenNguoiShipStr() {
        if (tennguoiship == null) {
            return "..........";
        }
        return tennguoiship;
    }

    public String getSDTShipStr() {
        if (sdtship == null) {
            return "..........";
        }
        return sdtship;
    }

    public String getNgayShipStr() {
        if (ngayship == null) {
            return "..........";
        }
        return String.valueOf(ngayship);
    }

    public String getNgayNhanStr() {
        if (ngaynhan == null) {
            return "..........";
        }
        return String.valueOf(ngaynhan);
    }

    public String getTienshipStr() {
        DecimalFormat df = new DecimalFormat("#,###");
        if (tienship == null) {
            return "0VNĐ";
        }
        return String.valueOf(df.format(tienship)) + "VNĐ";
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
        } else if (trangThai == 5) {
            return "Đã giao";
        } else if (trangThai == 6) {
            return "Khách hẹn lại";
        }
        return "Đã trả hàng";
    }

    public String getLyDoStr() {
        if (lydo == null) {
            return "..........";
        }
        return lydo;
    }

    public String getMaGDStr() {
        if (maGiaoDich == null) {
            return "..........";
        }
        return maGiaoDich;
    }

    public String getHinhThucTT() {
        return hinhThucThanhToan == null ? "Chưa chọn" : (hinhThucThanhToan == 0 ? "Tiền mặt" : hinhThucThanhToan == 1 ? "Chuyển khoản" : "Kết hợp");
    }

    public Object[] toDaTaRow() {
        return new Object[]{ma, ngayTao, ngayThanhToan == null ? "No" : ngayThanhToan, getThanhTienStr(), maNV,
            tenNguoiNhan == null ? "No" : tenNguoiNhan, diaChi == null ? "No" : diaChi, tennguoiship == null ? "No" : tennguoiship, ngayship == null ? "No" : ngayship, ngaynhan == null ? "No" : ngaynhan, getLyDoStr(), getTT()};
    }
}
