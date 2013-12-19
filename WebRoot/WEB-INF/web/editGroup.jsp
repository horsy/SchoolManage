<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="updateGroupInfo" src="" style="display: none"></iframe>
	<form action="updateGroupInfo.html" method="post" name="editGroupInfoForm" id="editGroupInfoForm"
		target="updateGroupInfo">
		<table border="0" style="width:100%;margin-top:30px;">
			<tr>
				<td class="change_password_td_label" style="width: 145px;">
					名称：
				</td>
				<td class="inputTd">
					<div id="gradeNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="name" id="name" maxlength="16" value="${groupInfo.name}"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				<td class="addUserExplain">
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
					<div class="change_password_submint" onclick="javascript:toSubmit('editGroupInfoForm')" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		<input type="hidden" name="typeId" id="typeId" value="${groupInfo.typeId}" />
		<input type="hidden" name="id" id="id" value="${groupInfo.id}" >
		<input type="hidden" name="parentId" id="parentId" value="${groupInfo.parentId}" >
	</form>
	<input type="hidden" id="blurId">
</div>
<script type="text/javascript">
	$(document).ready(function(){
		oldValue=[];
		oldValue=getValue('editGroupInfoForm');
	});
</script>