<?php 
	//连接MySQL数据库
	//$db_host = "localhost";
	//$db_user = "u284008300_bysj";
	//$db_pwd = "1025263614";
	//$db_name = "u284008300_bysj";
	//$db_host = "localhost";
	//$db_user = "root";
	//$db_pwd = "1025263614";
	//$db_name = "task";
	//$conn = mysqli_connect($db_host,$db_user,$db_pwd,$db_name);
	$conn = mysqli_connect(SAE_MYSQL_HOST_M.':'.SAE_MYSQL_PORT,SAE_MYSQL_USER,SAE_MYSQL_PASS);
	/* check connection */
	if (mysqli_connect_error()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
	}
?>