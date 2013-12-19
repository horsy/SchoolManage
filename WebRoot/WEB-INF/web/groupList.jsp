<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="overflow-y: auto;">
	<div >
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<table class="show_user_list_table" id="gradeListTable">
			<tr class="show_table_head_tr">
				<td>
					&nbsp;
				</td>
				<td class="show_table_head_line">
					编号
				</td>
				<td class="show_table_head_line">
					名称
				</td>
				<td class="show_table_head_line">
					类型
				</td>
			</tr>
			<s:iterator value="%{returnList}" status="objList">
				<tr
					class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>"
					onmouseover='this.className="moveIn";'
					onmouseout='this.className="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>";'>
					<td class=TdBody_checkBox>
						<input type="checkbox"
							name="obj<s:property value="id" />"
							id="obj<s:property value="id" />" onclick="document.getElementById('selectAllDiv').className='select_all';">
					</td>
					<td
						onclick="javascript:groupInfoList_editGroup(<s:property value="id" />,'${groupType.name}信息')">
						<s:property value="id" />
					</td>
					<td
						onclick="javascript:groupInfoList_editGroup(<s:property value="id" />,'${groupType.name}信息')">
						<s:property value="name" />
						
					</td>
					<td
						onclick="javascript:groupInfoList_editGroup(<s:property value="id" />,'${groupType.name}信息')">
						${groupType.name}
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	
	<div >
		<table border="0" class="show_user_list_table">
			<tr>
				<td class="select" valign="middle">
					<div id="selectAllDiv" class="select_all" onclick="javascript:selectAll('gradeListTable')"></div>
					<div class="select_font">全选</div>
				</td>
				<td class="td_span"></td>
				<td class="select">
					<div id="oppositeSelectDiv" class="select_all" onclick="javascript:oppositeSelect('gradeListTable')"></div>
					<div class="select_font">反选</div>
				</td>
				<td class="td_span"></td>
				<td class="add show_user_list_bottom_height"
					onclick="javascript:dialog_ming_adduser('addGroupInfo.html?typeId=${groupType.id}&parentId=${id}','添加${groupType.name}','500','220',2,'addUserHeadStyleClass','addUserBodyStyleClass')"
					onMouseOut="this.className='add show_user_list_bottom_height'" 
					onMouseDown="this.className='add_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="del show_user_list_bottom_height"
					onclick="javascript:groupList_del('gradeListTable')"
					onMouseOut="this.className='del show_user_list_bottom_height'"
					onMouseOver="this.className='del_move_in show_user_list_bottom_height'">
				</td>
				<td>
					&nbsp;
				</td>
				
			</tr>
		</table>
		<input type="hidden" id="gradeList_schoolId" name="gradeList_schoolId" value="${id}">
	</div>
</div>