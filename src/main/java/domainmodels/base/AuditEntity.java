package domainmodels.base;

import infrastructure.listener.AuditEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
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
@EntityListeners(AuditEntityListener.class)
public abstract class AuditEntity extends StatusDeleted{

    @Column(name = "created_date", updatable = false)
    private Long createdDate;

    @Column(name = "last_modified_date")
    private Long lastModifiedDate;

}
