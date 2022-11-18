package domainmodels;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author thangncph26123
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GioHangChiTietId implements Serializable {

    
    private String gioHangId;

    private String chiTietSPId;
}
