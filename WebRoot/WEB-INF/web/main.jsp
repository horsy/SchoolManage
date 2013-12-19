<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.school.publicSomething.PublicSTH"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ include file="./common/head.jsp"%>
	<body class="bg" onunload="javascript:closeWindow(event)">
		<!--
		<div id="bgCopyRightDiv" style="z-index:-100;position:absolute;left:0px;top:0px;width:100%;height:100%;">
			<table border="0" width="100%" height="100%">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td height="15px" align="center">
						<div class="login_bottom"></div>
					</td>
				</tr>
			</table>
		</div>
		<div id="bgVersionDiv" style="z-index:-100;position:absolute;left:0px;top:0px;width:100%;height:100%;">
			<table border="0" width="100%" height="100%">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td class="version">
						内部版本：<%=PublicSTH.VERSION%>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
		</div>
		-->
		<div id="mainHead" class="main_top">
			<table border="0" class="main_top_table">
				<tr>
					<td class="main_top_title">
						${mainDomain.titleName}
					</td>
					<s:iterator value="%{mainDomain.moduleList}">
						<td id='<s:property value="target" />' class="main_top_td" onclick="javascript:main_clickMenu('<s:property value="target" />')">
							<s:property value="moduleName" />
						</td>
					</s:iterator>
					<td>
						&nbsp;
					</td>
					<td class="main_top_wellcome_image">
					</td>
					<td class="main_top_wellcome">
						<nobr>
							${mainDomain.loginUser}，欢迎您!&nbsp;&nbsp;
							<span onclick="javascript:exit()" style="cursor: pointer;">退出</span>
						</nobr>
					</td>
				</tr>
			</table>
		</div>
		<div id="body_show">
		</div>
	</body>
	
	<script type="text/javascript">
		
		if (top.location != self.location){
			top.location = self.location;
		}
		window.name="main";

		var schoolTree;
 	  	var schoolTreeSetting = {
			async: true,
			asyncUrl : schoolTree_getAsyncUrl,  //获取节点数据的URL地址
//			showIcon : main_showIconForTree,
			asyncParam: ["name"],  //获取节点数据时，必须的数据名称，例如：id、name
			callback:{
				click:schoolTree_zTreeClick,
				beforeExpand:schoolTree_zTreeBeforeExpand
			}
		};
		var zNodes =[{name:'${mainDomain.systemName}',id:0,nodeType:'root',root:1,isParent:true}];
				
	  	function schoolTree_getAsyncUrl(treeNode){
	  		var url;
	  		if(treeNode){
	  			//if(treeNode.nodeType=='school'){
	  				url="getSchoolNode.html?id="+treeNode.id+"&type="+treeNode.nodeType+"&schoolId="+treeNode.schoolId;
	  			//}
	  		}
	  		return url;
	  	}
	  	function schoolTree_zTreeClick(event, treeId, treeNode){
	  		
	  		if(treeNode.nodeType=="department"){
	  			getGradeList(1,treeNode.id);
	  			//$("#schoolShow").load("gradeList.html?id="+treeNode.id+"&page=1&pageSize="+getLogPageSize()+getNoCache());
	  		}else if(treeNode.nodeType=="grade"){
	  			classList_getClassList(1,treeNode.id);
	  			//$("#schoolShow").load("classList.html?id="+treeNode.id+"&page=1&pageSize="+getLogPageSize()+getNoCache());
	  		}else if(treeNode.nodeType=="class"){
	  			//$("#schoolShow").load("studentList.html?classID="+treeNode.id+"&page=1&pageSize="+getLogPageSize()+getNoCache());
	  			studentList_getStudentList(1,treeNode.id);
	  		}else if(treeNode.nodeType=="student"){
	  			studentList_editStudent(treeNode.id);
	  		}else{
	  			getGroupList(treeNode.nodeType,treeNode.id);
	  		}
	  		
	  	}
	  	function getGroupList(type,id){
	  		$("#schoolShow").load("groupList.html?type="+type+"&id="+id+getNoCache());
	  	}
	  	function schoolTree_zTreeBeforeExpand(treeId, treeNode){
	  		schoolTree.reAsyncChildNodes(treeNode, "refresh");
			return true;
	  	}
	  	function getNoCache() {
			return "&anticache=" + Math.floor(Math.random() * 1000);
		}
		function showEquipment(id){
			var treeNode = schoolTree.getNodeByParam("nodeId",id);
			$("#equipmentShow").load("equipmentInfo.html?equipmentId="+treeNode.id+"&nodeType="+treeNode.nodeType+getNoCache());
		}
		
		


		function getShowBobyHeight(){
			var windowHeight;
			if (document.documentElement && document.documentElement.clientHeight
					&& document.documentElement.clientWidth) {
				windowHeight = document.documentElement.clientHeight;
			}
			var mainHeadHeight = document.getElementById("mainHead").offsetHeight;
			var showBodyHeight=windowHeight- mainHeadHeight-90;
			showBodyHeight=showBodyHeight>0?showBodyHeight:600;
			return showBodyHeight;
		}

		$(document).ready(function(){
			//main_changeTab('equipmentTab');
			//setBgDivHeight();
			//main_Config();
			main_clickMenu('student_info');
		});
		
		function main_Config(){
			var showBodyHeight=getShowBobyHeight();
			document.getElementById("menuDiv").style.height=showBodyHeight+90+"px";
			document.getElementById("menuShow").style.height=showBodyHeight+90+"px";
		}
		

		function setBgDivHeight(){
			var showBodyHeight=getShowBobyHeight();
			document.getElementById("bgCopyRightDiv").style.height=showBodyHeight+90+document.getElementById("mainHead").offsetHeight+"px";
			document.getElementById("bgVersionDiv").style.height=showBodyHeight+90+document.getElementById("mainHead").offsetHeight+"px";
			alert(document.getElementById("bgVersionDiv").style.height);
		}
	</SCRIPT>
</html>
