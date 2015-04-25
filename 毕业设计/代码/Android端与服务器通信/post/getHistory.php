<?php
	/**
	* 获取设备的历史记录，用于生成图标
	* 传递给客户端的JSON数据格式为：
	* [
    * {
    *     "xsnr_id": 1, 
    *     "count": 1
    * }, 
    * {
    *     "xsnr_id": 1, 
    *     "count": 1
    * }
	* ]
	*/
	//连接数据库
	include("../connect.php");
	
	//获取传递过来的参数
	//$place_id = $_POST['place_id'];
	$device_id = $_POST['device_id'];
	
	//获取巡视内容ID
	$sql_xsnr_arr = "select xsnr.id from xsnr where sblx_id=$device_id";
	$xsnr_result = mysqli_query($conn,$sql_xsnr_arr);
	
	//符合查询结果的巡视内容ID数组
	$xsnr_id_arr = array();
	//返回给客户端的JSON数组
	$json_arr = array();
	//当查询结果类型boolean时，不返回结果
	if(gettype($xsnr_result)=="boolean") {
		
	} else {
		while($row=mysqli_fetch_array($xsnr_result)) {
			//echo $row['id'];
			array_push($xsnr_id_arr,$row['id']);
		}
		//打印符合查询结果的巡视内容数组
		//print_r($xsnr_id_arr);
		
		//获取巡视内容次数
		
		foreach($xsnr_id_arr as $xsnr_id) {
			$sql_history = "select * from history where xsnr_id=$xsnr_id";
			$result = mysqli_query($conn,$sql_history);
			$arr = array("xsnr_id"=>$xsnr_id,"count"=>$result->num_rows);
			array_push($json_arr,$arr);
			//echo "巡视内容ID：".$xsnr_id." 次数：".$result->num_rows;
		}
	}
	
	print_r(json_encode($json_arr));
?>