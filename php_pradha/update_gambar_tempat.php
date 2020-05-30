
<?php
include_once 'connect.php';

$part = "./upload/";
$uml = "http://192.168.1.104/web/pradha/upload/";
$filename = "img".rand(9,9999).".jpg";

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$key1 = $_POST['key'];
	if($_FILES['imageupload']){ 
			$values = mysqli_query($conn,"SELECT * FROM tempat_wisata WHERE keypradha2 = '$key1'");
			$quer = mysqli_fetch_assoc($values);
			$gambar = substr($quer["Gambar"],39);
			unlink("upload/$gambar");
			$destinationfile = $part.$filename;
			$gambar = $uml.$filename;
			move_uploaded_file($_FILES['imageupload']['tmp_name'], $destinationfile);
			$update = "UPDATE tempat_wisata SET Gambar = '$gambar' WHERE keypradha2 = '$key1'";
			$exeinsert = mysqli_query($conn, $update);
			echo ($exeinsert) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil')) : json_encode(array('kode' =>2, 'pesan' => 'data gagal'));
	}
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}
?>