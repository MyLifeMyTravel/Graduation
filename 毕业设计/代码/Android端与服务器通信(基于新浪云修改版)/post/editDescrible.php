<?php
	include("../connect.php");
	
	$account = "116040384";
	$email = "1111";
	$sql = "update user set email=$email where account=$account";
	
	//$account = "116040384";
	//$phone = "1111";
	//$sql = "update user set phone=$phone where account=$account";
	
	//$account = "116040384";
	//$email = "1234";
	//$sql = "update user set email=$email where account=$account";
	echo $sql;
	mysql_query($sql，$conn) or die("错误：".mysqli_error($conn));
	mysql_close($conn);
?>
