<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="overflow-y: auto;">
	<div>
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<center>
			<table style="margin-top: 30px; width: 75%;" id="teacherLevelTable">
				<caption class="captionStyle">
					教师职位
				</caption>
				<tr>
					<td colspan="3" class="tdList">
						<s:iterator value="%{teacherTypeList}" status="objList">
							<input type="checkBox" name='obj<s:property value="id" />'
								id='obj<s:property value="id" />' />
							<s:property value="typeName" />&nbsp;
						</s:iterator>
					</td>
				</tr>
			</table>
			<table style="width:75%;">
				<tr>
					<td>
						&nbsp;
					</td>
					<td class="add show_user_list_bottom_height"
						onclick="javascript:teacherLevel_add()"
						onMouseOut="this.className='add show_user_list_bottom_height'"
						onMouseDown="this.className='add_move_in show_user_list_bottom_height'">
					</td>
					<td class="del show_user_list_bottom_height"
						onclick="javascript:teacherLevel_del('teacherLevelTable')"
						onMouseOut="this.className='del show_user_list_bottom_height'"
						onMouseOver="this.className='del_move_in show_user_list_bottom_height'">
					</td>
				</tr>
			</table>
		</center>
	</div>
</div>