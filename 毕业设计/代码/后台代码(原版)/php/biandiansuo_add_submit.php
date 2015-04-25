<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$loc_num = $_POST['loc_num'];
	$loc_name = $_POST['loc_name'];
	$loc_province = $_POST['loc_province'];
	$loc_city = $_POST['loc_city'];
	$loc_area = $_POST['loc_area'];
	$loc = $_POST['loc'];
	
	print_r($loc_num . $loc_name . $loc_province . $loc_city . $loc_area . $loc);
	$sql = "insert into location (loc_name,loc_province,loc_city,loc_area,loc,loc_num)
	values
	('$loc_name','$loc_province','$loc_city','$loc_area','$loc','$loc_num')";
	if( !mysql_query($sql,$con) ) {
		die("数据库连接失败，请刷新重试！");
	}else {
		header("Location:http://localhost/Lightning/Html/biandiansuo_add.html");
	}
	
	mysql_close($con);
?>