<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div >
	<iframe name="saveAddingStudent" src="" style="display: none"></iframe>
	<form action="saveStudent.html" method="post" name="addStudentForm" id="addStudentForm"
		target="saveAddingStudent">
		<center>
		<table border="0" style="margin-top:30px; width:845px;">
			<tr>
				<td class="change_password_td_label">
					学号：
				</td>
				<td class="inputTd">
					<div id="studentIdDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="studentId" name="studentId" maxlength="32" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					姓名：
				</td>
				<td class="short_input_name">
					<input class="short_input_name" type="text" id="userName" name="userName" maxlength="16" >
				</td>
				<td rowspan="3" class="photo_td"><img  id="photoImg" alt="" class="photo" src="">
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
						<input class="input" type="text" id="birthday" name="birthday" readonly="readonly" onclick="javascript:showCalendar(this,event)"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
			
				<td class="change_password_td_label">
					性别：
				</td>
				<td>
						<select class="short_input" id="sexId" name="sexId">
							<s:iterator value="%{sexList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="sex"/>
								</option>
							</s:iterator>
						</select>
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
						<input class="input" type="text" id="identityCardId" name="identityCardId" maxlength="18" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					民族：
				</td>
				<td>
					<select class="short_input" id="nationId" name="nationId">
							<s:iterator value="%{nationList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="nation"/>
								</option>
							</s:iterator>
					</select>
				</td>
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					户口：
				</td>
				<td class="change_password_input_td">
					<div id="hukouDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="hukou" name="hukou" maxlength="128" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
			
				<td class="change_password_td_label">
					电话：
				</td>
				<td class="short_input">
						<input class="short_input" type="text" id="telephone" name="telephone" maxlength="15" >
				</td>
				<td align="center">
					<input style="width:65px;" type="file" name="uploadPhoto" id="uploadPhoto" onchange="javascript:upload()">
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
						<input class="input" type="text" id="email" name="email" maxlength="32" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					QQ号码：
				</td>
				<td colspan="2">
					<div id="qqDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="qq" name="qq" maxlength="32" 
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
						<input class="input" type="text" id="dorm" name="dorm" maxlength="64" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					血型：
				</td>
				<td colspan="2">
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
				<td colspan="2">
					<div id="entranceDateDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="entranceDate" name="entranceDate" readonly="readonly" onclick="javascript:showCalendar(this,event)"
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
						<input class="input" type="text" id="leaveSchoolDate" name="leaveSchoolDate" readonly="readonly" onclick="javascript:showCalendar(this,event)"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					政治面貌：
				</td>
				<td colspan="2">
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
						<input class="input" type="text" id="guardian" name="guardian" maxlength="16" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					监护人电话：
				</td>
				<td colspan="2">
					<div id="guardianTelephoneDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="guardianTelephone" name="guardianTelephone" maxlength="15" 
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
						<input class="input" type="text" id="homeAddress" name="homeAddress" maxlength="128" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
				<td class="change_password_td_label">
					联系地址：
				</td>
				<td colspan="2">
					<div id="touchAddressDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" id="touchAddress" name="touchAddress" maxlength="128" 
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				
			</tr>
			
			<tr>
				<td class="change_password_td_label">
					特长：
				</td>
				<td colspan="4" class="change_password_input_td_tc">
					<div id="specialSkillDiv" class="change_password_input_div_tc"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div_tc',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_tc_movie_in',event)">
						<input class="input_tc" type="text" id="specialSkill" name="specialSkill" maxlength="128" 
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
					<div class="cancel" onclick="javascript:addStudent_cancel('${classId}')" onmousedown="this.className='cancel_down'" onmouseup="this.className='cancel'" onmouseout="this.className='cancel'"></div>
				</td>
				<td style="width: 35px;">
				</td>
				<td style="width:100px;">
					<div class="change_password_submint" onclick="document.forms.addStudentForm.submit();" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		</center>
		<input type="hidden" id="classId" name="classId" value="${classId}">
		<input type="hidden" id="photoName" name="photoName" value="" >
	</form>
	<!-- 
	<iframe id="saveUploadPhoto" name="saveUploadPhoto" src="" style="display: none"></iframe>
	<form action="uploadPhoto.html" method="post" name="uploadFile"	enctype="multipart/form-data" target="saveUploadPhoto">
		<span style="display:none;"><input type="file" name="uploadPhoto" id="uploadPhoto" onchange="javascript:upload()"></span>
	</form>
	 -->
	<input type="hidden" id="blurId">
</div>