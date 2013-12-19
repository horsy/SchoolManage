<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="saveBeforeHandAdd" src="" style="display: none"></iframe>
	<form action="saveBeforeHandAdd.html" method="post" name="beforeHandAddForm"
		target="saveBeforeHandAdd">
		<table border="0" style="width:100%;margin-top:30px;">
			<tr>
				<td class="change_password_td_label" style="width: 145px;">
					起始学号：
				</td>
				<td class="inputTd">
					<div id="studentIdStartDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="studentIdStart" id="studentIdStart" maxlength="32" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					预添加学生信息的学号开始位置。（包含此学号)
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					结束学号：
				</td>
				<td>
					<div id="studentIdEndDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="studentIdEnd" name="studentIdEnd" maxlength="32" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)"/>
					</div>
				</td>
				<td class="addUserExplain">
					预添加学生信息的学号结束位置。（包含此学号)
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					默认密码：
				</td>
				<td>
					<div id="passwordDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="password" name="password" maxlength="32" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)"/>
					</div>
				</td>
				<td class="addUserExplain">
					学生自行录入信息前，登录系统用的密码。
				</td>
			</tr>
			
			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					班级：
				</td>
				<td>
					<div id="classIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="classId" name="classId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{classList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="className"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					此学号段学生所属的班级。
				</td>
			</tr>
			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td class="change_password_td_label"><font color="red">说明：</font></td>
				<td colspan="2" class="addUserExplain">
					将待添加的学生学号预先录入系统。在登录页面，学生根据学号登录，自行录入自己的信息。最后由教师审核。减轻学校老师的负担。
				</td>
			</tr>
		</table>
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
					<div class="change_password_submint" onclick="document.forms.beforeHandAddForm.submit();" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
	</form>
	<input type="hidden" id="blurId">
</div>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("classId").value="${classId}";
	});
</script>