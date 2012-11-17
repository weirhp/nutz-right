<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户已选择的角色列表</title>
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
  
  function deleteRoleFromUser(id){
    $.post('${base}/role_mgr/deleteRoleFromUser',{userId:'${userId}',roleId:id},function(reData){
  	  $('#btFind').trigger('click');
  	  var msg = "删除失败！";
  	  if(reData.json().success){
  	    msg = '删除成功！';
  	  }
  	  $('#msg').html(msg);
  	});
  }
  
</script>
</head>
<body>
  <div class="main">
    <form id="rolesForm" action="${base }/role_mgr/listForUserSelected" method="post" class="form-horizontal">
      <div class="findCon">
      角色名:<input name="role.name" type="text" value="${role.name }"/> 
      <input name="userId" type="hidden" value="${userId }"/> 
      <input type="button" id="btFind" class="btn" value="查询" />      
      </div>
      <div id="msg"></div>
      <input type="hidden" name="page.pageNumber" />
      <input type="hidden" name="page.pageSize" value="10" />
      <c:if test="${fn:length(roles)>0}">
      <div id="Pagination2" class="jpager cf"></div>
      <table class="datatable">
        <thead>
          <tr>
            <td>ID</td>
            <td>名称</td>
            <td>编码</td>
            <td>操作</td>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="role2" items="${roles}" varStatus="stat">
          <tr>
            <td>${role2.id }</td>
            <td>${role2.name }</td>
            <td>${role2.code }</td>
            <td>
            <a href="javascript:" onclick="deleteRoleFromUser(${role2.id},this);">删除</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div id="Pagination" class="jpager cf"></div>
      </c:if>
      <c:if test="${fn:length(roles)<=0}">
        未查询到数据。
      </c:if>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
