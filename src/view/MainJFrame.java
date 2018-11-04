/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author duann
 */
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

            @Override
            public void actionPerformed(ActionEvent e) {
                lblDongHo.setText(format.format(new Date()));
            }

        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        btnHoaDon = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnBan = new javax.swing.JButton();
        btnKhuVuc = new javax.swing.JButton();
        btnKhoHang = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnLoaiSanPham = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnCaLamViec = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnHuongDan = new javax.swing.JButton();
        pnlTrangThai = new javax.swing.JPanel();
        lblTrangThai = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        lblNoiDung = new javax.swing.JLabel();
        mnb = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuQuanLy = new javax.swing.JMenu();
        mniNhanVien = new javax.swing.JMenuItem();
        mniKhuVuc = new javax.swing.JMenuItem();
        mniBan = new javax.swing.JMenuItem();
        mniKhoHang = new javax.swing.JMenuItem();
        mniSanPham = new javax.swing.JMenuItem();
        mniLoaiSanPham = new javax.swing.JMenuItem();
        mniCaLamViec = new javax.swing.JMenuItem();
        mniHoaDon = new javax.swing.JMenuItem();
        mnuThongKe = new javax.swing.JMenu();
        mniDoanhThu = new javax.swing.JMenuItem();
        mniTKKhoHang = new javax.swing.JMenuItem();
        mniTKNhanVien = new javax.swing.JMenuItem();
        mnuTroGiup = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mniGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolBar.setRollover(true);

        btnHoaDon.setText("Danh mục gọi món");
        btnHoaDon.setFocusable(false);
        btnHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHoaDon.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnHoaDon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        toolBar.add(btnHoaDon);
        toolBar.add(jSeparator6);

        btnBan.setText("Bàn");
        btnBan.setFocusable(false);
        btnBan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBan.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnBan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });
        toolBar.add(btnBan);

        btnKhuVuc.setText("Khu vực");
        btnKhuVuc.setFocusable(false);
        btnKhuVuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhuVuc.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnKhuVuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuVucActionPerformed(evt);
            }
        });
        toolBar.add(btnKhuVuc);

        btnKhoHang.setText("Kho hàng");
        btnKhoHang.setFocusable(false);
        btnKhoHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhoHang.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnKhoHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoHangActionPerformed(evt);
            }
        });
        toolBar.add(btnKhoHang);
        toolBar.add(jSeparator7);

        btnLoaiSanPham.setText("Loại sản phẩm");
        btnLoaiSanPham.setFocusable(false);
        btnLoaiSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnLoaiSanPham.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnLoaiSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoaiSanPhamActionPerformed(evt);
            }
        });
        toolBar.add(btnLoaiSanPham);

        btnSanPham.setText("Sản phẩm");
        btnSanPham.setFocusable(false);
        btnSanPham.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSanPham.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnSanPham.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });
        toolBar.add(btnSanPham);

        btnCaLamViec.setText("Ca làm việc");
        btnCaLamViec.setFocusable(false);
        btnCaLamViec.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCaLamViec.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnCaLamViec.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCaLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaLamViecActionPerformed(evt);
            }
        });
        toolBar.add(btnCaLamViec);
        toolBar.add(jSeparator8);

        btnHuongDan.setText("Hướng dẫn");
        btnHuongDan.setFocusable(false);
        btnHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHuongDan.setMargin(new java.awt.Insets(2, 10, 2, 10));
        btnHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuongDanActionPerformed(evt);
            }
        });
        toolBar.add(btnHuongDan);

        pnlTrangThai.setBackground(new java.awt.Color(215, 222, 229));

        lblTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Info.png"))); // NOI18N
        lblTrangThai.setText("Hệ thống quản lý quán cà phê");

        lblDongHo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Alarm.png"))); // NOI18N
        lblDongHo.setText("10:55 PM");

        javax.swing.GroupLayout pnlTrangThaiLayout = new javax.swing.GroupLayout(pnlTrangThai);
        pnlTrangThai.setLayout(pnlTrangThaiLayout);
        pnlTrangThaiLayout.setHorizontalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrangThaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTrangThai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongHo)
                .addGap(25, 25, 25))
        );
        pnlTrangThaiLayout.setVerticalGroup(
            pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTrangThaiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTrangThaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai)
                    .addComponent(lblDongHo))
                .addContainerGap())
        );

        lblNoiDung.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/c2145ba06862c3e19669ed71bcf14ae4.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNoiDung)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(pnlTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mnuHeThong.setText("Hệ thống");

        mniDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mniDangNhap.setText("Đăng nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangNhap);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_MASK));
        mniDangXuat.setText("Đăng xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.add(jSeparator1);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setText("Kết thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniKetThuc);

        mnb.add(mnuHeThong);

        mnuQuanLy.setText("Quản lý");

        mniNhanVien.setText("Nhân viên");
        mniNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhanVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniNhanVien);

        mniKhuVuc.setText("Khu vực");
        mniKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhuVucActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniKhuVuc);

        mniBan.setText("Bàn");
        mniBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBanActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniBan);

        mniKhoHang.setText("Kho hàng");
        mniKhoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKhoHangActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniKhoHang);

        mniSanPham.setText("Sản phẩm");
        mniSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSanPhamActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniSanPham);

        mniLoaiSanPham.setText("Loại sản phẩm");
        mniLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLoaiSanPhamActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniLoaiSanPham);

        mniCaLamViec.setText("Ca làm việc");
        mniCaLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCaLamViecActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniCaLamViec);

        mniHoaDon.setText("Hóa đơn");
        mniHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHoaDonActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniHoaDon);

        mnb.add(mnuQuanLy);

        mnuThongKe.setText("Thống kê");

        mniDoanhThu.setText("Doanh thu");
        mniDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoanhThuActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniDoanhThu);

        mniTKKhoHang.setText("Kho hàng");
        mniTKKhoHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKKhoHangActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKKhoHang);

        mniTKNhanVien.setText("Nhân viên");
        mniTKNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTKNhanVienActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniTKNhanVien);

        mnb.add(mnuThongKe);

        mnuTroGiup.setText("Trợ giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setText("Hướng dẫn sử dụng");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniHuongDan);
        mnuTroGiup.add(jSeparator5);

        mniGioiThieu.setText("Giới thiệu sản phẩm");
        mniGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGioiThieuActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniGioiThieu);

        mnb.add(mnuTroGiup);

        setJMenuBar(mnb);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuVucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhuVucActionPerformed

    private void btnKhoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhoHangActionPerformed

    private void btnLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoaiSanPhamActionPerformed

    }//GEN-LAST:event_btnLoaiSanPhamActionPerformed

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniKetThucActionPerformed

    private void mniNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniNhanVienActionPerformed

    private void mniKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhuVucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniKhuVucActionPerformed

    private void mniBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniBanActionPerformed

    private void mniKhoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKhoHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniKhoHangActionPerformed

    private void mniDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoanhThuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniDoanhThuActionPerformed

    private void mniTKKhoHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKKhoHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniTKKhoHangActionPerformed

    private void mniTKNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTKNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniTKNhanVienActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_mniHuongDanActionPerformed

    private void mniGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGioiThieuActionPerformed
    }//GEN-LAST:event_mniGioiThieuActionPerformed

    private void mniSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniSanPhamActionPerformed

    private void mniLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniLoaiSanPhamActionPerformed

    private void mniCaLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCaLamViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniCaLamViecActionPerformed

    private void mniHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mniHoaDonActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnCaLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaLamViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCaLamViecActionPerformed

    private void btnHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuongDanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuongDanActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnCaLamViec;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnHuongDan;
    private javax.swing.JButton btnKhoHang;
    private javax.swing.JButton btnKhuVuc;
    private javax.swing.JButton btnLoaiSanPham;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblNoiDung;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JMenuBar mnb;
    private javax.swing.JMenuItem mniBan;
    private javax.swing.JMenuItem mniCaLamViec;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDoanhThu;
    private javax.swing.JMenuItem mniGioiThieu;
    private javax.swing.JMenuItem mniHoaDon;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniKhoHang;
    private javax.swing.JMenuItem mniKhuVuc;
    private javax.swing.JMenuItem mniLoaiSanPham;
    private javax.swing.JMenuItem mniNhanVien;
    private javax.swing.JMenuItem mniSanPham;
    private javax.swing.JMenuItem mniTKKhoHang;
    private javax.swing.JMenuItem mniTKNhanVien;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuQuanLy;
    private javax.swing.JMenu mnuThongKe;
    private javax.swing.JMenu mnuTroGiup;
    private javax.swing.JPanel pnlTrangThai;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
