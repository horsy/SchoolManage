<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div>
	<br>
	<table id="sipAndZfTable" border="0" class="show_table">
		<tr>
			<td colspan="2" class="show_talbe_head">
				转发服务器信息
			</td>
		</tr>
		<tr class="show_table_head_tr">
			<td  width="35%">
				名称
			</td>
			<td width="65%" class="show_table_head_line">
				属性
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td >
				设备名
			</td>
			<td >
				${equipment.displayName }
			</td>
		</tr>
		<tr class="show_table_body_tr_black">
			<td >
				设备类型
			</td>
			<td >
				${equipment.equipmentType.equipmentType }
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td >
				版本信息
			</td>
			<td >
				${equipment.version }
			</td>
		</tr>
		<tr class="show_table_body_tr_black">
			<td >
				是否在线(指令服务器)
			</td>
			<td >
				${isOnline }
			</td>
		</tr>
		<tr class="show_table_body_tr_white">
			<td >
				IP地址
			</td>
			<td >
				${equipment.ip }
			</td>
		</tr>
		<tr class="show_table_body_tr_black">
			<td >
				端口
			</td>
			<td >
				${equipment.port }
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
<!--
$(document).ready(function(){
	main_setSipAndZfInfoDivHeight();
});
//-->
</script>