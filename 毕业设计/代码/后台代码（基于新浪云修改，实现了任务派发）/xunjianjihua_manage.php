<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理巡检计划</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：管理巡检计划</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <span class="box"><img src="images/add.png" /></span>
                        <a href="xunjianjihu_add.php" target="main">添加巡检计划</a>
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="xunjianjihuamanage_table">
						<table>
                        <tr>
                        <th>巡检任务编号</th><th>巡检线路</th><th>巡检时间</th><th>巡检人员</th><th>完成状况</th>
                        </tr>
                        <?php include('php/xunjianjihua_manager_show_table.php'); ?>
                        </table> 
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
