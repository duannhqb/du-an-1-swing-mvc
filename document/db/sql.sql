 -- sa - 123456
CREATE DATABASE QlCoffee
GO
USE QlCoffee
GO
CREATE TABLE KhuVuc(
	MaKhuVuc INT IDENTITY(1,1) PRIMARY KEY,
	TenKhuVuc NVARCHAR(50) NOT NULL UNIQUE,
	ViTri NVARCHAR(50) NOT NULL,
	GhiChu NVARCHAR(255) NULL
)
GO
CREATE TABLE Ban(
	MaBan INT PRIMARY KEY,
	MaKhuVuc INT NOT NULL REFERENCES KhuVuc(MaKhuVuc),
	TrangThai BIT DEFAULT 0
)
GO
CREATE TABLE LoaiSanPham(
	MaLoaiSP INT IDENTITY(1,1) PRIMARY KEY,
	TenLoaiSP NVARCHAR(50) NOT NULL UNIQUE
)
GO
CREATE TABLE SanPham(
	MaSanPham INT IDENTITY(1,1) PRIMARY KEY,
	TenSanPham NVARCHAR(50) NOT NULL,
	MaLoaiSP INT NOT NULL REFERENCES LoaiSanPham(MaLoaiSP),
	GiaBan FLOAT NOT NULL, CHECK(GiaBan>=0),
	TrangThai BIT DEFAULT 0,
	GhiChu NVARCHAR(255) NOT NULL
)
GO
CREATE TABLE CaLamViec(
	MaCaLV INT IDENTITY(1,1) PRIMARY KEY,
	BatDau NVARCHAR(50) NOT NULL,
	KetThuc NVARCHAR(50) NOT NULL,
	GhiChu NVARCHAR(255) NOT NULL,
	TenCaLV NVARCHAR(50) NOT NULL UNIQUE
)
GO
CREATE TABLE NhanVien(
	MaNhanVien INT IDENTITY(1,1) PRIMARY KEY,
	HoTen NVARCHAR(50) NOT NULL,
	MatKhau NVARCHAR(50) NOT NULL,
	VaiTro BIT DEFAULT 0,
	NgaySinh DATE NOT NULL,
	GioiTinh BIT DEFAULT 0,
	Email NVARCHAR(50) NOT NULL UNIQUE,
	DienThoai NVARCHAR(50) NOT NULL UNIQUE,
	GhiChu NVARCHAR(255) NULL,
	MaCaLV INT REFERENCES CaLamViec(MaCaLV),
	SoNgayLV INT DEFAULT 0
)
GO
CREATE TABLE HoaDon(
	MaHoaDon INT IDENTITY(1,1) PRIMARY KEY,
	MaNhanVien INT REFERENCES NhanVien(MaNhanVien),
	MaBan INT REFERENCES Ban(MaBan),
	GhiChu NVARCHAR(255) NOT NULL,
	TrangThai BIT DEFAULT 0,
	NgayThanhToan DATE,
	ThanhTien FLOAT
)
GO
CREATE TABLE KhoHang(
	MaKhoHang INT IDENTITY(1,1) PRIMARY KEY,
	MaSanPham INT REFERENCES SanPham(MaSanPham),
	NgayNhap DATE NOT NULL,
	MaNhanVien INT REFERENCES NhanVien(MaNhanVien),
	SoLuong INT DEFAULT 0, CHECK(SoLuong>=0),
	GhiChu NVARCHAR(255) NULL,
	HanSuDung DATE,
)
GO
CREATE TABLE HoaDonChiTiet(
	MaHoaDonCT INT IDENTITY(1,1) PRIMARY KEY,
	MaHoaDon INT REFERENCES HoaDon(MaHoaDon),
	SoLuongSP INT NOT NULL, CHECK(SoLuongSP>=0),
	MaSanPham INT REFERENCES SanPham(MaSanPham)
)

-- truy van thao tac bang

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


-- proc

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


-- cap account admin - quan ly 

INSERT INTO CaLamViec (BatDau, KetThuc, GhiChu, TenCaLV) VALUES ('00:00','00:00','','Full')

--

INSERT INTO NhanVien (HoTen , MatKhau , VaiTro , NgaySinh , GioiTinh , Email , DienThoai , GhiChu , MaCaLV ,  SoNgayLV ) 
VALUES  ('admin','admin',0,'10/10/2000',0,'admin@gmail.com','0111111111','', 2, 0)


--

SELECT * FROM NhanVien
SELECT * FROM Ban
SELECT * FROM KhuVuc
SELECT * FROM KhoHang
SELECT * FROM LoaiSanPham
SELECT * FROM CaLamViec
SELECT * FROM SanPham
SELECT * FROM HoaDon
SELECT * FROM HoaDonChiTiet

