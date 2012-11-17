<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>角色列表</title>
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
    $('#btAdd').click(function(){
      location.href = 'toEdit';
    });
  });
	function deleteRole(id,el){
	  if(!confirm("你确定删除吗？相关的用户将失去相关的权限。")) return false;
	  $.post('delete',{id:id},function(data){
	    var json = eval('('+data+')');
	    if(json.success){
	      $(el).parents('tr').eq(0).remove();
	    }else{
	      alert('删除失败！');
	    }
	  });
	}
	
	//设置角色使用的菜单
	function setRoleMenus(id){
	  $.dialog({
	    id:'selectRoles',
	    fixed:true,
	    width:400,
	    height:300,
	    content: 'url:${base}/menu/toSetMenuForRole?roleId=' + id,
	    init: function(){
	      
	    }
	  });
	}
</script>
</head>
<body>
  <div class="main">
    <form action="${base }/role_mgr/list" method="post" class="form-horizontal">
      <div class="findCon">
      角色名:<input name="role.name" type="text" value="${role.name }"/> 
      <input type="button" id="btFind" class="btn" value="查询" />   
      <input type="button" id="btAdd" class="btn" value="添加" />      
      </div>
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
              <a href="javascript:" onclick="deleteRole(${role2.id},this);">删除</a>
              <a href="${base }/role_mgr/toEdit?id=${role2.id}">修改</a>
              <a href="javascript:setRoleMenus(${role2.id})">设置可操作菜单</a>
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
