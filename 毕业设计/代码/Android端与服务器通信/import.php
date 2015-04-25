<?php
	/**
	* 导入场地类型、设备类型、巡视内容、巡视标准
	*/
	
	/* ------------------ 函数---------------------*/
	function startWith($haystack, $needle) {
		return (substr($haystack, 0, strlen($needle))==$needle);
	}
	
	include("connect.php");
	
	$file = fopen("./file/cdlx.md","r");
	$cdlx_id = 1;
	$sblx_id = 1;
	$xsnr_id = 1;
	$xsbz_id = 1;
	$length;
	while(!feof($file)) {
		$line = fgets($file);
		if(startWith($line,"##")) {
			$length = strlen("##");
			$cdlx = mb_substr($line,$length);
			echo $cdlx;
			$sql = "insert into cdlx values($cdlx_id,'$cdlx')";
			mysqli_query($conn,$sql) or die("错误：".mysqli_error($conn));
			$cdlx_id++;
		}else if(startWith($line,"- ")) {
			$length = strlen("- ");
			$sblx = mb_substr($line,$length);
			echo $sblx;
			$sql = "insert into sblx values($sblx_id,$cdlx_id-1,'$sblx','')";
			mysqli_query($conn,$sql) or die("错误：".mysqli_error($conn));
			$sblx_id++;
		}else if(startWith($line,"\t- ")) {
			$length = strlen("\t- ");
			$xsnr = mb_substr($line,$length);
			echo $xsnr;
			$sql = "insert into xsnr values($xsnr_id,$sblx_id-1,'$xsnr')";
			mysqli_query($conn,$sql) or die("错误：".mysqli_error($conn));
			$xsnr_id++;
		}else if(startWith($line,"\t\t- ")) {
			$length = strlen("\t\t- ");
			$xsbz = mb_substr($line,$length);
			echo $xsbz;
			$sql = "insert into xsbz values($xsbz_id,$sblx_id-1,$xsnr_id-1,'$xsbz')";
			mysqli_query($conn,$sql) or die("错误：".mysqli_error($conn));
			$xsbz_id++;
		}
		//echo $line."<br />";
	}
	fclose($file)
	
	
?>