<?php
include_once('connect.php');

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$key1 = $_POST['key'];
	$query = ("SELECT transaksi.keypradha2 , penyedia_dan_wisata.* FROM transaksi LEFT JOIN penyedia_dan_wisata USING(keypradha1) WHERE keypradha2 = $key1 AND User = 'Travel_Provider'");
	$result = mysqli_query($conn,$query);
	echo ($result) ? json_encode(array("kode" => 1, "result"=>$result)) : json_encode(array("kode" => 0, "pesan"=>"data not found"));
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}
?>
