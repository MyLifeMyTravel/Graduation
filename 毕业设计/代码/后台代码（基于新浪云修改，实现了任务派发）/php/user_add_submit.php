<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$user_id = $_POST['user_id'];
	$user_name = $_POST['user_name'];
	$user_pwd = $_POST['user_pwd'];
	$user_type_id = $_POST['user_type_id'];
	$user_permission = $_POST['user_permission'];
	$user_dept_id = $_POST['user_dept_id'];
	$user_info = $_POST['user_info'];
	
	$sql = "insert into users (user_id,user_name,user_pwd,user_type_id,user_permission,user_dept_id,user_info) 
	values
	('$user_id','$user_name','$user_pwd','$user_type_id','$user_permission','$user_dept_id','$user_info')";
	print_r($sql);
	if( !mysql_query($sql,$con) ) {
		die("数据库连接失败，请刷新重试！");
	}else {
		header("Location:http://localhost/Lightning/Html/user_add.html");
	}
	
	mysql_close($con);
?>