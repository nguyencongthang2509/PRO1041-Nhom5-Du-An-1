package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "nhan_vien")
public class NhanVien extends PrimaryEntity implements Serializable {

    @Column(name = "ma", length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(name = "ho_ten", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String ten;

    @Column(name = "gioi_tinh")
    private Integer gioiTinh;

    @Column(name = "ngay_sinh", columnDefinition = "date")
    private Date ngaySinh;

    @Column(name = "dia_chi", length = EntityProperties.LENGTH_ADDRESS)
    @Nationalized
    private String diaChi;

    @Column(name = "sdt", length = EntityProperties.LENGTH_PHONE)
    private String sdt;

    @Column(name = "email", length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(name = "mat_khau", length = EntityProperties.LENGTH_PASSWORD)
    private String matKhau;

    @Column(name = "vai_tro", nullable = false)
    private Integer vaiTro;

    @Column(name = "status_deleted", columnDefinition = "int default 0")
    private Integer trangThaiXoa = 0;
}
