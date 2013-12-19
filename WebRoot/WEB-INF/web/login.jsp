<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ include file="./common/head.jsp"%>
	<body onload="javascript:init()">
		<form name="loginForm" method="post" action="loginCheck.htm">
		<div style=" position: relative;">
		<center>
			<table border="0" width="75%" height="97%">
				<tr style="height: 40%;">
					<td id="login_top_td" class="login_title_td"
						style="text-align: center;"colspan="2">
						<img id="login_top_img" src="../image/login_top.png" width="0px"
							height="0px"/>
					</td>
				</tr>
				<tr>
					<td id="login_people_td" colspan="1">
						<img id="login_people_img" src="../image/login_people.png" height="0px" />
					</td>
					<td align="right">
						<table border="0" style="margin-right: 40px;">
							<tr>
								<td class="loginLable">
									登录名
								</td>
							</tr>
							<tr>
								<td>
									<div id="userNameDiv"  class="login_page_input_div" onMouseOut="javascript:loginPage_changeClassName(this,'login_page_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'login_page_inputing_div',event)"><input onfocus="javascript:loginPage_changeClassName(this.parentNode,'login_page_inputing_div',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'login_page_input_div',event)" class="login_page_input" id="j_username" name="j_username" type="text"/></div>
								</td>
							</tr>
							<tr>
								<td class="loginLable">
									密&nbsp;&nbsp;&nbsp;&nbsp;码
								</td>
							</tr>
							<tr>
								<td>
									<div id="passwordDiv" class="login_page_input_div" onMouseOut="javascript:loginPage_changeClassName(this,'login_page_input_div',event)" onMouseOver="javascript:loginPage_changeClassName(this,'login_page_inputing_div',event)"><input onkeyup="javascript:loginPage_changePassword()" onfocus="javascript:loginPage_changeClassName(this.parentNode,'login_page_inputing_div',event)" onblur="javascript:loginPage_changeClassName(this.parentNode,'login_page_input_div',event)" onkeydown="javascript:keyNumAll(event)" class="login_page_input" id="j_password" name="j_password" type="password"/></div>
								</td>
							</tr>
							<tr>
						 		<td height="50px" style="vertical-align:middle;">
						 			<div id="remenberCheckBox" onclick="javascript:loginPage_onclickCheckBox()" onMouseOver="javascript:loginPage_changeClassName(this,'login_page_on_check_box',event)" onMouseOut="javascript:loginPage_changeClassName(this,'login_page_check_box',event)" class="login_page_check_box"></div><div class="login_page_check_box_label"></div><div id="loginButton" onclick="javascript:loginPage_submit()" onMouseOver="javascript:loginPage_changeClassName(this,'login_button_in',event)" onMouseOut="javascript:loginPage_changeClassName(this,'login_button',event)" class="login_button"></div>
						 		</td>
						 	</tr>
						</table>
					</td>
				</tr>
			</table>
			</center>
		</div>
		<div
			style="z-index: -100; position: absolute; left: 0px; top: 0px; width: 100%; height: 100%;">
			<table border="0" width="100%" height="100%">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="version">
						建议采用IE、FireFox、Google浏览器登录&nbsp;&nbsp;&nbsp;&nbsp;内部版本：${version}&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="blurId" name="blurId">
	 	<input type="hidden" id="remenberLoginName" name="remenberLoginName" >
	 	</form>
		<script type="text/javascript">
			//$(document).ready(
			function init(){
				if (window.name == "main") {
					window.name = "login";
					document.location.href = "login.html";
				}else{
					//dialog_ming("loginView.html", "", "400", "320", 1,"headStyleClass", "bodyStyleClass");
				}
				if (top.location != self.location){
					top.location = self.location;
				}
				loginPage_getCookie();
				var loginTopTd=document.getElementById("login_top_td");
				var loginTopImg=document.getElementById("login_top_img");
				loginTopImg.style.width=(loginTopTd.offsetWidth-5)+"px";
				loginTopImg.style.height=(loginTopTd.offsetHeight-5)+"px";
				var loginPeopleTd=document.getElementById("login_people_td");
				var loginPeopleImg=document.getElementById("login_people_img");
				loginPeopleImg.style.height=loginPeopleTd.offsetHeight+"px";
				
			}
			//);
			function keyNumAll(evt) {

				evt = (evt) ? evt : ((window.event) ? window.event : ""); //兼容IE和Firefox获得keyBoardEvent对象
				var key = evt.keyCode ? evt.keyCode : evt.which;//兼容IE和Firefox获得keyBoardEvent对象的键值
			
				if (key == 13) {
					loginPage_submit();
				}
			}
		</script>
	</body>
</html>
