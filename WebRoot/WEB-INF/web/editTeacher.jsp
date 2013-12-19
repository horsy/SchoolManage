<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="updateTeacher" src="" style="display: none"></iframe>
	<form action="updateTeacher.html" method="post" name="updateTeacherForm" id="updateTeacherForm"
		target="updateTeacher">
		<table border="0" style="width:100%;margin-top:30px;">
			<tr>
				<td class="change_password_td_label" style="width: 145px;">
					姓名：
				</td>
				<td class="inputTd">
					<div id="userNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="userName" maxlength="16" value="${teacher.userName}"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					教师的姓名
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					性别：
				</td>
				<td>
					<div id="sexIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="sexId" name="sexId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{sexList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="sex"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					教师性别
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					联系电话：
				</td>
				<td>
					<div id="telephoneDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="telephone" maxlength="16" value="${teacher.telephone}"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="change_password_td_label">
					职位：
				</td>
				<td>
					<div id="typeIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="typeId" name="typeId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{teacherTypeList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="typeName"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					&nbsp;
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
					<div class="change_password_submint" onclick="javascript:toSubmit('updateTeacherForm')" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		<input type="hidden" id="id" name="id" value="${teacher.id}" >
		<input type="hidden" id="loginName" name="loginName" value="${teacher.loginName}" >
		<input type="hidden" id="enableLogin" name="enableLogin" value="${teacher.enableLogin}"/>
	</form>
	<input type="hidden" id="blurId">
</div>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("sexId").value="${teacher.sexId}";
		document.getElementById("typeId").value="${teacher.typeId}";
		oldValue=[];
		oldValue=getValue('updateTeacherForm');
	});
</script>