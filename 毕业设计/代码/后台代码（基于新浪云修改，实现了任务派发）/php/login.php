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
	
	include("../php/connect.php");
	
	$account = $_POST['account'];
	$password = $_POST['password'];
	
	$result = mysql_query("select * from user where account='$account' and $password='$password'");
	$row = mysql_fetch_array($result);
	
	if(mysql_num_rows($result) != 0) {
		header("Location:http://1.wodebiyesheji.sinaapp.com/backstage/index.html");
	}else {
		echo "<script>alert('对不起，你没有权限登录！')</script>";
		//header("Location:Location:http://1.wodebiyesheji.sinaapp.com/backstage/index.html");
		echo "<script>location.href='Location:http://1.wodebiyesheji.sinaapp.com/backstage/index.html'</script>";
	}
?>