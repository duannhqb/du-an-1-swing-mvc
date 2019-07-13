select * from hoaDon
select * from hoaDonchitiet
delete hoadon where MaHoaDon = 99
delete HoaDonChiTiet where MaHoaDonCT = 88
select SCOPE_IDENTITY()

select @@IDENTITY

select * from nhanvien

select * from KhoHang

SELECT SUM(SoLuong) FROM KhoHang WHERE MaSanPham = 1 group by MaSanPham

SELECT MaKhoHang, SUM(SoLuong) FROM KhoHang WHERE MaSanPham = 2 and SoLuong > 0 GROUP BY MaKhoHang

SELECT MaKhoHang, SoLuong FROM KhoHang WHERE MaSanPham = 2 and SoLuong > 0 ORDER BY NgayNhap ASC

SELECT MaKhoHang FROM KhoHang WHERE MaSanPham = 1 and SoLuong > 0 GROUP BY MaKhoHang