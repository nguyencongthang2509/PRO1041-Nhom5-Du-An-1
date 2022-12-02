package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
@Table(name = "khach_hang")
public class KhachHang extends PrimaryEntity implements Serializable {
    
    @Column(name = "ma", length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(name = "ho_ten", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String hoTen;

    @Column(name = "ngay_sinh", columnDefinition = "date")
    private Date ngaySinh;

    @Column(name = "gioi_tinh")
    private Integer gioiTinh;

    @Column(name = "sdt", length = EntityProperties.LENGTH_PHONE)
    private String sdt;

    @Column(name = "email", length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(name = "dia_chi", length = EntityProperties.LENGTH_ADDRESS)
    @Nationalized
    private String diaChi;
    
    @Column(name = "cap_bac")
    private Integer capBac = 0;// 0: đồng 0%, 1: bạc 3-5tr : 3%, 2: Vàng: 5tr - 10tr : 5%, Kim cương: > 10tr : 10%
    
    @Column(name = "thoi_diem_reset_gan_nhat")
    private Long thoiDiemResetGanNhat;
    
    @Column(name = "status_deleted", columnDefinition = "int default 0")
    private Integer trangThaiXoa = 0;

}
