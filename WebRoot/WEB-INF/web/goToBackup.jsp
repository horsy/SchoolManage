<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <meta http-equiv='refresh' content='0;url=backup.html?path=${path }'>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript">
  	var i='数据备份中';
  	setInterval("goToBackup_view()",500);
  	function goToBackup_view(){
  		i=i+'.';
  		if(i=='数据备份中..........'){
  			i='数据备份中';
  		}
  		parent.dabaseManager_showBackuping("${path }info","<font color=red>"+i+"</font>");
  	}
  </script>
  </head>
</html>
