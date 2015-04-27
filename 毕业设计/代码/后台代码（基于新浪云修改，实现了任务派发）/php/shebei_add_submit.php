<?php
	//尚未完成，还需修改
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$device_id = $_POST['device_id'];
	$device_name = $_POST['select_device_name'];
	$device_type = $_POST['select_device_type'];
	$device_loc_id = $_POST['select_loc_name'];
	$device_parameter = $_POST['device_parameter'];
	
	$sql = "insert into device values('$device_id','$device_name','$device_type','$device_loc_id','$device_parameter')";
	print_r($sql);
	if( !mysql_query($sql,$con) ) {
		header("Location:http://localhost/Lightning/Html/shebei_add.html");
		echo "<script>alert(添加设备信息失败);</script>";
	}else {
		header("Location:http://localhost/Lightning/Html/shebei_add.html");
		echo "<script>alert('添加设备信息成功');</script>";
	}
	
	mysql_close($con);
?>