INSERT INTO NhanVien(HoTen, MatKhau, VaiTro, NgaySinh, GioiTinh, Email, DienThoai, GhiChu, MaCaLV, SoNgayLV) VALUES (?,?,?,?,?,?,?,?,?,?)
UPDATE NhanVien SET HoTen=?, MatKhau=?, VaiTro=?, NgaySinh=?, GioiTinh=?, Email=?, DienThoai=?, GhiChu=?, MaCaLV=?, SoNgayLV=? WHERE MaNhanVien=?
DELETE FROM NhanVien WHERE MaNhanVien=?
SELECT * FROM NhanVien
SELECT * FROM NhanVien WHERE MaNhanVien=?

INSERT INTO Ban(MaBan,MaKhuVuc, TrangThai) VALUES(?,?,?)
UPDATE Ban SET MaKhuVuc=?, TrangThai=? WHERE MaBan=?
DELETE FROM Ban WHERE MaBan=?
SELECT * FROM Ban
SELECT * FROM WHERE MaBan=?

INSERT INTO KhuVuc(TenKhuVuc, ViTri) VALUES (?,?)
UPDATE KhuVuc SET TenKhuVuc=?, ViTri=? WHERE MaKhuVuc=?
DELETE FROM KhuVuc WHERE MaKhuVuc=?
SELECT * FROM KhuVuc
SELECT * FROM KhuVuc WHERE MaKhuVuc=?

INSERT INTO KhoHang( MaSanPham, NgayNhap, MaNhanVien, SoLuong, GhiChu, HanSuDung) VALUES (?,?,?,?,?,?)
UPDATE KhoHang SET MaSanPham=?, NgayNhap=?, MaNhanVien=?, SoLuong=?, GhiChu=?, HanSuDung=? WHERE MaKhoHang=?
DELETE FROM KhoHang WHERE MaKhoHang=?
SELECT * FROM KhoHang 
SELECT * FROM KhoHang WHERE MaKhoHang=?

INSERT INTO LoaiSanPham (TenLoaiSp) VALUES(?)
UPDATE LoaiSanPham TenLoaiSP=? WHERE MaLoaiSP=?
DELETE FROM LoaiSanPham WHERE MaLoaiSP=?
SELECT * FROM LoaiSanPham
SELECT * FROM LoaiSanPham WHERE MaLoaiSP=?

INSERT INTO CaLamViec (BatDau, KetThuc, GhiChu) VALUES (?,?,?)
UPDATE CaLamViec SET BatDau=?, KetThuc=?, GhiChu=? WHERE MaCaLV=?
DELETE FROM CaLamViec WHERE MaCaLV=?
SELECT * FROM CaLamViec 
SELECT * FROM CaLamViec WHERE MaCaLV=?

INSERT INTO SanPham (TenSanPham, MaLoaiSP, GiaBan, TrangThai, GhiChu) VALUES (?,?,?,?,?)
UPDATE SanPham SET TenSanPham=?, MaLoaiSP=? GiaBan=?, TrangThai=?, GhiChu=? WHERE MaSanPham=?
DELETE FROM SanPham WHERE MaSaPham=?
SELECT * FROM SanPham 
SELECT * FROM SanPham WHERE MaSanPham=?

INSERT INTO HoaDon (MaNhanVien, MaBan, GhiChu, TrangThai, NgayThanhToan) VALUES(?,?,?,?,?)
UPDATE HoaDon SET MaNhanVien= ?, MaBan= ?, GhiChu= ?, TrangThai= ?, NgayThanhToan= ? WHERE MaHoaDon= ?
DELETE FROM HoaDon WHERE MaHoaDon= ?
SELECT * FROM HoaDon  
SELECT * FROM HoaDon  WHERE MaHoaDon= ?

INSERT INTO HoaDonChiTiet (MaHoaDon, SoLuongSP, MaSanPham) VALUES (?,?,?)
UPDATE HoaDonChiTiet SET MaHoaDon= ?, SoLuongSP=?, MaSanPham= ? WHERE MaHoaDonCT=?
DELETE FROM HoaDonChiTiet WHERE MaHoaDonCT=?
SELECT * FROM HoaDonChiTiet
SELECT * FROM HoaDonChiTiet WHERE MaHoaDonCT=?
