<?php
	$file_path = "../img/user/";
     
    $file_path = $file_path . basename( $_FILES['capture']['name']);
    if(move_uploaded_file($_FILES['capture']['tmp_name'], $file_path)) {
        echo "succeed";
    } else{
        echo "fail";
    }
?>
