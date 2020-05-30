<?php
	define('hostname', 'localhost');
	define('username', 'root');
	define('password', '');
	define('databases', 'pradha');
	
	$conn = new mysqli(hostname,username,password,databases) or die (mysql_errno());
?>