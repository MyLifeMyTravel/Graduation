<?php
	/**
	* 更新巡检任务的状态
	*/
	
	include("../connect.php");
	
	$task_id = $_POST['task_id'];
	
	$sql = "update task set flag='finished' where id=$task_id";
	
	mysqli_query($conn,$sql);
	
	mysqli_close($conn);
?>