<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="saveUpdateGrade" src="" style="display: none"></iframe>
	<form action="updateGrade.html" method="post" name="updateGradeForm" id="updateGradeForm"
		target="saveUpdateGrade">
		<table border="0" style="width:100%;margin-top:30px;">
			<tr>
				<td class="change_password_td_label" style="width: 145px;">
					年级名：
				</td>
				<td class="inputTd">
					<div id="gradeNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="gradeName" value="${grade.gradeName}"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					年级的名称，比如：2000级/高2000级
				</td>
			</tr>

			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>
			<!-- 
			<tr>
				<td class="change_password_td_label">
					级别：
				</td>
				<td>
					<div id="levelDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<select class="input" id="levelId" name="levelId"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
							<s:iterator value="%{levelList}" status="roList">
								<option value='<s:property value="id"/>'>
									<s:property value="level"/>
								</option>
							</s:iterator>
						</select>
					</div>
				</td>
				<td class="addUserExplain">
					指该年级属于：高中、初中还是小学。
				</td>
			</tr>
			 -->
			<tr>
				<td colspan="3">
					&nbsp;
				</td>
			</tr>

			<tr>
				<td class="change_password_td_label">
					入学年份：
				</td>
				<td>
					<div id="inSchoolYearDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="inSchoolYear" value="${grade.inSchoolYear}"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
					哪一年入学。
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
					<div class="change_password_submint" onclick="javascript:toSubmit('updateGradeForm')" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" id="id" value="${grade.id}" >
		<input type="hidden" name="groupId" id="groupId" value="${grade.groupId}" >
		<input type="hidden" name="levelId" id="levelId" value="${grade.levelId}" >
	</form>
	<input type="hidden" id="blurId">
</div>
<script type="text/javascript">
	$(document).ready(function(){
		//document.getElementById("levelId").value="${grade.levelId}";
		oldValue=[];
		oldValue=getValue('updateGradeForm');
	});
</script>