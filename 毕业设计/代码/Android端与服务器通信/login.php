<?php 
	/**
	* 输出数据格式
	* {"flag":"succeed","user":{"name":"\u5389\u5723\u6770","pic":null,"info":null}}
	*/
	include("connect.php");
	
	//获取客户端传来的参数
	$account = $_GET["account"];
	$password = $_GET["password"];
	$alias = "user.name as name,dept.name as deptName,position.name as pName,user.describe as describe,dept.describe as deptDescribe,position.describe as pDescribe";
	
	$sql = "select * from user,dept,position where account=$account and password=$password and user.deptID=dept.id and user.positionID=position.id";
	//echo $sql;
	$result = mysqli_query($conn,$sql);
	
	if($result->num_rows == 1) {
		$arr = array("flag"=>"succeed");
		while($row = mysqli_fetch_array($result)) {
			$id = $row['id'];
			$account = $row['account'];
			$name = $row['name'];
			$pic = $row['pic'];
			$description = $row['description'];
			$phone = $row['phone'];
			$email = $row['email'];
			$dept = $row['dName'];
			$position = $row['pName'];
			$joinTime = $row['joinTime'];
			$device_id = $row['device_id'];
			$arr = $arr + array('user'=>array('id'=>$id,'account'=>$account,'name'=>$name,'pic'=>$pic,'description'=>$description,'phone'=>$phone,'email'=>$email,'dept'=>$dept,'position'=>$position,'joinTime'=>$joinTime,'deviceId'=>$device_id));
		}
	}else {
		$arr = array('flag'=>"failed");
	}
	print_r(json_encode($arr));
?> 