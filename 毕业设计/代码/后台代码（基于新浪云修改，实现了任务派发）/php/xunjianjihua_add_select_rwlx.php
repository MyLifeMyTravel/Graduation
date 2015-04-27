<?php
		
	include("../php/connect.php");
	
	$result = mysql_query("select * from rwlx");
	
	while($row = mysql_fetch_array($result)) {
		$rwlx_id = $row['id'];
		$name = $row['name'];
		echo "<option value='$rwlx_id'>$name</option>";
	}
	mysql_close();
?>