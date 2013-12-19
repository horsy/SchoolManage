<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<iframe name="saveDirectInfo" id="saveUploadAlarm" style="display: none"
	src=""></iframe>
<form name="setDirectInfo" id="setUploadAlarm"
	action="saveDirectInfo.html" method="post" target="saveDirectInfo">
	<p></p>
	<table class="textBodyFont">
		<tr>
			<td>
				IP地址：
			</td>
			<td>
				<input name="ip" type="text" value="${ip }">
			</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>
				web端口：
			</td>
			<td>
				<input name="webPort" type="text" value="${webPort }">
			</td>
		</tr>
		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td>
				跨级管理端口：
			</td>
			<td>
				<input name="socketPort" type="text" value="${socketPort }">
			</td>
		</tr>

		<tr>
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="保存">
			</td>
		</tr>
	</table>
	<input type="hidden" name="sipuri" value="${sipuri }" />
	<input type="hidden" name="equipmentId" value="${equipmentId }"/>
</form>
