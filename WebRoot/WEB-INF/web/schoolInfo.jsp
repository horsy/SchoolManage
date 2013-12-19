<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="textBodyFont">
	<div style="height:90px;">
	</div>
	<center>
		<iframe name="saveSchoolInfo" src="" style="display: none"></iframe>
		<form action="saveSchoolInfo.html" method="post" name="editSchoolForm" id="editSchoolForm"
			target="saveSchoolInfo">
			<table border="0">
				<tr>
					<td class="change_password_td_label">
						学校名称：
					</td>
					<td class="inputTd">
					<div id="schoolNameDiv" class="change_password_input_div"
						onMouseOut="javascript:loginPage_changeClassName(this,'change_password_input_div',event)"
						onMouseOver="javascript:loginPage_changeClassName(this,'change_password_input_div_move_in',event)">
						<input class="input" type="text" name="schoolName" value="${systemConfig.systemName}" maxlength="16"
							onfocus="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div_move_in',event)"
							onblur="javascript:loginPage_changeClassName(this.parentNode,'change_password_input_div',event)">
					</div>
				</td>
				</tr>
				<tr>
					<td colspan="2">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<div class="change_password_submint" onclick="javascript:toSubmit('editSchoolForm')" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
					</td>
				</tr>
			</table>
		</form>
		<input type="hidden" id="blurId">
	</center>
</div>
<script type="text/javascript">
	$(document).ready(function (){
		oldValue=[];
		oldValue=getValue('editSchoolForm');
	});
</script>