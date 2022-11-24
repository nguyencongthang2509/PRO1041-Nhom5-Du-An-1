/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.quanly.viewmodel;

/**
 *
 * @author HP
 */
public class SPKhuyenMaiResponse {
    
    private String id;
    private String maKhuyenMai;
    private String tenKhuyenMai;
    
    public Object[] toDataRow(){
        return new Object[]{maKhuyenMai, tenKhuyenMai};
    }
}
