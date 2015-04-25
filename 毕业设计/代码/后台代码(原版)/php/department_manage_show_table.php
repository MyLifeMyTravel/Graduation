<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from department");
	
	while($row = mysql_fetch_array($result)) {
		$dept_id = $row['dept_id'];
		$dept_name = $row['dept_name'];
		$dept_info = $row['dept_info'];
		$dept_manager = $row['dept_manager'];
		
		echo "<tr>";
        echo "<td>$dept_id</td><td>$dept_name</td><td>$dept_manager</td><td>$dept_info</td><td><a href='#'>编辑</a></td><td><a href='#'>删除</a></td>";
		echo "</tr>";
	}
	mysql_close($con);
?>