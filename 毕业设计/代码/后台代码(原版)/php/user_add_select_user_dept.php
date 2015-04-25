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
		echo "<option value='$dept_id'>$dept_name</option>";
	}
	mysql_close($con);
?>