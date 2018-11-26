/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.BanDAO;
import DAO.HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.KhoHangDAO;
import DAO.SanPhamDAO;
import helper.DialogHelper;
import helper.XDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.Ban;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;

/**
 *
 * @author duann
 */
public class GoiMonJFrame extends javax.swing.JFrame {

    /**
     * Creates new form GoiMonJFrame
     */
    HoaDonDAO dao = new HoaDonDAO();
    HoaDonChiTietDAO daoCT = new HoaDonChiTietDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    BanDAO banDAO = new BanDAO();
    KhoHangDAO khDAO = new KhoHangDAO();
    int index = 0;
    int soLuong = 0;
    int maSanPham;

    public GoiMonJFrame() {
        initComponents();
        init();
    }

    public GoiMonJFrame(int index) {
        initComponents();
        init();
        this.index = index;
        fillToForm(index);
    }

    void init() {
        this.setLocationRelativeTo(null);
        fillComboBoxBan();
        fillComboBoxSanPham();
    }

    boolean isThanhToan(int maHD) {
        List<HoaDon> list = dao.select();
        for (HoaDon hoaDon : list) {
            if (hoaDon.getMaHoaDon() == maHD) {
                if (hoaDon.isTrangThai()) {
//                    đã thanh toán thì không cho thêm nhập xóa sửa dữ liệu
                    return true;
                }
            }
        }
        return false;
    }

    void setStatus(boolean insertTable) {
        btnThem.setEnabled(insertTable);
        btnSua.setEnabled(insertTable);
        btnMoi.setEnabled(insertTable);
    }

    void fillToForm(int maHoaDon) {
        HoaDon model = dao.findById(maHoaDon);
        setModel(model);
        cboBan.disable();
//        gán số lượng trước khi sửa để thêm vào kho hàng để cập nhật số lượng mới nếu có thay đổi
        soLuong = Integer.parseInt(txtSoLuong.getText());
        maSanPham = spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham();

        if (isThanhToan(maHoaDon)) {
            setStatus(false);
        }

    }

    void fillComboBoxSanPham() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboSanPham.getModel();
        model.removeAllElements();
        try {
            List<SanPham> list = spDAO.select();
            for (SanPham sp : list) {
                model.addElement(sp.getTenSanPham());
            }
        } catch (Exception e) {
        }
    }

    void fillComboBoxBan() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboBan.getModel();
        model.removeAllElements();
        try {
            List<Ban> list = banDAO.select();
            for (Ban ban : list) {
                model.addElement(ban.getMaBan());
            }
        } catch (Exception e) {
        }
    }

    HoaDon getModelHD() {
        HoaDon hoaDon = new HoaDon();
//      mã nhân viên sau khu tạo form đăng nhập sẽ sửa lại
        hoaDon.setMaNhanVien(1);
        hoaDon.setMaBan((int) cboBan.getSelectedItem());
        hoaDon.setGhiChu("");
        hoaDon.setTrangThai(false);
//        ngày thanh toán: ban đầu sản phẩm được thêm vào hóa đơn thì chưa thanh toán, nhưng, ta vẫn lấy ngày thanh toán là ngày thêm vào hóa đơn, rồi sau đó chạy phần update sửa ngày thanh toán, để tránh null dữ liệu dẫn đến bug
        hoaDon.setNgayThanhToan(XDate.now());
        hoaDon.setThanhTien(Float.parseFloat(txtThanhTien.getText()));
        try {
            hoaDon.setMaHoaDon(Integer.parseInt(cboSanPham.getToolTipText()));
        } catch (Exception e) {
        }
        return hoaDon;
    }

    HoaDonChiTiet getModelHDCT() {
        HoaDonChiTiet hoaDonCT = new HoaDonChiTiet();
        String tenSP = (String) cboSanPham.getSelectedItem();
//      với trường hợp gọi món thì mã hóa đơn chi tiết sẽ bị NULL và được set mã tự động vào db, còn trường hợp cập nhật món đã gọi thì cboBan đã được set giá trị ở setModel      

        if (cboBan.getToolTipText() != null) {
            hoaDonCT.setMaHoaDonCT(Integer.parseInt(cboBan.getToolTipText()));
        }
        hoaDonCT.setMaSanPham(spDAO.findByName(tenSP).getMaSanPham());
        hoaDonCT.setSoLuongSP(Integer.parseInt(txtSoLuong.getText()));
        return hoaDonCT;
    }

    HoaDon checkHD(HoaDon model) {
        List<HoaDon> list = dao.getAll();
        for (HoaDon hoaDon : list) {
            if (hoaDon.getMaHoaDon() == model.getMaHoaDon()) {
                return hoaDon;
            }
        }
        return null;
    }

    void setModel(HoaDon model) {
        HoaDon hoaDon = checkHD(model);
//        null thì chưa có cái này để giúp cho click vào bảng trong hóa đơn chi tiết hiển thị cái này lên
//        nếu mà mã hóa đơn = null thì chưa có mã hóa đơn vì vậy => thêm mới
//        ngược lại nếu mã hóa đơn đã có thì sẽ trả về hóa đơn rồi get mã hóa đơn về để chỉnh sửa
        if (hoaDon != null) {
//            mã hóa đơn chi tiết gán lên tooltiptext cboban
            cboBan.setToolTipText(String.valueOf(hoaDon.getHoaDonChiTiet().getMaHoaDonCT()));
            cboSanPham.setToolTipText(String.valueOf(hoaDon.getMaHoaDon()));
//            
            cboBan.setSelectedItem(hoaDon.getMaBan());
            cboSanPham.setSelectedItem(hoaDon.getSanPham().getTenSanPham());
            txtSoLuong.setText(String.valueOf(hoaDon.getHoaDonChiTiet().getSoLuongSP()));
            txtThanhTien.setText(String.valueOf(hoaDon.getThanhTien()));
        } else {

            cboBan.setSelectedItem(model.getMaBan());
            cboSanPham.setSelectedItem(model.getSanPham().getTenSanPham());
            txtSoLuong.setText(String.valueOf(model.getHoaDonChiTiet().getSoLuongSP()));
            txtThanhTien.setText(String.valueOf(model.getThanhTien()));
        }
    }

    private void insert() {
        if (khDAO.getSLByMaSP(spDAO.findByName((String) cboSanPham.
                getSelectedItem()).getMaSanPham()) != 0) {
            if (khDAO.getSLByMaSP(spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham())
                    >= Integer.parseInt(txtSoLuong.getText())) {
                HoaDon model = getModelHD();
                int soLuongTheoMaKH = 0;
                try {
//                        trả về mã kho hàng đầu tiên trong danh sách, được sắp xếp tăng dần về ngày nhập hàng, sẽ bán những sản phẩm trong mã kho hàng được thêm đầu tiên, tránh hỏng hàng
                    int maKhoHang = khDAO.getMaKhoHangByMaSP(spDAO.findByName((String) cboSanPham.
                            getSelectedItem()).getMaSanPham());

//                        Kiểm tra số lượng sản phẩm theo mã kho hàng, ( ở trên cũng có kiểm tra số lượng, nhưng đó là kiểm tra GROUP BY theo mã sản phẩm )
                    soLuongTheoMaKH = khDAO.getMaSLHang(maKhoHang);
                    if (soLuongTheoMaKH >= Integer.parseInt(txtSoLuong.getText())) {
//                      thêm mới vào bảng hóa đơn, sau đó thêm mới vào bảng hóa đơn chi tiết lấy mã hóa đơn cho bảng hóa đơn chi tiết là mã hóa đơn được thêm vào cuối cùng
                        if (dao.insert(model)) {

                            HoaDonChiTiet modelChiTiet = getModelHDCT();
                            daoCT.insert(modelChiTiet, dao.getIDIdentity());

                            DialogHelper.setInfinity(lblMSG, "Thêm mới thành công!");

//                      thêm thành công thì số lượng sản phẩm trong kho bị giảm
                            khDAO.updateSLByMaSP(Integer.parseInt(txtSoLuong.getText()),
                                    spDAO.findByName((String) cboSanPham.
                                            getSelectedItem()).getMaSanPham(), maKhoHang);
//                      load lại bảng đơn hàng và bảng thông tin sản phẩm ở danh mục sau khi gọi món
                            DanhMucJFrame.loadDonHangTheoBan();

//                      gọi món xong thì bàn sẽ chuyển sang màu xanh và đợi thanh toán
                            banDAO.datBan(1, (int) cboBan.getSelectedItem());

//                      gọi phương thức để load lại tab bàn ở danh mục
                            DanhMucJFrame.loadTabs();

                            try {
//                      thêm xong load bảng hóa đơn ở danh mục
                                DanhMucJFrame.loadDonHangTheoBan();
                            } catch (Exception e) {
                            }
//                            thêm xong tắt form => tránh bug =))
                            this.dispose();
                        }
                    } else {
                        DialogHelper.alert(this, "Số lượng sản phẩm trong kho hàng cũ chỉ còn : " + soLuongTheoMaKH + ".");
                    }
                } catch (Exception e) {
                }
            } else {
                DialogHelper.alert(this, "Số lượng sản phẩm chỉ còn " + khDAO.getSLByMaSP(
                        spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham()));
            }
        } else {
            DialogHelper.alert(this, "Sản phẩm đã hết hàng!");
        }
    }

    void update() {
        if (khDAO.getSLByMaSP(spDAO.findByName((String) cboSanPham.
                getSelectedItem()).getMaSanPham()) != 0) {
            if (khDAO.getSLByMaSP(spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham())
                    >= Integer.parseInt(txtSoLuong.getText())) {
                HoaDon model = getModelHD();
                try {
//                      thêm mới vào bảng hóa đơn, sau đó thêm mới vào bảng hóa đơn chi tiết lấy mã hóa đơn cho bảng hóa đơn chi tiết là mã hóa đơn được thêm vào cuối cùng
                    if (dao.update(model)) {

                        HoaDonChiTiet modelChiTiet = getModelHDCT();
                        daoCT.update(modelChiTiet);

                        DialogHelper.setInfinity(lblMSG, "Cập nhật thành công!");

//                      trước khi tính số lượng sản phẩm tồn kho sau khi sửa thì phải cộng vào số lượng đã thêm vào hóa đơn từ trước để đảm bảo số lượng sản phẩm trước khi thêm vào đơn hàng
//                        khDAO.themSLByMaSP(soLuong, maSanPham);
//                        trả về mã kho hàng đầu tiên trong danh sách, được sắp xếp tăng dần về ngày nhập hàng, sẽ bán những sản phẩm trong mã kho hàng được thêm đầu tiên, tránh hỏng hàng                       
                        int maKhoHang = khDAO.getMaKhoHangByMaSP(maSanPham);
                        khDAO.themSLByMaSP(soLuong, maSanPham, maKhoHang);

                        maKhoHang = khDAO.getMaKhoHangByMaSP(spDAO.findByName((String) cboSanPham.
                                getSelectedItem()).getMaSanPham());

//                      cập nhật thành công thì số lượng sản phẩm trong kho bị giảm đi
                        khDAO.updateSLByMaSP(Integer.parseInt(txtSoLuong.getText()),
                                spDAO.findByName((String) cboSanPham.
                                        getSelectedItem()).getMaSanPham(), maKhoHang);

//                      load lại bảng đơn hàng và bảng thông tin sản phẩm ở danh mục sau khi sửa món
                        DanhMucJFrame.loadDonHangTheoBan();

//                      gọi phương thức để load lại tab bàn ở danh mục
                        DanhMucJFrame.loadTabs();

                        try {
//                      sửa xong load bảng hóa đơn ở danh mục
                            DanhMucJFrame.loadDonHangTheoBan();
                        } catch (Exception e) {
                        }

//                        load lại bảng hóa đơn chi tiết sau khi chỉnh sửa
                        HoaDonChiTietJFrame.load();

                    }
                } catch (Exception e) {
                    DialogHelper.alert(this, "Thêm mới thất bại!");
                    System.out.println(e.toString());
                }
            } else {
                DialogHelper.alert(this, "Số lượng sản phẩm chỉ còn " + khDAO.getSLByMaSP(
                        spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham()));
            }
        } else {
            DialogHelper.alert(this, "Sản phẩm đã hết hàng!");
        }

    }

    void tinhTien() {
        try {
            if (!txtSoLuong.getText().isEmpty()) {
                String tenSP = (String) cboSanPham.getSelectedItem();
                txtThanhTien.setText(String.valueOf(spDAO.findByName(tenSP).
                        getGiaBan() * Integer.parseInt(txtSoLuong.getText())));
            } else {
                txtThanhTien.setText("");
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
        txtSoLuong = new javax.swing.JTextField();
        lblSanPham = new javax.swing.JLabel();
        lblBan = new javax.swing.JLabel();
        btnMoi = new javax.swing.JButton();
        lblSoLuong = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        btnSua = new javax.swing.JButton();
        cboBan = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        lblThanhTien = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        lblMSG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh mục món");

        txtSoLuong.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSoLuongCaretUpdate(evt);
            }
        });

        lblSanPham.setText("Sản phẩm");

        lblBan.setText("Bàn");

        btnMoi.setText("Mới");

        lblSoLuong.setText("Số lượng");

        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lblThanhTien.setText("Thành tiền");

        txtThanhTien.setEditable(false);

        lblMSG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlWrapperLayout = new javax.swing.GroupLayout(pnlWrapper);
        pnlWrapper.setLayout(pnlWrapperLayout);
        pnlWrapperLayout.setHorizontalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(10, 10, 10)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblSanPham)
                        .addGap(52, 52, 52)
                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblBan)
                        .addGap(81, 81, 81)
                        .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblSoLuong)
                        .addGap(57, 57, 57)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addComponent(lblThanhTien)
                        .addGap(48, 48, 48)
                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlWrapperLayout.setVerticalGroup(
            pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWrapperLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblBan))
                    .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblSanPham))
                    .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblSoLuong))
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlWrapperLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblThanhTien))
                    .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem)
                    .addGroup(pnlWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSua)
                        .addComponent(btnMoi)))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSoLuongCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSoLuongCaretUpdate
        // TODO add your handling code here:
        tinhTien();
    }//GEN-LAST:event_txtSoLuongCaretUpdate

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        update();
//        sau khi update thì lưu lại số lượng, phục vụ cho việc cập nhật
        soLuong = Integer.parseInt(txtSoLuong.getText());
        maSanPham = spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
        tinhTien();
    }//GEN-LAST:event_cboSanPhamActionPerformed

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
            java.util.logging.Logger.getLogger(GoiMonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GoiMonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GoiMonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GoiMonJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GoiMonJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JComboBox<String> cboBan;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JLabel lblBan;
    private javax.swing.JLabel lblMSG;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JPanel pnlWrapper;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
