/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.BanDAO;
import DAO.HoaDonDAO;
import DAO.KhoHangDAO;
import DAO.KhuVucDAO;
import DAO.LoaiSanPhamDAO;
import DAO.SanPhamDAO;
import helper.DialogHelper;
import helper.XDate;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Ban;
import model.SanPham;

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
        loadSanPham();
    }

    public static void loadTabs() {
        loadBanChung();
        loadBanCoKhach();
        loadBanChuaCoKhach();
    }

    public static void loadBanChung() {
        List<Ban> list = banDAO.select();
        pnlBan.removeAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isTrangThai()) {
//                int iii = i;
                ban = new JLabel(CafeGreen);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
//                ban.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                        if (e.getClickCount() == 2) {
//                            xemCTTheoBan(list.get(iii).getMaBan());
//                        }
//                    }
//                });
                pnlBan.add(ban);
            } else {
                ban = new JLabel(CafeBlack);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
                ban.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        goiMon();
                    }
                });
                pnlBan.add(ban);
            }
        }
    }

    public static void loadBanCoKhach() {
        List<Ban> list = banDAO.select();
        pnlDaCoKhach.removeAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isTrangThai()) {
//                int iii = i;
                JLabel ban = new JLabel(CafeGreen);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
//                ban.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                        if (e.getClickCount() == 2) {
//                            xemCTTheoBan(list.get(iii).getMaBan());
//                        }
//                    }
//                });
                pnlDaCoKhach.add(ban);
            }
        }
    }

    public static void loadBanChuaCoKhach() {
        List<Ban> list = banDAO.select();
        pnlChuaCoKhach.removeAll();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isTrangThai()) {
                JLabel ban = new JLabel(CafeBlack);
                if (list.get(i).getMaBan() < 10) {
                    ban.setText("Bàn 0" + list.get(i).getMaBan());
                } else {
                    ban.setText("Bàn " + list.get(i).getMaBan());
                }
                ban.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        goiMon();
                    }
                });
                pnlChuaCoKhach.add(ban);
            }
        }
    }

    void xemThongTinBan() {
        DialogHelper.alert(this, "Xem thông tin bàn");
    }

//    static void xemCTTheoBan(int maBan) {
//        new GoiMonJFrame(maBan).setVisible(true);
//    }
    void xemThongTinChiTiet() {
        new HoaDonChiTietJFrame().setVisible(true);
    }

    static void goiMon() {
        new GoiMonJFrame().setVisible(true);
    }

    public static void loadDonHangTheoBan() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        try {
            List<Object[]> list = hdDAO.getHoaDonTheoBan();
            for (Object[] objects : list) {
                objects[1] = kvDAO.findById((int) objects[1]).getTenKhuVuc();
                model.addRow(objects);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

//    gọi món xong thì load lại để cập nhật số lượng nên dùng static
    public static void loadSanPham() {
        DefaultTableModel model1 = (DefaultTableModel) tblSanPham.getModel();
        model1.setRowCount(0);
        try {
            List<SanPham> list = spDAO.select();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getTenSanPham(),
                    lspDAO.findById(sp.getMaLoaiSP()).getTenLoaiSP(),
                    sp.getGiaBan(),
                    khDAO.getSLByMaSP(sp.getMaSanPham())
                };
                model1.addRow(row);
            }
        } catch (Exception e) {
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
        tabs = new javax.swing.JTabbedPane();
        pnlBan = new javax.swing.JPanel();
        pnlDaCoKhach = new javax.swing.JPanel();
        pnlChuaCoKhach = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        tblDonHang = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        lblSanPham = new javax.swing.JLabel();
        pnlDanhMuc = new javax.swing.JPanel();
        btnGoiMon = new javax.swing.JButton();
        btnChiTiet = new javax.swing.JButton();
        btnBan = new javax.swing.JButton();
        btnKhuVuc = new javax.swing.JButton();
        btnLoaiSP = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnCaLamViec = new javax.swing.JButton();
        btnKhoHang = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnCapNhatBan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabs.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        pnlBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabs.addTab("Chung", pnlBan);

        pnlDaCoKhach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabs.addTab("Đã có khách", pnlDaCoKhach);

        pnlChuaCoKhach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabs.addTab("Chưa có khách", pnlChuaCoKhach);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bàn", "Khu vực", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        tblDonHang.setText("Đơn hàng của bàn: ");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        lblSanPham.setText("Thông tin sản phẩm");

        btnGoiMon.setText("Gọi món");
        btnGoiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiMonActionPerformed(evt);
            }
        });

        btnChiTiet.setText("Chi tiết");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDanhMucLayout = new javax.swing.GroupLayout(pnlDanhMuc);
        pnlDanhMuc.setLayout(pnlDanhMucLayout);
        pnlDanhMucLayout.setHorizontalGroup(
            pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnGoiMon)
                .addGap(18, 18, 18)
                .addComponent(btnChiTiet)
                .addContainerGap(263, Short.MAX_VALUE))
        );
        pnlDanhMucLayout.setVerticalGroup(
            pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDanhMucLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGoiMon)
                    .addComponent(btnChiTiet))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBan.setText("Bàn");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnKhuVuc.setText("Khu vực");
        btnKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuVucActionPerformed(evt);
            }
        });

        btnLoaiSP.setText("Loại sản phẩm");
        btnLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiSPActionPerformed(evt);
            }
        });

        btnNhanVien.setText("Nhân viên");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnCaLamViec.setText("Ca làm việc");
        btnCaLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaLamViecActionPerformed(evt);
            }
        });

        btnKhoHang.setText("Kho hàng");
        btnKhoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoHangActionPerformed(evt);
            }
        });

        btnSanPham.setText("Sản phẩm");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnCapNhatBan.setText("Cập nhật bàn");
        btnCapNhatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(btnBan)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhuVuc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoaiSP)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhanVien)
                        .addGap(18, 18, 18)
                        .addComponent(btnCaLamViec)
                        .addGap(18, 18, 18)
                        .addComponent(btnKhoHang)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCapNhatBan))
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlWrapperLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSanPham)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tblDonHang, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(51, 51, 51))
                            .addGroup(pnlWrapperLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(pnlDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(31, Short.MAX_VALUE))))))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCapNhatBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(tabs)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(tblDonHang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBan)
                    .addComponent(btnKhuVuc)
                    .addComponent(btnLoaiSP)
                    .addComponent(btnNhanVien)
                    .addComponent(btnCaLamViec)
                    .addComponent(btnKhoHang)
                    .addComponent(btnSanPham))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        // TODO add your handling code here:
        new GoiMonJFrame().setVisible(true);
    }//GEN-LAST:event_btnGoiMonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblHoaDon.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                if (DialogHelper.confirm(this, "Bạn muốn thanh toán cho bàn này")) {
                    int maBan = (int) tblHoaDon.getValueAt(index, 0);
//                cập nhật trạng thái bàn: 0 là đã thanh toán và 1 là chưa thanh toán, khách vừa đặt hóa đơn thì sẽ = 1
                    banDAO.datBan(0, maBan);
//                cập nhật trạng thái đơn hàng cho mỗi hóa đơn theo bàn
//                1 là đã thanh toán, mặc định là 0
                    hdDAO.updateTrangThaiHD(1, XDate.now(), maBan);

//                    sau khi cập nhật trạng thái cho bàn và hóa đơn => load lại thông tin ở bảng và JPanel
                    DanhMucJFrame.loadTabs();
                    DanhMucJFrame.loadDonHangTheoBan();
                }
            }
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        // TODO add your handling code here:
        xemThongTinChiTiet();
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        // TODO add your handling code here:
        new BanJFrame().setVisible(true);
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuVucActionPerformed
        // TODO add your handling code here:
        new KhuVucJFrame().setVisible(true);
    }//GEN-LAST:event_btnKhuVucActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        new SanPhamJFrame().setVisible(true);
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiSPActionPerformed
        // TODO add your handling code here:
        new LoaiSanPhamJFrame().setVisible(true);
    }//GEN-LAST:event_btnLoaiSPActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        new NhanVienJFrame().setVisible(true);
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnCaLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaLamViecActionPerformed
        // TODO add your handling code here:
        new CaLamViecJFrame().setVisible(true);
    }//GEN-LAST:event_btnCaLamViecActionPerformed

    private void btnKhoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoHangActionPerformed
        // TODO add your handling code here:
        new KhoHangJFrame().setVisible(true);
    }//GEN-LAST:event_btnKhoHangActionPerformed

    private void btnCapNhatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatBanActionPerformed
        // TODO add your handling code here:
        this.loadTabs();
    }//GEN-LAST:event_btnCapNhatBanActionPerformed

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
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnCaLamViec;
    private javax.swing.JButton btnCapNhatBan;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnGoiMon;
    private javax.swing.JButton btnKhoHang;
    private javax.swing.JButton btnKhuVuc;
    private javax.swing.JButton btnLoaiSP;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSanPham;
    public static javax.swing.JPanel pnlBan;
    public static javax.swing.JPanel pnlChuaCoKhach;
    public static javax.swing.JPanel pnlDaCoKhach;
    private javax.swing.JPanel pnlDanhMuc;
    private javax.swing.JPanel pnlWrapper;
    public static javax.swing.JTabbedPane tabs;
    private javax.swing.JLabel tblDonHang;
    public static javax.swing.JTable tblHoaDon;
    public static javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
