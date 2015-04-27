<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from device");
	
	while($row = mysql_fetch_array($result)) {
		$device_id = $row['device_id'];
		$device_name = $row['device_name'];
		$device_type = $row['device_type'];
		$device_loc_id = $row['device_loc_id'];
		
		$result_location = mysql_query("select * from location where loc_id = $device_loc_id");
		$row_location = mysql_fetch_array($result_location);
		
		$device_loc_name = $row_location['loc_name'];
		
		echo "<tr>";
        echo "<td>$device_id</td><td>$device_name</td><td>$device_type</td><td>$device_loc_name</td><td><a href='#'>编辑</a></td><td><a href='#'>删除</a></td>";
		echo "</tr>";
	}
	mysql_close($con);
?>