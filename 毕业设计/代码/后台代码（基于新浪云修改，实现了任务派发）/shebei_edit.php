<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加设备</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：添加设备</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <span class="box"><img src="images/add.png" /></span>
                        <a href="shebei_add.php" target="main">添加设备</a>
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="shebeiadd_form">
                    	<form action="php/shebei_add_submit.php" method="post">
                        	<label for="shebei">设备编号：</label>
                            <input type="text" name="device_id"/><br  /><br  />
                            
                        	<label for="shebei">设备名称：</label>
                            <select name="select_device_name">
                            <option value="0">请选择设备名称</option>
                            <?php include('php/shebei_add_select_device_name.php'); ?>
                            </select>
                            <br  /><br  />
                            
                            <label for="shebei">设备类型：</label>
                            <select name="select_device_type">
                            <option value="0">请选择设备类型</option>
                            <?php include('php/shebei_add_select_device_name.php'); ?>
                            </select>
                            <br  /><br  />
                            
                            <!-- 设置为下拉框 -->
                            <label for="shebei">设备位置：</label>
                            <select name="select_loc_name">
                            <option value="0">请选择设备所在电力站</option>
                            <?php include('php/shebei_add_select_loc_name.php'); ?>
                            </select>
                            <br  /><br  />
                            
                            <label for="shebei">参&nbsp;&nbsp;&nbsp;&nbsp;数：</label>
                            <input type="text" name="device_parameter"/><br  /><br  />
                            
                            <input type="submit" style="margin-left:70px; cursor:pointer;" value="提交" />
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
