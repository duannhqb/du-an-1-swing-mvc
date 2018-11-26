/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.BanDAO;
import DAO.DanhMucDAO;
import DAO.HoaDonChiTietDAO;
import DAO.HoaDonDAO;
import DAO.KhoHangDAO;
import DAO.SanPhamDAO;
import helper.DialogHelper;
import helper.XDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.Ban;
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;
import static view.DanhMucJFrame.banDAO;
import static view.DanhMucJFrame.hdDAO;
import static view.DanhMucJFrame.index;
import static view.DanhMucJFrame.khDAO;
import static view.DanhMucJFrame.loadTabs;
import static view.DanhMucJFrame.lspDAO;
import static view.DanhMucJFrame.spDAO;
import static view.DanhMucJFrame.tblHoaDon;

/**
 *
 * @author duann
 */
public class ThongTinDonHangJFrame extends javax.swing.JFrame {

    /**
     * Creates new form ThongTinDonHangJFrame
     */
    DanhMucDAO dmdao = new DanhMucDAO();
    HoaDonDAO dao = new HoaDonDAO();
    HoaDonChiTietDAO daoCT = new HoaDonChiTietDAO();
    SanPhamDAO spDAO = new SanPhamDAO();
    BanDAO banDAO = new BanDAO();
    KhoHangDAO khDAO = new KhoHangDAO();
    int index = 0;
    int soLuong = 0;
    int maSanPham;
    int maBan;

    public ThongTinDonHangJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public ThongTinDonHangJFrame(int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        loadSanPham();
        loadDonHangByBan(id);
        this.maBan = id;
        lblThongTinCuaBan.setText("Thông tin của bàn: " + id);
        lblTongTien.setText("Tổng thanh toán: " + dmdao.getThanhToanTheoBan(id));
        fillComboBoxSanPham();
    }

    public void loadDonHangByBan(int id) {
        DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
        model.setRowCount(0);
        try {
            List<Object[]> list = dmdao.getDanhSachSPTheoBan(id);
            for (Object[] objects : list) {
                model.addRow(objects);
            }
        } catch (Exception e) {
        }
    }

    public void loadSanPham() {
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

    HoaDon getModelHD() {
        HoaDon hoaDon = new HoaDon();
//      mã nhân viên sau khu tạo form đăng nhập sẽ sửa lại
        hoaDon.setMaNhanVien(1);
        hoaDon.setGhiChu("");
        hoaDon.setTrangThai(false);
//        ngày thanh toán: ban đầu sản phẩm được thêm vào hóa đơn thì chưa thanh toán, nhưng, ta vẫn lấy ngày thanh toán là ngày thêm vào hóa đơn, rồi sau đó chạy phần update sửa ngày thanh toán, để tránh null dữ liệu dẫn đến bug
        hoaDon.setNgayThanhToan(XDate.now());
        hoaDon.setThanhTien(Float.parseFloat(txtThanhTien.getText()));
        try {
            hoaDon.setMaHoaDon(Integer.parseInt(cboSanPham.getToolTipText()));
        } catch (Exception e) {
        }
        hoaDon.setMaBan(maBan);
        return hoaDon;
    }

    HoaDonChiTiet getModelHDCT() {
        HoaDonChiTiet hoaDonCT = new HoaDonChiTiet();
        String tenSP = (String) cboSanPham.getSelectedItem();

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
                            banDAO.datBan(1, maBan);

//                      gọi phương thức để load lại tab bàn ở danh mục
                            DanhMucJFrame.loadTabs();

                            try {
//                      thêm xong load bảng hóa đơn ở danh mục
                                DanhMucJFrame.loadDonHangTheoBan();
                            } catch (Exception e) {
                            }

                            this.loadSanPham();
                            this.loadDonHangByBan(maBan);
                            lblTongTien.setText("Tổng thanh toán: " + dmdao.getThanhToanTheoBan(maBan));
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

                        this.loadSanPham();
                        this.loadDonHangByBan(maBan);
                        lblTongTien.setText("Tổng thanh toán: " + dmdao.getThanhToanTheoBan(maBan));

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        lblTongTien = new javax.swing.JLabel();
        cboSanPham = new javax.swing.JComboBox<>();
        lblSanPham = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblSoLuong = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        lblThanhTien = new javax.swing.JLabel();
        lblThongTinCuaBan = new javax.swing.JLabel();
        lblGoiMon = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        lblListSanPham = new javax.swing.JLabel();
        btnGoiMon = new javax.swing.JButton();
        btnSuaMon = new javax.swing.JButton();
        btnXoaMon = new javax.swing.JButton();
        lblMSG = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Loại sản phẩm", "Đơn giá", "Số lượng còn lại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Loại", "Giá bán", "Số lượng mua", "Thành tiền", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblThongTin);

        lblTongTien.setText("Tổng tiền");

        cboSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSanPhamActionPerformed(evt);
            }
        });

        lblSanPham.setText("Sản phẩm");

        txtSoLuong.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSoLuongCaretUpdate(evt);
            }
        });

        lblSoLuong.setText("Số lượng");

        txtThanhTien.setEditable(false);

        lblThanhTien.setText("Thành tiền");

        lblThongTinCuaBan.setText("Thông tin của bàn 12");

        lblGoiMon.setText("Gọi món");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblListSanPham.setText("Sản phẩm");

        btnGoiMon.setText("Gọi món");
        btnGoiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiMonActionPerformed(evt);
            }
        });

        btnSuaMon.setText("Sửa món");
        btnSuaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMonActionPerformed(evt);
            }
        });

        btnXoaMon.setText("Xóa");

        lblMSG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblListSanPham)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblThongTinCuaBan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGoiMon)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSanPham)
                                        .addGap(39, 39, 39)
                                        .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSoLuong)
                                        .addGap(44, 44, 44)
                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblThanhTien)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnGoiMon)
                                        .addGap(27, 27, 27)
                                        .addComponent(btnSuaMon)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnThanhToan))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(lblMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaMon))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThongTinCuaBan)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblListSanPham))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(lblGoiMon)
                            .addComponent(btnXoaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMSG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblSanPham))
                            .addComponent(cboSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblSoLuong))
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblThanhTien))
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGoiMon)
                            .addComponent(btnSuaMon)
                            .addComponent(btnThanhToan))))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        // TODO add your handling code here:
        this.insert();
    }//GEN-LAST:event_btnGoiMonActionPerformed

    private void txtSoLuongCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSoLuongCaretUpdate
        // TODO add your handling code here:
        this.tinhTien();
    }//GEN-LAST:event_txtSoLuongCaretUpdate

    private void cboSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSanPhamActionPerformed
        // TODO add your handling code here:
        this.tinhTien();
    }//GEN-LAST:event_cboSanPhamActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        this.index = tblSanPham.rowAtPoint(evt.getPoint());
        if (this.index >= 0) {
            cboSanPham.setSelectedItem(tblSanPham.getValueAt(index, 0));
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnSuaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMonActionPerformed
        // TODO add your handling code here:
        this.update();

//        sau khi update thì lưu lại số lượng, phục vụ cho việc cập nhật
        soLuong = Integer.parseInt(txtSoLuong.getText());
        maSanPham = spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham();
    }//GEN-LAST:event_btnSuaMonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (this.index >= 0) {
            if (DialogHelper.confirm(this, "Bạn muốn thanh toán cho bàn này")) {
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
        loadTabs();
    }//GEN-LAST:event_btnThanhToanActionPerformed

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
            java.util.logging.Logger.getLogger(ThongTinDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinDonHangJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongTinDonHangJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGoiMon;
    private javax.swing.JButton btnSuaMon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JComboBox<String> cboSanPham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGoiMon;
    private javax.swing.JLabel lblListSanPham;
    private javax.swing.JLabel lblMSG;
    private javax.swing.JLabel lblSanPham;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThongTinCuaBan;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
