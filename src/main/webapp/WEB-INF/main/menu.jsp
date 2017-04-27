<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<ul id="menu">
	<!-- 每个用户都可以看到主页 -->
	<li><a href="${pageContext.request.contextPath }/login/toIndex.do" class="index_<c:if test="${currentModuleId==0 }">on</c:if><c:if test="${currentModuleId!=0 }">off</c:if>"></a></li>

	<!-- 以下7个模块需要动态创建 -->
	<c:forEach items="${allModules }" var="module">
		<c:if test="${module.module_id==1 }">
			<li><a href="${pageContext.request.contextPath }/role/findRole.do?currentPage=1" class="role_<c:if test="${currentModuleId==1 }">on</c:if><c:if test="${currentModuleId!=1 }">off</c:if>"></a></li>
		</c:if>
		<c:if test="${module.module_id==2 }">
			<li><a href="${pageContext.request.contextPath }/admin/findAdmin.do?currentPage=1" class="admin_<c:if test="${currentModuleId==2 }">on</c:if><c:if test="${currentModuleId!=2 }">off</c:if>"></a></li>
		</c:if>
		<c:if test="${module.module_id==3 }">
			<li><a href="${pageContext.request.contextPath }/cost/findCost.do?currentPage=1" class="fee_<c:if test="${currentModuleId==3 }">on</c:if><c:if test="${currentModuleId!=3 }">off</c:if>"></a></li>
		</c:if>
		<c:if test="${module.module_id==4 }">
			<li><a href="${pageContext.request.contextPath }/account/findAccount.do?currentPage=1" class="account_<c:if test="${currentModuleId==4 }">on</c:if><c:if test="${currentModuleId!=4 }">off</c:if>"></a></li>
		</c:if>
		<c:if test="${module.module_id==5 }">
			<li><a href="${pageContext.request.contextPath }/service/findService.do?currentPage=1" class="service_<c:if test="${currentModuleId==5 }">on</c:if><c:if test="${currentModuleId!=5 }">off</c:if>"></a></li>
		</c:if>
		<c:if test="${module.module_id==6 }">
			<li><a href="${pageContext.request.contextPath }/bill/findBill.do?currentPage=1" class="bill_<c:if test="${currentModuleId==6 }">on</c:if><c:if test="${currentModuleId!=6 }">off</c:if>"></a></li>
		</c:if>
		<c:if test="${module.module_id==7 }">
			<li><a href="${pageContext.request.contextPath }/report/findReport.do?currentPage=1" class="report_<c:if test="${currentModuleId==7 }">on</c:if><c:if test="${currentModuleId!=7 }">off</c:if>"></a></li>
		</c:if>
	</c:forEach>

	<!-- 每个用户都可以看到个人信息、修改密码 -->
	<li><a href="${pageContext.request.contextPath }/user/toInformation.do" class="information_<c:if test="${currentModuleId==8 }">on</c:if><c:if test="${currentModuleId!=8 }">off</c:if>"></a></li>
	<li><a href="${pageContext.request.contextPath }/user/toPassword.do" class="password_<c:if test="${currentModuleId==9 }">on</c:if><c:if test="${currentModuleId!=9 }">off</c:if>"></a></li>
</ul>