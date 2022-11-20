package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    
    @Column(name = "ma", length = EntityProperties.LENGTH_CODE)
    private String ma;

    @Column(name = "ho_ten", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String hoTen;

    @Column(name = "ngay_sinh", columnDefinition = "date")
    private Date ngaySinh;

    @Column(name = "gioi_tinh")
    private int gioiTinh;

    @Column(name = "sdt", length = EntityProperties.LENGTH_PHONE)
    private String sdt;

    @Column(name = "email", length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(name = "dia_chi", length = EntityProperties.LENGTH_ADDRESS)
    @Nationalized
    private String diaChi;

}
