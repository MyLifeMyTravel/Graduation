<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from plan");
	
	while($row = mysql_fetch_array($result)) {
		$plan_id = $row['plan_id'];
		
		$circut_id = $row['circut_id'];
		$result_circut = mysql_query("select * from circut");
		$row_circut = mysql_fetch_array($result_circut);
		$circut_name = $row_circut['circut_name'];
		
		$plan_time = $row['plan_time'];
		
		$plan_user_id = $row['plan_user_id'];
		$result_user = mysql_query("select * from users where user_id = $plan_user_id ");
		$row_user = mysql_fetch_array($result_user);
		$plan_user_name = $row_user['user_name'];
		
		if($row['plan_state'] == 0){
			$plan_state = "未完成";
		}else {
			$plan_state = "完成";	
		}
		
		$plan_note = $row['plan_note'];
		
		echo "<tr>";
        echo "<td>$plan_id</td><td>$circut_name</td><td>$plan_time</td><td>$plan_user_name</td><td>$plan_state</td><td>$plan_note</td><td><a href='#'>编辑</a></td><td><a href='#'>删除</a></td>";
		echo "</tr>";
	}
	mysql_close($con);
?>