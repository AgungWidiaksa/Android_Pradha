<?php
	include_once('connect.php');

	$query = "SELECT * FROM tempat_wisata WHERE Jenis_Tempat = 'Umum_Bali'";
	$result = mysqli_query($conn,$query);

	$arraydata = array();

	while($baris = mysqli_fetch_assoc($result)){

		$arraydata[] = $baris;	
	}

	echo ($result) ?
	json_encode(array("kode" => 1, "result"=>$arraydata)) :
	json_encode(array("kode" => 0, "pesan"=>"data not found"));
?>