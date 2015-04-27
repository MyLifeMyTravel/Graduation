<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>统计分析</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：统计分析</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <!--<span class="box"><img src="images/add.png" /></span>
                        <a href="xunjianjihu_add.php" target="main">添加巡检计划</a>-->
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="shebeimanage_table">
						<table>
                        <tr>
                        <th>设备类型</th><th>故障率</th><th>最常见故障</th>
                        </tr>
                        <tr>
                        <td>主变压器</td><td>10%</td><td>电气连接件破损</td>
                        </tr>
                        <tr>
                        <td>互感器</td><td>4%</td><td>漏油</td>
                        </tr>
                        <tr>
                        <td>真空断路器</td><td>7%</td><td>音响破损</td>
                        </tr>
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
