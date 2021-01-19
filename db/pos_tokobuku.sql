-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Jan 2021 pada 10.26
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos_tokobuku`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `order_detail`
--

CREATE TABLE `order_detail` (
  `no` int(11) NOT NULL,
  `no_order` int(11) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `harga_unit` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `order_detail`
--

INSERT INTO `order_detail` (`no`, `no_order`, `id_buku`, `harga_unit`, `jumlah`) VALUES
(1, 1, 1, 80000, 5),
(2, 2, 3, 50000, 10),
(3, 2, 2, 30000, 3),
(4, 1, 2, 20000, 2);

--
-- Trigger `order_detail`
--
DELIMITER $$
CREATE TRIGGER `stock_down_up` AFTER UPDATE ON `order_detail` FOR EACH ROW BEGIN
UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok+NEW.jumlah) WHERE tb_buku.id_buku = NEW.id_buku;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stok_up_ordering` AFTER INSERT ON `order_detail` FOR EACH ROW BEGIN
UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok+NEW.jumlah) WHERE tb_buku.id_buku = NEW.id_buku;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stok_up_up` BEFORE UPDATE ON `order_detail` FOR EACH ROW BEGIN
UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok-OLD.jumlah) WHERE tb_buku.id_buku = OLD.id_buku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_buku`
--

CREATE TABLE `tb_buku` (
  `id_buku` int(11) NOT NULL,
  `judul` varchar(50) DEFAULT NULL,
  `pengarang` varchar(50) DEFAULT NULL,
  `penerbit` varchar(50) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `id_kategori` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_buku`
--

INSERT INTO `tb_buku` (`id_buku`, `judul`, `pengarang`, `penerbit`, `stok`, `harga`, `id_kategori`) VALUES
(1, 'Cerdas Pemrograman Java', 'Gramedia', 'Surabaya', 4, 100000, 1),
(2, 'Basis Data MySQL', 'Cahaya Ilmu', 'Surabaya', 8, 120000, 1),
(3, 'Detective Conan', 'Gosho', 'Jepang', 9, 150000, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kategori`
--

CREATE TABLE `tb_kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_kategori`
--

INSERT INTO `tb_kategori` (`id_kategori`, `nama`) VALUES
(1, 'Pemrograman'),
(2, 'Komik');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_order`
--

CREATE TABLE `tb_order` (
  `no_order` int(11) NOT NULL,
  `id_supplier` int(11) NOT NULL,
  `tgl` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_order`
--

INSERT INTO `tb_order` (`no_order`, `id_supplier`, `tgl`) VALUES
(1, 2, '2019-05-01'),
(2, 1, '2019-05-28');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pegawai`
--

CREATE TABLE `tb_pegawai` (
  `id_pegawai` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `pass_word` varchar(16) NOT NULL,
  `level` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `stat` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_pegawai`
--

INSERT INTO `tb_pegawai` (`id_pegawai`, `user_name`, `pass_word`, `level`, `nama`, `stat`) VALUES
(1, 'admin', 'admin', 0, 'Admin', 'login'),
(2, 'pegawai1', 'peg1', 1, 'Pegawai1', 'login'),
(3, 'pegawai3', 'peg3', 1, 'Pegawai3', 'logout'),
(4, 'pegawai4', 'peg4', 1, 'Pegawai4', 'logout'),
(5, 'firyan2903', '12345678', 1, 'Firyanul Rizky', 'login');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_supplier`
--

CREATE TABLE `tb_supplier` (
  `id_supplier` int(11) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `telp` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_supplier`
--

INSERT INTO `tb_supplier` (`id_supplier`, `nama`, `alamat`, `telp`) VALUES
(1, 'Firyanul Agency', 'Jl. Raya Ngurah Rai', '0895606181117'),
(2, 'Unud Agency', 'Jl. Udayana', '0361-xxxx');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_transaksi`
--

CREATE TABLE `tb_transaksi` (
  `no_transaksi` int(11) NOT NULL,
  `id_pegawai` int(11) NOT NULL,
  `tgl` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tb_transaksi`
--

INSERT INTO `tb_transaksi` (`no_transaksi`, `id_pegawai`, `tgl`) VALUES
(1, 1, '2019-05-01'),
(2, 1, '2019-05-28'),
(3, 1, '2019-05-28'),
(4, 1, '2019-05-28'),
(5, 1, '2019-05-28'),
(6, 5, '2019-05-28'),
(7, 1, '2021-01-17');

--
-- Trigger `tb_transaksi`
--
DELIMITER $$
CREATE TRIGGER `delete_detail` BEFORE DELETE ON `tb_transaksi` FOR EACH ROW DELETE FROM transaksi_detail WHERE no_transaksi = OLD.no_transaksi
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_detail`
--

CREATE TABLE `transaksi_detail` (
  `no` int(11) NOT NULL,
  `no_transaksi` int(11) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi_detail`
--

INSERT INTO `transaksi_detail` (`no`, `no_transaksi`, `id_buku`, `jumlah`) VALUES
(1, 1, 1, 3),
(3, 2, 1, 3),
(4, 3, 1, 1),
(5, 5, 2, 5),
(6, 6, 3, 1);

--
-- Trigger `transaksi_detail`
--
DELIMITER $$
CREATE TRIGGER `balik_stok` AFTER DELETE ON `transaksi_detail` FOR EACH ROW UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok+OLD.jumlah) WHERE tb_buku.id_buku = OLD.id_buku
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `balik_stok_up` BEFORE UPDATE ON `transaksi_detail` FOR EACH ROW UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok+OLD.jumlah) WHERE tb_buku.id_buku = OLD.id_buku
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `kurang_stok` AFTER INSERT ON `transaksi_detail` FOR EACH ROW UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok-NEW.jumlah) WHERE tb_buku.id_buku=NEW.id_buku
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `kurang_stok_up` AFTER UPDATE ON `transaksi_detail` FOR EACH ROW UPDATE tb_buku SET tb_buku.stok=(tb_buku.stok-NEW.jumlah) WHERE tb_buku.id_buku = NEW.id_buku
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`no`),
  ADD KEY `order_detail_ibfk1` (`no_order`),
  ADD KEY `order_detail_ibfk2` (`id_buku`);

--
-- Indeks untuk tabel `tb_buku`
--
ALTER TABLE `tb_buku`
  ADD PRIMARY KEY (`id_buku`),
  ADD KEY `tb_buku_ibfk1` (`id_kategori`);

--
-- Indeks untuk tabel `tb_kategori`
--
ALTER TABLE `tb_kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `tb_order`
--
ALTER TABLE `tb_order`
  ADD PRIMARY KEY (`no_order`),
  ADD KEY `tb_order_ibfk1` (`id_supplier`);

--
-- Indeks untuk tabel `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  ADD PRIMARY KEY (`id_pegawai`);

--
-- Indeks untuk tabel `tb_supplier`
--
ALTER TABLE `tb_supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indeks untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`no_transaksi`),
  ADD KEY `tb_transaksi_ibfk1` (`id_pegawai`);

--
-- Indeks untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD PRIMARY KEY (`no`),
  ADD KEY `transaksi_detail_ibfk1` (`no_transaksi`),
  ADD KEY `transaksi_detail_ibfk2` (`id_buku`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `tb_buku`
--
ALTER TABLE `tb_buku`
  MODIFY `id_buku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `tb_kategori`
--
ALTER TABLE `tb_kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_order`
--
ALTER TABLE `tb_order`
  MODIFY `no_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `tb_pegawai`
--
ALTER TABLE `tb_pegawai`
  MODIFY `id_pegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `tb_supplier`
--
ALTER TABLE `tb_supplier`
  MODIFY `id_supplier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  MODIFY `no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `order_detail_ibfk1` FOREIGN KEY (`no_order`) REFERENCES `tb_order` (`no_order`),
  ADD CONSTRAINT `order_detail_ibfk2` FOREIGN KEY (`id_buku`) REFERENCES `tb_buku` (`id_buku`);

--
-- Ketidakleluasaan untuk tabel `tb_buku`
--
ALTER TABLE `tb_buku`
  ADD CONSTRAINT `tb_buku_ibfk1` FOREIGN KEY (`id_kategori`) REFERENCES `tb_kategori` (`id_kategori`);

--
-- Ketidakleluasaan untuk tabel `tb_order`
--
ALTER TABLE `tb_order`
  ADD CONSTRAINT `tb_order_ibfk1` FOREIGN KEY (`id_supplier`) REFERENCES `tb_supplier` (`id_supplier`);

--
-- Ketidakleluasaan untuk tabel `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD CONSTRAINT `tb_transaksi_ibfk1` FOREIGN KEY (`id_pegawai`) REFERENCES `tb_pegawai` (`id_pegawai`);

--
-- Ketidakleluasaan untuk tabel `transaksi_detail`
--
ALTER TABLE `transaksi_detail`
  ADD CONSTRAINT `transaksi_detail_ibfk1` FOREIGN KEY (`no_transaksi`) REFERENCES `tb_transaksi` (`no_transaksi`),
  ADD CONSTRAINT `transaksi_detail_ibfk2` FOREIGN KEY (`id_buku`) REFERENCES `tb_buku` (`id_buku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
