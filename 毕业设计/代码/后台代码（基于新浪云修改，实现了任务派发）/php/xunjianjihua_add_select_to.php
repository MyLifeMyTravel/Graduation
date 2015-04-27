<?php
		
	include("../php/connect.php");
	
	$result = mysql_query("select *from user where deptID=2");
	
	while($row = mysql_fetch_array($result)) {
		$account = $row['account'];
		$name = $row['name'];
		echo "<option value='$account'>$name</option>";
	}
	mysql_close();
?>