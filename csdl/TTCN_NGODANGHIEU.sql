-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th3 18, 2019 lúc 10:23 PM
-- Phiên bản máy phục vụ: 10.1.36-MariaDB
-- Phiên bản PHP: 7.0.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `TTCN_NGODANGHIEU`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_chitiethoadon`
--

CREATE TABLE `tbl_chitiethoadon` (
  `id` int(11) NOT NULL,
  `id_hoadon` int(255) NOT NULL,
  `id_monan` int(11) NOT NULL,
  `sl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_chitiethoadon`
--

INSERT INTO `tbl_chitiethoadon` (`id`, `id_hoadon`, `id_monan`, `sl`) VALUES
(1, 1, 2, 5),
(6, 4, 2, 2),
(7, 4, 4, 2),
(8, 5, 2, 2),
(9, 5, 4, 7),
(10, 6, 4, 1),
(11, 6, 5, 2),
(12, 6, 6, 2),
(13, 6, 7, 1),
(14, 7, 1, 1),
(15, 7, 2, 3),
(16, 7, 3, 1),
(17, 8, 3, 2),
(18, 8, 6, 1),
(19, 9, 6, 3),
(20, 9, 7, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_hangxuat`
--

CREATE TABLE `tbl_hangxuat` (
  `id` int(11) NOT NULL,
  `id_sp` int(11) NOT NULL,
  `sl` int(11) NOT NULL,
  `date_xuat` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_hangxuat`
--

INSERT INTO `tbl_hangxuat` (`id`, `id_sp`, `sl`, `date_xuat`) VALUES
(3, 1, 50, '2019-03-13'),
(4, 2, 30, '2018-03-13'),
(5, 3, 20, '2019-03-18'),
(6, 3, 30, '2019-03-18'),
(7, 2, 20, '2019-03-18'),
(8, 5, 20, '2019-03-18');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_hoadon`
--

CREATE TABLE `tbl_hoadon` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_hoadon` date NOT NULL,
  `tongtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_hoadon`
--

INSERT INTO `tbl_hoadon` (`id`, `id_user`, `date_hoadon`, `tongtien`) VALUES
(1, 1, '2018-03-01', 150000),
(4, 1, '2019-03-14', 1400000),
(5, 1, '2018-03-14', 2150000),
(6, 1, '2019-03-14', 1550000),
(7, 1, '2019-03-14', 2300000),
(8, 2, '2019-03-14', 750000),
(9, 1, '2019-04-14', 1850000),
(10, 0, '2019-03-18', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_monan`
--

CREATE TABLE `tbl_monan` (
  `id` int(11) NOT NULL,
  `ten_monan` varchar(255) NOT NULL,
  `mota_monan` varchar(255) NOT NULL,
  `gia_monan` int(11) NOT NULL,
  `img_monan` varchar(255) NOT NULL,
  `ma_monan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_monan`
--

INSERT INTO `tbl_monan` (`id`, `ten_monan`, `mota_monan`, `gia_monan`, `img_monan`, `ma_monan`) VALUES
(1, 'Ba ba nấu chuối đậu', 'Ba ba là động vật thuộc lớp bò sát, thường sinh sống ở các vùng nước ngọt thuộc đồng bằng sông Hồng, Tây Nam Bộ,…Thịt ba ba mang lại giá trị dinh dưỡng cao, phần mai của nó chứa vitamin D có lợi cho sức khỏe.', 550000, 'baba.png', 'MA01'),
(2, 'ĐẶC SẢN XÔI LE LE', 'Le Le không chỉ là món ăn ngon bổ dưỡng đặc sản nhiều vùng miền mà còn được đánh giá cao về giá trị dinh dưỡng, có khả năng phục hồi sức khỏe.', 550000, 'xoilele.png', 'MA02'),
(3, 'Gỏi bò tắc non cà pháo', 'Gỏi bò tắc non cà pháo món ngon độc đáo trọn vị Quá Ngon, vị chua ngon của cà pháo hòa quyện từng miếng thịt bò dai tạo nên hương vị khó cưỡng cho các thực khách.', 100000, 'botoi.png', 'MA03'),
(4, 'Bánh bột lọc đậm đà xứ Huế', 'Chiếc bánh bột lọc trong suốt, đơn giản nhưng lại rất cầu kỳ, là món ăn chơi rất nổi tiếng của xứ Huế được nhiều người ưa thích.', 150000, 'banhbotloc.png', 'MA04'),
(5, 'Bánh nậm đậm chất Cố đô', 'Bánh nậm là một món ăn dân gian, mang nét bình dị của vùng đất Cố Đô. Từ lâu, bánh nậm đã trở nên quen thuộc với người Việt Nam và trở thành món ăn tinh túy, đậm đà bản sắc dân tộc.', 100000, 'banhnam.png', 'MA05'),
(6, 'Lẩu cá kèo lá', 'Lẩu cá kèo lá giang vốn là món ăn rất dân dã, giản dị, đặc trưng của người miền Tây Nam Bộ. Với hương vị lẩu chua chua, thơm ngon chắc chẳng ai có thể quên được.', 550000, 'laucakeo.png', 'MA06'),
(7, 'Ngọt ngon ba chỉ cuộn rau củ', 'Các bữa cơm với đủ loại thịt, cá, tôm, cua… giàu chất đạm đã khiến bạn chán ngấy. Để dung hòa cho lượng đạm cung cấp vào cơ thể và giảm việc ngấy món ăn đầy đầu mỡ, chất đạm.', 100000, 'thitcuoan.png', 'MA07'),
(8, 'Gỏi thanh long', 'Ngoài việc là loại trái cây thông thường, chúng ta có thể chế biến thanh long thành một món gỏi lạ miệng. Chứa hàm lượng chất dinh dưỡng dồi dào, nhiều Vitamin, chất xơ, protein…tốt cho sức khỏe, thanh long là loại trái cây phổ biến mang lại nhiều lợi ích', 550000, 'Goithanhlong.png', 'MA08'),
(9, 'Lòng dồi heo tộc', 'Để có được món Đặc sản lòng dồi heo tộc đúng chuẩn, không kém phần công phu như các nhà hàng đặc sản mà vẫn hội tụ được các mùi vị nguyên vẹn của lá mơ, beo béo nhưng không ngán của đậu xanh, mỡ heo và nhất là vị đậm đà của mắm tôm', 100000, 'longdoiheotoc.png', 'MA09'),
(10, 'Gỏi nha đam', 'Gỏi nha đam giúp thanh nhiệt cho cơ thể, cho làn da tươi trẻ mịn màng, đặc biệt tốt cho những chị em có nhu cầu giảm cân. ', 550000, 'nhadam.png', 'MA010'),
(11, 'Gà Đông Tảo hầm sâm', 'Gà Đông Tảo hay còn được gọi là Gà Tiến Vua một giống gà đặc hữu và quý hiếm của Việt Nam, do dân làng Đông Tảo (huyện Khoái Châu, Hưng Yên) thuần dưỡng từ lâu đời. Gà Đông Tảo đòi hỏi kỳ công chăm sóc và khó nuôi. ', 100000, 'gadongtao.png', 'MA011'),
(12, 'Nem nướng', 'Nem nướng là món ăn được rất nhiều người yêu thích bởi mùi vị thơm ngon của nó mang lại. Nem nướng tại Nhà hàng Quá Ngon rất được yêu thích bởi vị thơm ngon đặc trưng được nhà hàng chế biến theo công thức đặc biệt hợp khẩu vị người dân Sài Gòn', 150000, 'nemnuong.png', 'MA012'),
(13, 'Ba ba hầm thuốc bắc', 'Ba ba hầm thuốc bắc là món ăn vừa ngon lại vừa bổ dưỡng cho cả nhà ăn hết nồi cơm. Với một số công dụng nổi bật như chưa bệnh ho han kéo dài, bổ thần kinh và tim mạch…', 100000, 'babahapthuocbac.png', 'MA013'),
(14, 'Ốc vòi voi nướng mỡ hành', 'Ốc vòi voi còn có tên gọi khác là tù hài. Ốc vòi voi nướng mỡ hành là món ngon hấp dẫn, ăn vào có vị giòn ngọt cộng thêm mỡ hành làm cho thịt tu hài thêm béo ngậy  làm cho món ăn ai cũng muốn thưởng thức.', 550000, 'ocvoicoi.png', 'MA014'),
(15, 'Đuôi heo hầm thuốc bắc', 'Món đuôi heo hầm thuốc bắc là món ăn mà theo Đông Y có rất nhiều chất dinh dưỡng ví dụ như giúp thai phụ bổ khí, dưỡng huyết, chống được các cơn suy nhược cơ thể khi mang thai.', 550000, 'duoiheo.jpg', 'MA015');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_sp`
--

CREATE TABLE `tbl_sp` (
  `id` int(11) NOT NULL,
  `ten_sp` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `sl_sp` int(11) DEFAULT NULL,
  `ngaynhap` date DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `nhasx` varchar(255) DEFAULT NULL,
  `loai_sp` int(2) DEFAULT NULL,
  `gia` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `tbl_sp`
--

INSERT INTO `tbl_sp` (`id`, `ten_sp`, `id_user`, `sl_sp`, `ngaynhap`, `img`, `nhasx`, `loai_sp`, `gia`) VALUES
(1, 'Coca Cola', 1, 2000, '2019-03-01', 'TTCN_NGODANGHIEU/img/hang/coca.png', 'CôCa', 0, 10000),
(3, 'Cá chép', 1, 20, '2019-03-18', 'TTCN_NGODANGHIEU/img/hang/cachep.png', 'nong dan', 1, 20000),
(5, 'Cá rô phi', 1, 980, '2019-03-18', 'TTCN_NGODANGHIEU/img/hang/carophi.png', 'nong san', 1, 50000),
(6, 'Chai Chai', 1, 980, '2019-03-18', 'TTCN_NGODANGHIEU/img/hang/chaichai.png', 'nong san', 1, 50000),
(7, 'Tôm hùm', 1, 980, '2019-03-18', 'TTCN_NGODANGHIEU/img/hang/tomhum.png', 'nong san', 1, 50000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `namelogin_user` varchar(255) NOT NULL,
  `pass_user` varchar(255) NOT NULL,
  `level` int(11) NOT NULL,
  `name_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `namelogin_user`, `pass_user`, `level`, `name_user`) VALUES
(1, 'admin', 'admin', 1, 'Ngô Đăng Hiếu'),
(4, '2', '2', 2, 'Tô Minh Đức');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_chitiethoadon`
--
ALTER TABLE `tbl_chitiethoadon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_hangxuat`
--
ALTER TABLE `tbl_hangxuat`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_hoadon`
--
ALTER TABLE `tbl_hoadon`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_monan`
--
ALTER TABLE `tbl_monan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_sp`
--
ALTER TABLE `tbl_sp`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbl_chitiethoadon`
--
ALTER TABLE `tbl_chitiethoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `tbl_hangxuat`
--
ALTER TABLE `tbl_hangxuat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `tbl_hoadon`
--
ALTER TABLE `tbl_hoadon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `tbl_monan`
--
ALTER TABLE `tbl_monan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `tbl_sp`
--
ALTER TABLE `tbl_sp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
