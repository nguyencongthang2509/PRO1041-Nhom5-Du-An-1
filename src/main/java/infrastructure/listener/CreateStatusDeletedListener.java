package infrastructure.listener;

import domainmodels.base.StatusDeleted;
import javax.persistence.PrePersist;

/**
 *
 * @author thangncph26123
 */
public class CreateStatusDeletedListener {

    @PrePersist
    private void onCreate(StatusDeleted statusDeleted) {
        if (statusDeleted.getTrangThaiXoa() == null) {
            statusDeleted.setTrangThaiXoa(0);
        }
    }
}
