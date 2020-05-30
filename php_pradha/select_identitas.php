<?php
	include_once('connect.php');

	$query = "SELECT * FROM penyedia_dan_wisata";
	$result = mysqli_query($conn,$query);

	$arraydata = array();

	while($baris = mysqli_fetch_assoc($result)){

		$arraydata[] = $baris;	
	}
	echo json_encode($arraydata);
?>