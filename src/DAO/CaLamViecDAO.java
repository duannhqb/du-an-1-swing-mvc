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
import model.CaLamViec;

/**
 *
 * @author duann
 */
public class CaLamViecDAO {

    public void insert(CaLamViec model) {
        String sql = "INSERT INTO CaLamViec (BatDau, KetThuc, GhiChu) VALUES (?,?,?)";
        Jdbc.executeUpdate(sql,
                model.getMaCaLamViec(),
                model.getTenCaLamViec(),
                model.getBatDau(),
                model.getKetThuc(),
                model.getGhiChu());
    }

    public void update(CaLamViec model) {
        String sql = "UPDATE CaLamViec SET BatDau=?, KetThuc=?, GhiChu=? WHERE MaCaLV=?";
        Jdbc.executeUpdate(sql,
                model.getMaCaLamViec(),
                model.getTenCaLamViec(),
                model.getBatDau(),
                model.getKetThuc(),
                model.getGhiChu());
    }

    public void delete(String maCaLamViec) {
        String sql = "DELETE FROM CaLamViec WHERE MaCaLV=?";
        Jdbc.executeQuery(sql, maCaLamViec);
    }

    private CaLamViec readFromResultSet(ResultSet rs) throws SQLException {
        CaLamViec model = new CaLamViec();
        model.setMaCaLamViec(rs.getInt(1));
        model.setTenCaLamViec(rs.getString(2));
        model.setBatDau(rs.getString(3));
        model.setKetThuc(rs.getString(4));
        model.setGhiChu(rs.getString(5));
        return model;
    }

    private List<CaLamViec> select(String sql, Object... args) {
        List<CaLamViec> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Jdbc.executeQuery(sql, args);
                while (rs.next()) {
                    CaLamViec model = readFromResultSet(rs);
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
}
