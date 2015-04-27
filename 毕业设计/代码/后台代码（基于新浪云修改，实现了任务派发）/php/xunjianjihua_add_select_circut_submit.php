<?php
	//连接数据库
	include("../php/connect.php");
	
	$from = 'admin';
	$flag = 'unfinished';
	
	//接收表单中的数据
	$rwlx_id = $_POST['rwlx_id'];
	$to = $_POST['to'];
	$site_id = $_POST['site_id'];
	$date = $_POST['plan_date'];
	$time = $_POST['plan_time'];
	
	//echo "<script language='javascript' type='text/javascript'>";
	//echo " alert('".$."');";
	//echo " window.location ='/topic/isoktoshow.php?pic='".$val_overview_pic ."';";
	//echo "</script>";
	
	$sql = "INSERT INTO task (rwlx_id, task_from, task_to, date, time, site_id, flag) 
			VALUES ($rwlx_id, '$from', '$to', '$date', '$time', $site_id, '$flag')";
			
	$result = mysql_query($sql,$conn);
	print_r(mysql_error($conn));
	header("Location:http://1.wodebiyesheji.sinaapp.com/backstage/xunjianjihu_add.php");
	
?>