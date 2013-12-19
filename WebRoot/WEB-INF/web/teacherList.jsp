<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="overflow-y: auto;">
	<div>
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<table class="show_user_list_table" id="teacherListTable">
			<tr class="show_table_head_tr">
				<td>
					&nbsp;
				</td>
				<td class="show_table_head_line">
					姓名
				</td>
				<td class="show_table_head_line">
					性别
				</td>
				<td class="show_table_head_line">
					职位
				</td>
				<td class="show_table_head_line">
					电话
				</td>
				<td class="show_table_head_line">
					最近登录
				</td>
				<td class="show_table_head_line">
					登录IP
				</td>
				<td class="show_table_head_line">
					登录权限
				</td>
			</tr>
			<s:iterator value="%{teacherList}" status="objList">
				<tr
					class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>"
					onmouseover='this.className="moveIn";'
					onmouseout='this.className="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>";'>
					<td class=TdBody_checkBox>
						<input type="checkbox" name="obj<s:property value="teacher.id" />"
							id="obj<s:property value="teacher.id" />"
							onclick="document.getElementById('selectAllDiv').className='select_all';">
					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="teacher.userName" />
					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="sex.sex" />

					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="teacherType.typeName" />

					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="teacher.telephone" />
					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="teacher.lastLoginTime" />
					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="teacher.lastLoginIp" />
					</td>
					<td
						onclick="javascript:teacherList_editTeacher(<s:property value="teacher.id" />)">
						<s:property value="loginInfo" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<div>
		<table border="0" class="show_user_list_table">
			<tr>
				<td class="select" valign="middle">
					<div id="selectAllDiv" class="select_all"
						onclick="javascript:selectAll('teacherListTable')"></div>
					<div class="select_font">
						全选
					</div>
				</td>
				<td class="td_span"></td>
				<td class="select">
					<div id="oppositeSelectDiv" class="select_all"
						onclick="javascript:oppositeSelect('teacherListTable')"></div>
					<div class="select_font">
						反选
					</div>
				</td>
				<td class="td_span"></td>
				<td class="add show_user_list_bottom_height"
					onclick="javascript:teacherList_addTeacher()"
					onMouseOut="this.className='add show_user_list_bottom_height'"
					onMouseDown="this.className='add_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="del show_user_list_bottom_height"
					onclick="javascript:teacherList_del('teacherListTable')"
					onMouseOut="this.className='del show_user_list_bottom_height'"
					onMouseOver="this.className='del_move_in show_user_list_bottom_height'">
				</td>
				<td>
					&nbsp;
				</td>

				<s:if test="%{pageTotal>1}">
					<s:if test="%{page>1}">
						<td class="beforePage show_user_list_bottom_height"
							onMouseOut="this.className='beforePage show_user_list_bottom_height'"
							onMouseOver="this.className='beforePage_move_in show_user_list_bottom_height'"
							onclick="javascript:teacherList_getTeacherList(${page-1 })">

						</td>
						<td class="td_span"></td>
					</s:if>
					<s:if test="%{page>3}">
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:teacherList_getTeacherList(1)">
							1
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:teacherList_getTeacherList(2)">
							2
						</td>
						<td class="td_span"></td>
						<s:if test="%{page<7}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:teacherList_getTeacherList(3)">
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
									onclick="javascript:teacherList_getTeacherList(${page-1})">
									${page-1}
							</s:if>
							<s:else>
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:teacherList_getTeacherList(${page-2})">
									${page-2}
								</td>
							</s:else>
							<td class="td_span"></td>
							<s:if test="%{page>5}">
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:teacherList_getTeacherList(${page-1})">
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
								onclick="javascript:teacherList_getTeacherList(1)">
								1
							</td>
							<td class="td_span"></td>
							<s:if test="%{page>2}">
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:teacherList_getTeacherList(2)">
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


					<s:if test="%{pageTotal>3}">
						<s:if test="%{pageTotal-4>page}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:teacherList_getTeacherList(${page+1 })">
								${page+1}
							</td>
							<td class="td_span"></td>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:teacherList_getTeacherList(${page+2 })">
								${page+2}
							</td>
							<td class="td_span"></td>
							<s:if test="%{page+6>pageTotal}">
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:teacherList_getTeacherList(${page+3 })">
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
								onclick="javascript:teacherList_getTeacherList(${pageTotal-1 })">
								${pageTotal-1 }
							</td>
							<td class="td_span"></td>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:teacherList_getTeacherList(${pageTotal})">
								${pageTotal}
							</td>
							<td class="td_span"></td>
						</s:if>
						<s:else>
							<s:if test="%{page < pageTotal}">
								<s:if test="%{page+1<pageTotal}">
									<s:if test="%{page+2<pageTotal}">
										<s:if test="%{page+3<pageTotal}">
											<td class="page show_user_list_bottom_height"
												onMouseOut="this.className='page show_user_list_bottom_height'"
												onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
												onclick="javascript:teacherList_getTeacherList(${pageTotal-3 })">
												${pageTotal-3 }
											</td>
											<td class="td_span"></td>
										</s:if>
										<td class="page show_user_list_bottom_height"
											onMouseOut="this.className='page show_user_list_bottom_height'"
											onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
											onclick="javascript:teacherList_getTeacherList(${pageTotal-2 })">
											${pageTotal-2 }
										</td>
										<td class="td_span"></td>
									</s:if>
									<td class="page show_user_list_bottom_height"
										onMouseOut="this.className='page show_user_list_bottom_height'"
										onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
										onclick="javascript:teacherList_getTeacherList(${pageTotal-1 })">
										${pageTotal-1 }
									</td>
									<td class="td_span"></td>
								</s:if>
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:teacherList_getTeacherList(${pageTotal})">
									${pageTotal}
								</td>
								<td class="td_span"></td>
							</s:if>
						</s:else>
					</s:if>
					<s:else>
						<s:if test="%{page<pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:teacherList_getTeacherList(${page+1})">
								${page+1}
							</td>
							<td class="td_span"></td>
							<s:if test="%{page+1<pageTotal}">
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:teacherList_getTeacherList(${page+2})">
									${page+2}
								</td>
								<td class="td_span"></td>
							</s:if>
						</s:if>
					</s:else>
					<s:if test="%{page<pageTotal}">
						<td class="nextPage show_user_list_bottom_height"
							onMouseOut="this.className='nextPage show_user_list_bottom_height'"
							onMouseOver="this.className='nextPage_move_in show_user_list_bottom_height'"
							onclick="javascript:teacherList_getTeacherList(${page+1 })">
						</td>
					</s:if>
				</s:if>
			</tr>
		</table>
		<input type="hidden" id="classId" name="classId" value="${classID}" />
	</div>
</div>