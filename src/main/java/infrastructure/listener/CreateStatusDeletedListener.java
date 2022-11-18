package infrastructure.listener;

import domainmodels.base.StatusDeleted;
import infrastructure.constant.TrangThaiXoa;
import javax.persistence.PrePersist;

/**
 *
 * @author thangncph26123
 */
public class CreateStatusDeletedListener {

    @PrePersist
    private void onCreate(StatusDeleted statusDeleted) {
        statusDeleted.setTrangThaiXoa(TrangThaiXoa.DANG_HOAT_DONG);
    }
}
