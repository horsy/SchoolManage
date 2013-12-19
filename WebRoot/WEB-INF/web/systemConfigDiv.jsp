<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<div id='systemConfigDiv' class='divSystemSet'>
	<!-- 
		<ul id='systemConfigTree' class="systemConfigUl" >
			<li class="systemConfigLi" onclick="javascript:systemSet('schoolInfo')">学校信息</li>
			<li class="systemConfigLi" onclick="javascript:systemSet('teacherLevel')">教师职位</li>
			<li class="systemConfigLi" onclick="javascript:systemSet('nationList')">民族列表</li>
			<li class="systemConfigLi" onclick="javascript:systemSet('bloodGroup')">血型列表</li>
			<li class="systemConfigLi" onclick="javascript:systemSet('politicalOrientation')">政治面貌</li>
			<li class="systemConfigLi" onclick="javascript:systemSet('studentStatus')">学生状态</li>
		</ul>
	 -->
		<table class="systemConfigTable">
			<tr>
				<td class="systemConfigTdSelected" id="schoolInfo" onclick="javascript:systemSet('schoolInfo')">
					学校信息
				</td>
			</tr>
			<tr>
				<td class="systemConfigTd" id="teacherLevel" onclick="javascript:systemSet('teacherLevel')">
					教师职位
				</td>
			</tr>
			<tr>
				<td class="systemConfigTd" id="nationList" onclick="javascript:systemSet('nationList')">
					民族列表
				</td>
			</tr>
			<tr>
				<td class="systemConfigTd" id="bloodGroup" onclick="javascript:systemSet('bloodGroup')">
					血型列表
				</td>
			</tr>
			<tr>
				<td class="systemConfigTd" id="politicalOrientation" onclick="javascript:systemSet('politicalOrientation')">
					政治面貌
				</td>
			</tr>
			<tr>
				<td class="systemConfigTd" id="studentStatus" onclick="javascript:systemSet('studentStatus')">
					学生状态
				</td>
			</tr>
		</table>
	</div>
	<div id='systemConfigShow' class='divShow'></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var showBodyHeight=getShowBobyHeight();
		document.getElementById("systemConfigDiv").style.height=showBodyHeight+80+"px";
		document.getElementById("systemConfigShow").style.height=showBodyHeight+80+"px";
		systemSet('schoolInfo');
	});
</script>