<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<SPAN id=pagelink>
	<DIV
		style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
		共[<B>${page.totalCount}</B>]条记录,[<B>${page.totalPage}</B>]页
		,每页显示
		<select name="pageSize">
		
		<option value="2" <c:if test="${page.pageSize==2 }">selected</c:if>>2</option>
		<option value="3" <c:if test="${page.pageSize==3 }">selected</c:if>>3</option>
		</select>
		条
		<c:if test="${page.pageCode > 1 }">
			[<a  href="javascript:to_page(${page.pageCode-1})">前一页</a>]
		</c:if>
		
		<B>${page.pageCode}</B>
		<c:if test="${page.pageCode < page.totalPage }">
			[<a href="javascript:to_page(${page.pageCode+1})">后一页</a>] 
		</c:if>
		
		到
		<input type="text" size="3" id="page" name="pageCode" />
		页
		
		<input type="button" value="Go" onclick="to_page()"/>
	</DIV>
</SPAN>