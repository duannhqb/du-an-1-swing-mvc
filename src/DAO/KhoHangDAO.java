/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import helper.Jdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.KhoHang;

/**
 *
 * @author duann
 */
public class KhoHangDAO {

    public void insert(KhoHang model) {
        String sql = "INSERT INTO KhoHang( MaSanPham, NgayNhap, MaNhanVien, SoLuong, GhiChu, HanSuDung) VALUES (?,?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getMaSanPham(),
                model.getNgayNhap(),
                model.getMaNhanVien(),
                model.getSoLuong(),
                model.getGhiChu(),
                model.getHanSuDung());
    }

    public void update(KhoHang model) {
        String sql = "UPDATE KhoHang SET MaSanPham=?, NgayNhap=?, MaNhanVien=?, SoLuong=?, GhiChu=?, HanSuDung=? WHERE MaKhoHang=?";
        Jdbc.executeUpdate(sql,
                model.getMaSanPham(),
                model.getNgayNhap(),
                model.getMaNhanVien(),
                model.getSoLuong(),
                model.getGhiChu(),
                model.getHanSuDung(),
                model.getMaKhoHang());
    }

    public void delete(int MaKhoHang) {
        String sql = "DELETE FROM KhoHang WHERE MaSanPham=?";
        Jdbc.executeUpdate(sql, MaKhoHang);
    }

    private KhoHang readFormResultSet(ResultSet rs) throws SQLException {
        KhoHang khohang = new KhoHang();
        khohang.setMaKhoHang(rs.getInt(1));
        khohang.setMaSanPham(rs.getInt(2));
        khohang.setNgayNhap(rs.getDate(3));
        khohang.setMaNhanVien(rs.getInt(4));
        khohang.setSoLuong(rs.getInt(5));
        khohang.setGhiChu(rs.getString(6));
        khohang.setHanSuDung(rs.getDate(7));
        return khohang;
    }

    public List<KhoHang> select(String sql, Object... args) {
        List<KhoHang> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    KhoHang khohang = readFormResultSet(rs);
                    list.add(khohang);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;

    }

    public List<KhoHang> select() {
        String sql = "SELECT * FROM KhoHang ";
        return select(sql);
    }

    public KhoHang findById(int maKhoHang) {
        String sql = "SELECT * FROM KhoHang WHERE MaKhoHang=?";
        List<KhoHang> list = select(sql, maKhoHang);
        return list.size() > 0 ? list.get(0) : null;
    }

    public KhoHang findByName(int name) {
        String sql = "SELECT * FROM KhoHang WHERE HoTen like ?";
        List<KhoHang> list = select(sql, name);
        return list.size() > 0 ? list.get(0) : null;

    }

}
