CREATE PROC sp_ThongKeKhoHang
AS BEGIN
SELECT 
MaSanPham, 
SUM(SoLuongSP) 
FROM HoaDonChiTiet 
GROUP BY MaSanPham
END

exec sp_ThongKeKhoHang

--
CREATE PROC sp_ThongKeDoanhThu
AS BEGIN
SELECT
	YEAR(NgayThanhToan),
	SUM(ThanhTien)
	from HoaDon 
	Group by YEAR(NgayThanhToan)
END

exec sp_ThongKeDoanhThu

-- 

CREATE PROC sp_ThongKeDoanhThuTheoThang
AS BEGIN
SELECT
	NgayThanhToan,
	SUM(ThanhTien)
	from HoaDon 
	Group by NgayThanhToan
END

exec sp_ThongKeDoanhThuTheoThang

--

CREATE PROC sp_ThongKeNhanVien
AS BEGIN
SELECT 
HoTen, 
SoNgayLV, 
VaiTro, 
MaCaLV 
FROM NhanVien
END

exec sp_ThongKeNhanVien


SELECT SUM(ThanhTien), YEAR(NgayThanhToan) from HoaDon Group by YEAR(NgayThanhToan)
SELECT SUM(ThanhTien), NgayThanhToan from HoaDon Group by (NgayThanhToan)
select * from HoaDon

SELECT * FROM NhanVien
SELECT HoTen, SoNgayLV, VaiTro, MaCaLV FROM NhanVien
