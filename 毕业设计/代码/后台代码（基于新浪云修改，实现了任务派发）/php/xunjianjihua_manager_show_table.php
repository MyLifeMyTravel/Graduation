<?php
	include("../php/connect.php");
	
	$sql = "select *from task";
	$result = mysql_query($sql,$conn);

	while($row = mysql_fetch_array($result)) {
		$task_id = $row['id'];
		
		$site_id = $row['site_id'];
		$time = $row['date']." ".$row['time'];
		$task_to = $row['task_to'];
		$flag = $row['flag'];
		
		echo "<tr>";
        echo "<td>$task_id</td><td>$site_id</td><td>$time</td><td>$task_to</td><td>$flag</td>";
		echo "</tr>";
	}
	mysql_close($con);
?>