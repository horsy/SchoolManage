<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div id="userListDiv" style="overflow-y: auto;">
	<div class="show_user_list_div">
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<br />
		<table id="userTable" class="show_user_list_table">
			<tr class="show_table_head_tr">
				<td>
					&nbsp;
				</td>
				<td>
					登录名
				</td>
				<td class="show_table_head_line">
					用户名
				</td>
				<td class="show_table_head_line">
					最后登录时间
				</td>
				<td class="show_table_head_line">
					最后登录IP
				</td>
				<td class="show_table_head_line">
					是否在线
				</td>
				<td class="show_table_head_line">
					用户权限
				</td>
			</tr>
			<s:iterator value="%{userList.userList}" id="list" status="objList">
				<tr
					class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>"
					onmouseover='this.className="moveIn";'
					onmouseout='this.className="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>";'>
					<td class=TdBody_checkBox>
						<input type="checkbox"
							name="obj<s:text name='#list.userId'></s:text>"
							id="obj<s:text name='#list.userId'></s:text>" onclick="document.getElementById('selectAllDiv').className='select_all';">
					</td>
					<td
						onclick="javascript:editUser(<s:text name='#list.userId'></s:text>)">
						<s:text name="#list.loginName"></s:text>
					</td>
					<td
						onclick="javascript:editUser(<s:text name='#list.userId'></s:text>)">
						<s:text name="#list.displayName"></s:text>
					</td>
					<td
						onclick="javascript:editUser(<s:text name='#list.userId'></s:text>)">
						<s:text name="#list.lastDate"></s:text>
					</td>
					<td
						onclick="javascript:editUser(<s:text name='#list.userId'></s:text>)">
						<s:text name="#list.lastIp"></s:text>
					</td>
					<td
						onclick="javascript:editUser(<s:text name='#list.userId'></s:text>)">
						<s:text name="#list.isOnline"></s:text>
					</td>
					<td
						onclick="javascript:editUser(<s:text name='#list.userId'></s:text>)">
						<s:text name="#list.rightName"></s:text>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<br />
	<div class="show_user_list_div">
		<table border="0" class="show_user_list_table">
			<tr>
				<td class="select" valign="middle">
					<div id="selectAllDiv" class="select_all" onclick="javascript:selectAll('userTable')"></div>
					<div class="select_font">全选</div>
				</td>
				<td class="td_span"></td>
				<td class="select">
					<div id="oppositeSelectDiv" class="select_all" onclick="javascript:oppositeSelect('userTable')"></div>
					<div class="select_font">反选</div>
				</td>
				<td class="td_span"></td>
				<td class="add show_user_list_bottom_height"
					onclick="javascript:dialog_ming_adduser('addUser.html','添加','800','520',2,'addUserHeadStyleClass','addUserBodyStyleClass')"
					onMouseOut="this.className='add show_user_list_bottom_height'" 
					onMouseDown="this.className='add_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="flush show_user_list_bottom_height"
					onclick="javascript:showUserList()"
					onMouseOut="this.className='flush show_user_list_bottom_height'"
					onMouseOver="this.className='flush_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="clear show_user_list_bottom_height"
					onclick="javascript:clearCheckBox('userTable')"
					onMouseOut="this.className='clear show_user_list_bottom_height'"
					onMouseOver="this.className='clear_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="del show_user_list_bottom_height"
					onclick="javascript:deleteUser('userTable')"
					onMouseOut="this.className='del show_user_list_bottom_height'"
					onMouseOver="this.className='del_move_in show_user_list_bottom_height'">
				</td>
				<td>
					&nbsp;
				</td>
				<td class="beforePage show_user_list_bottom_height"
					onMouseOut="this.className='beforePage show_user_list_bottom_height'"
					onMouseOver="this.className='beforePage_move_in show_user_list_bottom_height'"
					onclick="javascript:getUserList(${page-1 })">

				</td>
				<td class="td_span"></td>

				<s:if test="%{page>3}">
					<td class="page show_user_list_bottom_height"
						onMouseOut="this.className='page show_user_list_bottom_height'"
						onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
						onclick="javascript:getUserList(1)">
						1
					</td>
					<td class="td_span"></td>
					<td class="page show_user_list_bottom_height"
						onMouseOut="this.className='page show_user_list_bottom_height'"
						onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
						onclick="javascript:getUserList(2)">
						2
					</td>
					<td class="td_span"></td>
					<s:if test="%{page<7}">
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getLogList(3)">
							3
						</td>
						<td class="td_span"></td>
					</s:if>
					<s:else>
						<td class="td_ellipsis">
							……
						</td>
						<td class="td_span"></td>
					</s:else>
					<s:if test="%{page>4}">
						<s:if test="%{page==5}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(${page-1})">
								${page-1}
						</s:if>
						<s:else>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(${page-2})">
								${page-2}
							</td>
						</s:else>
						<td class="td_span"></td>
						<s:if test="%{page>5}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(5)">
								${page-1}
							</td>
							<td class="td_span"></td>
						</s:if>
					</s:if>
				</s:if>
				<s:else>
					<s:if test="%{page>1}">
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getUserList(1)">
							1
						</td>
						<td class="td_span"></td>
						<s:if test="%{page>2}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(2)">
								2
							</td>
							<td class="td_span"></td>
						</s:if>
					</s:if>
				</s:else>


				<td class="page_move_in show_user_list_bottom_height">
					${page}
				</td>
				<td class="td_span"></td>


				<s:if test="%{userList.pageTotal>3}">
					<s:if test="%{userList.pageTotal-4>page}">
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getUserList(${page+1 })">
							${page+1}
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getUserList(${page+2 })">
							${page+2}
						</td>
						<td class="td_span"></td>
						<s:if test="%{page+6>userList.pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(${page+3 })">
								${page+3}
							</td>
							<td class="td_span"></td>
						</s:if>
						<s:else>
							<td class="td_ellipsis">
								……
							</td>
							<td class="td_span"></td>
						</s:else>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getUserList(${userList.pageTotal-1 })">
							${userList.pageTotal-1 }
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getUserList(${userList.pageTotal})">
							${userList.pageTotal}
						</td>
						<td class="td_span"></td>
					</s:if>
					<s:else>
						<s:if test="%{page < userList.pageTotal}">
							<s:if test="%{page+1<userList.pageTotal}">
								<s:if test="%{page+2<userList.pageTotal}">
									<s:if test="%{page+3<userList.pageTotal}">
										<td class="page show_user_list_bottom_height"
											onMouseOut="this.className='page show_user_list_bottom_height'"
											onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
											onclick="javascript:getUserList(${userList.pageTotal-3 })">
											${userList.pageTotal-3 }
										</td>
										<td class="td_span"></td>
									</s:if>
									<td class="page show_user_list_bottom_height"
										onMouseOut="this.className='page show_user_list_bottom_height'"
										onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
										onclick="javascript:getUserList(${userList.pageTotal-2 })">
										${userList.pageTotal-2 }
									</td>
									<td class="td_span"></td>
								</s:if>
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:getUserList(${userList.pageTotal-1 })">
									${userList.pageTotal-1 }
								</td>
								<td class="td_span"></td>
							</s:if>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(${userList.pageTotal})">
								${userList.pageTotal}
							</td>
							<td class="td_span"></td>
						</s:if>
					</s:else>
				</s:if>
				<s:else>
					<s:if test="%{page<userList.pageTotal}">
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getUserList(${page+1})">
							${page+1}
						</td>
						<td class="td_span"></td>
						<s:if test="%{page+1<pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getUserList(${page+2})">
								${page+2}
							</td>
							<td class="td_span"></td>
						</s:if>
					</s:if>
				</s:else>
				<td class="nextPage show_user_list_bottom_height"
					onMouseOut="this.className='nextPage show_user_list_bottom_height'"
					onMouseOver="this.className='nextPage_move_in show_user_list_bottom_height'"
					onclick="javascript:getUserList(${page+1 })">
				</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
<!--
$(document).ready(function(){
	userList_setHeight();
});
//-->
</script>
