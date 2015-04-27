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
    $hostname = SAE_MYSQL_HOST_M.':'.SAE_MYSQL_PORT;
    $dbuser = SAE_MYSQL_USER;
    $dbpass = SAE_MYSQL_PASS;
    $dbname = SAE_MYSQL_DB;
	$conn = mysql_connect(SAE_MYSQL_HOST_M.':'.SAE_MYSQL_PORT,SAE_MYSQL_USER,SAE_MYSQL_PASS);
	/* check connection */
    if ($conn) {
        mysql_select_db(SAE_MYSQL_DB,$conn);
    }
?>