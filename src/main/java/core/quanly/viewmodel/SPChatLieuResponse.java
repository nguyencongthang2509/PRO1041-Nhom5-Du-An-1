
package core.quanly.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author HP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SPChatLieuResponse {

    private String id;
    private String ma;
    private String ten;

    public Object[] toDateRow() {
        return new Object[]{ma, ten};
    }
}
