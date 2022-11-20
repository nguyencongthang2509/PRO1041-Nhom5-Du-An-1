package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
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
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @Column(name = "ma", length = EntityProperties.LENGTH_CODE)
    private String ma;

    @Column(name = "hinh_thuc_giao_hang")
    private int hinhThucGiaoHang;
    
    @Column(name = "hinh_thuc_thanh_toan")
    private int hinhThucThanhToan;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "ngay_ship")
    private Date ngayShip;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "ten_nguoi_nhan", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String tenNguoiNhan;

    @Column(name = "dia_chi", length = EntityProperties.LENGTH_ADDRESS)
    @Nationalized
    private String diaChi;

    @Column(name = "sdt", length = EntityProperties.LENGTH_NAME)
    private String sdt;

    @Column(name = "trang_thai", nullable = false)
    private int trangThai;
}
