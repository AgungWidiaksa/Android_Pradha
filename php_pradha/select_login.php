<?php
include_once 'connect.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$usernames = $_POST['username_log'];
	$passwords = $_POST['passwords_log'];
	$jenis_user = $_POST['radiogroup_log'];
	$select = "SELECT * FROM penyedia_dan_wisata WHERE Username = '$usernames' and User = '$jenis_user'";
	$exeselect = mysqli_query($conn, $select);
	if(mysqli_num_rows($exeselect) === 1){
		$quer = mysqli_fetch_assoc($exeselect);
		if(password_verify($passwords,$quer["Password"])){
			if($jenis_user == "Travel_Provider"){
				$data = array("kode" => 6, "pesan" => "Travel_Provider", "result" => 
					array(
						array("keypradha1" => $quer["keypradha1"])
					));	
				echo json_encode($data);
			}
			elseif($jenis_user == "Traveler"){
				$data = array("kode" => 5, "pesan" => "Travel_Provider", "result" => 
					array(
						array("keypradha1" => $quer["keypradha1"])
					));	
				echo json_encode($data);	
			}
		}
		else{
			echo json_encode(array('kode' =>2, 'pesan' => 'pasword salah'));
		}
	}
	else{
		echo json_encode(array('kode' =>3, 'pesan' => 'data gagal'));
	}
	
}
else{
	echo json_encode(array('kode' =>4, 'pesan' => 'request tidak valid'));
}

?>