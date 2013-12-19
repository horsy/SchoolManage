<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div >
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<table class="show_user_list_table" id="studentListTable">
			<tr class="show_table_head_tr">
				<td>
					&nbsp;
				</td>
				<td class="show_table_head_line">
					学号
				</td>
				<td class="show_table_head_line">
					姓名
				</td>
				<td class="show_table_head_line">
					性别
				</td>
			</tr>
			<s:iterator value="%{studentList}" status="objList">
				<tr
					class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>"
					onmouseover='this.className="moveIn";'
					onmouseout='this.className="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>";'>
					<td class=TdBody_checkBox>
						<input type="checkbox"
							name="obj<s:property value="student.id" />"
							id="obj<s:property value="student.id" />" onclick="document.getElementById('selectAllDiv').className='select_all';">
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:property value="student.studentId" />
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:property value="student.userName" />
						
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:property value="sex.sex" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	
	<div >
		<table border="0" class="show_user_list_table">
			<tr>
				<td class="select" valign="middle">
					<div id="selectAllDiv" class="select_all" onclick="javascript:selectAll('studentListTable')"></div>
					<div class="select_font">全选</div>
				</td>
				<td class="td_span"></td>
				<td class="select">
					<div id="oppositeSelectDiv" class="select_all" onclick="javascript:oppositeSelect('studentListTable')"></div>
					<div class="select_font">反选</div>
				</td>
				<td class="td_span"></td>
				<td class="add show_user_list_bottom_height"
					onclick="javascript:addStudent(${classID})"
					onMouseOut="this.className='add show_user_list_bottom_height'" 
					onMouseDown="this.className='add_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="del show_user_list_bottom_height"
					onclick="javascript:studentList_del('studentListTable')"
					onMouseOut="this.className='del show_user_list_bottom_height'"
					onMouseOver="this.className='del_move_in show_user_list_bottom_height'">
				</td>
				<!-- 
				<td class="td_span"></td>
				<td class="beforeHand show_user_list_bottom_height"
					onclick="javascript:studentList_beforeHandAdd(${classID})" 
					onMouseOut="this.className='beforeHand show_user_list_bottom_height'"
					onMouseDown="this.className='beforeHand_click show_user_list_bottom_height'">
				</td>
				 -->
				<td>
					&nbsp;
				</td>
				
			</tr>
		</table>
	</div>