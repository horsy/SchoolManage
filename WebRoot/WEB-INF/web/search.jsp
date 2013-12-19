<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="overflow-y: auto;">
	<center>
		<table>
			<tr>
				<td>
					<select>
						<option>姓名</option>
					</select>
				</td>
				<td>
					<input type="text" id="name" name="name">
				</td>
				<td>
					<input type="button" value="查询" onclick="javascript:search()" >
				</td>
			</tr>
		</table>
	</center>
</div>
<div style="overflow-y: auto;" id="schoolShow">
	
</div>