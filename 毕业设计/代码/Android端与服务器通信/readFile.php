<?php
	$url = $_GET['url'];
	$fileList = file("./guide/list.txt");
	$arr = array();
	foreach($fileList as $line => $content){
        $content = iconv('gb2312','utf-8',$content);
		echo $content;
    }
?>