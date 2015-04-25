<?php
	/**
	* 更新巡检任务的状态
	*/
	
	include("../connect.php");
	
	$task_id = $_POST['task_id'];
	
	$sql = "update task set flag='finished' where id=$task_id";
	
	mysql_query($sql,$conn);
	
	mysql_close($conn);
?>