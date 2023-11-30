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
public class BangPhanCong implements Serializable{
    private LaiXe lx;
    private Tuyen t;
    private int soLuot;

    public BangPhanCong() {
    }

    public BangPhanCong(LaiXe lx, Tuyen t, int soLuot) {
        this.lx = lx;
        this.t = t;
        this.soLuot = soLuot;
    }

    public LaiXe getLx() {
        return lx;
    }

    public void setLx(LaiXe lx) {
        this.lx = lx;
    }

    public Tuyen getT() {
        return t;
    }

    public void setT(Tuyen t) {
        this.t = t;
    }

    public int getSoLuot() {
        return soLuot;
    }

    public void setSoLuot(int soLuot) {
        this.soLuot = soLuot;
    }
    
    public Object[] toObject() {
        return new Object[] {
            lx.getMa(), lx.getTen(), t.getMaT(), soLuot
        };
    }
}
