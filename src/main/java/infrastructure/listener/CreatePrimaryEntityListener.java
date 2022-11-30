package infrastructure.listener;

import domainmodels.base.PrimaryEntity;
import java.util.UUID;
import javax.persistence.PrePersist;

/**
 *
 * @author thangncph26123
 */
public class CreatePrimaryEntityListener {
    
    @PrePersist
    private void onCreate(PrimaryEntity primaryEntity){
        primaryEntity.setId(UUID.randomUUID().toString());
    }
}
