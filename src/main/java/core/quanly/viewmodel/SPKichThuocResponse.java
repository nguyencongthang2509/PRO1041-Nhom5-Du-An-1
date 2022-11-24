/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

/**
 *
 * @author HP
 */
public class SPKichThuocResponse {
    
    private String id;
    private String maKichThuoc;
    private String tenKichThuoc;
    
    public Object[] toDataRow(){
        return new Object[]{maKichThuoc, tenKichThuoc};
    }
}
