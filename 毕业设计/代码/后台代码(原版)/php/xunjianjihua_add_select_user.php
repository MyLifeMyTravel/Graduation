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
		echo "<option value='$user_id'>$user_name</option>";
	}
	mysql_close($con);
?>