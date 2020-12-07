<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<div>
	<table>
	<a href="/bRegMod?typ=${typ}"><button>글쓰기</button></a>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성일</td>
		</tr>		
		<c:forEach items="${list}" var="item">
			<tr class="pointer" onclick="clkItem(${typ}, ${item.i_board})">
				<td>${item.i_board}</td>
				<td>${item.title}</td>
				<td>${item.r_dt}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<script>
	function clkItem(typ, i_board) {
		//console.log('typ: ' + typ + ', i_board : ' + i_board);
		//var url = '/bDetail?typ='+typ+'&i_board=' +i_board; 가독성이 떨어짐
		var url = `/bDetail?typ=\${typ}&i_board=\${i_board}`;
		console.log('url:'+url); // 주소값 이동했는지 확인.
		location.href = url; // 주소값 이동 
	}
</script>