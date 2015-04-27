<?php
		
	include("../php/connect.php");
	
	$result = mysql_query("select *from site");
	
	while($row = mysql_fetch_array($result)) {
		$site_id = $row['id'];
		$site_name = $row['name'];
		echo "<option value='$site_id'>$site_name</option>";
	}
	mysql_close();
?>