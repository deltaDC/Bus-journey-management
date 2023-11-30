/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author PDC
 */
public class LaiXe implements Serializable{
    private int ma;
    private String ten, dchi, trdo;

    public LaiXe() {
    }

    public LaiXe(int ma, String ten, String dchi, String trdo) {
        this.ma = ma;
        this.ten = ten;
        this.dchi = dchi;
        this.trdo = trdo;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDchi() {
        return dchi;
    }

    public void setDchi(String dchi) {
        this.dchi = dchi;
    }

    public String getTrdo() {
        return trdo;
    }

    public void setTrdo(String trdo) {
        this.trdo = trdo;
    }
    
    public Object[] toObject() {
        return new Object[] {
            ma, ten, dchi, trdo
        };
    }
}
