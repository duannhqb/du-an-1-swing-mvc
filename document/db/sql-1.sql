INSERT INTO KhuVuc(TenKhuVuc, ViTri, GhiChu) 
VALUES  ('KhuA','ViTri','Khu Vip'),
		('KhuB','ViTri','Khu Dan Gia'),
		('KhuC','ViTri','Khu Vip'),
		('KhuD','ViTri','Khu Vip'),
		('KhuE','ViTri','Khu Dan Gia')



INSERT INTO Ban(MaBan, MaKhuVuc, TrangThai) VALUES ( 19, 1, 0),( 13, 2, 0),
		( 14, 3, 0),
		( 15, 4, 0),
		( 16, 5, 0)



INSERT INTO LoaiSanPham (TenLoaiSP) 
VALUES  ('Sua chua ko duong'),
		('Ca Phe'),
		('Sting'),
		('7UP'),
		('Tra Sua')

		select SCOPE_IDENTITY()
		SELECT IDENT_CURRENT('HoaDon')


INSERT INTO SanPham (TenSanPham, MaLoaiSP, GiaBan, TrangThai, GhiChu) 
VALUES  ('Bo Huc',1,'10.000',0,'Con Hang'),
		('Sting',3,'10.000',0,'Het Hang'),
		('7UP',4,'10.000',0,'Con Hang'),
		('Ca Phe',2,'10.000',0,'Con Hang'),
		('Tra Sua',5,'10.000',0,'Con Hang')



INSERT INTO CaLamViec ( BatDau , KetThuc , GhiChu , TenCaLV ) 
VALUES  ('7.00','11.30','Lam Chuyen Can','Ca1'),
		('13.00','15.30','Lam Dung Gio','Ca2'),
		('7.00','11.30','Lam Dung Gio','Ca3'),
		('7.00','11.30','Lam Rat Tot','Ca4'),
		('13.00','15.30','Lam Nhiet Tinh','Ca5')



INSERT INTO NhanVien (HoTen , MatKhau , VaiTro , NgaySinh , GioiTinh , Email , DienThoai , GhiChu , MaCaLV ,  SoNgayLV ) 
VALUES  ('Nguyen Van Anh','111','0','10/10/2000','0','Anh@Gmail.com','01639656985','nhan vien dang ky lam viec', 1, 0),
		('Tran Quoc Toan','222','0','01/05/1999','0','Toan@Gmail.com','09686532145','nhan vien dang ky lam viec', 2, 0),
		('Phan Tran Hung','333','0','09/12/1998','0','Hung@Gmail.com','0987696585','nhan vien dang ky lam viec', 3, 0),
		('Ly Thanh Long','444','0','03/05/2000','0','Long@Gmail.com','01624545454','nhan vien dang ky lam viec', 4, 0),
		('Luong Son Ba','555','0','07/01/1998','0','Ba@Gmail.com','01635696857','nhan vien dang ky lam viec',5, 0)



INSERT INTO HoaDon ( MaNhanVien , MaBan , GhiChu , TrangThai , NgayThanhToan, ThanhTien) 
VALUES  ( 4, 12,'Chua ODER',0,'07/11/2018',4546),
		( 4, 13,'Da ODER',0,'07/11/2018',546546),
		( 4, 14,'Da ODER',0,'08/11/2018',54654),
		( 4, 15,'Da ODER',0,'08/11/2018',65655),
		(4, 16,'Da ODER',0,'09/11/2018',54654)



INSERT INTO KhoHang ( MaSanPham , NgayNhap , MaNhanVien , SoLuong , GhiChu , HanSuDung ) 
VALUES	(1,'10/10/2018',4,0,'kho hang moi nhap','10/11/2018'),
		(2,'07/09/2018',4,0,'kho hang moi nhap','07/10/2018'),
		(3,'06/05/2018',5,0,'kho hang moi nhap','06/06/2018'),
		(4,'11/10/2018',5,0,'kho hang moi nhap','10/10/2018'),
		(5,'06/09/2018',5,0,'kho hang moi nhap','06/10/2018')



INSERT INTO HoaDonChiTiet ( MaHoaDon , SoLuongSP , MaSanPham) 
VALUES  ( 1, 3, 1),
		( 2, 2, 4),
		( 3, 7, 5),
		( 4, 5, 3),
		( 5, 4, 2)






SELECT * FROM NhanVien
SELECT * FROM Ban
SELECT * FROM KhuVuc
SELECT * FROM KhoHang
SELECT * FROM LoaiSanPham
SELECT * FROM CaLamViec
SELECT * FROM SanPham
SELECT * FROM HoaDon
SELECT * FROM HoaDonChiTiet

