
<?php
include_once 'connect.php';

$part = "./upload/";
$uml = "http://192.168.1.104/web/pradha/upload/";
$filename = "img".rand(9,9999).".jpg";

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$namatempat = $_POST['namatempat'];
	$namatempat1 = $_POST['namatempat1'];
	$deskripsi = $_POST['deskripsi'];
	$waktu = $_POST['waktu'];	
	$alamat = $_POST['alamat'];
	$harga = $_POST['harga'];
	$persyaratan = $_POST['persyaratan'];
	$jenistempat = $_POST['jenis_tempat'];
	$key1 = $_POST['key'];
	$use = mysqli_query($conn,"SELECT * FROM tempat_wisata WHERE Nama_tempat = '$namatempat'");
	$quer = mysqli_fetch_assoc($use);
	if(!$quer || $namatempat1 == $namatempat){
		$update = "UPDATE tempat_wisata SET Nama_tempat = '$namatempat', Deskripsi = '$deskripsi' , Waktu = '$waktu' , Alamat_Lokasi = '$alamat', Harga = '$harga' , Persyaratan = '$persyaratan' , Jenis_Tempat = '$jenistempat' WHERE keypradha2 = '$key1'";
		$exeinsert = mysqli_query($conn, $update);
		echo ($exeinsert) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'data gagal'));
	}	
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}
?>