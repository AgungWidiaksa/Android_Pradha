<?php
	include_once('connect.php');
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$key1 = $_POST['key1'];
		$delQuery = mysqli_query($conn,"DELETE FROM transaksi WHERE keypradha1 = '$key1'");
		echo ($delQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'gagal'));
	}
	else{
		echo json_encode(array('kode' =>3, 'pesan' => 'gagal'));
	}
?>