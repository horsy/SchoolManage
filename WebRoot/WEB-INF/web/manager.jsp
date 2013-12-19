<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
<!--
var url="${url}";
if(startWith(url,"http")){
	document.location.href=url;
}else{
	alert(url);
	window.close();
}

function startWith(str,subStr){
	var reg=new RegExp("^"+subStr);     
  	return reg.test(str);   
}
//-->
</script>