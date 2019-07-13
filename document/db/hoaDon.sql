use QlCoffee
go
update Ban set TrangThai = 1 where MaBan = 12
go
SELECT * from HoaDon JOIN HoaDonChiTiet ON HoaDon.MaHoaDon = HoaDonChiTiet.MaHoaDon JOIN SanPham on HoaDonChiTiet.MaSanPham = SanPham.MaSanPham
go
SELECT Ban.MaBan, KhuVuc.MaKhuVuc, HoaDon.ThanhTien, HoaDon.TrangThai from HoaDon JOIN Ban ON HoaDon.MaBan = Ban.MaBan JOIN KhuVuc ON Ban.MaKhuVuc = KhuVuc.MaKhuVuc GROUP BY Ban.MaBan
go
SELECT * FROM SanPham WHERE TenSanPham='Bo Huc'
go
select * from nhanvien
go
SELECT Ban.MaBan, Sum(HoaDon.ThanhTien) from HoaDon JOIN Ban ON HoaDon.MaBan = Ban.MaBan JOIN KhuVuc ON Ban.MaKhuVuc = KhuVuc.MaKhuVuc Where HoaDon.TrangThai = 1 GROUP BY Ban.MaBan
go
SELECT ban.MaBan ,sum(ban.MaKhuVuc)/count(Ban.MaKhuVuc), Sum(HoaDon.ThanhTien) from HoaDon JOIN Ban ON HoaDon.MaBan = Ban.MaBan JOIN KhuVuc ON Ban.MaKhuVuc = KhuVuc.MaKhuVuc Where HoaDon.TrangThai = 1 group by Ban.MaBan
go 
SELECT ban.MaBan ,sum(ban.MaKhuVuc)/count(Ban.MaKhuVuc), Sum(HoaDon.ThanhTien) from HoaDon JOIN Ban ON HoaDon.MaBan = Ban.MaBan JOIN KhuVuc ON Ban.MaKhuVuc = KhuVuc.MaKhuVuc Where HoaDon.TrangThai = 0 group by Ban.MaBan
go 
select * from Ban
go 
select * from KhuVuc
go
SELECT SoLuong FROM KhoHang WHERE MaSanPham = 1
go
SELECT ban.MaBan ,sum(ban.MaKhuVuc)/count(Ban.MaKhuVuc), Sum(HoaDon.ThanhTien) from HoaDon JOIN Ban ON HoaDon.MaBan = Ban.MaBan JOIN KhuVuc ON Ban.MaKhuVuc = KhuVuc.MaKhuVuc where ban.TrangThai = 0 group by Ban.MaBan
go
update KhoHang set SoLuong = ? where MaSanPham = ?