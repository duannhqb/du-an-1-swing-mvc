/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.KhoHangDAO;
import DAO.SanPhamDAO;
import helper.DialogHelper;
import helper.XDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.KhoHang;
import model.SanPham;

/**
 *
 * @author Admin
 */
public class KhoHangJFrame extends javax.swing.JFrame {

    /**
     * Creates new form quanlykhohang
     */
    public KhoHangJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        load();
    }
    int index = 0;
    KhoHangDAO dao = new KhoHangDAO();
    SanPhamDAO spdao = new SanPhamDAO();

    void init() {
        fillCombobox();
    }

    void fillCombobox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        try {
            List<SanPham> list = spdao.select();
            for (SanPham sanPham : list) {
                model.addElement(sanPham.getTenSanPham());
            }
        } catch (Exception e) {
        }
    }

    void load() {
        DefaultTableModel model = (DefaultTableModel) tblQuanLyKhoHang.getModel();
        model.setRowCount(0);
        try {
            List<KhoHang> list = dao.select();
            for (KhoHang kh : list) {
                Object[] row = {
                    kh.getMaKhoHang(),
                    spdao.findById(kh.getMaSanPham()).getTenSanPham(),
                    kh.getMaNhanVien(),
                    kh.getNgayNhap(),
                    kh.getHanSuDung(),
                    kh.getSoLuong()
                };
                model.addRow(row);

            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");

        }
    }

    void insert() {
        KhoHang model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại!");
            System.out.println(e.toString());
        }
    }

    void update() {
        KhoHang model = getModel();
        try {
            dao.update(model);
            this.load();
            DialogHelper.alert(this, "Cập nhập thành công!");
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhập thất bại!");
        }
    }

    void delete() {
        if (DialogHelper.confirm(this, "Bạn thực sự muôn xóa Kho Hàng này!")) {
            Integer makh = Integer.valueOf(cboSanPham.getToolTipText());
            try {
                dao.delete(makh);
                this.load();
                this.clear();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }

        }
    }

    void clear() {
        KhoHang model = new KhoHang();
        String sanpham = (String) cboSanPham.getSelectedItem();
        SanPham sp = spdao.findByName(sanpham);
        model.setSoLuong(model.getSoLuong());
        model.setGhiChu(model.getGhiChu());
        this.cboSanPham.setSelectedItem(0);
        this.setModel(model);
    }

    void edit() {
        try {
            int makh = (int) tblQuanLyKhoHang.getValueAt(this.index, 0);
            KhoHang model = dao.findById(makh);
            if (model != null) {
                this.setModel(model);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void setModel(KhoHang model) {
        txtSoLuong.setToolTipText(String.valueOf(model.getMaKhoHang()));
        cboSanPham.setToolTipText(String.valueOf(model.getMaKhoHang()));
        SanPham sp = spdao.findById(model.getMaSanPham());

        try {
            cboSanPham.setSelectedItem(sp.getTenSanPham());
        } catch (Exception e) {
        }

        txtSoLuong.setText(String.valueOf(model.getSoLuong()));
        txtGhiChu.setText(model.getGhiChu());
    }

    KhoHang getModel() {

        KhoHang khoHang = new KhoHang();
        SanPham sanPham = new SanPham();
        String tenSanPham = (String) cboSanPham.getSelectedItem();
        SanPham sp = spdao.findByName(tenSanPham);
        khoHang.setMaKhoHang(Integer.valueOf(txtSoLuong.getToolTipText()));
        khoHang.setMaSanPham(Integer.valueOf(txtSoLuong.getToolTipText()));
        khoHang.setMaNhanVien(Integer.valueOf(txtSoLuong.getToolTipText()));
        khoHang.setMaKhoHang(khoHang.getMaKhoHang());
        khoHang.setMaSanPham(sp.getMaSanPham());
        khoHang.setMaNhanVien(4);
//        khoHang.setMaNhanVien(ShareHelper.USER.getMaNhanVien());
        khoHang.setNgayNhap(XDate.now());
        khoHang.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        khoHang.setGhiChu(txtGhiChu.getText());
        khoHang.setHanSuDung(XDate.add(30)); // hạn sử dụng 1 tháng kể từ ngày thêm 

        return khoHang;
    }

    void setStatus(boolean insertable) {
        btnthem.setEnabled(insertable);
        btnsua.setEnabled(insertable);
        btnxoa.setEnabled(insertable);
        btnmoi.setEnabled(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblQuanLyKhoHang.getRowCount() - 1;
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);

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
        lblTieuDe = new javax.swing.JLabel();
        lblsanpham = new javax.swing.JLabel();
        lblghichu = new javax.swing.JLabel();
        lblsoluong = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQuanLyKhoHang = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnmoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        cboSanPham = new javax.swing.JComboBox<>();
        txtSoLuong = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý kho hàng");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Quản Lý Kho Hàng");

        lblsanpham.setText("Sản Phẩm:");

        lblghichu.setText("Ghi Chú:");

        lblsoluong.setText("Số Lượng:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

        tblQuanLyKhoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Kho Hàng", "Tên Sản Phẩm", "Mã Nhân Viên", "Ngày Nhập", "Ngày hết hạn", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblQuanLyKhoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanLyKhoHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblQuanLyKhoHang);

        btnthem.setText("THÊM");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setText("SỬA");
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setText("XÓA");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnmoi.setText("MỚI");
        btnmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmoiActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");

        btnPrev.setText("<<");

        btnNext.setText(">>");

        btnLast.setText(">|");

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(btnthem)
                        .addGap(18, 18, 18)
                        .addComponent(btnsua, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnmoi, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblsanpham)
                            .addComponent(lblsoluong)
                            .addComponent(lblghichu))
                        .addGap(45, 45, 45)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                            .addComponent(cboSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoLuong))))
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWrapperLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163))))
            .addComponent(lblTieuDe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblsanpham)
                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNext)
                            .addComponent(btnLast))
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst)
                            .addComponent(btnPrev))))
                .addGap(18, 18, 18)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblsoluong)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblghichu)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnthem)
                            .addComponent(btnsua)
                            .addComponent(btnxoa)
                            .addComponent(btnmoi)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        delete();

//        sau khi xóa xong thì load lại bảng sản phẩm ở danh mục chính
        DanhMucJFrame.loadSanPham();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        insert();

//        sau khi thêm xong thì load lại bảng sản phẩm ở danh mục chính
        DanhMucJFrame.loadSanPham();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        // TODO add your handling code here:
        update();

//        sau khi sửa xong thì load lại bảng sản phẩm ở danh mục chính
        DanhMucJFrame.loadSanPham();
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnmoiActionPerformed

    private void tblQuanLyKhoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanLyKhoHangMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblQuanLyKhoHang.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblQuanLyKhoHangMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        fillCombobox();
        load();
        clear();
        setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line argumentsu
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
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KhoHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnmoi;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblghichu;
    private javax.swing.JLabel lblsanpham;
    private javax.swing.JLabel lblsoluong;
    private javax.swing.JPanel pnlWrapper;
    private javax.swing.JTable tblQuanLyKhoHang;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
