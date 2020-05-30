<?php
include_once('connect.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$key1 = $_POST['key1'];
	$key2 = $_POST['key2'];
	$query = ("SELECT transaksi.keypradha2 , penyedia_dan_wisata.* FROM transaksi LEFT JOIN penyedia_dan_wisata USING(keypradha1) WHERE keypradha1 != '$key1' AND keypradha2 = '$key2'");
	$result = mysqli_query($conn,$query);
	$arraydata = array();
	while($baris = mysqli_fetch_assoc($result)){
		$arraydata[] = $baris;	
	}
	echo ($result) ? json_encode(array("kode" => 1, "result"=>$arraydata)) : json_encode(array("kode" => 0, "pesan"=>"data not found"));	
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}
?>