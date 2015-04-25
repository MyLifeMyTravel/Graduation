<?php
	/**
	* 更新巡检任务的状态
	*/
	
	include("../connect.php");
	
	$account = $_POST['account'];
	$device_id = $_POST['device_id'];
	
	$sql = "update user set device_id='$device_id' where account='$account'";
	echo $sql;
	
	mysqli_query($conn,$sql);
	
	mysqli_close($conn);
?>