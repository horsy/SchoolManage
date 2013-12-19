/*************************************表单内容是否修改判断***************************************/
var oldValue = [];
function getValue(formId) {
	var nowValue = [];
	var form = document.getElementById(formId);
	var inputs = form.getElementsByTagName('input');
	for (i = 0; i < inputs.length; i++) {
		nowValue[nowValue.length] = inputs.item(i).value;
	}
	var selected = form.getElementsByTagName('select');
	for (i = 0; i < selected.length; i++) {
		nowValue[nowValue.length] = selected.item(i).options[selected.item(i).selectedIndex].value;
	}
	return nowValue;
}
function isChange(oldValue, newValue) {
	var flag = false;
	for (i = 0; i < oldValue.length; i++) {
		if (oldValue[i] != newValue[i]) {
			flag = true;
			break;
		}
	}
	return flag;
}

function toSubmit(formId, action) {
	var newValue = getValue(formId);
	if (isChange(oldValue, newValue)) {
		eval("document.forms." + formId + ".submit();");
		//alert("有信息修改");
	} else {
		alert("无信息需要更新");
		var bd=document.getElementById('bgDiv_2');
		if(bd){
			dialog_close('bgDiv_2')
		}
	}
}

/************************************end 表单判断*********************************************/

function closeWindow(event) {
	if (event.clientX <= 0 && event.clientY < 0) {
		exit();
	}
}
function exit() {
	document.location.href = "exit.html";
}

/**************************************************ajax Form****************************************/
/**
 * 用AJAX提交表单，此函数主要获取表单的内容。
 * @param {Object} formName 需要提交表单的form
 */
function ajaxSubmitForm(formName, selectedId, objName, treeName) {
	var form = document.getElementById(formName);
	var elements = form.elements;// Enumeration the form elements            
	var element;
	var i;
	var postContent = "";// Form contents need to submit   
	for (i = 0; i < elements.length; ++i) {
		var element = elements[i];
		if (element.type == "text" || element.type == "textarea"
				|| element.type == "hidden") {
			postContent += encodeURIComponent(element.name) + "="
					+ encodeURIComponent(element.value) + "&";
		} else if (element.type == "select-one"
				|| element.type == "select-multiple") {
			var options = element.options, j, item;
			for (j = 0; j < options.length; ++j) {
				item = options[j];
				if (item.selected) {
					postContent += encodeURIComponent(element.name) + "="
							+ encodeURIComponent(item.value) + "&";
				}
			}
		} else if (element.type == "checkbox" || element.type == "radio") {
			if (element.checked) {
				postContent += encodeURIComponent(element.name) + "="
						+ encodeURIComponent(element.value) + "&";
			}
		} else if (element.type == "file") {
			if (element.value != "") {
				postContent += encodeURIComponent(element.name) + "="
						+ encodeURIComponent(element.value) + "&";
			}
		} else {
			postContent += encodeURIComponent(element.name) + "="
					+ encodeURIComponent(element.value) + "&";
		}
	}
	//alert(postContent);            
	ajaxSubmit(form.action, form.method, postContent, selectedId, objName,
			treeName);
}
/**
 * 用AJAX异步提交表单内容。
 * @param {Object} url - the url to do submit 
 * @param {Object} method - "get" or "post"
 * @param {Object} postContent - the string with values to be submited  
 */
function ajaxSubmit(url, method, postContent, selectedId, objName, treeName) {

	if (window.XMLHttpRequest) {// code for Mozilla, etc. 
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {// code for IE
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	if (xmlhttp) {
		xmlhttp.onreadystatechange = function() {
			// if xmlhttp shows "loaded"                                
			if (xmlhttp.readyState == 4) {
				ajaxSubmitReturn(selectedId, objName, treeName, xmlhttp);
			}
		};
		if (method.toLowerCase() == "get") {
			xmlhttp.open("GET", url + "?" + postContent, true);
			xmlhttp.send(null);
		} else if (method.toLowerCase() == "post") {
			xmlhttp.open("POST", url, true);
			xmlhttp.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xmlhttp.send(postContent);
		}
	} else {
		alert("不能创建XMLHttpRequest。");
	}
}
function ajaxSubmitReturn(selectedId, objName, treeName, xmlhttp) {
	var idArray = selectedId.split("_");
	var idArrayLen = idArray.length - 1;
	for (i = 0; i < idArrayLen; i++) {
		main_delTreeNode(objName + idArray[i], treeName);
		ajaxDelList("obj" + idArray[i]);
	}
	if (xmlhttp && xmlhttp.responseText) {
		alert(xmlhttp.responseText);
	}
}
function ajaxDelList(objId) {
	var obj = document.getElementById(objId);
	var tr = obj.parentNode.parentNode;
	var tbody = tr.parentNode;
	tbody.removeChild(tr);
}

/*************************************end ajax form************************************/

/*************************************************DIV 拖动********************************/
/**
 * 拖动类
 * @param {Object} divTitle 标题DIV名称
 * @param {Object} divBody BODY DIV名称
 * @memberOf {TypeName} 
 */
function dragMing(divTitle, divBody) {
	var obj = this;
	this.divTitle = divTitle;
	this.divBody = divBody;
	this.deltaX = 0;
	this.deltaY = 0;
	/**
	 * 开始拖动
	 * @param {Object} dragEvent 拖动事件
	 */
	function dragStart(dragEvent) {
		obj.deltaX = dragEvent.clientX - $(obj.divBody).offset().left;
		obj.deltaY = dragEvent.clientY - $(obj.divBody).offset().top;
		$(document).bind("mousemove", dragMove);
		$(document).bind("mouseup", dragStop);
		dragEvent.preventDefault();
	}
	/**
	 * 执行拖动，实质为修改DIV的外边距
	 * @param {Object} dragEvent 拖动事件
	 */
	function dragMove(dragEvent) {
		$(obj.divBody).css( {
			"left" : (dragEvent.clientX - obj.deltaX) + "px",
			"top" : (dragEvent.clientY - obj.deltaY) + "px"
		});
		dragEvent.preventDefault();
	}
	/**
	 * 拖动结束，取消鼠标绑定
	 */
	function dragStop() {
		$(document).unbind("mousemove", dragMove);
		$(document).unbind("mouseup", dragStop);
	}
	$(obj.divTitle).bind("mousedown", dragStart);
}
/*************************************end DIV 拖动**************************************/

/*************************************open dialog**************************************/
/**
 * 弹出窗口
 * @param {Object} bgDiv 弹出窗口的背景DIV ID(已存在，只是隐藏掉了)，显示窗口根据bgDIV后加"_dialogBody"和"_dialogTitle"来命名
 * @param {Object} theUrl 弹出窗口上显示内容的URL
 * @param {Object} titleValue 弹出窗口上标题栏文字
 * @param {Object} divWidth 弹出窗口的宽度
 * @param {Object} divHeight 弹出窗口的高度
 */
function dialog_ming(theUrl, titleValue, divWidth, divHeight, nodeId,
		headStyleClass, bodyStyleClass) {
	var openDialog = new dialog_class(theUrl, titleValue, divWidth, divHeight,
			nodeId, headStyleClass, bodyStyleClass);
	openDialog.dialog_create();
	openDialog.dialog_open();
}

function dialog_class(theUrl, titleValue, divWidth, divHeight, nodeId,
		headStyleClass, bodyStyleClass) {
	var obj = this;
	//this.bgDiv = "bgDiv_"+addType+"_" + Math.floor(Math.random() * 1000);
	this.bgDiv = "bgDiv_" + nodeId;
	this.titleValue = titleValue;
	this.divWidth = divWidth;
	this.divHeight = divHeight;
	this.headStyleClass = headStyleClass;
	this.bodyStyleClass = bodyStyleClass;
	//dialog_create();
	//dialog_open();

	this.dialog_create = function() {
		var bgDivObj = document.createElement("div");
		bgDivObj.setAttribute("id", obj.bgDiv);
		//		bgDivObj.setAttribute("class", "bgDiv");
		//		bgDivObj.setAttribute("width", obj.divWidth);
		//		bgDivObj.setAttribute("height", obj.divHeight);
		//		bgDivObj.setAttribute("bgcolor", "red");
		//		bgDivObj.setAttribute("class",obj.headStyleClass);
		document.body.appendChild(bgDivObj);
		dialog_position(obj.divWidth, obj.divHeight, "1", obj.bgDiv);

	}

	this.dialog_open = function() {

		var dialogDivBodyHead = "<div id='" + obj.bgDiv
				+ "_dialogBody' class='" + obj.bodyStyleClass + "'>";
		var dialogDivTitleHead = "<div id='" + obj.bgDiv
				+ "_dialogTitle' class='" + obj.headStyleClass + "'>";
		var dialogDivTitle = "";

		var divEnd = "</div>";

		$.ajax( {
			url : theUrl,
			cache : false,
			success : function(html) {
				document.getElementById(obj.bgDiv).innerHTML = "";
				$("#" + obj.bgDiv).append(
						dialogDivTitleHead + obj.titleValue + divEnd);
				$("#" + obj.bgDiv).append(dialogDivBodyHead + html + divEnd);
				var darg = new dragMing("#" + obj.bgDiv + "_dialogTitle", "#"
						+ obj.bgDiv);
			}
		});
	}
	/**
	 * 关闭弹出窗口
	 * @param {Object} bgDiv 弹出窗口的背景DIV ID
	 */
	this.dialog_close = function() {
		document.body.removeChild(document.getElementById(obj.bgDiv));
	}
}
/**
 * 关闭弹出窗口
 * @param {Object} bgDiv 弹出窗口的背景DIV ID
 */
function dialog_close(bgDiv) {
	document.body.removeChild(document.getElementById(bgDiv));
	document.body.removeChild(document.getElementById(bgDiv + "_bg"));
}

/**
 * 将弹出窗口居中显示 
 * @param {Object} TB_WIDTH 弹出窗口的宽度 
 * @param {Object} TB_HEIGHT 弹出窗口的高度
 * @param {Object} zIndex 显示优先级 css
 * @param {Object} divID 弹出显示窗口的DIV ID
 */
function dialog_position(TB_WIDTH, TB_HEIGHT, zIndex, divID) {

	var de = document.documentElement;
	var w = self.innerWidth || (de && de.clientWidth)
			|| document.body.clientWidth;
	var h = self.innerHeight || (de && de.clientHeight)
			|| document.body.clientHeight;

	if (window.innerHeight && window.scrollMaxY) {
		yScroll = window.innerHeight + window.scrollMaxY;
	} else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
		yScroll = document.body.scrollHeight;
	} else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
		yScroll = document.body.offsetHeight;
	}

	$("#" + divID).css( {
		width : TB_WIDTH + "px",
		height : TB_HEIGHT + "px",
		left : ((w - TB_WIDTH) / 2) + "px",
		top : ((h - TB_HEIGHT) / 2) + "px",
		position : "absolute",
		"z-index" : zIndex,
		bottom : "0px",
		right : "0px",
		"text-align" : "center"
	});
}
/*************************************** end open dialog************************************/

/*************************************open dialog old**************************************/
/**
 * 弹出窗口
 * @param {Object} bgDiv 弹出窗口的背景DIV ID(已存在，只是隐藏掉了)，显示窗口根据bgDIV后加"_dialogBody"和"_dialogTitle"来命名
 * @param {Object} theUrl 弹出窗口上显示内容的URL
 * @param {Object} titleValue 弹出窗口上标题栏文字
 * @param {Object} divWidth 弹出窗口的宽度
 * @param {Object} divHeight 弹出窗口的高度
 */
function dialog_ming_adduser(theUrl, titleValue, divWidth, divHeight, nodeId,
		headStyleClass, bodyStyleClass) {
	var openDialog = new dialog_class_adduser(theUrl, titleValue, divWidth,
			divHeight, nodeId, headStyleClass, bodyStyleClass);
	openDialog.dialog_create();
	openDialog.dialog_open();
}

function dialog_class_adduser(theUrl, titleValue, divWidth, divHeight, nodeId,
		headStyleClass, bodyStyleClass) {
	var obj = this;
	//this.bgDiv = "bgDiv_"+addType+"_" + Math.floor(Math.random() * 1000);
	this.bgDiv = "bgDiv_" + nodeId;
	this.titleValue = titleValue;
	this.divWidth = divWidth;
	this.divHeight = divHeight;
	this.headStyleClass = headStyleClass;
	this.bodyStyleClass = bodyStyleClass;
	//dialog_create();
	//dialog_open();

	this.dialog_create = function() {
		var bgDivBg = document.createElement("div");
		bgDivBg.setAttribute("id", obj.bgDiv + "_bg");
		bgDivBg.setAttribute("class", "bgDiv_bg");
		document.body.appendChild(bgDivBg);
		var bgDivObj = document.createElement("div");
		bgDivObj.setAttribute("id", obj.bgDiv);
		bgDivObj.setAttribute("class", "bgDiv");
		document.body.appendChild(bgDivObj);
	}

	this.dialog_open = function() {
		var titleHeight = "30";
		var dialogDivBodyHead = "<div id='" + obj.bgDiv
				+ "_dialogBody' class='" + obj.bodyStyleClass
				+ "' style='width:" + obj.divWidth + "px;height:"
				+ obj.divHeight + "px;'>";
		var dialogDivTitleHead = "<div id='"
				+ obj.bgDiv
				+ "_dialogTitle' class='"
				+ obj.headStyleClass
				+ "' style='height:"
				+ titleHeight
				+ "px;width:"
				+ obj.divWidth
				+ "px;text-align:center;background-color:#94BEFF;cursor:pointer;'>";

		var dialogDivBody = "<div id='" + obj.bgDiv
				+ "_dialogBodyBody' class='" + obj.bodyStyleClass
				+ "' style='width:" + obj.divWidth + "px;height:"
				+ (obj.divHeight - titleHeight) + "px;'>";

//		var dialogDivTitle = "<table class='addUserBodyTitleTable' height='"
//				+ titleHeight
//				+ "px'><tr><td class='addImageTd'><img src='image/+.png'></td><td class='addUserBodyTitleTdText'>"
//				+ obj.titleValue
//				+ "</td><td>&nbsp;</td><td class='addCloseImageTd'><img class='imageClose' id='"
//				+ obj.bgDiv
//				+ "_close' src='image/close_1.png'></img></td></tr></table>";
		var dialogDivTitle = "<table class='addUserBodyTitleTable' height='"
				+ titleHeight
				+ "px'><tr><td class='addImageTd'><img src='image/+.png'></td><td class='addUserBodyTitleTdText'>"
				+ obj.titleValue
				+ "</td><td>&nbsp;</td></tr></table>";
		var dialogDivEndTitle = "</div>";
		var dialogDivEndBody = "</div>";

		$
				.ajax( {
					url : theUrl,
					cache : false,
					success : function(html) {
						document.getElementById(obj.bgDiv).innerHTML = "";
						html = "<table width="
								+ obj.divWidth
								+ "px height="
								+ (obj.divHeight - titleHeight)
								+ "px><tr><td width=100% height=100% align=center valign=middle>"
								+ html + "</td></tr></table>";
						$("#" + obj.bgDiv).append(
								dialogDivBodyHead + dialogDivTitleHead
										+ dialogDivTitle + dialogDivEndTitle
										+ dialogDivBody + html
										+ dialogDivEndBody + dialogDivEndBody);

						var dialogWidth = document.getElementById(obj.bgDiv
								+ "_dialogBody").clientWidth;
						var dialogHeight = document.getElementById(obj.bgDiv
								+ "_dialogBody").clientHeight;
						dialog_position(dialogWidth, dialogHeight, 10,
								obj.bgDiv + "_dialogBody");
						var darg = new dragMing("#" + obj.bgDiv
								+ "_dialogTitle", "#" + obj.bgDiv
								+ "_dialogBody");
						$("#" + obj.bgDiv + "_close").bind("click",
								obj.dialog_close);
					}
				});
	}
	/**
	 * 关闭弹出窗口
	 * @param {Object} bgDiv 弹出窗口的背景DIV ID
	 */
	this.dialog_close = function() {
		document.body.removeChild(document.getElementById(obj.bgDiv));
		document.body.removeChild(document.getElementById(obj.bgDiv + "_bg"));
	}
}
/*************************************** end open dialog old************************************/

/**************************************loign page*************************/
function loginPage_onMouseOver(obj) {
	obj.className = 'login_page_inputing_div';
}
function loginPage_onMouseOut(obj) {
	if (document.getElementById(document.activeElement.id).parentNode != obj) {
		obj.className = 'login_page_input_div';
	}
}
function loginPage_changeClassName(obj, name, ev) {
	if (ev.type == "focus") {
		document.getElementById("blurId").value = obj.id;
	} else if (ev.type == "mouseout") {
		if (obj.id == "remenberCheckBox") {
			if (document.getElementById("remenberLoginName").value == "1") {
				return;
			}
		} else if (document.getElementById("blurId").value == obj.id) {
			return;
		}
	} else if (ev.type == "mouseover") {
		if (obj.id == "remenberCheckBox") {
			if (document.getElementById("remenberLoginName").value == "1") {
				return;
			}
		}
	}
	obj.className = name;
}function loginPage_onclickCheckBox() {
	var isChecked = document.getElementById("remenberLoginName").value;
	if (isChecked == "1") {
		document.getElementById("remenberCheckBox").className = "login_page_check_box";
		document.getElementById("remenberLoginName").value = "0";
	} else {
		document.getElementById("remenberCheckBox").className = "login_page_checked_box";
		document.getElementById("remenberLoginName").value = "1";
	}
}
function loginPage_submit() {
	var isRemenber = document.getElementById("remenberLoginName").value;
	loginPage_setCookie(document.getElementById("j_username").value, isRemenber);
	document.forms.loginForm.submit();
}

function loginPage_setCookie(userName, isRemenber) {
	var then = new Date();
	then.setTime(then.getTime() + 1866240000000);
	if (isRemenber == "1") {
		document.cookie = "jf_j_username=" + userName + ";expires="
				+ then.toGMTString();
	} else {
		document.cookie = "jf_j_username=;expires=" + then.toGMTString();
	}

}
function loginPage_changePassword() {
	var password = document.getElementById("j_password").value;
	if (password.length > 2) {
		document.getElementById("loginButton").className = "login_button_in";
	} else {
		document.getElementById("loginButton").className = "login_button";
	}
}
function loginPage_getCookie() {
	var userName;
	var cookieString = new String(document.cookie)
	var cookieHeader = "jf_j_username="
	var beginPosition = cookieString.indexOf(cookieHeader)
	cookieString = cookieString.substring(beginPosition);
	var ends = cookieString.indexOf(";");
	if (ends != -1) {
		cookieString = cookieString.substring(0, ends);
	}
	if (beginPosition > -1) {
		userName = cookieString.substring(cookieHeader.length);
		if (userName != "") {
			document.getElementById('j_username').value = userName;
			loginPage_onclickCheckBox();
		}
	}
}
/*****************************end login page************************/

/******************************main start*************************/
function main_setSipAndZfInfoDivHeight() {
	var windowHeight;
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		windowHeight = document.documentElement.clientHeight;
	}
	var mainHeadHeight = document.getElementById("mainHead").offsetHeight;
	document.getElementById("equipmentShow").style.height = windowHeight
			- mainHeadHeight + "px";
	var equipmentShowDivWidth = document.getElementById("equipmentShow").offsetWidth;
	document.getElementById("sipAndZfTable").style.width = equipmentShowDivWidth
			- 195 + "px";
}
function main_setTreeDivHeight() {
	var windowHeight;
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		windowHeight = document.documentElement.clientHeight;
	}
	var mainHeadHeight = document.getElementById("mainHead").offsetHeight;
	document.getElementById("equipmentTreeDiv").style.height = windowHeight
			- mainHeadHeight + "px";
}
function main_changeTabBackground(obj) {
	document.getElementById("student_info").className = "main_top_td";
	document.getElementById("teacher_info").className = "main_top_td";
	document.getElementById("user_info").className = "main_top_td";
	document.getElementById("log_info").className = "main_top_td";
	document.getElementById("system_config").className = "main_top_td";
	document.getElementById("search_info").className = "main_top_td";
	eval('document.getElementById("' + obj + '").className="main_top_td_selected";');
}
function main_clickMenu(target){
	if(target=='student_info'){
		$('#body_show').load('studentDiv.html',{'aaa':'bbb'});
	}else if(target=='teacher_info'){
		teacherList_getTeacherList(1);
	}else if(target=='user_info'){
		main_userInfo();
	}else if(target=='log_info'){
		getLogList(1);
	}else if(target=='system_config'){
		$('#body_show').load('systemConfigDiv.html',{'aaa':'bbb'});
	}else if(target=='search_info'){
		main_searcherInfo();
	}
	main_changeTabBackground(target);
}
function main_searcherInfo(){
	$('#body_show').load('search.html',{'aaa':'bbb'});
}
function main_userInfo(){
	$('#body_show').load('userInfo.html', {
		'aa' : 'aa'
	});
}
function main_instanceKHD() {
	$('#body_show').load('user.html', {
		'page' : 1,
		'pageSize' : getLogPageSize()
	});
}
function main_instanceRight() {
	//var khdContent=document.getElementById("body_show").innerHTML;
	//if(khdContent.length==0){
	$('#body_show').load('rightSet.html', {
		'aaa' : 'bbb'
	});
	//}
}

function main_instanceLog() {
	//var logContent=document.getElementById("blogs_spacerank_3").innerHTML;
	//if(logContent.length==0){

	$('#body_show').load('logList.html?page=1&pageSize=' + getLogPageSize(), {
		'aaa' : 'bbb'
	});
	//}
}

function main_intstanceEditUser() {
	$('#body_show').load('editAdmin.html', {
		'aaa' : 'bbb'
	});
}

function main_instanceDBManager() {
	$('#body_show').load('databaseManager.html', {
		'aaa' : 'bbb'
	});
}

function main_instanceInspectionTree() {
	main_setTreeDivHeight();
	var inspectionTreeContent = document.getElementById("equipmentTree").innerHTML;
	if (inspectionTreeContent.length == 0) {
		equipmentTree = $("#equipmentTree").zTree(equipmentTreeSetting, zNodes);
		var rootNode = equipmentTree.getNodeByParam("id", 0);
		equipmentTree.expandNode(rootNode, true, false);
	}
}
/***************************end main*******************************/
/*********************************systemConfig***************************/
function systemSet(target){
	if(target=='schoolInfo'){
		$("#systemConfigShow").load("schoolInfo.html",{"schoolId":"local"});
	}else if(target=='teacherLevel'){
		$("#systemConfigShow").load("teacherLevel.html",{"aaa":"aaa"});
	}else if(target=='nationList'){
		$("#systemConfigShow").load("nationList.html",{"aaa":"aaa"});
	}else if(target=='bloodGroup'){
		$("#systemConfigShow").load("bloodGroup.html",{"aaa":"aaa"});
	}else if(target=='politicalOrientation'){
		$("#systemConfigShow").load("politicalOrientation.html",{"aaa":"aaa"});
	}else if(target=='studentStatus'){
		$("#systemConfigShow").load("studentStatusList.html",{"aaa":"aaa"});
	}
	systemSet_setSelect(target);
}
function systemSet_setSelect(target){
	document.getElementById("schoolInfo").className = "systemConfigTd";
	document.getElementById("teacherLevel").className = "systemConfigTd";
	document.getElementById("nationList").className = "systemConfigTd";
	document.getElementById("bloodGroup").className = "systemConfigTd";
	document.getElementById("politicalOrientation").className = "systemConfigTd";
	document.getElementById("studentStatus").className = "systemConfigTd";
	eval('document.getElementById("' + target + '").className="systemConfigTdSelected";');
}
function teacherLevel_add(){
	dialog_ming_adduser('addTeacherLevel.html', '添加教师职位', '500', '220',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function teacherLevel_del(tableId){
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的职位");
	} else {
		if (confirm("确认删除选中的职位？\n删除职位将同时设置原教师的职位为未知。")) {
			$("#delUserForm").load('delTeacherType.html', {
				'id' : delUser
			});
		}
	}
}
function nationList_add(){
	dialog_ming_adduser('addNation.html', '添加民族', '500', '220',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function nationList_del(tableId){
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的民族");
	} else {
		if (confirm("确认删除选中的民族？\n删除民族将同时设置原学生的民族为未知。")) {
			$("#delUserForm").load('delNation.html', {
				'id' : delUser
			});
		}
	}
}
function bloodGroup_add(){
	dialog_ming_adduser('addBloodGroup.html', '添加血型', '500', '220',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function bloodGroup_del(tableId){
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的血型");
	} else {
		if (confirm("确认删除选中的血型？\n删除血型将同时设置原学生的血型为未知。")) {
			$("#delUserForm").load('delBloodGroup.html', {
				'id' : delUser
			});
		}
	}
}
function politicalOrientation_add(){
	dialog_ming_adduser('addPoliticalOrientation.html', '添加政治面貌', '500', '220',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function politicalOrientation_del(tableId){
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的政治面貌");
	} else {
		if (confirm("确认删除选中的政治面貌？\n删除政治面貌将同时设置原学生的政治面貌为未知。")) {
			$("#delUserForm").load('delPoliticalOrientation.html', {
				'id' : delUser
			});
		}
	}
}
function studentStatus_add(){
	dialog_ming_adduser('addStudentStatus.html', '添加学生状态', '500', '220',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function studentStatus_del(tableId){
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的学生状态");
	} else {
		if (confirm("确认删除选中的学生状态？\n删除学生状态将同时设置原学生的学生状态为未知。")) {
			$("#delUserForm").load('delStudentStatus.html', {
				'id' : delUser
			});
		}
	}
}
/***************************************end systemConfig*******************/

/**********************************start userList***********************************/
function selectAll(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			document.getElementById(checkList[i].id).checked = true;
		}
	}
	document.getElementById("selectAllDiv").className = "select_all_selected";
}

function oppositeSelect(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				document.getElementById(checkList[i].id).checked = false;
			} else {
				document.getElementById(checkList[i].id).checked = true;
			}
		}
	}
	document.getElementById("selectAllDiv").className = "select_all";
	var oppositeSelectDivClass = document.getElementById("oppositeSelectDiv").className;
	if (oppositeSelectDivClass == "select_all") {
		document.getElementById("oppositeSelectDiv").className = "select_all_selected";
	} else {
		document.getElementById("oppositeSelectDiv").className = "select_all";
	}
}

function clearCheckBox(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			document.getElementById(checkList[i].id).checked = false;
		}
	}
}

function deleteUser(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的客户端");
	} else {
		if (confirm("确认删除选中的客户端？")) {
			$("#delUserForm").load('delUser.html', {
				'userId' : delUser
			});
		}
	}
}
function editUser(userId) {
	dialog_ming_adduser('editUser.html?userId=' + userId, '编辑用户', '800', '520',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function userList_setHeight() {
	var windowHeight;
	if (document.documentElement && document.documentElement.clientHeight
			&& document.documentElement.clientWidth) {
		windowHeight = document.documentElement.clientHeight;
	}
	var mainHeadHeight = document.getElementById("mainHead").offsetHeight;
	document.getElementById("userListDiv").style.height = windowHeight
			- mainHeadHeight + "px";
}
function getUserList(page) {
	$('#body_show').load('user.html', {
		'page' : page,
		'pageSize' : getLogPageSize()
	});
}
/**********************************end userList*******************************/
/***********************************start gradeList*************************/
function getGradeList(page, id) {
	$('#schoolShow').load('gradeList.html', {
		'id' : id,
		'page' : page,
		'pageSize' : getLogPageSize()
	});
}
function gradeList_editGrade(gradeId) {
	dialog_ming_adduser('editGrade.html?id=' + gradeId, '年级信息', '700', '380',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}

function gradeList_del(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的年级");
	} else {
		if (confirm("确认删除选中的年级？\n删除年级将同时删除其下的班级及所有学生信息。")) {
			var schoolId = document.getElementById("gradeList_schoolId").value;
			$("#delUserForm").load('delGrade.html', {
				'id' : delUser,
				'schoolId' : schoolId
			});
		}
	}
}
/********************************end gradeList********************************/

/*******************************start classList************************/
function showClassList(gradeId) {
	$('#schoolShow').load('classList.html', {
		'id' : gradeId,
		'page' : 1,
		'pageSize' : getLogPageSize()
	});
}
function classList_editClass(classId) {
	dialog_ming_adduser('editClass.html?id=' + classId, '班级信息', '700', '420',
			2, 'addUserHeadStyleClass', 'addUserBodyStyleClass');
}
function classList_getClassList(page, gradeId) {
	$("#schoolShow").load(
			"classList.html?id=" + gradeId + "&page=" + page + "&pageSize="
					+ getLogPageSize() + getNoCache());
}
function classList_del(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的班级");
	} else {
		if (confirm("确认删除选中的班级？\n删除班级将同时删除班级下的所有学生信息。")) {
			var gradeId = document.getElementById("classList_gradeId").value;
			$("#delUserForm").load('delClass.html', {
				'id' : delUser,
				'gradeId' : gradeId
			});
		}
	}
}
/*******************************end classList*********************************/

/******************************start studentList**********************************/
function addStudent(classId) {
	$("#schoolShow").load("studentInfo.html?classId=" + classId + getNoCache());
}
function addStudent_cancel(classId) {
	$("#schoolShow").load(
			"studentList.html?classID=" + classId + "&page=1&pageSize="
					+ getLogPageSize() + getNoCache());
}
function showCalendar(obj, evt) {
	var calendar = new Calendar();
	calendar.show(obj);
	//new Calendar().show(obj);
	$(document).on("click", function() {
		calendar.hide();
	});
	var e = (evt) ? evt : window.event;
	if (window.event) {
		e.cancelBubble = true;
	} else {
		e.stopPropagation();
	}
}
function showStudentList(classId) {
	$("#schoolShow").load(
			"studentList.html?classID=" + classId + "&page=1&pageSize="
					+ getLogPageSize() + getNoCache());
}

function studentList_editStudent(id) {
	$("#schoolShow").load("studentInfo.html?id=" + id + getNoCache());
}
function studentList_beforeHandAdd(classId) {
	dialog_ming_adduser('beforeHandAdd.html?classId=' + classId + getNoCache(),
			'预添加', '700', '420', 2, 'addUserHeadStyleClass',
			'addUserBodyStyleClass')
}
function studentList_getStudentList(page, classId) {
	$('#schoolShow').load('studentList.html', {
		'classID' : classId,
		'page' : page,
		'pageSize' : getLogPageSize()
	});
}
function studentList_del(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的学生");
	} else {
		if (confirm("确认删除选中的学生？")) {
			var classId = document.getElementById("classId").value;
			$("#delUserForm").load('delStudent.html', {
				'id' : delUser,
				'classId' : classId
			});
		}
	}
}
/***************************end studentList*************************************/
/************************addStudent start*********************************/
function uploadPhoto(){
	document.getElementById("uploadPhoto").click();
}
function setPhoto(photo){
	document.getElementById("photoImg").src=photo;
}
function setPhotoName(photoName){
	document.getElementById("photoName").value=photoName;
}
/****************************end addStudent************************************/

/******************************editStudent start************************************/
function review(id) {
	dialog_ming_adduser('review.html?id=' + id + getNoCache(), '审核', '400',
			'220', 2, 'addUserHeadStyleClass', 'addUserBodyStyleClass')
}
function upload(){
	setActionToUploadFile();
	var addStudentForm=document.getElementById("addStudentForm");
	if(addStudentForm){
		document.forms.addStudentForm.submit();
	}else{
		document.forms.updateStudentForm.submit();
	}
	
	
}
function setActionToUploadFile(){
	var addStudentForm=document.getElementById("addStudentForm");
	if(addStudentForm){
		document.getElementById("addStudentForm").action="uploadPhoto.html";
		document.getElementById("addStudentForm").encoding ="multipart/form-data";
	}else{
		document.getElementById("updateStudentForm").action="uploadPhoto.html";
		document.getElementById("updateStudentForm").encoding ="multipart/form-data";
	}
}
function setActionToStudent(){
	var addStudentForm=document.getElementById("addStudentForm");
	if(addStudentForm){
		document.getElementById("addStudentForm").action="saveStudent.html";
		document.getElementById("addStudentForm").encoding ="application/x-www-form-urlencoded";
	}else{
		document.getElementById("updateStudentForm").action="saveStudent.html";
		document.getElementById("updateStudentForm").encoding ="application/x-www-form-urlencoded";
	}
	
}

/*****************************end editStudent****************************/

/*********************************logList**********************************/
function getLogList(page) {
	$('#body_show').load('logList.html', {
		'page' : page,
		'pageSize' : getLogPageSize()
	});
}
function getLogPageSize() {
	var pageSize = parseInt(getShowBobyHeight() / 35);
	return pageSize;
}

/*******************************end logList*****************************/

/********************************database manager*****************************/
function getDiskInfo() {
	$("#diskList").load("diskInfo.html", {
		"aa" : "bb"
	});
}
function backup(path) {
	if (confirm("备份数据库到" + path + "盘？")) {
		document.forms.backupForm.path.value = path;
		document.forms.backupForm.submit();
	}
	//	document.getElementById("backupFrame").location.href="backup.html?path="+path;
}
/************************end database manager***************************************/

/**************************start teacherList***************************************/
function teacherList_addTeacher() {
	dialog_ming_adduser('addTeacher.html?aa=aa' + getNoCache(), '添加教师', '700',
			'420', 2, 'addUserHeadStyleClass', 'addUserBodyStyleClass')
}
function teacherList_getTeacherList(page) {
	$('#body_show').load('teacherList.html', {
		'page' : page,
		'pageSize' : getLogPageSize()
	});
}
function teacherList_del(tableId) {
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要删除的教师");
	} else {
		if (confirm("确认删除选中的教师？")) {
			$("#delUserForm").load('delTeacher.html', {
				'id' : delUser
			});
		}
	}
}
function teacherList_editTeacher(id){
	dialog_ming_adduser('editTeacher.html?id='+id + getNoCache(), '教师信息', '700',
			'420', 2, 'addUserHeadStyleClass', 'addUserBodyStyleClass')
}

/******************************end teacherList***************************************/
/**************************start groupList********************************************/
function groupInfoList_editGroup(id,groupType){
	dialog_ming_adduser('editGroup.html?id='+id + getNoCache(), groupType, '500',
			'220', 2, 'addUserHeadStyleClass', 'addUserBodyStyleClass')
}
function groupList_del(tableId){
	var checkList = document.getElementById(tableId).getElementsByTagName(
			"input");
	var delUser = "";
	for ( var i = 0; i < checkList.length; i++) {
		if (checkList[i].type == "checkbox") {
			if (document.getElementById(checkList[i].id).checked == true) {
				delUser = delUser + "_" + checkList[i].id;
			}
		}
	}
	if (delUser.length == 0) {
		alert("请选择要内容");
	} else {
		if (confirm("确认删除选中内容？\n删除的同时将删除所有下属及年级、班级及所有学生信息。")) {
			var schoolId = document.getElementById("gradeList_schoolId").value;
			$("#delUserForm").load('delGroup.html', {
				'id' : delUser,
				'schoolId' : schoolId
			});
		}
	}
}
/************************end groupList********************************************/

/************************search********************************/
function search(){
	var name=document.getElementById("name").value;
	if(name.length==0){
		alert("请输入查询类容");
		return;
	}
	$("#schoolShow").load('searcherStudent.html', {
				'name' : name
			});
}

/***********************end search**************************/
