/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.LoaiSanPhamDAO;
import helper.DialogHelper;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.LoaiSanPham;

/**
 *
 * @author Admin
 */
public class LoaiSanPhamJFrame extends javax.swing.JFrame {

    /**
     * Creates new form LoaiSanPhamJFrame
     */
    public LoaiSanPhamJFrame() {
        initComponents();
        setLocationRelativeTo(null);
        load();
    }
    int index = 0;
    LoaiSanPhamDAO dao = new LoaiSanPhamDAO();
<<<<<<< HEAD
    
    
    void load(){
=======

    void init() {

    }

    void load() {
>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df
        DefaultTableModel model = (DefaultTableModel) tblLoaiSanPham.getModel();
        model.setRowCount(0);
        try {
            List<LoaiSanPham> list = dao.select();
            for (LoaiSanPham lsp : list) {
                Object[] row = {
                    lsp.getMaLoaiSP(),
                    lsp.getTenLoaiSP()
                };
                model.addRow(row);

            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy ván dữ liệu");
        }
    }

    void insert() {
        LoaiSanPham model = getModel();
        try {
            dao.insert(model);
            this.load();
            this.clear();
            DialogHelper.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Thêm mới thất bại");
        }
    }

    void update() {
        LoaiSanPham model = getModel();
        try {
            dao.update(model);
            this.load();
<<<<<<< HEAD
            DialogHelper.alert(this, "Cập nhâp thành công");

=======
<<<<<<< HEAD
            this.clear();
            DialogHelper.alert(this , "Cập nhâp thành công");
=======
<<<<<<< HEAD
            DialogHelper.alert(this, "Cập nhâp thành công");
=======
            DialogHelper.alert(this , "Cập nhật thành công");
>>>>>>> 697c0e6908ea5220acda662c457d21dc750e289d
>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df
>>>>>>> cd7bb5e526359109b8d55d078fdcf537064dc711
        } catch (Exception e) {
            DialogHelper.alert(this, "Cập nhật thất bại");
            System.out.println(e.toString());
        }
    }
<<<<<<< HEAD
    
    
    void delete(){
        if(DialogHelper.confirm(this, "Bạn muốn xóa hàng này")){
            Integer maLoaiSP = Integer.valueOf(txtLoaiSanPham.getToolTipText());
            try {
                dao.delete(maLoaiSP);
                this.load();
                this.clear();
                DialogHelper.alert(this , "Xoá thành công");
=======

    void delete(int maLoaiSP) {
        if (DialogHelper.confirm(this, "Bạn muốn xóa hàng này")) {
            try {
                dao.delete(maLoaiSP);
                this.load();
                DialogHelper.alert(this, "Xoá thành công");
>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại");
            }
        }
    }

    void clear() {
        LoaiSanPham model = new LoaiSanPham();
        model.setMaLoaiSP(model.getMaLoaiSP());
        model.setTenLoaiSP(model.getTenLoaiSP());
<<<<<<< HEAD
        this.setModel(model);
        txtLoaiSanPham.setText("");
        
=======

>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df
    }

    void edit() {
        try {
            int malsp = (int) tblLoaiSanPham.getValueAt(this.index, 0);
            LoaiSanPham model = dao.findById(malsp);

            if (model != null) {
                this.setModel(model);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
<<<<<<< HEAD
    
    void setModel(LoaiSanPham model){
        txtLoaiSanPham.setToolTipText(String.valueOf(model.getMaLoaiSP()));
        txtLoaiSanPham.setText(model.getTenLoaiSP());
        
    }
    LoaiSanPham getModel(){
        LoaiSanPham model = new LoaiSanPham();
        model.setMaLoaiSP(Integer.valueOf(txtLoaiSanPham.getToolTipText()));
=======

    void setModel(LoaiSanPham model) {
        txtLoaiSanPham.setText(model.getTenLoaiSP());
        txtLoaiSanPham.setToolTipText(String.valueOf(model.getMaLoaiSP()));
    }

    LoaiSanPham getModel() {
        LoaiSanPham model = new LoaiSanPham();
        model.setMaLoaiSP((int) tblLoaiSanPham.getValueAt(this.index , 0));
>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df
        model.setTenLoaiSP(txtLoaiSanPham.getText());
        model.setMaLoaiSP(Integer.parseInt(txtLoaiSanPham.getToolTipText()));
        return model;
    }
<<<<<<< HEAD
    
    
    
     void setStatus(boolean insertable) {
        btnThem.setEnabled(insertable);
        btnSua.setEnabled(insertable);
        btnXoa.setEnabled(insertable);
        btnMoi.setEnabled(insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblLoaiSanPham.getRowCount() - 1;
        btnNext.setEnabled(!insertable && last);
        btnLast.setEnabled(!insertable && last);
        btnFirst.setEnabled(!insertable && first);
        btnPrev.setEnabled(!insertable && first);
    }
    
    
    
    
=======
>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df

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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiSanPham = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        lblLoaiSanPham = new javax.swing.JLabel();
        txtLoaiSanPham = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý loại sản phẩm");

        lblTieuDe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("Quản Lý Loại Sản Phẩm");

        tblLoaiSanPham.setAutoCreateRowSorter(true);
        tblLoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Loại Sản Phẩm", "Tên Loại Sản Phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
<<<<<<< HEAD
                true, false
=======
                false, false
>>>>>>> 37414f2552fc5fbf1b6c6b66028ea39e24ba31df
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLoaiSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblLoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLoaiSanPham);

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("MỚI");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setText("<<");

        btnPrev.setText("|<");

        btnNext.setText(">|");

        btnLast.setText(">>");

        lblLoaiSanPham.setText("Loại sản phẩm");

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTieuDe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lblLoaiSanPham)
                        .addGap(23, 23, 23)
                        .addComponent(txtLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnFirst)
                        .addGap(18, 18, 18)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLast)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiSanPham)
                    .addComponent(txtLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua)
                        .addGap(10, 10, 10)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMoi)))
                .addGap(27, 27, 27)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLast))
                    .addComponent(btnFirst))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete(Integer.parseInt(txtLoaiSanPham.getToolTipText()));
        this.load();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tblLoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSanPhamMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            this.index = tblLoaiSanPham.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblLoaiSanPhamMouseClicked

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
            java.util.logging.Logger.getLogger(LoaiSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoaiSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoaiSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoaiSanPhamJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoaiSanPhamJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLoaiSanPham;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JPanel pnlWrapper;
    private javax.swing.JTable tblLoaiSanPham;
    private javax.swing.JTextField txtLoaiSanPham;
    // End of variables declaration//GEN-END:variables
}
