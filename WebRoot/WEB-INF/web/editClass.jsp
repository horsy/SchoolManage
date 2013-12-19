<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="saveEditClass" src="" style="display: none"></iframe>
	<form action="saveEditClass.html" method="post" name="editClassForm" id="editClassForm"
		target="saveEditClass">
		<table border="0" style="width:100%;margin-top:30px;">
			<tr>
				<td class="change_password_td_label" style="width: 145px;">
					班级名：
				</td>
				<td class="inputTd">
					<div id="classNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="className" value="${clas.className}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					班级的名称，比如：2000级一班/高2000级二班
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					年级：
				</td>
				<td>
					<div id="gradeIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="gradeId" name="gradeId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{gradeList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="gradeName"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					所属的年级
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					班主任：
				</td>
				<td>
					<div id="teacherIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="teacherId" name="teacherId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{teacherList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="userName"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					该班级的班主任
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
		</table>
		<br />
		<br />
		<br />
		<br />
		<table border="0" style="width: 90%;">
			<tr>
				<td>
					&nbsp;
				</td>
				<td style="width:100px;">
					<div class="cancel" onclick="javascript:dialog_close('bgDiv_2')" onmousedown="this.className='cancel_down'" onmouseup="this.className='cancel'" onmouseout="this.className='cancel'"></div>
				</td>
				<td style="width: 35px;">
				</td>
				<td style="width:100px;">
					<div class="change_password_submint" onclick="javascript:toSubmit('editClassForm')" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" id="id" value="${clas.id}"/>
	</form>
	<input type="hidden" id="blurId">
</div>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("gradeId").value="${clas.gradeId}";
		document.getElementById("teacherId").value="${clas.teacherId}";
		oldValue=[];
		oldValue=getValue('editClassForm');
	});
</script>