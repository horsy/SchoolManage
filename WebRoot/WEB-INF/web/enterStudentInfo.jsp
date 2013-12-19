<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ include file="./common/head.jsp"%>
	<body class="bg" onunload="javascript:closeWindow(event)">
<div >
	<iframe name="saveAddingStudent" src="" style="display: none"></iframe>
	<form action="saveEnterStudent.html" method="post" name="addStudentForm"
		target="saveAddingStudent">
		<center>
		<table border="0" style="margin-top:30px; width:835px;">
			<caption class="captionStyle">信息录入</caption>
			<tr>
				<td class="change_password_td_label">
					学号：
				</td>
				<td class="inputTd">
					<div id="studentIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="studentId" name="studentId" maxlength="32" value="${student.studentId}" readonly="readonly" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					姓名：
				</td>
				<td>
					<div id="userNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="userName" name="userName" maxlength="16" value="${student.userName}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>

			</tr>
		

			<tr>
				<td class="change_password_td_label">
					出生日期：
				</td>
				<td>
					<div id="birthdayDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="birthday" name="birthday" readonly="readonly" onclick="javascript:showCalendar(this,event)" value="${student.birthday}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
			
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
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					身份证：
				</td>
				<td>
					<div id="identityCardIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="identityCardId" name="identityCardId" maxlength="18" value="${student.identityCardId}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					民族：
				</td>
				<td>
					<div id="nationIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="nationId" name="nationId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{nationList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="nation"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					户口：
				</td>
				<td>
					<div id="hukouDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="hukou" name="hukou" maxlength="128" value="${student.hukou}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
			
				<td class="change_password_td_label">
					电话：
				</td>
				<td>
					<div id="telephoneDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="telephone" name="telephone" maxlength="15" value="${student.telephone}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>

			</tr>
			
			<tr>
				<td class="change_password_td_label">
					电子邮箱：
				</td>
				<td>
					<div id="emailDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="email" name="email" maxlength="32" value="${student.email}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					QQ号码：
				</td>
				<td>
					<div id="qqDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="qq" name="qq" maxlength="32" value="${student.qq}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
			
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					宿舍：
				</td>
				<td>
					<div id="dormDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="dorm" name="dorm" maxlength="64" value="${student.dorm}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					血型：
				</td>
				<td>
					<div id="bloodGroupIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="bloodGroupId" name="bloodGroupId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{bloodGroupList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="groupName"/>
								</option>
							</s:iterator>
							
						</select>
					</div>
				</td>
				
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					状态：
				</td>
				<td>
					<div id="statusIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="statusId" name="statusId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{studentStatusList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="statusName"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				
				<td class="change_password_td_label">
					入学日期：
				</td>
				<td>
					<div id="entranceDateDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="entranceDate" name="entranceDate" readonly="readonly" onclick="javascript:showCalendar(this,event)" value="${student.entranceDate}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					离校日期：
				</td>
				<td>
					<div id="leaveSchoolDateDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="leaveSchoolDate" name="leaveSchoolDate" readonly="readonly" onclick="javascript:showCalendar(this,event)" value="${student.leaveSchoolDate}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					政治面貌：
				</td>
				<td>
					<div id="politicalOrientationIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="politicalOrientationId" name="politicalOrientationId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{politicalOrientationList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="politicalOrientation"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				
			</tr>
			<tr>
				<td class="change_password_td_label">
					监护人：
				</td>
				<td>
					<div id="guardianDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="guardian" name="guardian" maxlength="16" value="${student.guardian}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					监护人电话：
				</td>
				<td>
					<div id="guardianTelephoneDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="guardianTelephone" name="guardianTelephone" maxlength="15" value="${student.guardianTelephone}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
			</tr>
			<tr>
				<td class="change_password_td_label">
					家庭住址：
				</td>
				<td>
					<div id="homeAddressDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="homeAddress" name="homeAddress" maxlength="128" value="${student.homeAddress}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					联系地址：
				</td>
				<td>
					<div id="touchAddressDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="touchAddress" name="touchAddress" maxlength="128" value="${student.touchAddress}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
			</tr>
			<tr>
				<td class="change_password_td_label">
					特长：
				</td>
				<td colspan="3">
					<div id="specialSkillDiv" class="change_password_input_div_tc"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div_tc',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_tc_movie_in',event)">
						<input class="input_tc" type="text" id="specialSkill" name="specialSkill" maxlength="128" value="${student.specialSkill}" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_tc_movie_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_tc',event)">
					</div>
				</td>
			</tr>
		</table>
		<br/>
		<table border="0" style="width:700px;">
			<tr>
				<td>
					&nbsp;
				</td>
				<td style="width:100px;">
					<div class="cancel" onclick="javascript:exit()" onmousedown="this.className='cancel_down'" onmouseup="this.className='cancel'" onmouseout="this.className='cancel'"></div>
				</td>
				<td style="width: 35px;">
				</td>
				<td style="width:100px;">
					<div class="change_password_submint" onclick="document.forms.addStudentForm.submit();" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		</center>
		<input type="hidden" id="classId" name="classId" value="${student.classId}"/>
		<input type="hidden" id="id" name="id" value="${student.id}"/>
	</form>
	<input type="hidden" id="blurId">
</div>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("sexId").value="${student.sexId}";
		document.getElementById("nationId").value="${student.nationId}";
		document.getElementById("bloodGroupId").value="${student.bloodGroupId}";
		document.getElementById("statusId").value="${student.statusId}";
		document.getElementById("politicalOrientationId").value="${student.politicalOrientationId}";
	});
</script>
</body>
</html>