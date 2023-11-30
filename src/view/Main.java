/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.CustomValidate;
import controller.IOFile;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BangPhanCong;
import model.LaiXe;
import model.Tuyen;

/**
 *
 * @author PDC
 */
public class Main extends javax.swing.JFrame {

    private List<LaiXe> lxList;
    private List<Tuyen> tList;
    private List<BangPhanCong> bpcList;
    
    private String fLX, fT, fBPC;
    private DefaultTableModel tmLX, tmT, tmBPC;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        fLX = "src/controller/LX.dat";
        fT = "src/controller/TUYEN.dat";
        fBPC = "src/controller/PHANCONG.dat";
        
        if (new File(fLX).exists()) {
            lxList = IOFile.doc(fLX);
        } else {
            lxList = new ArrayList<>();
        }
        if (new File(fT).exists()) {
            tList = IOFile.doc(fT);
        } else {
            tList = new ArrayList<>();
        }
        if (new File(fBPC).exists()) {
            bpcList = IOFile.doc(fBPC);
        } else {
            bpcList = new ArrayList<>();
        }
        
        tmLX = (DefaultTableModel) tbLX.getModel();
        tmT = (DefaultTableModel) tbT.getModel();
        tmBPC = (DefaultTableModel) tbBPC.getModel();
        
        docDataLX();
        setButtonThemLX(true);
        eventLX();
        
        docDataT();
        setButtonThemT(true);
        eventT();
        
        docDataBPC();
        setButtonThemBPC(true);
        eventBPC();
        
        maLXBPCCB.removeAllItems();
        maTBPCCB.removeAllItems();

        for (LaiXe x : lxList) {
            maLXBPCCB.addItem(x.getMa() + "");
        }

        for (Tuyen t : tList) {
            maTBPCCB.addItem(t.getMaT() + "");
        }
        
        eventThongKe();
    }

    //------------------------------------------------------
    private void docDataLX() {
        tmLX.setRowCount(0);
        for (LaiXe lx : lxList) {
            tmLX.addRow(lx.toObject());
        }
    }
    
    private void setButtonThemLX(boolean b) {
        nhapLXBT.setEnabled(b);
        themLXBT.setEnabled(!b);
    }
    
    private void eventLX() {
        nhapLXBT.addActionListener(event -> {
            setButtonThemLX(false);
            maLXTF.setText(10000 + lxList.size() + "");
            tenLXTF.requestFocus();
        });
        
        themLXBT.addActionListener(event -> {
            try {
                int ma = Integer.parseInt(maLXTF.getText());
                String ten = tenLXTF.getText();
                String dchi = dchiLXTF.getText();
                String trdo = trdoLXCB.getSelectedItem().toString();
                
                if (ten.isEmpty() || dchi.isEmpty() || trdo.isEmpty()) {
                    throw new CustomValidate("khong dc de trong");
                }
                
                LaiXe x = new LaiXe(
                        ma,
                        ten,
                        dchi,
                        trdo
                );
                
                lxList.add(x);
                tmLX.addRow(x.toObject());
                
                setButtonThemLX(true);
                
            } catch (CustomValidate e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        });
        
        luuLXBT.addActionListener(event -> {
            IOFile.viet(fLX, lxList);
        });
        
        xoaLXBT.addActionListener(event -> {
            int row = tbLX.getSelectedRow();
            
            if (row >= 0 && row < tmLX.getRowCount()) {
                lxList.remove(row);
                tmLX.removeRow(row);
                docDataLX();
            } else {
                JOptionPane.showMessageDialog(this, "chon dong de xoa");
            }
        });
        
        giamLXRBT.addActionListener(event -> {
            if (giamLXRBT.isSelected()) {
                tangLXRBT.setSelected(false);
            }
        });
        tangLXRBT.addActionListener(event -> {
            if (tangLXRBT.isSelected()) {
                giamLXRBT.setSelected(false);
            }
        });
        
        sxLXBT.addActionListener(event -> {
            if (giamLXRBT.isSelected() && !tangLXRBT.isSelected()) {
                Comparator<LaiXe> giam = new Comparator<LaiXe>() {
                    @Override
                    public int compare(LaiXe o1, LaiXe o2) {
                        String trdo1 = o1.getTrdo();
                        String trdo2 = o2.getTrdo();
                        
                        return trdo1.compareTo(trdo2);
                    }
                };
                Collections.sort(lxList, giam);
            } else if (!giamLXRBT.isSelected() && tangLXRBT.isSelected()) {
                Comparator<LaiXe> tang = new Comparator<LaiXe>() {
                    @Override
                    public int compare(LaiXe o1, LaiXe o2) {
                        String trdo1 = o1.getTrdo();
                        String trdo2 = o2.getTrdo();
                        
                        return trdo2.compareTo(trdo1);
                    }
                };
                Collections.sort(lxList, tang);
            } else {
                JOptionPane.showMessageDialog(this, "phai chon 1 cach sap xep");
            }
            docDataLX();
        });
    }

    //------------------------------------------------------
    
    
    
    //------------------------------------------------------
    private void docDataT() {
        tmT.setRowCount(0);
        for (Tuyen lx : tList) {
            tmT.addRow(lx.toObject());
        }
    }
    
    private void setButtonThemT(boolean b) {
        nhapTBT.setEnabled(b);
        themTBT.setEnabled(!b);
    }
    
    private void eventT() {
        nhapTBT.addActionListener(event -> {
            setButtonThemT(false);
            maTTF.setText(100 + tList.size() + "");
            sddTTF.requestFocus();
        });
        
        themTBT.addActionListener(event -> {
            try {
                int ma = Integer.parseInt(maTTF.getText());
                String kc = kcTTF.getText();
                
                if (kc.isEmpty() || sddTTF.getText().isEmpty()) {
                    throw new CustomValidate("khong dc de trong");
                }
                
                if(!kc.matches("\\dkm")) {
                    throw new CustomValidate("nhap dung dinh dang so + km viet lien");
                }
                
                int soDiemDung = Integer.parseInt(sddTTF.getText());
                
                if (soDiemDung < 0) {
                    throw new CustomValidate("so diem dung phai duong");
                }
                
                Tuyen t = new Tuyen(ma, soDiemDung, kc);
                tList.add(t);
                tmT.addRow(t.toObject());
                
                setButtonThemT(true);
                
            } catch (CustomValidate e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "so diem dung phai la so nguyen");
            }
        });
        
        luuTBT.addActionListener(event -> {
            IOFile.viet(fT, tList);
        });
        
        suaTBT.addActionListener(event -> {
            int row = tbT.getSelectedRow();
            
            if (row >= 0 && row < tmT.getRowCount()) {
                Tuyen newT = new Tuyen(
                        Integer.parseInt(maTTF.getText()),
                        Integer.parseInt(sddTTF.getText()),
                        kcTTF.getText()
                );
                
                tList.set(row, newT);
                tmT.removeRow(row);
                docDataT();
            } else {                
                JOptionPane.showMessageDialog(this, "chon dong de sua");
            }
        });
    }

    //------------------------------------------------------
    
    
    
    
    
    
    //------------------------------------------------------
    private void docDataBPC() {
        tmBPC.setRowCount(0);
        for (BangPhanCong lx : bpcList) {
            tmBPC.addRow(lx.toObject());
        }
    }
    
    private void setButtonThemBPC(boolean b) {
        nhapBPCBT.setEnabled(b);
        themBPCBT.setEnabled(!b);
    }
    
    private int getSoLuot(int maLX) {
        for(BangPhanCong bpc : bpcList) {
            if(bpc.getLx().getMa() == maLX) {
                return bpc.getSoLuot();
            }
        }
        return 0;
    }
    
    private boolean ktraTuyen(int maLX, int maT) {
        for(BangPhanCong bpc : bpcList) {
            if(bpc.getLx().getMa() == maLX && bpc.getT().getMaT() == maT) {
                return false;
            }
        }
        return true;
    }
    
    private LaiXe getLaiXeByMa(int maLX) {
        for(LaiXe x : lxList) {
            if(x.getMa() == maLX) {
                return x;
            }
        }
        return null;
    }
    
    private Tuyen getTuyenByMa(int maT) {
        for(Tuyen x : tList) {
            if(x.getMaT() == maT) {
                return x;
            }
        }
        return null;
    }
    
    private boolean ktraLX(int maLX) {
        for(BangPhanCong bpc : bpcList) {
            if(bpc.getLx().getMa() == maLX) {
                return false;
            }
        }
        return true;
    }
    
    private void eventBPC() {
        lamtuoiBPCBT.addActionListener(event -> {
            maLXBPCCB.removeAllItems();
            maTBPCCB.removeAllItems();
            
            for (LaiXe x : lxList) {
                maLXBPCCB.addItem(x.getMa() + "");
            }
            
            for (Tuyen t : tList) {
                maTBPCCB.addItem(t.getMaT() + "");
            }
        });
        
        nhapBPCBT.addActionListener(event -> {
            setButtonThemBPC(false);
            soLuotBPCTF.requestFocus();
        });
        
        themBPCBT.addActionListener(event -> {
            try {
                String maLX = maLXBPCCB.getSelectedItem().toString();
                String maT = maTBPCCB.getSelectedItem().toString();
                
                if(soLuotBPCTF.getText().isEmpty()) {
                    throw new CustomValidate("khong dc de trong");
                }
                
                int soLuot = Integer.parseInt(soLuotBPCTF.getText());
                if(soLuot <= 0) {
                    throw new CustomValidate("so luot phai duong");
                }
                
                if(soLuot + getSoLuot(Integer.parseInt(maLX)) > 15) {
                    throw new CustomValidate("tong so luot phai nho hon 15");
                }
                
                if(!ktraLX(Integer.parseInt(maLX))) {
                    throw new CustomValidate("ma lai xe khong dc xuat hien 2 lan");
                }
                
                
                BangPhanCong bpc = new BangPhanCong(
                        getLaiXeByMa(Integer.parseInt(maLX)), 
                        getTuyenByMa(Integer.parseInt(maT)), 
                        soLuot
                );
                
                bpcList.add(bpc);
                tmBPC.addRow(bpc.toObject());
                docDataBPC();
                        
                setButtonThemBPC(true);
                
            } catch (CustomValidate e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        });
        
        luuBPCBT.addActionListener(event -> {
            IOFile.viet(fBPC, bpcList);
        });
        
        
        hotenRB.addActionListener(event -> {
            if (hotenRB.isSelected()) {
                soluongRB.setSelected(false);
            }
        });
        soluongRB.addActionListener(event -> {
            if (soluongRB.isSelected()) {
                hotenRB.setSelected(false);
            }
        });
        
        sxBPCBT.addActionListener(event -> {
            if (hotenRB.isSelected() && !soluongRB.isSelected()) {
                Comparator<BangPhanCong> hoten = new Comparator<BangPhanCong>() {
                    @Override
                    public int compare(BangPhanCong o1, BangPhanCong o2) {
                        String ten1 = o1.getLx().getTen();
                        String ten2 = o2.getLx().getTen();
                        
                        String[] arr1 = ten1.split("\\s+");
                        String[] arr2 = ten2.split("\\s+");
                        
                        ten1 = arr1[arr1.length - 1] + ten1;
                        ten2 = arr2[arr2.length - 1] + ten2;
                        
                        return ten1.compareTo(ten2);
                    }
                };
                Collections.sort(bpcList, hoten);
            } else if (!hotenRB.isSelected() && soluongRB.isSelected()) {
                Comparator<BangPhanCong> soLuong = new Comparator<BangPhanCong>() {
                    @Override
                    public int compare(BangPhanCong o1, BangPhanCong o2) {
                        int sl1 = o1.getSoLuot();
                        int sl2 = o2.getSoLuot();
                        
                        return sl2 - sl1;
                    }
                };
                Collections.sort(bpcList, soLuong);
            } else {
                JOptionPane.showMessageDialog(this, "phai chon 1 cach sap xep");
            }
            docDataBPC();
        });
    }

    //------------------------------------------------------
    
    
    //------------------------------------------------------
    private void eventThongKe() {
        thongkeBT.addActionListener(event -> {
            Map<String, Integer> mp = new HashMap<>();
            
            for(BangPhanCong bpc : bpcList) {
                String tenLX = bpc.getLx().getTen();
                String kc = bpc.getT().getKcach();
                String arrKC = kc.substring(0, kc.length() - 2);
                int intKC = Integer.parseInt(arrKC);
                
                if(mp.containsKey(tenLX)) {
                    mp.put(tenLX, mp.get(tenLX) + bpc.getSoLuot() * intKC);
                } else {
                    mp.put(tenLX, bpc.getSoLuot() * intKC);
                }
            }
            
            TA.setText(mp.toString());
        });
    }
    
    
    //------------------------------------------------------
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLX = new javax.swing.JTable();
        nhapLXBT = new javax.swing.JButton();
        themLXBT = new javax.swing.JButton();
        xoaLXBT = new javax.swing.JButton();
        luuLXBT = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        maLXTF = new javax.swing.JTextField();
        tenLXTF = new javax.swing.JTextField();
        dchiLXTF = new javax.swing.JTextField();
        trdoLXCB = new javax.swing.JComboBox<>();
        giamLXRBT = new javax.swing.JRadioButton();
        sxLXBT = new javax.swing.JButton();
        tangLXRBT = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbT = new javax.swing.JTable();
        nhapTBT = new javax.swing.JButton();
        themTBT = new javax.swing.JButton();
        suaTBT = new javax.swing.JButton();
        luuTBT = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        maTTF = new javax.swing.JTextField();
        kcTTF = new javax.swing.JTextField();
        sddTTF = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        themBPCBT = new javax.swing.JButton();
        luuBPCBT = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBPC = new javax.swing.JTable();
        soLuotBPCTF = new javax.swing.JTextField();
        nhapBPCBT = new javax.swing.JButton();
        maLXBPCCB = new javax.swing.JComboBox<>();
        maTBPCCB = new javax.swing.JComboBox<>();
        lamtuoiBPCBT = new javax.swing.JButton();
        hotenRB = new javax.swing.JRadioButton();
        soluongRB = new javax.swing.JRadioButton();
        sxBPCBT = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TA = new javax.swing.JTextArea();
        thongkeBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbLX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ma", "Ten", "Dchi", "Trdo"
            }
        ));
        jScrollPane1.setViewportView(tbLX);

        nhapLXBT.setText("Nhap");

        themLXBT.setText("Them");

        xoaLXBT.setText("Xoa");

        luuLXBT.setText("Luu");

        jLabel1.setText("Ma");

        jLabel2.setText("Ten");

        jLabel3.setText("Dchi");

        jLabel4.setText("Trdo");

        trdoLXCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F" }));

        giamLXRBT.setText("Trinh do giam");

        sxLXBT.setText("Sap xep");

        tangLXRBT.setText("Trinh do tang");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tangLXRBT))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tenLXTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maLXTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(trdoLXCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dchiLXTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(giamLXRBT))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nhapLXBT)
                                .addGap(139, 139, 139)
                                .addComponent(xoaLXBT)
                                .addGap(116, 116, 116)
                                .addComponent(luuLXBT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(themLXBT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(sxLXBT)
                                .addGap(8, 8, 8)))))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(maLXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tenLXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(dchiLXTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(trdoLXCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(94, 94, 94)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nhapLXBT)
                    .addComponent(xoaLXBT)
                    .addComponent(luuLXBT)
                    .addComponent(giamLXRBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(tangLXRBT)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(themLXBT)
                    .addComponent(sxLXBT))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Lai xe", jPanel1);

        tbT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ma", "Khoang cach", "So diem dung"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbT);

        nhapTBT.setText("Nhap");

        themTBT.setText("Them");

        suaTBT.setText("Sua");

        luuTBT.setText("Luu");

        jLabel5.setText("Ma");

        jLabel6.setText("Khoang cach");

        jLabel7.setText("So diem dung");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maTTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(kcTTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sddTTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(themTBT)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(nhapTBT)
                                .addGap(139, 139, 139)
                                .addComponent(suaTBT)
                                .addGap(116, 116, 116)
                                .addComponent(luuTBT)))))
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(maTTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(sddTTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(kcTTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(94, 94, 94)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nhapTBT)
                    .addComponent(suaTBT)
                    .addComponent(luuTBT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(themTBT)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 693, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Tuyen", jPanel2);

        themBPCBT.setText("Them");

        luuBPCBT.setText("Luu");

        jLabel8.setText("Ma lai xe");

        jLabel9.setText("Ma tuyen");

        jLabel10.setText("So luot");

        tbBPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ma lai xe", "Ten lai xe", "Ma tuyen", "So luot"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbBPC);

        nhapBPCBT.setText("Nhap");

        maLXBPCCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        maTBPCCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lamtuoiBPCBT.setText("Lam tuoi 2 ma");

        hotenRB.setText("Ho ten lai xe");

        soluongRB.setText("So luong tuyen giam dan");

        sxBPCBT.setText("Sap xep");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maTBPCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maLXBPCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(soLuotBPCTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lamtuoiBPCBT)
                        .addGap(45, 45, 45))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(nhapBPCBT)
                        .addGap(135, 135, 135)
                        .addComponent(luuBPCBT))
                    .addComponent(themBPCBT))
                .addGap(113, 113, 113)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(soluongRB)
                    .addComponent(hotenRB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sxBPCBT)
                .addGap(34, 34, 34))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(maLXBPCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(maTBPCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(soLuotBPCTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)
                        .addComponent(lamtuoiBPCBT)))
                .addGap(94, 94, 94)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nhapBPCBT)
                    .addComponent(luuBPCBT)
                    .addComponent(hotenRB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(sxBPCBT)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(themBPCBT))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soluongRB)))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Bang Phan Cong", jPanel3);

        TA.setColumns(20);
        TA.setRows(5);
        jScrollPane4.setViewportView(TA);

        thongkeBT.setText("Thong ke");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(thongkeBT)
                .addGap(301, 301, 301))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(thongkeBT)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thong ke", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTMouseClicked
        // TODO add your handling code here:
        int row = tbT.getSelectedRow();
        maTTF.setText(tbT.getValueAt(row, 0) + "");
        kcTTF.setText(tbT.getValueAt(row, 1) + "");
        sddTTF.setText(tbT.getValueAt(row, 2) + "");
    }//GEN-LAST:event_tbTMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TA;
    private javax.swing.JTextField dchiLXTF;
    private javax.swing.JRadioButton giamLXRBT;
    private javax.swing.JRadioButton hotenRB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField kcTTF;
    private javax.swing.JButton lamtuoiBPCBT;
    private javax.swing.JButton luuBPCBT;
    private javax.swing.JButton luuLXBT;
    private javax.swing.JButton luuTBT;
    private javax.swing.JComboBox<String> maLXBPCCB;
    private javax.swing.JTextField maLXTF;
    private javax.swing.JComboBox<String> maTBPCCB;
    private javax.swing.JTextField maTTF;
    private javax.swing.JButton nhapBPCBT;
    private javax.swing.JButton nhapLXBT;
    private javax.swing.JButton nhapTBT;
    private javax.swing.JTextField sddTTF;
    private javax.swing.JTextField soLuotBPCTF;
    private javax.swing.JRadioButton soluongRB;
    private javax.swing.JButton suaTBT;
    private javax.swing.JButton sxBPCBT;
    private javax.swing.JButton sxLXBT;
    private javax.swing.JRadioButton tangLXRBT;
    private javax.swing.JTable tbBPC;
    private javax.swing.JTable tbLX;
    private javax.swing.JTable tbT;
    private javax.swing.JTextField tenLXTF;
    private javax.swing.JButton themBPCBT;
    private javax.swing.JButton themLXBT;
    private javax.swing.JButton themTBT;
    private javax.swing.JButton thongkeBT;
    private javax.swing.JComboBox<String> trdoLXCB;
    private javax.swing.JButton xoaLXBT;
    // End of variables declaration//GEN-END:variables
}
