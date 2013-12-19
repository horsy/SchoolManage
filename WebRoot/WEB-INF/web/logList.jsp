<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div style="overflow-y: auto;">
	<div >
		<iframe name="delUserForm" id="delUserForm" style="display: none;"
			src=""></iframe>
		<table class="show_user_list_table" id="studentListTable">
			<tr class="show_table_head_tr">
				<td class="show_table_head_line" width="200px;">
					用户名
				</td>
				<td class="show_table_head_line" width="200px;">
					日志时间
				</td>
				<td class="show_table_head_line" width="200px;">
					IP地址
				</td>
				<td class="show_table_head_line" width="200px;">
					地址
				</td>
				<td class="show_table_head_line">
					日志内容
				</td>
			</tr>
			<s:iterator value="%{logList}" status="objList">
				<tr
					class="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>"
					onmouseover='this.className="moveIn";'
					onmouseout='this.className="<s:if test="#objList.odd==true">show_table_body_tr_white</s:if><s:else>show_table_body_tr_black</s:else>";'>
					<td>
						<s:property value="userName" />
					</td>
					<td>
						<s:property value="dateTime" />
					</td>
					<td>
						<s:property value="ip" />
					</td>
					<td>
						<s:property value="ipAddress" />
					</td>
					<td>
						<s:property value="content" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	
	<div >
		<table border="0" class="show_user_list_table">
			<tr>
				<td>
					&nbsp;
				</td>
				
				<s:if test="%{pageTotal>1}">
				<s:if test="%{page>1}">
				<td class="beforePage show_user_list_bottom_height"
					onMouseOut="this.className='beforePage show_user_list_bottom_height'"
					onMouseOver="this.className='beforePage_move_in show_user_list_bottom_height'"
					onclick="javascript:getLogList(${page-1 })">

				</td>
				<td class="td_span"></td>
				</s:if>
				<s:if test="%{page>3}">
					<td class="page show_user_list_bottom_height"
						onMouseOut="this.className='page show_user_list_bottom_height'"
						onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
						onclick="javascript:getLogList(1)">
						1
					</td>
					<td class="td_span"></td>
					<td class="page show_user_list_bottom_height"
						onMouseOut="this.className='page show_user_list_bottom_height'"
						onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
						onclick="javascript:getLogList(2)">
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
								onclick="javascript:getLogList(${page-1})">
								${page-1}
						</s:if>
						<s:else>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getLogList(${page-2})">
								${page-2}
							</td>
						</s:else>
						<td class="td_span"></td>
						<s:if test="%{page>5}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getLogList(${page-1})">
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
							onclick="javascript:getLogList(1)">
							1
						</td>
						<td class="td_span"></td>
						<s:if test="%{page>2}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getLogList(2)">
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
							onclick="javascript:getLogList(${page+1 })">
							${page+1}
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getLogList(${page+2 })">
							${page+2}
						</td>
						<td class="td_span"></td>
						<s:if test="%{page+6>pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getLogList(${page+3 })">
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
							onclick="javascript:getLogList(${pageTotal-1 })">
							${pageTotal-1 }
						</td>
						<td class="td_span"></td>
						<td class="page show_user_list_bottom_height"
							onMouseOut="this.className='page show_user_list_bottom_height'"
							onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
							onclick="javascript:getLogList(${pageTotal})">
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
											onclick="javascript:getLogList(${pageTotal-3 })">
											${pageTotal-3 }
										</td>
										<td class="td_span"></td>
									</s:if>
									<td class="page show_user_list_bottom_height"
										onMouseOut="this.className='page show_user_list_bottom_height'"
										onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
										onclick="javascript:getLogList(${pageTotal-2 })">
										${pageTotal-2 }
									</td>
									<td class="td_span"></td>
								</s:if>
								<td class="page show_user_list_bottom_height"
									onMouseOut="this.className='page show_user_list_bottom_height'"
									onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
									onclick="javascript:getLogList(${pageTotal-1 })">
									${pageTotal-1 }
								</td>
								<td class="td_span"></td>
							</s:if>
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getLogList(${pageTotal})">
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
							onclick="javascript:getLogList(${page+1})">
							${page+1}
						</td>
						<td class="td_span"></td>
						<s:if test="%{page+1<pageTotal}">
							<td class="page show_user_list_bottom_height"
								onMouseOut="this.className='page show_user_list_bottom_height'"
								onMouseOver="this.className='page_move_in show_user_list_bottom_height'"
								onclick="javascript:getLogList(${page+2})">
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
						onclick="javascript:getLogList(${page+1 })">
					</td>
				</s:if>
				</s:if>
			</tr>
		</table>
	</div>
</div>