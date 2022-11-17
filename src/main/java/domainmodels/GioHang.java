package domainmodels;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thangncph26123
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "gio_hang")
public class GioHang implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ngay_tao")
    private Long ngayTao;

    @Column(name = "ngay_thanh_toan")
    private Long ngayThanhToan;

    @Column(name = "ten_nguoi_nhan", length = 50)
    private String tenNguoiNhan;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "sdt", length = 20)
    private String sdt;

    @Column(name = "tinh_trang")
    private Integer tinhTrang;

    @Column(name = "create_date")
    private Long createDate;

    @Column(name = "last_modified_date")
    private Long lastModifiedDate;
}
