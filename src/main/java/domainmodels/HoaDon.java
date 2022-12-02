package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author thangncph26123
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "hoa_don")
public class HoaDon extends PrimaryEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien", nullable = false)
    private NhanVien nhanVien;

    @Column(name = "ma", length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(name = "hinh_thuc_giao_hang", nullable = false)
    private Integer hinhThucGiaoHang;

    @Column(name = "hinh_thuc_thanh_toan", nullable = false)
    private Integer hinhThucThanhToan;

    @Column(name = "ngay_tao", nullable = false)
    private Date ngayTao;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "ngay_mong_muon")
    private Date ngayMongMuon;

    @Column(name = "ngay_ship")
    private Date ngayShip;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "tien_khach_tra", columnDefinition = "decimal(20,0)")
    private BigDecimal tienKhachTra;

    @Column(name = "tien_khach_chuyen_khoan", columnDefinition = "decimal(20,0)")
    private BigDecimal tienKhachChuyenKhoan;

    @Column(name = "tien_ship", columnDefinition = "decimal(20,0)")
    private BigDecimal tienShip;

    @Column(name = "tien_thua", columnDefinition = "decimal(20,0)")
    private BigDecimal tienThua;

    @Column(name = "thanh_tien", columnDefinition = "decimal(20,0)")
    private BigDecimal thanhTien;

    @Column(name = "ten_nguoi_nhan", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String tenNguoiNhan;

    @Column(name = "dia_chi", length = EntityProperties.LENGTH_ADDRESS)
    @Nationalized
    private String diaChi;

    @Column(name = "sdt_nguoi_nhan", length = EntityProperties.LENGTH_PHONE)
    private String sdtNguoiNhan;

    @Column(name = "ten_nguoi_ship", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String tenNguoiShip;

    @Column(name = "sdt_nguoi_ship", length = EntityProperties.LENGTH_PHONE)
    private String sdtNguoiShip;

    @Column(name = "phan_tram_giam_gia")
    private Double phamTramGiamGia;

    @Column(name = "ly_do")
    private String lyDo;
    
    @Column(name = "trang_thai_thanh_toan")
    private Integer trangThaiThanhToan;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;
}
