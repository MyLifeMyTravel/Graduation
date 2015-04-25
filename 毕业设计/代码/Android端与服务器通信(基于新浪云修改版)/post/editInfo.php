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
	mysql_query($sql，$conn) or die("错误：".mysql_error($conn));
	if(mysql_affected_rows($conn)) {
		echo "failed:"+mysql_error($conn);
	}else {
		echo "succeed";
	}
	mysql_close($conn);
?>
