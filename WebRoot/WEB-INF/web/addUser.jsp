<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="saveAddingUser" src="" style="display: none"></iframe>
	<form action="saveAddingUser.html" method="post" name="addUserForm"
		target="saveAddingUser">
		<table border="0" class="addUserTable">
			<tr>
				<td class="change_password_td_label" style="width: 145px;">
					登录名：
				</td>
				<td class="inputTd">
					<div id="loginNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="loginName"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					只能由字母、数字、下划线组成。登录名长度不得小于三位，不得大于十六位。
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					用户名：
				</td>
				<td>
					<div id="displayNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="displayName"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					可以为英文单词、汉字等，不能为特殊字符。用户名长度不得大于十六位。
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					密码：
				</td>
				<td>
					<div id="loginPasswordDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="password" name="loginPassword"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					可以是字母、数字、特殊字符，也可以是汉字等其他字符。
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					确认密码：
				</td>
				<td>
					<div id="loginPassword2Div" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="password" name="loginPassword2"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					确认密码必须与密码一致。
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					权限：
				</td>
				<td>
					<div id="loginNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="rightId" name="rightId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{rightList}" id="rList" status="roList">
								<option value="<s:text name='#rList.rightId'></s:text>">
									<s:text name='#rList.rightName'></s:text>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					该客户端账号拥有的权限。
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
					<div class="cancel" onclick="document.body.removeChild(document.getElementById('bgDiv_2_bg'));document.body.removeChild(document.getElementById('bgDiv_2'));" onmousedown="this.className='cancel_down'" onmouseup="this.className='cancel'" onmouseout="this.className='cancel'"></div>
				</td>
				<td style="width: 35px;">
				</td>
				<td style="width:100px;">
					<div class="change_password_submint" onclick="document.forms.addUserForm.submit();" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
	</form>
	<input type="hidden" id="blurId">
</div>