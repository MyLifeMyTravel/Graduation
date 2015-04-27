<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加线路</title>
<link type="text/css" rel="stylesheet" href="style/houtai.css" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="js/jsAddress.js"></script>
</head>

<body>
 				<div class="right">
                    <div class="right_01">
                        <p>当前操作：添加线路</p>
                    </div>
                    <div class="right_shebeiadd_1">
                        <span class="box"><img src="images/add.png" /></span>
                        <a href="xianlu_add.php" target="main">添加线路</a>
                        <span class="box"><img src="images/shuaxin.png" /></span>
                        <a id="PageRefresh" style="cursor:pointer;">刷新</a>
                    </div>
                    <div class="xianluadd_form">
                    	<form action="php/xianlu_add_submit.php" method="post">
                        	<label for="xianlu">线路名称：</label>
                            <input type="text" /><br  /><br  />
                            
                        	<label for="xianlu_p1">线路位置1：</label>                                                            
                            <select id="cmbProvince"></select>
                            <select id="cmbCity"></select>
                            <select id="cmbArea"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                            
                            
                        	<label for="xianlu_p1">线路位置2：</label>                                                            
                            <select id="Select2_1"></select>
                            <select id="Select2_2"></select>
                            <select id="Select2_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                            
                            
                        	<label for="xianlu_p1">线路位置3：</label>                                                             
                            <select id="Select3_1"></select>
                            <select id="Select3_2"></select>
                            <select id="Select3_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                            
                            
                        	<label for="xianlu_p1">线路位置4：</label>                                                            
                            <select id="Select4_1"></select>
                            <select id="Select4_2"></select>
                            <select id="Select4_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                           
                            
                        	<label for="xianlu_p1">线路位置5：</label>                                                            
                            <select id="Select5_1"></select>
                            <select id="Select5_2"></select>
                            <select id="Select5_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                            
                            
                        	<label for="xianlu_p1">线路位置6：</label>                                                            
                            <select id="Select6_1"></select>
                            <select id="Select6_2"></select>
                            <select id="Select6_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                            
                            
                        	<label for="xianlu_p1">线路位置7：</label>                                                            
                            <select id="Select7_1"></select>
                            <select id="Select7_2"></select>
                            <select id="Select7_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                          
                            
                        	<label for="xianlu_p1">线路位置8：</label>                                                            
                            <select id="Select8_1"></select>
                            <select id="Select8_2"></select>
                            <select id="Select8_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                            
                            
                        	<label for="xianlu_p1">线路位置9：</label>                                                            
                            <select id="Select9_1"></select>
                            <select id="Select9_2"></select>
                            <select id="Select9_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  />                           
                            
                        	<label for="xianlu_p1">线路位置10：</label>                                                            
                            <select id="Select10_1"></select>
                            <select id="Select10_2"></select>
                            <select id="Select10_3"></select>
                            <input type="text" focucmsg="请输入具体位置" /><br  /><br  /> 
                            
                            
                            <input type="submit" style="margin-left:80px; cursor:pointer;" value="提交" />
                        </form>
                    </div>
                </div><!--right结束-->
                
    
<!--刷新页面js代码-->
<script type="text/javascript"> 
 $('#PageRefresh').click(function() { 
           window.location.reload();
 }); 
</script>   

<!--选择省市js代码-->
<script type="text/javascript">
	addressInit('cmbProvince', 'cmbCity', 'cmbArea', '浙江', '杭州', '上城区');
	addressInit('Select2_1', 'Select2_2', 'Select2_3', '浙江', '杭州', '上城区');
	addressInit('Select3_1', 'Select3_2', 'Select3_3', '浙江', '杭州', '上城区');
	addressInit('Select4_1', 'Select4_2', 'Select4_3', '浙江', '杭州', '上城区');
	addressInit('Select5_1', 'Select5_2', 'Select5_3', '浙江', '杭州', '上城区');
	addressInit('Select6_1', 'Select6_2', 'Select6_3', '浙江', '杭州', '上城区');
	addressInit('Select7_1', 'Select7_2', 'Select7_3', '浙江', '杭州', '上城区');
	addressInit('Select8_1', 'Select8_2', 'Select8_3', '浙江', '杭州', '上城区');
	addressInit('Select9_1', 'Select9_2', 'Select9_3', '浙江', '杭州', '上城区');
	addressInit('Select10_1', 'Select10_2', 'Select10_3', '浙江', '杭州', '上城区');
</script>

<!--获得焦点text文本框文字消失js代码-->
<script>
$(document).ready(function(){
$("TEXTAREA,input[focucmsg]") .each (function(){
$(this).val($(this).attr("focucmsg"));
$(this).focus(function(){
if($(this).val() == $(this).attr("focucmsg"))
{
$(this).val('');
}
});
$(this).blur(function(){
if(!$(this).val()){
$(this).val($(this).attr("focucmsg"));
}
});
});
});
</script>        
</body>
</html>
