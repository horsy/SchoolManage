<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="show_user_list_div">
		<iframe name="saveRightSetForm" id="saveRightSetForm" style="display: none;"
			src=""></iframe>
		<br/>
		<table id="rightTable" class="show_user_list_table">
			<tr id="user_header" class="show_table_head_tr">
				<s:iterator value="%{rightNameList}" id="nameList" status="objNameList">
					<td class="show_table_head_line">
						<s:text name='#nameList'></s:text>
					</td>
				</s:iterator>
			</tr>
			<s:iterator value="%{rightList}" id="list" status="objList">
				<tr class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>" style="cursor: default;">
					<td >
						<s:text name="#list.rightName"></s:text>
					</td>
					<td >
						<input type="checkbox" name="channelLabel_<s:text name="#list.rightId"></s:text>" id="channelLabel_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="distanceSetView_<s:text name="#list.rightId"></s:text>" id="distanceSetView_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="distanceSetWrite_<s:text name="#list.rightId"></s:text>" id="distanceSetWrite_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="streamMediaControl_<s:text name="#list.rightId"></s:text>" id="streamMediaControl_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="uploadVideoStreamMedia_<s:text name="#list.rightId"></s:text>" id="uploadVideoStreamMedia_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="alarmControl_<s:text name="#list.rightId"></s:text>" id="alarmControl_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="serverTime_<s:text name="#list.rightId"></s:text>" id="serverTime_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="serverControl_<s:text name="#list.rightId"></s:text>" id="serverControl_<s:text name="#list.rightId"></s:text>">
					</td>
					<td >
						<input type="checkbox" name="ptzControl_<s:text name="#list.rightId"></s:text>" id="ptzControl_<s:text name="#list.rightId"></s:text>">
					</td>
				</tr>
			</s:iterator>
			
		</table >
		
		
		<form action="saveRightSet.html" method="post" target="saveRightSetForm" name="rightSetForm">
			<center>
			<br>
			<table>
				<tr>
					<td align="center" onclick="javascript:saveRightSet()" class="right_set_explanation_save" onMouseOver="this.className='right_set_explanation_save_move_in'" onMouseOut="this.className='right_set_explanation_save'">
					</td>
				</tr>
			</table>
			</center>
			<input type="hidden" name="rightIdAll" id="rightIdAll">
			<input type="hidden" name="channelLabel" id="channelLabel">
			<input type="hidden" name="distanceSetView" id="distanceSetView">
			<input type="hidden" name="distanceSetWrite" id="distanceSetWrite">
			<input type="hidden" name="streamMediaControl" id="streamMediaControl">
			<input type="hidden" name="uploadVideoStreamMedia" id="uploadVideoStreamMedia">
			<input type="hidden" name="alarmControl" id="alarmControl">
			<input type="hidden" name="serverTime" id="serverTime">
			<input type="hidden" name="ptzControl" id="ptzControl">
			<input type="hidden" name="serverControl" id="serverControl">
		</form>
		<br>
		<br>
		<table width="75%" border="0" class="right_set_explanation" >
			<tr>
				<td class="right_set_explanation_name">
					<b>通道标签权限：</b>
				</td>
				<td class="right_set_explanation_value">对流媒体通道标签方案进行新增、保存、修改、设置等。
				</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>流媒体参数查询权限：</b>
				</td>
				<td class="right_set_explanation_value">对流媒体的参数进行查询。
				</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>远程参数设置权限：</b>
				</td>
				<td class="right_set_explanation_value">对流媒体的参数进行设置。
				</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>流媒体远程管理权限：</b>
				</td>
				<td class="right_set_explanation_value">对流媒体进行远程关机、重启。</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>视频上传控制权限：</b>
				</td>
				<td class="right_set_explanation_value">控制流媒体视频数据向级服务器上传。</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>报警管理权限：</b>
				</td><td class="right_set_explanation_value">历史数据报警查询和实时报警查询。</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>服务器时间管理权限：</b>
				</td><td class="right_set_explanation_value">查询和设置服务器的系统时间。</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>服务器远程管理权限：</b>
				</td>
				<td class="right_set_explanation_value">对服务器进行远程关机、重启。</td>
			</tr>
			<tr>
				<td class="right_set_explanation_name">
					<b>云台锁定权限：</b>
				</td>
				<td class="right_set_explanation_value">控制云台的锁定功能。可分别对上级和本级锁定，即：如果上级锁定，则，该云台所在的机构的所有上级机构的客户端，将不允许对它控制；如果本级锁定，则，该云台所在的机构的客户端将不允许对它控制。</td>
			</tr>
		</table>
</div>
<script type="text/javascript">
<!--
$(document).ready(function(){
	rightSet_setform();
});

function rightSet_setform(){
	<s:iterator value="%{rightList}" id="list" status="objList">
		<s:if test="%{#list.channelLabel==1}">
			document.getElementById("channelLabel_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("channelLabel_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.distanceSetView==1}">
			document.getElementById("distanceSetView_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("distanceSetView_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.distanceSetWrite==1}">
			document.getElementById("distanceSetWrite_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("distanceSetWrite_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.streamMediaControl==1}">
			document.getElementById("streamMediaControl_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("streamMediaControl_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.uploadVideoStreamMedia==1}">
			document.getElementById("uploadVideoStreamMedia_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("uploadVideoStreamMedia_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.alarmControl==1}">
			document.getElementById("alarmControl_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("alarmControl_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.serverTime==1}">
			document.getElementById("serverTime_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("serverTime_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.ptzControl==1}">
			document.getElementById("ptzControl_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("ptzControl_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
		<s:if test="%{#list.serverControl==1}">
			document.getElementById("serverControl_<s:text name="#list.rightId"></s:text>").checked=true;
		</s:if>
		<s:else>
			document.getElementById("serverControl_<s:text name="#list.rightId"></s:text>").checked=false;
		</s:else>
	</s:iterator>
}

function saveRightSet(){
			var rightId="";
			var channelLabel="";
			var distanceSetView="";
			var distanceSetWrite="";
			var streamMediaControl="";
			var uploadVideoStreamMedia="";
			var alarmControl="";
			var serverTime="";
			var serverControl="";
			var ptzControl="";
	<s:iterator value="%{rightList}" id="list" status="objList">
			rightId=rightId+"<s:text name="#list.rightId"></s:text>_";
			if(document.getElementById("channelLabel_<s:text name="#list.rightId"></s:text>").checked){
				channelLabel=channelLabel+"1_";
			}else{
				channelLabel=channelLabel+"0_";
			}
			if(document.getElementById("distanceSetView_<s:text name="#list.rightId"></s:text>").checked){
				distanceSetView=distanceSetView+"1_";
			}else{
				distanceSetView=distanceSetView+"0_";
			}
			
			if(document.getElementById("distanceSetWrite_<s:text name="#list.rightId"></s:text>").checked){
				distanceSetWrite=distanceSetWrite+"1_";
			}else{
				distanceSetWrite=distanceSetWrite+"0_";
			}
			
			if(document.getElementById("streamMediaControl_<s:text name="#list.rightId"></s:text>").checked){
				streamMediaControl=streamMediaControl+"1_";
			}else{
				streamMediaControl=streamMediaControl+"0_";
			}
			
			if(document.getElementById("uploadVideoStreamMedia_<s:text name="#list.rightId"></s:text>").checked){
				uploadVideoStreamMedia=uploadVideoStreamMedia+"1_";
			}else{
				uploadVideoStreamMedia=uploadVideoStreamMedia+"0_";
			}
			
			if(document.getElementById("alarmControl_<s:text name="#list.rightId"></s:text>").checked){
				alarmControl=alarmControl+"1_";
			}else{
				alarmControl=alarmControl+"0_";
			}
			
			if(document.getElementById("serverTime_<s:text name="#list.rightId"></s:text>").checked){
				serverTime=serverTime+"1_";
			}else{
				serverTime=serverTime+"0_";
			}
			
			if(document.getElementById("ptzControl_<s:text name="#list.rightId"></s:text>").checked){
				ptzControl=ptzControl+"1_";
			}else{
				ptzControl=ptzControl+"0_";
			}
			if(document.getElementById("serverControl_<s:text name="#list.rightId"></s:text>").checked){
				serverControl=serverControl+"1_";
			}else{
				serverControl=serverControl+"0_";
			}
	</s:iterator>
	document.getElementById("rightIdAll").value=rightId;
	document.getElementById("channelLabel").value=channelLabel;
	document.getElementById("distanceSetView").value=distanceSetView;
	document.getElementById("distanceSetWrite").value=distanceSetWrite;
	document.getElementById("streamMediaControl").value=streamMediaControl;
	document.getElementById("uploadVideoStreamMedia").value=uploadVideoStreamMedia;
	document.getElementById("alarmControl").value=alarmControl;
	document.getElementById("serverTime").value=serverTime;
	document.getElementById("serverControl").value=serverControl;
	document.getElementById("ptzControl").value=ptzControl;
	document.forms.rightSetForm.submit();
}

//-->
</script>