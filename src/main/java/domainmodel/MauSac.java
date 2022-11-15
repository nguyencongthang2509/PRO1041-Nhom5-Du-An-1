package domainmodel;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "mau_sac")
public class MauSac implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    
    @Column(name = "ma")
    private String ma;
    
    @Column(name = "ten")
    private String ten;
    
    @Column(name = "create_date")
    private Long createDate;
    
    @Column(name = "last_modified_date")
    private Long lastModifiedDate;
}
