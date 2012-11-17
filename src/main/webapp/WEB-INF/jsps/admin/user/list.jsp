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
    $('#btAdd').click(function(){
      location.href = 'toEdit';
    });
  });
	function deleteUser(id,el){
	  if(!confirm("你确定删除吗？相关的用户信息也会被删除。")) return false;
	  $.post('delete',{id:id},function(data){
	    var json = eval('('+data+')');
	    if(json.success){
	      $(el).parents('tr').eq(0).remove();
	    }else{
	      alert('删除失败！');
	    }
	  });
	}
	
	//选择角色
	function selectRoles(id){
	  $.dialog({
	    id:'selectRoles',
	    fixed:true,
	    width:400,
	    height:300,
	    content: 'url:${base}/role_mgr/listForUserSelect?userId=' + id,
	    init: function(){
	    }
	  });
	}
	
	//查看角色
	function showRoles(id){
	  $.dialog({
	    id:'selectRoles',
	    fixed:true,
	    width:400,
	    height:300,
	    content: 'url:${base}/role_mgr/listForUserSelected?userId=' + id,
	    init: function(){
	    }
	  });
	}
</script>
</head>
<body>
  <div class="main">
    <form action="${base }/user/list" method="post" class="form-horizontal">
      <div class="findCon">
      菜单名:<input name="user.name" type="text" value="${user.name }"/> 
      <input type="button" id="btFind" class="btn" value="查询" />   
      <input type="button" id="btAdd" class="btn" value="添加" />      
      </div>
      <input type="hidden" name="page.pageNumber" />
      <input type="hidden" name="page.pageSize" value="10" />
      <c:if test="${fn:length(users)>0}">
      <div id="Pagination2" class="jpager cf"></div>
      <table class="datatable">
        <thead>
          <tr>
            <td>ID</td>
            <td>用户名</td>
            <td>昵称</td>
            <td>操作</td>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="user2" items="${users}" varStatus="stat">
          <tr>
            <td>${user2.id }</td>
            <td>${user2.name }</td>
            <td>${user2.nickName }</td>
            <td>
              <a href="javascript:" onclick="deleteUser(${user2.id},this);">删除</a>
              <a href="${base }/user/toEdit?id=${user2.id}">修改</a>
              <a href="javascript:selectRoles(${user2.id});">设置角色</a>
              <a href="javascript:showRoles(${user2.id});">查看角色</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
      <div id="Pagination" class="jpager cf"></div>
      </c:if>
      <c:if test="${fn:length(users)<=0}">
        未查询到数据。
      </c:if>
    </form>
    <div class="clear"></div>
  </div>
</body>
</html>
