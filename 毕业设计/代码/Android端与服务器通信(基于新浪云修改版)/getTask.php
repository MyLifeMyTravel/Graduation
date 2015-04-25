<?php
	/**
	* 获取巡检任务，只查询flag=‘unfinished’的行
	*/
	include("connect.php");
	//设置默认时区
	date_default_timezone_set('prc');
	
	$account = $_GET['account'];
	$date = date("Y-m-d",time());
	//echo $date;
	$sql = "select *,rwlx.name as rname,site.name as sname from task,rwlx,site where task.to='$account' and task.date='$date' and task.rwlx_id=rwlx.id and task.site_id=site.id and task.flag='unfinished'";
	//echo $sql;
	$result = mysql_query($sql,$conn);
	
	$json = array();
	while($row = mysql_fetch_array($result)) {
		$id = $row['id'];
		$rwlx = $row['rname'];
		//echo $rwlx;
		$description = $row['description'];
		//echo $rwlx_description;
		$date = $row['date'];
		$time = $row['time'];
		$flag = $row['flag'];
		//站点信息
		$site_id = $row['site_id'];
		$site_name = $row['sname'];
		$site_identifier = $row['identifier'];
		$site_loc = $row['loc'];
		$site_province = $row['province'];
		$site_city = $row['city'];
		$site_area = $row['area'];
		$site_street = $row['street'];
		$arr = array('id'=>$id,'rwlx'=>$rwlx,'description'=>$description,'date'=>$date,'time'=>$time,'site_id'=>$site_id,'site_name'=>$site_name);
		$arr = $arr + array('site'=>array('id'=>$site_id,'identifier'=>$site_identifier,'name'=>$site_name,'loc'=>$site_loc,'province'=>$site_province,'city'=>$site_city,'area'=>$site_area,'street'=>$site_street));
		array_push($json,$arr);
	}
	print_r(json_encode($json));
?>