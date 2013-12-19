<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="textBodyFont">
	<div style="height:90px;">
	</div>
	<center>
		<iframe name="saveEditAdmin" src="" style="display: none"></iframe>
		<form action="saveEditAdmin.html" method="post" name="editAdminForm" id="editAdminForm"
			target="saveEditAdmin">
			<table border="0">
				<tr>
					<td class="change_password_td_label">
						旧密码：
					</td>
					<td>
						<div id="oldPasswordDiv" class="change_password_input_div" onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)"><input class="input" type="password" name="oldPassword" onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)"></div>
					</td>
				</tr>
				<tr>
					<td class="change_password_td_label">
						新密码：
					</td>
					<td>
						<div id="newPasswordDiv" class="change_password_input_div"  onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)"><input class="input" type="password" name="newPassword1"  onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)"></div>
					</td>
				</tr>
				<tr>
					<td class="change_password_td_label">
						确认新密码：
					</td>
					<td>
						<div id="newPassword2Div" class="change_password_input_div"  onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)"><input class="input" type="password" name="newPassword2"  onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<div class="change_password_submint" onclick="document.forms.editAdminForm.submit();" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
					</td>
				</tr>
			</table>
		</form>
		<input type="hidden" id="blurId">
	</center>
</div>