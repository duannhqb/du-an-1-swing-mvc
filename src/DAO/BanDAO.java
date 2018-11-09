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
import model.Ban;

/**
 *
 * @author duann
 */
public class BanDAO {

    public void insert(Ban model) {
        String sql = "INSERT INTO Ban(MaBan,MaKhuVuc,TrangThai) VALUES(?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getMaBan(),
                model.getMaKhuvuc(),
                model.isTrangThai());
    }

    public void update(Ban model) {
        String sql = "UPDATE Ban SET MaKhuVuc=?, TrangThai=? WHERE MaBan=?";
        Jdbc.executeUpdate(sql,
                model.getMaBan(),
                model.getMaKhuvuc(),
                model.isTrangThai());
    }

    public void delete(String maBan) {
        String sql = "DELETE FROM Ban WHERE MaBan=?";
        Jdbc.executeQuery(sql, maBan);
    }

    private Ban readFromResultSet(ResultSet rs) throws SQLException {
        Ban model = new Ban();
        model.setMaBan(rs.getInt(1));
        model.setMaKhuvuc(rs.getInt(2));
        model.setTrangThai(rs.getBoolean(3));
        return model;
    }

    private List<Ban> select(String sql, Object... arg) {
        List<Ban> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, arg);
                while (rs.next()) {
                    Ban model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Ban> select() {
        String Sql = "SELECT * FROM Ban";
        return select(Sql);
    }

    public Ban findById(String maBan) {
        String sql = "SELECT * FROM WHERE MaBan=?";
        List<Ban> list = select(sql, maBan);
        return list.size() > 0 ? list.get(0) : null;
    }
}
