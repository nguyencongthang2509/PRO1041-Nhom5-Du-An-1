package domainmodels.base;

import infrastructure.listener.CreateStatusDeletedListener;
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
@EntityListeners(CreateStatusDeletedListener.class)
public abstract class StatusDeleted {
    
    @Column(name = "status_deleted")
    private int trangThaiXoa;
}
