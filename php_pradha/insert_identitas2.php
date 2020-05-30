
<?php
include_once 'connect.php';

$part = "./upload/";
$uml = "http://192.168.1.104/web/pradha/upload/";
$filename = "img".rand(9,9999).".jpg";

$res = array();
$kode = "";
$pesan = "";

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	if($_FILES['imageupload']){ 
		$destinationfile = $part.$filename;
		move_uploaded_file($_FILES['imageupload']['tmp_name'], $destinationfile);
	}
	$gambar = $uml.$filename;
	$namatempat = $_POST['namatempat'];
	$deskripsi = $_POST['deskripsi'];
	$waktu = $_POST['waktu'];	
	$alamat = $_POST['alamat'];
	$harga = $_POST['harga'];
	$persyaratan = $_POST['persyaratan'];
	$jenistempat = $_POST['jenistempat'];
	$key1 = $_POST['key'];
	$use = mysqli_query($conn,"SELECT * FROM tempat_wisata WHERE Nama_tempat = '$namatempat'");
	$quer = mysqli_fetch_assoc($use);
	if($quer){		
		echo json_encode(array('kode' =>3, 'pesan' => 'nama tempat sudah tersedia'));
	}
	else{
		$insert = "INSERT INTO tempat_wisata(Nama_tempat,Deskripsi,Waktu,Alamat_Lokasi,Harga,Persyaratan,Jenis_Tempat,Gambar) VALUES('$namatempat','$deskripsi','$waktu','$alamat','$harga','$persyaratan','$jenistempat','$gambar')";
		$exeinsert = mysqli_query($conn, $insert);
		if($exeinsert){
			$response['error'] = false;
			$response['namatempat'] = $namatempat;
		}
		$use1 = mysqli_query($conn,"SELECT * FROM tempat_wisata WHERE Nama_tempat = '$namatempat'");
		$quer1 = mysqli_fetch_assoc($use1);
		if($quer1){
			$key2 = $quer1["keypradha2"];
			$insert1 = mysqli_query($conn,"INSERT INTO transaksi(keypradha1,keypradha2) VALUES('$key1','$key2')");
			echo ($insert1) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'data gagal'));
		}
		else{
			echo json_encode(array('kode' =>2, 'pesan' => 'data gagal'));
		}
	}
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}
?>