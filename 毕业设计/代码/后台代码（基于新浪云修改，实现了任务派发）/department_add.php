<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加部门</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：添加部门</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <span class="box"><img src="images/add.png" /></span>
                        <a href="department_add.php" target="main">添加部门</a>
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="useradd_form">
                    	<form action="php/department_add_submit.php" method="post">
                        	<label for="dept_name">部门名称：</label>
                            <input type="text" name="dept_name" /><br  /><br  />
                            
                            <label for="dept_manager">部门主管：</label>
                            <input type="text" name="dept_manager"/><br  /><br  />
                            
                            <label for="dept_info">部门简介：</label>
                            <textarea name="dept_info" cols="40" rows="7"></textarea><br  /><br  />
                            
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
