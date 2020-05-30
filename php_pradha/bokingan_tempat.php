<?php
	include_once('connect.php');

	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$key1 = $_POST['key1'];
		$query = ("SELECT transaksi.keypradha1 , tempat_wisata.* FROM transaksi RIGHT JOIN tempat_wisata ON transaksi.keypradha2 = tempat_wisata.keypradha2 WHERE keypradha1 = '$key1'");
		$result = mysqli_query($conn,$query);
		$arraydata = array();

		while($baris = mysqli_fetch_assoc($result)){

			$arraydata[] = $baris;	
		}

		echo ($result) ?
		json_encode(array("kode" => 1, "result"=>$arraydata)) :
		json_encode(array("kode" => 0, "pesan"=>"data not found"));
	}
	else{
		echo json_encode(array('kode' =>0, 'pesan' => 'data gagal'));
	}
?>