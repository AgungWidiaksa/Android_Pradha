<?php
	include_once('connect.php');
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$key = $_POST['key'];
		$values = mysqli_query($conn,"SELECT * FROM tempat_wisata WHERE keypradha2 = '$key'");
		$quer = mysqli_fetch_assoc($values);
		$gambar = substr($quer["Gambar"],39);
		unlink("upload/$gambar");
		mysqli_query($conn,"DELETE FROM transaksi WHERE keypradha2 = '$key'");
		$delQuery = mysqli_query($conn,"DELETE FROM tempat_wisata WHERE keypradha2 = '$key'");
		echo ($delQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'gagal'));
	}
	else{
		echo json_encode(array('kode' =>3, 'pesan' => 'gagal'));
	}
?>