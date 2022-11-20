/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

import domainmodels.base.StatusDeleted;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author HP
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SPHangViewModel extends StatusDeleted{
    private UUID id;
    private String ma;
    private String ten;

    
    public Object[] toDateRow(int index){
        return new Object[]{index, ma, ten};
    }
    
}
