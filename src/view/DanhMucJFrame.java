/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.*;
import helper.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Ban;

/**
 *
 * @author duann
 */
public class DanhMucJFrame extends javax.swing.JFrame {

    /**
     * Creates new form DanhMucJFrame
     */
    public static BanDAO banDAO = new BanDAO();
    public static KhoHangDAO khDAO = new KhoHangDAO();
    public static SanPhamDAO spDAO = new SanPhamDAO();
    public static LoaiSanPhamDAO lspDAO = new LoaiSanPhamDAO();
    public static HoaDonDAO hdDAO = new HoaDonDAO();
    public static KhuVucDAO kvDAO = new KhuVucDAO();
    DanhMucDAO dmdao = new DanhMucDAO();
    public static JLabel ban;
    public static int index = 0;
    public static ImageIcon CafeGreen;
    public static ImageIcon CafeBlack;

    static {
        CafeGreen = new ImageIcon(DanhMucJFrame.class.getResource("/images/icons8-cafe-50.png"));
        CafeBlack = new ImageIcon(DanhMucJFrame.class.getResource("/images/icons8-cafe-filled-50.png"));
    }

    public DanhMucJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadTabs();
        loadDonHangTheoBan();
        setIconImage(ShareHelper.APP_ICON);
        setBoderForTable(jScrollPane1);
    }

    public static void loadTabs() {
        loadBanChung();
    }

    void setBoderForTable(JScrollPane scp) {
        scp.setViewportBorder(null);
        scp.setBorder(null);
    }

    public static void loadBanChung() {
        List<Ban> list = banDAO.select();
        pnlBan.removeAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isTrangThai()) {
                int id = i;
                ban = new JLabel(CafeGreen);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
                ban.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            thongTinTheoBan(list.get(id).getMaBan());
                        }
                    }
                });
                pnlBan.add(ban);
                pnlBan.revalidate();
                pnlBan.setVisible(true);
            } else {
                ban = new JLabel(CafeBlack);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
                int id = i;
                ban.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            thongTinTheoBan(list.get(id).getMaBan());
                        }
                    }
                });
                pnlBan.add(ban);
                pnlBan.revalidate();
                pnlBan.setVisible(true);
            }
        }
    }

    public static void thongTinTheoBan(int id) {
        new ThongTinDonHangJFrame(id).setVisible(true);
    }

    void xemThongTinBan() {
        DialogHelper.alert(this, "Xem thông tin bàn");
    }

    void xemThongTinChiTiet() {
        new HoaDonChiTietJFrame().setVisible(true);
    }

    public static void loadDonHangTheoBan() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            List<Object[]> list = hdDAO.getHoaDonTheoBan();
            for (Object[] objects : list) {
                model.addRow(objects);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlWrapper = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        lblBanHD = new javax.swing.JLabel();
        pnlBan = new javax.swing.JPanel();
        mnu = new javax.swing.JMenuBar();
        mniBan = new javax.swing.JMenu();
        mniKhuVuc = new javax.swing.JMenu();
        mniSanPham = new javax.swing.JMenu();
        mniLoaiSanPham = new javax.swing.JMenu();
        mniNhanVien = new javax.swing.JMenu();
        mniCaLamViec = new javax.swing.JMenu();
        mniKhoHang = new javax.swing.JMenu();
        mniThongKe = new javax.swing.JMenu();
        mniChiTiet = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Ie9liJ.jpg"))); // NOI18N

        tblHoaDon.setAutoCreateRowSorter(true);
        tblHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bàn", "Khu vực"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setGridColor(new java.awt.Color(255, 255, 255));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        lblBanHD.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblBanHD.setForeground(new java.awt.Color(255, 255, 255));
        lblBanHD.setText("Thông tin các bàn đang hoạt động");

        pnlBan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(pnlBan, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBanHD)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlWrapperLayout.createSequentialGroup()
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblBanHD)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlWrapperLayout.createSequentialGroup()
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        mniBan.setText("Bàn");
        mniBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniBanMouseClicked(evt);
            }
        });
        mnu.add(mniBan);

        mniKhuVuc.setText("Khu vực");
        mniKhuVuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniKhuVucMouseClicked(evt);
            }
        });
        mnu.add(mniKhuVuc);

        mniSanPham.setText("Sản phẩm");
        mniSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniSanPhamMouseClicked(evt);
            }
        });
        mnu.add(mniSanPham);

        mniLoaiSanPham.setText("Loại sản phẩm");
        mniLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniLoaiSanPhamMouseClicked(evt);
            }
        });
        mnu.add(mniLoaiSanPham);

        mniNhanVien.setText("Nhân viên");
        mniNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniNhanVienMouseClicked(evt);
            }
        });
        mnu.add(mniNhanVien);

        mniCaLamViec.setText("Ca làm việc");
        mniCaLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniCaLamViecMouseClicked(evt);
            }
        });
        mnu.add(mniCaLamViec);

        mniKhoHang.setText("Kho hàng");
        mniKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniKhoHangMouseClicked(evt);
            }
        });
        mnu.add(mniKhoHang);

        mniThongKe.setText("Thống kê");
        mniThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniThongKeMouseClicked(evt);
            }
        });
        mnu.add(mniThongKe);

        mniChiTiet.setText("Chi tiết");
        mniChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mniChiTietMouseClicked(evt);
            }
        });
        mnu.add(mniChiTiet);

        setJMenuBar(mnu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblHoaDon.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                thongTinTheoBan((int) tblHoaDon.getValueAt(index, 0));
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void mniBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniBanMouseClicked
        // TODO add your handling code here:
        new BanJFrame().setVisible(true);
    }//GEN-LAST:event_mniBanMouseClicked

    private void mniKhuVucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniKhuVucMouseClicked
        // TODO add your handling code here:
        new KhuVucJFrame().setVisible(true);
    }//GEN-LAST:event_mniKhuVucMouseClicked

    private void mniSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniSanPhamMouseClicked
        // TODO add your handling code here:
        new SanPhamJFrame().setVisible(true);
    }//GEN-LAST:event_mniSanPhamMouseClicked

    private void mniLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        new LoaiSanPhamJFrame().setVisible(true);
    }//GEN-LAST:event_mniLoaiSanPhamMouseClicked

    private void mniNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniNhanVienMouseClicked
        // TODO add your handling code here:
        new NhanVienJFrame().setVisible(true);
    }//GEN-LAST:event_mniNhanVienMouseClicked

    private void mniCaLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniCaLamViecMouseClicked
        // TODO add your handling code here:
        new CaLamViecJFrame().setVisible(true);
    }//GEN-LAST:event_mniCaLamViecMouseClicked

    private void mniKhoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniKhoHangMouseClicked
        // TODO add your handling code here:
        new KhoHangJFrame().setVisible(true);
    }//GEN-LAST:event_mniKhoHangMouseClicked

    private void mniThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniThongKeMouseClicked
        // TODO add your handling code here:
        new ThongKeJFrame().setVisible(true);
    }//GEN-LAST:event_mniThongKeMouseClicked

    private void mniChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mniChiTietMouseClicked
        // TODO add your handling code here:
        this.xemThongTinChiTiet();
    }//GEN-LAST:event_mniChiTietMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DanhMucJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DanhMucJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBanHD;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JMenu mniBan;
    private javax.swing.JMenu mniCaLamViec;
    private javax.swing.JMenu mniChiTiet;
    private javax.swing.JMenu mniKhoHang;
    private javax.swing.JMenu mniKhuVuc;
    private javax.swing.JMenu mniLoaiSanPham;
    private javax.swing.JMenu mniNhanVien;
    private javax.swing.JMenu mniSanPham;
    private javax.swing.JMenu mniThongKe;
    private javax.swing.JMenuBar mnu;
    public static javax.swing.JPanel pnlBan;
    public static javax.swing.JPanel pnlWrapper;
    public static javax.swing.JTable tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
