<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$dept_name = $_POST['dept_name'];
	$dept_manager = $_POST['dept_manager'];
	$dept_info = $_POST['dept_info'];
	
	print_r($dept_name . $dept_manager . $dept_info);
	$sql = "insert into department (dept_info,dept_manager,dept_name) 
	values
	('$dept_info','$dept_manager','$dept_name')";
	if( !mysql_query($sql,$con) ) {
		die("数据库连接失败，请刷新重试！");
	}else {
		header("Location:http://localhost/Lightning/Html/department_add.html");
	}
	
	mysql_close($con);
?>