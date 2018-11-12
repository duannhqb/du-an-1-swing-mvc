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
import model.HoaDon;

/**
 *
 * @author duann
 */
public class HoaDonDAO {

    public void insert(HoaDon model) {
        String sql = "INSERT INTO HoaDon (MaNhanVien, MaBan, GhiChu, TrangThai, NgayThanhToan) VALUES(?,?,?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getMaNhanVien(),
                model.getMaBan(),
                model.getGhiChu(),
                model.isTrangThai(),
                model.getNgayThanhToan()
        );
    }

    public void update(HoaDon model) {
        String sql = "UPDATE HoaDon SET MaNhanVien= ?, MaBan= ?, GhiChu= ?, TrangThai= ?, NgayThanhToan= ? WHERE MaHoaDon= ?";
        Jdbc.executeUpdate(sql,
                model.getMaNhanVien(),
                model.getMaBan(),
                model.getGhiChu(),
                model.isTrangThai(),
                model.getNgayThanhToan(),
                model.getMaHoaDon()
        );
    }

    public void delect(String maHoaDon) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon= ?";
        Jdbc.executeUpdate(sql, maHoaDon);
    }

    private HoaDon readFormResultSet(ResultSet rs) throws SQLException {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(rs.getInt(1));
        hoaDon.setMaNhanVien(rs.getInt(2));
        hoaDon.setMaBan(rs.getInt(3));
        hoaDon.setGhiChu(rs.getString(4));
        hoaDon.setTrangThai(rs.getBoolean(5));
        hoaDon.setNgayThanhToan(rs.getDate(6));
        return hoaDon;
    }

    private List<HoaDon> select(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    HoaDon hoaDon = readFormResultSet(rs);
                    list.add(hoaDon);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<HoaDon> select() {
        String sql = "SELECT * FROM HoaDon";
        return select(sql);
    }

    public HoaDon findById(String maHoaDon) {
        String sql = "SELECT * FROM HoaDon  WHERE MaHoaDon= ?";
        List<HoaDon> list = select(sql, maHoaDon);
        return list.size() > 0 ? list.get(0) : null;
    }

}
