/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

/**
 *
 * @author HP
 */
public class SPMauSacResponse {
    
    private String id;
    private String maMauSac;
    private String tenMauSac;
    
    public Object[] toDataRow(){
        return new Object[]{maMauSac, tenMauSac};
    }
}
