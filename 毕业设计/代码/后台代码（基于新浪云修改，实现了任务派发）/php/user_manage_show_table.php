<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from users");
	
	while($row = mysql_fetch_array($result)) {
		$user_id = $row['user_id'];
		$user_name = $row['user_name'];
		$user_type_id = $row['user_type_id'];
		$user_dept_id = $row['user_dept_id'];
		$user_permission = $row['user_permission'];
		
		//用户类型
		$result_user_type = mysql_query("select * from users_type where type_id = $user_type_id");
		$row_user_type = mysql_fetch_array($result_user_type);
		$user_type = $row_user_type['type'];
		
		//用户所在部门
		$result_dept = mysql_query("select * from department where dept_id = $user_dept_id");
		$row_dept = mysql_fetch_array($result_dept);
		$user_dept = $row_dept['dept_name'];
		
		echo "<tr>";
        echo "<td>$user_id</td><td>$user_name</td><td>$user_type</td><td>$user_dept</td><td><a href='#'>编辑</a></td><td><a href='#'>删除</a></td>";
		echo "</tr>";
	}
	mysql_close($con);
?>