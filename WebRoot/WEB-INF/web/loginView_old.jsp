<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form name="loginForm" method="post" action="loginCheck.htm">
	 <table border="0" width="100%" style="margin:0px;padding:0px;">
	 	<tr>
	 		<td style="height:8px;">
	 			&nbsp;
	 		</td>
	 	</tr>
	 	<tr>
	 		<td height="25px">
	 			<div class="login_page_login_name_label">&nbsp;</div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td>
	 			<div id="userNameDiv"  class="login_page_input_div" onMouseOut="javascript:loginPage_changeClassName(this,'login_page_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'login_page_inputing_div',event)"><input onfocus="javascript:loginPage_changeClassName(this.parentNode,'login_page_inputing_div',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'login_page_input_div',event)" class="login_page_input" id="j_username" name="j_username" type="text"/></div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td height="25px">
	 			<div class="login_page_password_label">&nbsp;</div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td height="60px">
	 			<div id="passwordDiv" class="login_page_input_div" onMouseOut="javascript:loginPage_changeClassName(this,'login_page_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'login_page_inputing_div',event)"><input onkeyup="javascript:loginPage_changePassword()" onfocus="javascript:loginPage_changeClassName(this.parentNode,'login_page_inputing_div',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'login_page_input_div',event)" onkeydown="javascript:keyNumAll(event)" class="login_page_input" id="j_password" name="j_password" type="password"/></div>
	 		</td>
	 	</tr>
	 	<tr>
	 		<td height="50px" style="vertical-align:middle;">
	 			<div id="remenberCheckBox" onclick="javascript:loginPage_onclickCheckBox()" onMouseOver="javascript:loginPage_changeClassName(this,'login_page_on_check_box',event)" onMouseOut="javascript:loginPage_changeClassName(this,'login_page_check_box',event)" class="login_page_check_box"></div><div class="login_page_check_box_label"></div><div id="loginButton" onclick="javascript:loginPage_submit()" onMouseOver="javascript:loginPage_changeClassName(this,'login_button_in',event)" onMouseOut="javascript:loginPage_changeClassName(this,'login_button',event)" class="login_button"></div>
	 		</td>
	 	</tr>
	 </table>
	 <input type="hidden" id="blurId" name="blurId">
	 <input type="hidden" id="remenberLoginName" name="remenberLoginName" >
</form>
<script type="text/javascript">
<!--
$(document).ready(function(){
	loginPage_getCookie();
});
function keyNumAll(evt) {

	evt = (evt) ? evt : ((window.event) ? window.event : ""); //兼容IE和Firefox获得keyBoardEvent对象
	var key = evt.keyCode ? evt.keyCode : evt.which;//兼容IE和Firefox获得keyBoardEvent对象的键值

	if (key == 13) {
		loginPage_submit();
	}
}
//-->
</script>
