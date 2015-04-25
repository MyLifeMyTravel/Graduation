<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from device");
	
	while($row = mysql_fetch_array($result)) {
		$device_name = $row['device_name'];
		echo "<option value='$device_name'>$device_name</option>";
	}
	mysql_close($con);
?>