<?php
	include_once('connect.php');

	$npm = $_POST['npm'];

	$getdata = mysqli_query($conn,"SELECT * FROM penyedia_dan_wisata WHERE Nama_Lengkap = '$npm'");
	$rows = mysqli_num_rows($getdata);

	$delete = "DELETE FROM penyedia_dan_wisata WHERE npm = '$npm'";
	$exedelete = mysqli_query($conn,$delete);

	$respose = array();

	if($rows > 0){
		if($exedelete){
			$respose['code'] =1;
			$respose['message'] = "Delete Success";
		}
		else{
			$respose['code'] =0;
			$respose['message'] = "Failed Delete";
		}
	}
	else{
		$respose['code'] =0;
		$respose['message'] = "Failed Delete, data not found";
	}
	
	echo json_encode($respose);
?>