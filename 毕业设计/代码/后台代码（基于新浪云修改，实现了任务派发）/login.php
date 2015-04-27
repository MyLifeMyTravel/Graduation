<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电力设备智能巡检系统</title>
<link type="text/css" rel="stylesheet" href="style/style.css" />
</head>

<body>
	<div class="main_content">
        <div class="top">
            <img src="images/login.jpg" />
        </div>
        
        
        <div class="content">
        	<form class="denglu" action="php/login.php" method="post">
            	<label for="user">用户名：</label>
                <input type="text" name="account"/><br /><br />
                
            	<label for="psd">密&nbsp;&nbsp;码：</label>
                <input type="password" name="password"/><br /><br />
                
                <tr>
                <td><label for="checkword">验证码：</label></td>
                <td><input type="text" name="checkword" style="width:50px"/>
                <span><img src="http://jwgl.nbu.edu.cn:7777/nbdx/verifycode.servlet"
					onclick="ReShowCode()" align="middle" /></span></td>
                </tr><br /><br /><br />
                
                <input  type="submit" value="" style="background:url(images/button.jpg) no-repeat; width:79px; height:26px; margin-left:50px; border:none;" />
            </form>
        </div><!--content结束-->
   </div><!--main_content结束-->
</body>
</html>
