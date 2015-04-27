<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>电力设备智能巡检系统</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
</head>

<body>
	<div class="I_content">
    	<div class="I_top">
        	<div class="top_content">
        		<img src="images/H_logo.png" />
                <a class="top_a1" href="#"><img src="images/H_shouye.jpg" /></a>
                <a class="top_a2" id="PageRefresh" style="cursor:pointer;"><img src="images/H_shuaxin.png" /></a>
                <a class="top_a3" href="login.php"><img src="images/H_tuichu.png" /></a>
            </div><!--top_content-->
        </div><!--I_top结束-->
        <div class="I_main">
        	<div class="left">
				<div class="left_01">
                	<img class="zhedie" id="zhedie_04" src="images/H_xunjianguanli.png" />
                    <ul class="I_ul" id="ul_04">
                    	<li><a href="xunjianjihu_add.php" target="main">添加巡检计划</a></li>
                    	<li><a href="xunjianjihua_manage.php" target="main">管理巡检计划</a></li>
                    	<li><a href="xunjianjieguo_add.php" target="main">巡检结果录入</a></li>
                    	<li><a href="xunjianjieguo_manage.php" target="main">管理巡检结果</a></li>
                    </ul>
                </div><!--left_01结束-->
				
            	<div class="left_01">
                	<img class="zhedie" id="zhedie_02" src="images/H_xianluguanli.png" />
                    <ul class="I_ul" id="ul_02">
                    	<li><a href="xianlu_add.php" target="main">添加线路</a></li>
                    	<li><a href="xianlu_manage.php" target="main">管理线路</a></li>
                    </ul>
                </div><!--left_01结束-->
                
            	<div class="left_01">
                	<img class="zhedie" id="zhedie_03" src="images/H_biandiansuoguanli.png" />
                    <ul class="I_ul" id="ul_03">
                    	<li><a href="biandiansuo_add.php" target="main">添加变电所</a></li>
                    	<li><a href="biandiansuo_manage.php" target="main">管理变电所</a></li>
                    </ul>
                </div><!--left_01结束-->
                
            	<div class="left_01">
                	<img class="zhedie" id="zhedie_01" src="images/shebeiguanli.png" />
                    <ul class="I_ul" id="ul_01">
                    	<li><a href="shebei_add.php" target="main">添加设备</a></li>
                    	<li><a href="shebei_manage.php" target="main">管理设备</a></li>
                    </ul>
                </div><!--left_01结束-->
                
            	
            	<div class="left_01">
                	<img class="zhedie" id="zhedie_05" src="images/H_tongjifenxi.png" />
                    <ul class="I_ul" id="ul_05">
                    	<li><a href="tongjifenxi.php" target="main">数据表格</a></li>
                        <li><a href="tongjifenxi_zzt.php" target="main">柱状图</a></li>
                        <li><a href="tongjifenxi_qxt.php" target="main">曲线图</a></li>
                    </ul>
                </div><!--left_01结束-->
            	<div class="left_01">
                	<img class="zhedie" id="zhedie_06" src="images/xitongguanli.png"/>
                    <ul class="I_ul" id="ul_06">
                    	<li><a href="user_add.php" target="main">添加用户</a></li>
                    	<li><a href="user_manage.php" target="main">管理用户</a></li>
                        <li><a href="department_add.php" target="main">添加部门</a></li>
                        <li><a href="department_manage.php" target="main">管理部门</a></li>
                    </ul>
                </div><!--left_01结束-->
            </div><!--left结束-->
            <iframe src="shouye.php" scrolling="no" frameborder="0"; name="main">
               
            </iframe>
            <div class="footer">
                <p>版权所有：厉圣杰</p>
            </div><!--footer结束-->
        
        </div><!--I_main结束-->
    
    </div><!--I_content结束-->
    
    
<!--刷新页面js代码-->
<script type="text/javascript"> 
 $('#PageRefresh').click(function() { 
           window.location.reload();
 }); 
</script>


<!--折叠标签js代码-->
<script type="text/javascript"> 
$(document).ready(function(){
$("#zhedie_01").click(function(){
    $("#ul_01").slideToggle("2000");
  });
$("#zhedie_02").click(function(){
    $("#ul_02").slideToggle("2000");
  });
$("#zhedie_03").click(function(){
    $("#ul_03").slideToggle("slow");
  });
$("#zhedie_04").click(function(){
    $("#ul_04").slideToggle("slow");
  });
$("#zhedie_05").click(function(){
    $("#ul_05").slideToggle("slow");
  });
$("#zhedie_06").click(function(){
    $("#ul_06").slideToggle("slow");
  });
});
</script>
</body>
</html>
