
<?php
include_once 'connect.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$key1 = $_POST['key1'];
	$key2 = $_POST['key2'];
	$check = mysqli_query($conn,"SELECT * FROM transaksi WHERE keypradha1 = '$key1' AND keypradha2 = '$key2'");
	$quer = mysqli_fetch_assoc($check);
	if($quer){		
		echo json_encode(array('kode' =>3, 'pesan' => 'sudah diboking'));
	}
	else{
		$insert1 = mysqli_query($conn,"INSERT INTO transaksi(keypradha1,keypradha2) VALUES('$key1','$key2')");
		echo ($insert1) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'data gagal'));
	}
}
else{
	echo json_encode(array('kode' =>2, 'pesan' => 'data gagal'));
}
?>