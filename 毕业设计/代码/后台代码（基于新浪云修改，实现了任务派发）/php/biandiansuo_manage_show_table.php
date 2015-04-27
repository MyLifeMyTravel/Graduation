<?php
	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from location");
	
	while($row = mysql_fetch_array($result)) {
		$loc_num = $row['loc_num'];
		$loc_name = $row['loc_name'];
		$loc_province = $row['loc_province'];
		$loc_city = $row['loc_city'];
		$loc_area = $row['loc_area'];
		$loc = $row['loc'];
		
		echo "<tr>";
        echo "<td>$loc_num</td><td>$loc_name</td><td>$loc_province.$loc_city.$loc_area.$loc</td><td><a href='#'>编辑</a></td><td><a href='#'>删除</a></td>";
		echo "</tr>";
	}
	mysql_close($con);
?>