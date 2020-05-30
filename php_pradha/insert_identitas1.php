
<?php
include_once 'connect.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$fullname = $_POST['fullnames'];
	$country = $_POST['countrys'];//stripslasehes berfungsi untuk membersihkan form agar user tidak dapat menginputkan sysntak css or lainnya..
	$address = $_POST['addresss'];	
	$ages = $_POST['ages'];
	$emails = $_POST['emails'];
	$hps = $_POST['hps'];
	$usernames = $_POST['usernames'];
	$passwords = $_POST['passwords'];
	$jenis_user = $_POST['radiogroups'];
	$gender = $_POST['radiogroups1'];
	$use = mysqli_query($conn,"SELECT Username FROM penyedia_dan_wisata WHERE Username = '$usernames'");
	if(mysqli_fetch_assoc($use)){

		echo json_encode(array('kode' =>3, 'pesan' => 'username sudah tersedia'));
	
	}
	else{

		$password = password_hash($passwords, PASSWORD_DEFAULT);
		$insert = "INSERT INTO penyedia_dan_wisata(Nama_Lengkap,Negara,Alamat,Umur,Email,HP,Username,Password,User,Jenis_Kelamin) VALUES('$fullname','$country','$address','$ages','$emails','$hps','$usernames','$password','$jenis_user','$gender')";
		$exeinsert = mysqli_query($conn, $insert);
		echo ($exeinsert) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'data gagal'));

	}
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}
?>