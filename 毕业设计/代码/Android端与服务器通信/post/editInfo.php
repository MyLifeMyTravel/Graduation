<?php
	include("../connect.php");
	
	$accountKey = "account";
	$array = array_keys($_POST);
	$account = $array[0];
	$keyValue = $array[1];
	
    foreach($_POST as $key=>$value) {
		if($key==$accountKey) {
			$account = $_POST[$key];
		}else {
			$keyValue = $_POST[$key];
			$keyName = $key;
		}
	}
	//echo "key:".$keyName." value:".$keyValue;
	$sql = "update user set $keyName='$keyValue' where user.account=$account";
	//echo $sql;
	mysqli_query($conn,$sql) or die("错误：".mysqli_error($conn));
	if(mysqli_affected_rows($conn)) {
		echo "failed:"+mysqli_error($conn);
	}else {
		echo "succeed";
	}
	mysqli_close($conn);
?>
