<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加用户</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：添加用户</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <span class="box"><img src="images/add.png" /></span>
                        <a href="user_add.php" target="main">添加用户</a>
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="useradd_form">
                    	<form action="php/user_add_submit.php" method="post">
                        	<label for="user_id">用户编号：</label>
                            <input type="text" name="user_id"/><br  /><br  />
                            
                            <label for="user_name">用户名：</label>
                            <input type="text" name="user_name"/><br  /><br  />
                            
                            <label for="user_pwd">密&nbsp;&nbsp;码：</label>
                            <input type="text" name="user_pwd"/><br  /><br  />
                            
                        	<label for="user_type">用户类型：</label>
                            <select name="user_type_id">
                            <option value="0">请选择用户类型</option>
                            <option value="1">普通权限</option>
                            <option value="2">管理员</option>
                            </select><br  /><br  />
                            
                        	<label for="user_permission">用户权限：</label>
                            <select name="user_permission">
                            <option value="0">请设置用户权限</option>
                            <option value="1">普通权限</option>
                            <option value="2">管理员</option>
                            </select><br  /><br  />
                            
                            <label for="user_dept">用户部门：</label>
                            <select name="user_dept_id">
                            <option value="0">请选择用户部门</option>
                            <?php include('php/user_add_select_user_dept.php'); ?>
                            </select><br  /><br  />
                            
                            <label for="user_info">用户简介：</label>
                            <textarea cols="40" rows="7" name="user_info"></textarea><br  /><br  />
                            
                            <input type="submit" style="margin-left:60px; cursor:pointer;" value="提交" />
                        </form>
                    </div>
                </div><!--right结束-->
                
    
<!--刷新页面js代码-->
<script type="text/javascript"> 
 $('#PageRefresh').click(function() { 
           window.location.reload();
 }); 
</script>                
</body>
</html>
