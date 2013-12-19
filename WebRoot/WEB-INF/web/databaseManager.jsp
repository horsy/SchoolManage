<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div>
	<center>
		<br>
		<br>
		<br>
		<div id="diskList" style="width: 90%; text-align: center">
			<div style="font-family:'微软雅黑';font-size:16px;">
				加载磁盘信息中.........
			</div>
		</div>
		<br>
		<br>
		<table width="90%" border="0" class="right_set_explanation">
			<tr>
				<td class="right_set_explanation_name">
					<b>数据备份：</b>
				</td>
				<td class="right_set_explanation_value">
					备份到指定磁盘根目录下的bk文件夹内。备份文件夹的名称为instructionYYYY-MM-dd-HH-mm-ss。其中YYYY-MM-dd-HH-mm-ss为备份时间（YYYY：年份，MM：月份，dd：日期，HH：小时，mm：分钟，ss：秒）。
				</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>数据还原：</b>
				</td>
				<td class="right_set_explanation_value">
					数据还原需采用手动方式。
				</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>还原步骤：</b>
				</td>
				<td class="right_set_explanation_value">
					1、进入服务器，停止指令服务器服务器端程序，并在服务里面停止mysql服务。
					<br />
					2、进入mysql
					目录，删除/剪切mysql\data目录下的instruction文件夹和ibdata1文件,将备份的instructionXXXX-XX-XX-XX-XX-XX文件夹下的文件拷贝到mysql\data目录下。
					<br />
					3、在服务里面启动mysql服务。
				</td>
			</tr>
		</table>
	</center>
</div>
<iframe id="backupFrame" name="backupFrame" src="" style="display: none">
</iframe>
<form name="backupForm" id="backupForm" action="goToBackup.html"
	method="post" target="backupFrame">
	<input type="hidden" name="path">
</form>
<script type="text/javascript">
<!--
$(document).ready(function(){
	getDiskInfo();
});
function dabaseManager_showBackuping(id,value){
	eval("document.getElementById('"+id+"').innerHTML='"+value+"'");
}
//-->
</script>