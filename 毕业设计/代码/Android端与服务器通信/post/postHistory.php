<?php
	/**
	* 接收自客户端传来的JSON数据
	* POST参数的key为history
	* json数据格式为：
	* {
    * "time": "2015-04-24 20:25:18", 
    * "problems": [
    *     {
    *         "note": "正常", 
    *         "xsbz_id": 95
    *     }, 
    *     {
    *         "note": "温度上限达到100度", 
    *         "xsbz_id": 96
    *     }
    * ], 
    * "place_id": 6, 
    * "task_id": 1, 
    * "device_id": 8, 
    * "xsnr_id": 30
	* }
	*/
	include("../connect.php");
	
	$json = $_POST['history'];
	
	//解析json数据
	$obj = json_decode($json);
	$time = $obj->time;
	$problems = json_encode($obj->problems);
	$place_id = $obj->place_id;
	$task_id = $obj->task_id;
	$device_id = $obj->device_id;
	$xsnr_id = $obj->xsnr_id;

	//sql语句
	$sql = "insert into history (task_id,place_id,device_id,xsnr_id,problems,time) values ($task_id,$place_id,$device_id,$xsnr_id,'$problems','$time')";
	echo $sql;
	$result = mysqli_query($conn,$sql);
	print_r(mysqli_error($conn));
	mysqli_close($conn);
?>
