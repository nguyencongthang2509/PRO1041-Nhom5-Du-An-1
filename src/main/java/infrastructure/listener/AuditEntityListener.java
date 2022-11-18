package infrastructure.listener;

import domainmodels.base.AuditEntity;
import java.util.Calendar;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author thangncph26123
 */
public class AuditEntityListener {

    @PrePersist
    private void onCreate(AuditEntity entity) {
        entity.setCreatedDate(getCurrentTime());
        entity.setLastModifiedDate(getCurrentTime());
    }

    @PreUpdate
    private void onUpdate(AuditEntity entity) {
        entity.setLastModifiedDate(getCurrentTime());
    }

    private long getCurrentTime() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
