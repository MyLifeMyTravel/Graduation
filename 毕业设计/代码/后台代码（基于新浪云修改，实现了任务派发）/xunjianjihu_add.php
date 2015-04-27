<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加巡检计划</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：添加巡检计划</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <span class="box"><img src="images/add.png" /></span>
                        <a href="xunjianjihu_add.php" target="main">添加巡检计划</a>
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="shebeiadd_form">
                    	<form action="php/xunjianjihua_add_select_circut_submit.php" method="post">
							<label for="xunjianjihua">任务类型：</label>
                            <select name="rwlx_id">
                            <option value="0">请选择任务类型</option>
                            <?php include('php/xunjianjihua_add_select_rwlx.php'); ?>
                            </select>
                            <br  /><br  />
							
							<label for="xunjianjihua">巡检人员：</label>
                            <select name="to">
                            <option value="0">请选择巡检人员</option>
                            <?php include('php/xunjianjihua_add_select_to.php'); ?>
                            </select>
                            <br  /><br  />
							
                        	<label for="xunjianjihua">巡检站点：</label>
                            <select name="site_id">
                            <option value="0">请选择巡检站点</option>
                            <?php include('php/xunjianjihua_add_select_site.php'); ?>
                            </select>
                            <br  /><br  />
                            
                        	<label for="shebei">巡检日期：</label>
                            <input type="text" name="plan_date" />
							<label for="shebei">(2008-01-01)</label>
							<br  /><br  />
                            
							<label for="shebei">巡检时间：</label>
                            <input type="text" name="plan_time" />
							<label for="shebei">(00:00)</label>
							<br  /><br  />
                            
                            <input type="submit" style="margin-left:85px; cursor:pointer;" value="提交" />
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
