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
public class Tuyen implements Serializable{
    private int maT, soDiemDung;
    private String kcach;

    public Tuyen() {
    }

    public Tuyen(int maT, int soDiemDung, String kcach) {
        this.maT = maT;
        this.soDiemDung = soDiemDung;
        this.kcach = kcach;
    }

    public int getMaT() {
        return maT;
    }

    public void setMaT(int maT) {
        this.maT = maT;
    }

    public int getSoDiemDung() {
        return soDiemDung;
    }

    public void setSoDiemDung(int soDiemDung) {
        this.soDiemDung = soDiemDung;
    }

    public String getKcach() {
        return kcach;
    }

    public void setKcach(String kcach) {
        this.kcach = kcach;
    }
    
    public Object[] toObject() {
        return new Object[] {
            maT, kcach, soDiemDung
        };
    }
}
