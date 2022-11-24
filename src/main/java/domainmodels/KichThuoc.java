package domainmodels;

import domainmodels.base.PrimaryEntity;
import infrastructure.constant.EntityProperties;
import java.io.Serializable;
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
@Table(name = "kich_thuoc")
public class KichThuoc extends PrimaryEntity implements Serializable {

    @Column(name = "ma", length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(name = "ten", length = EntityProperties.LENGTH_CODE)
    @Nationalized
    private String ten;

}
