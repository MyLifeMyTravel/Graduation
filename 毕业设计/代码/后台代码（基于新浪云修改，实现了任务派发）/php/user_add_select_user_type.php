<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from users_type");
	
	while($row = mysql_fetch_array($result)) {
		$type = $row['type'];
		$type_id = $row['type_id'];
		echo "<option value='$type_id'>$type</option>";
	}
	mysql_close($con);
?>