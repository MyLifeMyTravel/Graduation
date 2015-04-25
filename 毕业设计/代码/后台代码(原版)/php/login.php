<?php
	/*替换为你自己的数据库名*/
	//$dbname = 'SJOPlmsFKSaYBExdtcLW';
	/*填入数据库连接信息*/
	//$host = 'sma.duapp.com';
	//$port = 4050;
	//$user = 'ZVHbaGSYGfo0dW5inCxRigq7';//用户名(api key)
	//$pwd = 'BHLQk686KvNfTu3UQcDxnGDBCTQc4LnE';//密码(secret key)
	 /*以上信息都可以在数据库详情页查找到*/
	 
	/*接着调用mysql_connect()连接服务器*/
	//$con = @mysql_connect("{$host}:{$port}",$user,$pwd,true);

	$userID = $_POST['userID'];
	$password = $_POST['pwd'];

	$con = @mysql_connect("localhost","root","1025263614");
	if( !$con ) {
		die("Could not connect:" . mysql_error());	
	}
	mysql_select_db("justdoit",$con);
	
	$result = mysql_query("select * from users where user_id = $userID");
	$row = mysql_fetch_array($result);
	
	if($row['user_pwd'] == $password and $row['user_type_id'] == 1) {
		header("Location:http://localhost/JustDoIt/index.html");
	}else {
		echo "<script>alert('对不起，你没有权限登录！')</script>";
		//header("Location:http://localhost/Lightning/Html/index.html");
		echo "<script>location.href='http://localhost/JustDoIt/login.html'</script>";
	}
?>