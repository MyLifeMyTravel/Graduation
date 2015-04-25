<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from circut");
	
	while($row = mysql_fetch_array($result)) {
		$circut_id = $row['circut_id'];
		$circut_name = $row['circut_name'];
		echo "<option value='$circut_id'>$circut_name</option>";
	}
	mysql_close($con);
?>