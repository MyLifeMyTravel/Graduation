<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from location");
	
	while($row = mysql_fetch_array($result)) {
		$loc_name = $row['loc_name'];
		$loc_id = $row['loc_id'];
		echo "<option value='$loc_id'>$loc_name</option>";
	}
	mysql_close($con);
?>