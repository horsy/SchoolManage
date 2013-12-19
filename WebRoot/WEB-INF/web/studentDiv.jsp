<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="height: 100%; width: 100%;">
	<div id='schoolDiv' class='divTree'>
		<ul id='schoolTree' class='tree'></ul>
	</div>
	<div id='schoolShow' class='divShow'></div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var showBodyHeight=getShowBobyHeight();
		document.getElementById("schoolDiv").style.height=showBodyHeight+80+"px";
		document.getElementById("schoolShow").style.height=showBodyHeight+80+"px";
		
		var schoolTreeContent = document.getElementById("schoolTree").innerHTML;
		if (schoolTreeContent.length == 0) {
			schoolTree = $("#schoolTree").zTree(schoolTreeSetting, zNodes);
			var rootNode = schoolTree.getNodeByParam("root", 1);
			schoolTree.expandNode(rootNode, true, false);
		}
		var rootNode = schoolTree.getNodeByParam("root", 1);
		//schoolTree.expandNode(rootNode, true, false);
		//getGradeList(1,rootNode.id);
		getGroupList(rootNode.nodeType,rootNode.id);
	});
</script>