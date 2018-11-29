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
import model.HoaDon;
import model.HoaDonChiTiet;
import model.SanPham;
import static view.DanhMucJFrame.hdDAO;
import static view.DanhMucJFrame.loadDonHangTheoBan;
import static view.DanhMucJFrame.loadTabs;
import static view.DanhMucJFrame.lspDAO;

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
    int dem = 0;
    int mahdct;

    public ThongTinDonHangJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadDonHangByBan(1);
    }

    public ThongTinDonHangJFrame(int id) {
        initComponents();
        this.setLocationRelativeTo(null);
        loadSanPham();
        loadDonHangByBan(id);
        this.maBan = id;
        lblThongTinCuaBan.setText("Thông tin của bàn: " + id);
        tongTien();
        fillComboBoxSanPham();
        txtSoLuong.setText("0");
        setStatus();
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
            System.out.println(e.toString());
        }
    }

    void setStatus() {
        boolean insertable;
        if (tblThongTin.getRowCount() > 0) {
            insertable = false;
        } else {
            insertable = true;
        }
        btnSuaMon.setEnabled(!insertable);
        btnThanhToan.setEnabled(!insertable);
        btnXoaMon.setEnabled(!insertable);

    }

    void clear() {
        cboSanPham.setSelectedIndex(0);
        txtSoLuong.setText("0");
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

    void setModel(HoaDonChiTiet model) {
        cboSanPham.setSelectedItem(spDAO.findById(model.getMaSanPham()).getTenSanPham());
        txtSoLuong.setText(String.valueOf(model.getSoLuongSP()));
        this.tinhTien();
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

    HoaDon getModelHD() {
        HoaDon hd = new HoaDon();
        hd.setMaBan(maBan);
        hd.setMaNhanVien(1);
        hd.setGhiChu("");
        hd.setTrangThai(false); // false = 0
        hd.setNgayThanhToan(XDate.now());
        hd.setThanhTien(0);
        return hd;
    }

    HoaDonChiTiet getModelHDCT() {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoLuongSP(Integer.parseInt(txtSoLuong.getText()));
        hdct.setMaSanPham(spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham());
        return hdct;
    }

    int insertHDWhenCallMon() {
        HoaDon hd = getModelHD();
        hdDAO.insert(hd);
        return hdDAO.getIDIdentity();
    }

    int maHD;

    void insert() {
        try {
            if (Integer.parseInt(txtSoLuong.getText()) > 0) {
                if (tblThongTin.getRowCount() == 0) {
//                    nếu bảng rỗng thì chưa có hóa đơn => tạo mới hóa đơn rồi get mã hóa đơn vừa thêm gán vào =))
                    maHD = insertHDWhenCallMon();
                } else {
//                    Tất cả những mã hóa đơn chi tiết đều map đến 1 hóa đơn
                    maHD = daoCT.getMaHDByMaHDCT((int) tblThongTin.getValueAt(0, 0));
                }
                HoaDonChiTiet model = getModelHDCT();
                daoCT.insert(model, maHD);

//                mã kho hàng lấy từ ngày thêm hàng sớm nhất
                int maKhoHangMoi = khDAO.getMaKhoHangByMaSP(spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham());

//                thêm thành công thì số lượng sản phẩm trong kho bị giảm
                khDAO.updateSLByMaSP(Integer.parseInt(txtSoLuong.getText()), spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham(), maKhoHangMoi);

//                sửa lại trạng thái bàn sau khi gọi món
//                1 : trạng thái bàn đã được đặt chỗ.
                banDAO.datBan(1, maBan);

                lblTongTien.setText("Tổng thanh toán: " + dmdao.getThanhToanTheoBan(maBan) + " vnđ");

                loadDonHangByBan(maBan);
                loadSanPham();
                loadTabs();
                loadDonHangTheoBan();

                DialogHelper.setInfinity(lblMSG, "Thêm mới thành công!");
            } else {
                DialogHelper.alert(this, "Bạn phải nhập số lượng.");
            }
        } catch (Exception e) {
        }
    }

    public void update() {
        try {
            if (Integer.parseInt(txtSoLuong.getText()) > 0) {

                HoaDonChiTiet hdct = getModelHDCT();
//                mã hóa đơn chi tiết lấy được khi click vào bảng
                daoCT.update(hdct, mahdct);

//                mã sản phẩm được lấy khi click vào bảng hóa đơn ở trên
                int maKhoHang = khDAO.getMaKhoHangByMaSP(maSanPham);

//                trước khi sửa thì phải cộng lại số lượng đã mua rồi mới thêm hoặc bớt sau
                khDAO.themSLByMaSP(soLuong, maSanPham, maKhoHang);

                int maKhoHangMoi = khDAO.getMaKhoHangByMaSP(spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham());

//                sau khi cập nhật thì số lượng trong kho sẽ giảm đi txtSoluong lần
                khDAO.updateSLByMaSP(Integer.parseInt(txtSoLuong.getText()), spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham(), maKhoHangMoi);

                lblTongTien.setText("Tổng thanh toán: " + dmdao.getThanhToanTheoBan(maBan) + " vnđ");

                loadDonHangByBan(maBan);
                loadSanPham();
                loadTabs();
                loadDonHangTheoBan();
                this.clear();
            } else {
                DialogHelper.alert(this, "Vui lòng nhập số lượng.");
            }
        } catch (Exception e) {
        }
    }

    void tongTien() {
        float tong = 0;
        for (int i = 0; i < tblThongTin.getRowCount(); i++) {
            tong += Float.parseFloat(tblThongTin.getValueAt(i, 5).toString());
        }
        lblTongTien.setText("Tổng thanh toán: " + tong + " vnđ");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        btnGoiMon = new javax.swing.JButton();
        btnSuaMon = new javax.swing.JButton();
        btnXoaMon = new javax.swing.JButton();
        lblMSG = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        pnlHidden = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        lblListSanPham = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã HDCT", "Sản phẩm", "Loại hàng", "Đơn giá", "Số lượng mua", "Thành tiền", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinMouseClicked(evt);
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
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });

        lblMSG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnClear.setText("Mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

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
        tblSanPham.setGridColor(new java.awt.Color(255, 255, 255));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        lblListSanPham.setText("Sản phẩm");

        txtFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout pnlHiddenLayout = new javax.swing.GroupLayout(pnlHidden);
        pnlHidden.setLayout(pnlHiddenLayout);
        pnlHiddenLayout.setHorizontalGroup(
            pnlHiddenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHiddenLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblListSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(txtFind))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
        );
        pnlHiddenLayout.setVerticalGroup(
            pnlHiddenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHiddenLayout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addGroup(pnlHiddenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblListSanPham)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(pnlHidden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
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
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnGoiMon)
                                                .addGap(27, 27, 27)
                                                .addComponent(btnSuaMon)
                                                .addGap(32, 32, 32)
                                                .addComponent(btnClear))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblThanhTien)
                                                .addGap(35, 35, 35)
                                                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                        .addComponent(btnThanhToan))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGoiMon)
                                .addGap(79, 79, 79)
                                .addComponent(lblMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(492, 492, 492)
                            .addComponent(btnXoaMon)
                            .addGap(40, 40, 40))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblThongTinCuaBan)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(26, 26, 26)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThongTinCuaBan))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoaMon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMSG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGoiMon))
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
                    .addComponent(btnThanhToan)
                    .addComponent(btnClear))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHidden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        // TODO add your handling code here:
        this.insert();
        this.setStatus();
        this.clear();
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
        if (tblSanPham.getValueAt(index, 0).toString().equals(cboSanPham.getSelectedItem())) {
            dem += 1;
            txtSoLuong.setText(String.valueOf(dem));
        } else {
            dem = 1;
            txtSoLuong.setText(String.valueOf(dem));
        }
        if (this.index >= 0) {
            cboSanPham.setSelectedItem(tblSanPham.getValueAt(index, 0));
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnSuaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMonActionPerformed
        // TODO add your handling code here:
        this.update();
    }//GEN-LAST:event_btnSuaMonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        if (this.index >= 0) {
            if (DialogHelper.confirm(this, "Tổng cộng tiền cho bàn [ " + maBan + " ] là : " + dmdao.getThanhToanTheoBan(maBan) + " vnđ")) {

//                cập nhật trạng thái bàn: 0 là đã thanh toán và 1 là chưa thanh toán, khách vừa đặt hóa đơn thì sẽ = 1
                banDAO.datBan(0, maBan);
//                cập nhật trạng thái đơn hàng cho mỗi hóa đơn theo bàn
//                1 là đã thanh toán, mặc định là 0
                hdDAO.updateTrangThaiHD(1, XDate.now(), maBan);

//                    sau khi cập nhật trạng thái cho bàn và hóa đơn => load lại thông tin ở bảng và JPanel
                DanhMucJFrame.loadTabs();
                DanhMucJFrame.loadDonHangTheoBan();

                this.dispose();
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void tblThongTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinMouseClicked
        // TODO add your handling code here:
        this.index = tblThongTin.rowAtPoint(evt.getPoint());
        if (this.index >= 0) {
            setModel(daoCT.findById((int) tblThongTin.getValueAt(index, 0)));
            mahdct = (int) tblThongTin.getValueAt(index, 0);

//            lưu lại số lượng và sản phẩm ở trên bảng để có thể update lại số lượng sản phẩm vào giỏ hàng trước khi sửa món
            soLuong = Integer.parseInt(txtSoLuong.getText());
            maSanPham = spDAO.findByName((String) cboSanPham.getSelectedItem()).getMaSanPham();
        }
        this.tinhTien();
    }//GEN-LAST:event_tblThongTinMouseClicked

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        // TODO add your handling code here:
        this.setStatus();
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void txtFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCaretUpdate
        // TODO add your handling code here:

    }//GEN-LAST:event_txtFindCaretUpdate

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
    private javax.swing.JButton btnClear;
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
    private javax.swing.JPanel pnlHidden;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}
