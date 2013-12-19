<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<center>
<table border="0"  style="text-align: left;font-family:'宋体';font-size:12px;">
	<tr>
		<td style="font-family:'微软雅黑';font-size:16px;">
			磁盘信息
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
	<s:iterator value="%{diskList}" id="list" status="objList">
		<!-- 
		<tr>
			<td>
				<div
					style="background-color: #000000;color:white;padding-top:6px;font-family:'宋体';font-size:12px;width:<s:text name="100-#list.useablePercent"></s:text>%; height: 24px;z-index=-1;float:left;">
					&nbsp;&nbsp;<s:text name='#list.diskName'></s:text>盘
				</div>
				<s:if test="#list.useableSize>0">
					<div
						style="background-color: green; width:<s:text name="#list.useablePercent"></s:text>%; color: #ffffff;height: 30px;z-index=-1;float:left;"></div>
				</s:if>
				<div style="z-index: 1;font-family:'宋体';font-size:12px;margin-top:5px;margin-bottom:10px;">
					<div style="width:270px;float:left;">磁盘信息：共
					<s:text name='#list.totalSpace'></s:text>
					G，
					<s:text name='#list.useableSize'></s:text>
					G可用。&nbsp;&nbsp;&nbsp;</div>
					<span style="cursor:pointer;color:blue;" onclick="javascript:backup('<s:text name="#list.diskName"></s:text>')">备份到<s:text name='#list.diskName'></s:text>盘</span>&nbsp;&nbsp;<span id="<s:text name='#list.diskName'></s:text>info"></span></div>
			</td>
		</tr>
		 -->
		 	<td style="width:230px;height:150px;">
		 		<img style="cursor:pointer;" onclick="javascript:backup('<s:text name="#list.diskName"></s:text>')" src='diskImage.html?useablePercent=<s:text name="#list.useablePercent"></s:text>&useableSize=<s:text name="#list.useableSize"></s:text>&_random=<%=Math.random() %>'>
		 	</td>
	</s:iterator>
	</tr>
	<tr>
	<s:iterator value="%{diskList}" id="list" status="objList">
		 	<td style="padding-left:10px;">
		 		<span style="font-family:'微软雅黑';font-size:18px;"><s:text name="#list.diskName"></s:text>盘</span>&nbsp;总容量：<s:text name="#list.totalSpace"></s:text>，空闲容量：<s:text name="#list.useableSize"></s:text>
		 	</td>
	</s:iterator>
	</tr>
	<tr>
	<s:iterator value="%{diskList}" id="list" status="objList">
		 	<td style="padding-left:40px;">
		 		&nbsp;<span id="<s:text name='#list.diskName'></s:text>info"></span>&nbsp;
		 	</td>
	</s:iterator>
	</tr>
	<tr>
		<td style="font-family:'宋体';font-size:12px;color:red;">数据库大小：${dataBaseSize }</td>
	</tr>
</table>
</center>