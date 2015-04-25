<?php
	/**
	* 获取特殊巡视要求
	*/
	
	$file = fopen("./file/tsxs.md","r");
	
	while(!feof($file)) {
		$line = fgets($file);
		echo $line;
	}
?>