<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<iframe name="saveUploadAlarm" id="saveUploadAlarm" style="display: none" src=""></iframe>
<form name="setUploadAlarm" id="setUploadAlarm"
	action="saveUploadAlarm.html" method="post" target="saveUploadAlarm">
	<p></p>
	<table class="textBodyFont">
		<tr>
			<td>视频丢失</td>
			<td><input name="videoLost" type="checkbox" ${uploadAlarm.videoLost }></td>
			<td>动态检测</td>
			<td><input name="checkMove" type="checkbox" ${uploadAlarm.checkMove }></td>
		
			<td>硬盘已满</td>
			<td><input name="diskFull" type="checkbox" ${uploadAlarm.diskFull }></td>
		</tr>
		<tr>
			<td>硬盘故障</td>
			<td><input name="diskError" type="checkbox" ${uploadAlarm.diskError }></td>
			<td>视频遮挡</td>
			<td><input name="coverVideo" type="checkbox" ${uploadAlarm.coverVideo }></td>
			<td>红外报警</td>
			<td><input name="infraredAlarm" type="checkbox" ${uploadAlarm.infraredAlarm }></td>
		</tr>
		<tr>
			<td colspan="6"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a class="font_a" href="javascript:checkAllUploadAlarm()">全选</a></td>
			<td colspan="2" align="center"><a class="font_a" href="javascript:clearUploadAlarm()">清空</a></td>
			<td colspan="2" align="center"><a class="font_a" href="javascript:unselectUploadAlarm()">反选</a></td>
		</tr>
		<tr>
			<td colspan="6"></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input type="submit" value="保存"></td>
		</tr>
	</table>
	<input type="hidden" name="equipmentId" value="${equipmentId }"/>
	<input type="hidden" name="uploadAlarmCode" value="${uploadAlarmCode }" />
</form>
<script type="text/javascript">
<!--
	function checkAllUploadAlarm(){
		document.forms.setUploadAlarm.videoLost.checked=true;
		document.forms.setUploadAlarm.checkMove.checked=true;
		document.forms.setUploadAlarm.diskFull.checked=true;
		document.forms.setUploadAlarm.diskError.checked=true;
		document.forms.setUploadAlarm.coverVideo.checked=true;
		document.forms.setUploadAlarm.infraredAlarm.checked=true;
	}
	function clearUploadAlarm(){
		document.forms.setUploadAlarm.videoLost.checked=false;
		document.forms.setUploadAlarm.checkMove.checked=false;
		document.forms.setUploadAlarm.diskFull.checked=false;
		document.forms.setUploadAlarm.diskError.checked=false;
		document.forms.setUploadAlarm.coverVideo.checked=false;
		document.forms.setUploadAlarm.infraredAlarm.checked=false;
	}
	function unselectUploadAlarm(){
		if(document.forms.setUploadAlarm.videoLost.checked){
			document.forms.setUploadAlarm.videoLost.checked=false;
		}else{
			document.forms.setUploadAlarm.videoLost.checked=true;
		}
		if(document.forms.setUploadAlarm.checkMove.checked){
			document.forms.setUploadAlarm.checkMove.checked=false;
		}else{
			document.forms.setUploadAlarm.checkMove.checked=true;
		}
		if(document.forms.setUploadAlarm.diskFull.checked){
			document.forms.setUploadAlarm.diskFull.checked=false;
		}else{
			document.forms.setUploadAlarm.diskFull.checked=true;
		}
		if(document.forms.setUploadAlarm.diskError.checked){
			document.forms.setUploadAlarm.diskError.checked=false;
		}else{
			document.forms.setUploadAlarm.diskError.checked=true;
		}
		if(document.forms.setUploadAlarm.coverVideo.checked){
			document.forms.setUploadAlarm.coverVideo.checked=false;
		}else{
			document.forms.setUploadAlarm.coverVideo.checked=true;
		}
		if(document.forms.setUploadAlarm.infraredAlarm.checked){
			document.forms.setUploadAlarm.infraredAlarm.checked=false;
		}else{
			document.forms.setUploadAlarm.infraredAlarm.checked=true;
		}
	}
//-->
</script>
