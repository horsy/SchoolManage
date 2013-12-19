<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<iframe name="saveAddingClass" src="" style="display: none"></iframe>
	<form action="saveReview.html" method="post" name="addClassForm"
		target="saveAddingClass">
		<br />
		<table border="0" style="width:100%;margin-top:40px;">
			<tr>
			

			<tr>
				<td style="text-align:center;" class="change_password_td_label">
					<input type="radio" name="reviewValue" value="1" checked><font color="green">通过</font>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="reviewValue" value="2"><font color="red">未通过</font>
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
					<div class="change_password_submint" onclick="document.forms.addClassForm.submit();" onmousedown="this.className='change_password_submint_down'" onmouseup="this.className='change_password_submint'" onmouseout="this.className='change_password_submint'"></div>
				</td>
			</tr>
		</table>
		<input type="hidden" id="id" name="id" value="${id}">
	</form>
	<input type="hidden" id="blurId">
</div>