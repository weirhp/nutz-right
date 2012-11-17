<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单项列表</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
    $("#Pagination,#Pagination2").pagination(${pager.recordCount}, {current_page:${pager.pageNumber - 1}, callback:function(cur){
        $('input[name="page.pageNumber"]').val(cur + 1);
        $("form").eq(0).submit();
    }});
    $('#btFind').click(function(){
      $('input[name="page.pageNumber"]').val(1);
      $("form").eq(0).submit();
    });
  });
</script>
</head>
<body>
  <div class="main">
    <form action="${base }/menu/list" method="post" class="form-horizontal">
      <div class="findCon">
      菜单名:<input name="menu.name" type="text" value="${menu.name }"/> 
      <input type="button" id="btFind" class="btn" value="查询" />   
      <input type="button" id="btAdd" class="btn" value="添加" />      
      </div>
      <input type="hidden" name="page.pageNumber" />
      <input type="hidden" name="page.pageSize" value="10" />
      <c:if test="${fn:length(menus)>0}">
      <div id="Pagination2" class="jpager cf"></div>
      <table class="datatable">
        <thead>
          <tr>
            <td>ID</td>
            <td>名称</td>
            <td>地址</td>
            <td>父ID</td>
            <td>操作</td>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="menu" items="${menus}" varStatus="stat">
          <tr>
            <td>${menu.id }</td>
            <td>${menu.name }</td>
            <td>${menu.url }</td>
            <td>${menu.parentId }</td>
            <td>
              <a href="#">删除</a>
              <a href="${base }/menu/toEdit?id=${menu.id}">修改</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div id="Pagination" class="jpager cf"></div>
      </c:if>
      <c:if test="${fn:length(menus)<=0}">
        未查询到数据。
      </c:if>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
