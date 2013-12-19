<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div>
	<br />
	<table id="sipAndZfTable" class="show_table">
		<tr>
			<td colspan="2" class="show_talbe_head">
				SIP服务器信息
			</td>
		</tr>

		<tr class="show_table_head_tr">
			<td width="35%">
				名称
			</td>
			<td width="65%" class="show_table_head_line">
				属性
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td>
				设备类型
			</td>
			<td>
				${equipment.equipmentType.equipmentType }
			</td>
		</tr>
		<tr class="show_table_body_tr_black">
			<td>
				版本信息
			</td>
			<td>
				${equipment.version }
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td>
				域名
			</td>
			<td>
				${equipment.sipuri }
			</td>
		</tr>
		<tr class="show_table_body_tr_black">
			<td>
				数据库版本
			</td>
			<td>
				${equipment.databaseVersion }
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td>
				是否在线(指令服务器)
			</td>
			<td>
				${isOnline }
			</td>
		</tr>
		<tr class="show_table_body_tr_black">
			<td >
				IP地址
			</td>
			<td>
				${equipment.ip }
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td>
				端口
			</td>
			<td>
				${equipment.port }
			</td>
		</tr>
		<tr class="show_table_body_tr_black"
			onmouseover='this.className="moveIn";'
			onmouseout='this.className="show_table_body_tr_black";'
			onclick="dialog_ming_adduser('setUploadAlarm.html?equipmentId=${equipment.equipmentId }&uploadAlarmCode=${equipment.isUploadAlarm }','报警信息上传(${equipment.displayName})','280','160',2,'addUserHeadStyleClass','addUserBodyStyleClass');"
			title="6位数字分别代表以下报警信息是否上传到指令服务器：&#13;1、视频丢失&#13;2、动态检测&#13;3、硬盘已满&#13;4、硬盘故障&#13;5、视频遮挡&#13;6、红外报警&#13;其中：0-不上传、1-上传">
			<td>
				报警上传
			</td>
			<td>
				${equipment.isUploadAlarm }
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td>
				管理中心
			</td>
			<td>
				http://${sipInfo.webIp}:${sipInfo.webPort}&nbsp;&nbsp;
				<a target="_blank"
					href="managerCenter.html?ip=${sipInfo.webIp}&port=${sipInfo.webPort}&socketPort=${sipInfo.socketPort}">进入管理中心</a>&nbsp;&nbsp;
				<a
					href="javascript:dialog_ming_adduser('setDirectInfo.html?equipmentId=${equipment.equipmentId }&sipuri=${sipInfo.sipuri }&ip=${sipInfo.webIp}&webPort=${sipInfo.webPort}&socketPort=${sipInfo.socketPort}','远程管理(${equipment.displayName})信息','280','180',2,'addUserHeadStyleClass','addUserBodyStyleClass');">修改地址</a>
			</td>
		</tr>
		<s:if test='%{equipment.isOnline=="1"}'>
			<tr class="show_table_body_tr_black">
				<td>
					SIP服务
				</td>
				<td>
					${sipInfo.sysState }
				</td>
			</tr>
			<tr class="show_table_body_tr_white">
				<td>
					注册状态(注册上级SIP)
				</td>
				<td>
					${sipInfo.registeInfo }
				</td>
			</tr>
			<tr class="show_table_body_tr_black">
				<td>
					CPU信息
				</td>
				<td>
					${sipInfo.cpu }
				</td>
			</tr>
			<tr class="show_table_body_tr_white">
				<td>
					内存信息
				</td>
				<td>
					${sipInfo.memory }
				</td>
			</tr>
			<tr class="show_table_body_tr_black">
				<td>
					SIP服务器外网IP
				</td>
				<td>
					${sipInfo.sipIpOut }
				</td>
			</tr>
			<tr class="show_table_body_tr_white">
				<td>
					SIP服务器内网IP
				</td>
				<td>
					${sipInfo.sipIpIn }
				</td>
			</tr>
			<tr class="show_table_body_tr_black">
				<td>
					SIP服务端口
				</td>
				<td>
					${sipInfo.sipPort }
				</td>
			</tr>
			<s:if test='%{sipInfo.type=="3"}'>
				<tr class="show_table_body_tr_white">
					<td>
						是否按时间计划录像
					</td>
					<td>
						${sipInfo.nvrInfo.isTimerStore }
					</td>
				</tr>
				<tr class="show_table_body_tr_black">
					<td>
						录像打包时间
					</td>
					<td>
						${sipInfo.nvrInfo.packagingTime }分钟/次
					</td>
				</tr>
				<tr class="show_table_body_tr_white">
					<td>
						录像存盘位置
					</td>
					<td>
						${sipInfo.nvrInfo.diskStart }盘 - ${sipInfo.nvrInfo.diskEnd }盘
					</td>
				</tr>
				<tr class="show_table_body_tr_black">
					<td>
						磁盘信息
					</td>
					<td>
						${sipInfo.nvrInfo.diskInfo }
					</td>
				</tr>
			</s:if>
		</s:if>
	</table>
</div>
<script type="text/javascript">
<!--
$(document).ready(function(){
	main_setSipAndZfInfoDivHeight();
});
//-->
</script>
