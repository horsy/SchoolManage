<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="overflow-y: auto;">
	<div >
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<table class="show_user_list_table" id="studentListTable">
			<tr class="show_table_head_tr">
				<td>
					&nbsp;
				</td>
				<td class="show_table_head_line">
					学号
				</td>
				<td class="show_table_head_line">
					姓名
				</td>
				<td class="show_table_head_line">
					性别
				</td>
				<td class="show_table_head_line">
					班级
				</td>
				<!-- 
				<td class="show_table_head_line">
					审核
				</td>
				 -->
			</tr>
			<s:iterator value="%{studentList}" status="objList">
				<tr
					class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>"
					onmouseover='this.className="moveIn";'
					onmouseout='this.className="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>";'>
					<td class=TdBody_checkBox>
						<input type="checkbox"
							name="obj<s:property value="student.id" />"
							id="obj<s:property value="student.id" />" onclick="document.getElementById('selectAllDiv').className='select_all';">
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:property value="student.studentId" />
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:property value="student.userName" />
						
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:property value="sex.sex" />
					</td>
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						${clas.className}
					</td>
					<!-- 
					<td
						onclick="javascript:studentList_editStudent(<s:property value="student.id" />)">
						<s:if test="review.id==0">
							<font color="#FF8040"><s:property value="review.content" /></font>
						</s:if>
						<s:elseif test="review.id==1">
							<font color="green"><s:property value="review.content" /></font>
						</s:elseif>
						<s:elseif test="review.id==2">
							<font color="red"><s:property value="review.content" /></font>
						</s:elseif>
						<s:elseif test="review.id==-1">
							<font><s:property value="review.content" /></font>
						</s:elseif>
					</td>
					 -->
				</tr>
			</s:iterator>
		</table>
	</div>
	
	<div >
		<table border="0" class="show_user_list_table">
			<tr>
				<td class="select" valign="middle">
					<div id="selectAllDiv" class="select_all" onclick="javascript:selectAll('studentListTable')"></div>
					<div class="select_font">全选</div>
				</td>
				<td class="td_span"></td>
				<td class="select">
					<div id="oppositeSelectDiv" class="select_all" onclick="javascript:oppositeSelect('studentListTable')"></div>
					<div class="select_font">反选</div>
				</td>
				<td class="td_span"></td>
				<td class="add show_user_list_bottom_height"
					onclick="javascript:addStudent(${classID})"
					onMouseOut="this.className='add show_user_list_bottom_height'" 
					onMouseDown="this.className='add_move_in show_user_list_bottom_height'">
				</td>
				<td class="td_span"></td>
				<td class="del show_user_list_bottom_height"
					onclick="javascript:studentList_del('studentListTable')"
					onMouseOut="this.className='del show_user_list_bottom_height'"
					onMouseOver="this.className='del_move_in show_user_list_bottom_height'">
				</td>
				<!-- 
				<td class="td_span"></td>
				<td class="beforeHand show_user_list_bottom_height"
					onclick="javascript:studentList_beforeHandAdd(${classID})" 
					onMouseOut="this.className='beforeHand show_user_list_bottom_height'"
					onMouseDown="this.className='beforeHand_click show_user_list_bottom_height'">
				</td>
				 -->
				<td>
					&nbsp;
				</td>
				
				<s:if test="%{pageTotal>1}">
				<s:if test="%{page>1}">
				<td class="beforePage show_user_list_bottom_height"
					onMouseOut="this.className='beforePage show_user_list_bottom_height'"
					onMouseOver="this.className='beforePage_move_in show_user_list_bottom_height'"
					onclick="javascript:studentList_getStudentList(${page-1 },${classID})">

				</td>
				<td class="td_span"></td>
				</s:if>
				<s:if test="%{page>3}">
					<td class="page show_user_list_bottom_height"
						onMouseOut="this.className='page show_user_list_bottom_height'"
						onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
						onclick="javascript:studentList_getStudentList(1,${classID})">
						1
					</td>
					<td class="td_span"></td>
					<td class="page show_user_list_bottom_height"
						onMouseOut="this.className='page show_user_list_bottom_height'"
						onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
						onclick="javascript:studentList_getStudentList(2,${classID})">
						2
					</td>
					<td class="td_span"></td>
					<s:if test="%{page<7}">
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:studentList_getStudentList(3,${classID})">
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
								onclick="javascript:studentList_getStudentList(${page-1},${classID})">
								${page-1}
						</s:if>
						<s:else>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:studentList_getStudentList(${page-2},${classID})">
								${page-2}
							</td>
						</s:else>
						<td class="td_span"></td>
						<s:if test="%{page>5}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:studentList_getStudentList(${page-1},${classID})">
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
							onclick="javascript:studentList_getStudentList(1,${classID})">
							1
						</td>
						<td class="td_span"></td>
						<s:if test="%{page>2}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:studentList_getStudentList(2,${classID})">
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
							onclick="javascript:studentList_getStudentList(${page+1 },${classID})">
							${page+1}
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:studentList_getStudentList(${page+2 },${classID})">
							${page+2}
						</td>
						<td class="td_span"></td>
						<s:if test="%{page+6>pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:studentList_getStudentList(${page+3 },${classID})">
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
							onclick="javascript:studentList_getStudentList(${pageTotal-1 },${classID})">
							${pageTotal-1 }
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:studentList_getStudentList(${pageTotal},${classID})">
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
											onclick="javascript:studentList_getStudentList(${pageTotal-3 },${classID})">
											${pageTotal-3 }
										</td>
										<td class="td_span"></td>
									</s:if>
									<td class="page show_user_list_bottom_height"
										onMouseOut="this.className='page show_user_list_bottom_height'"
										onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
										onclick="javascript:studentList_getStudentList(${pageTotal-2 },${classID})">
										${pageTotal-2 }
									</td>
									<td class="td_span"></td>
								</s:if>
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:studentList_getStudentList(${pageTotal-1 },${classID})">
									${pageTotal-1 }
								</td>
								<td class="td_span"></td>
							</s:if>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:studentList_getStudentList(${pageTotal},${classID})">
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
							onclick="javascript:studentList_getStudentList(${page+1},${classID})">
							${page+1}
						</td>
						<td class="td_span"></td>
						<s:if test="%{page+1<pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:studentList_getStudentList(${page+2},${classID})">
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
						onclick="javascript:studentList_getStudentList(${page+1 },${classID})">
					</td>
				</s:if>
				</s:if>
			</tr>
		</table>
		<input type="hidden" id="classId" name="classId" value="${classID}"/>
	</div>
</div>