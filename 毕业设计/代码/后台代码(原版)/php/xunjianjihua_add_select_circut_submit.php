<?php
	//连接数据库
	$con = @mysql_connect("localhost","root","1025263614");
	//判断是否连接成
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	//选择数据库
	mysql_select_db("justdoit",$con);
	//接收表单中的数据
	$circut_id = $_POST['circut_id'];
	$plan_time = $_POST['plan_time'];
	$plan_user_id = $_POST['plan_user_id'];
	$plan_note = $_POST['plan_note'];
	$plan_admin_id = 0;
	$plan_state = 0;
	
	$sql = "insert into plan (circut_id, plan_time, plan_user_id, plan_admin_id, plan_state, plan_note)
			values 
			('$circut_id', '$plan_time', '$plan_user_id', '$plan_admin_id', '$plan_state', '$plan_note')";
			
	if( !mysql_query($sql,$con) ){
		echo "<script>alert('添加失败，请重新添加！')</script>";
		die("Error:" . mysql_error());	
	}else {
		echo "<script language='javascript'>alert('添加成功!');</script>";	
		header("Location:http://localhost/Lightning/Html/xunjianjihu_add.html");
	}
?>