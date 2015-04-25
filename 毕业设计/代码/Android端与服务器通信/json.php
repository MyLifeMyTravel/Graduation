<?php
	$arr = array('a' => 1, 'b' => 2, 'c' => 3);
	$arr1 = array('d' => 1, 'e' => 2, 'c' => $arr);
	$arr2 = array('d' => 1, 'e' => 2, 'c' => "'a' => 1, 'b' => 2, 'c' => 3");
	echo json_encode($arr1);
	echo json_encode($arr2);
?>