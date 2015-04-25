<?php
	include("connect.php");
	
	$site_id = $_GET['site_id'];
	
	$sql = "select * from place where place.site_id=$site_id";
	$result = mysqli_query($conn,$sql);
	
	$json = array();
	while($row = mysqli_fetch_array($result)) {
		$id = $row['id'];
		$identifier = $row['identifier'];
		$cdlx_id = $row['cdlx_id'];
		$site_id = $row['site_id'];
		$name = $row['name'];
		$arr = array('id'=>$id,'identifier'=>$identifier,'cdlx_id'=>$cdlx_id,'site_id'=>$site_id,'name'=>$name);
		array_push($json,$arr);
	}
	print_r(json_encode($json));
?>