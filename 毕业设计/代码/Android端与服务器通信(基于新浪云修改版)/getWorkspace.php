<?php
	include("connect.php");
	
	$site_id = $_GET['site_id'];
	
	$sql = "select * from workspace where workspace.site_id=$site_id";
	$result = mysql_query($sql,$conn);
	
	$json = array();
	while($row = mysqli_fetch_array($result)) {
		$_id = $row['_id'];
		$alias = $row['place_alias'];
		$arr = array('id'=>$_id,'name'=>$alias);
		array_push($json,$arr);
	}
	print_r(json_encode($json));
?>