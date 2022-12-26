package domainmodels.base;


import infrastructure.constant.EntityProperties;
import infrastructure.listener.CreatePrimaryEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author thangncph26123
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(CreatePrimaryEntityListener.class)
public abstract class PrimaryEntity extends AuditEntity implements IsIdentified {

    @Id
    @Column(name = "id", length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

}
