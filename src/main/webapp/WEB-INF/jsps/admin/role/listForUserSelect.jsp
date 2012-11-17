<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户可选择的角色列表</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function() {
    $("#Pagination,#Pagination2").pagination(${pager.recordCount}, {current_page:${pager.pageNumber - 1}, callback:function(cur){
        $('input[name="page.pageNumber"]').val(cur + 1);
        $("form").eq(0).submit();
    }});
    
    $('#rolesForm').delegate('#selectAll', 'click', function(){
      $('#rolesForm :checkbox[name="roleIds"]').attr('checked', $(this)[0].checked);
    });
    
    $('#btFind').click(function(){
      $('input[name="page.pageNumber"]').val(1);
      $("form").eq(0).submit();
    });
    //保存选择的角色
    $('#btSave').click(function(){
      var checkedEls = $(':input:checked[name="roleIds"]');
      if(checkedEls.length>0){
      	var data = $('#rolesForm').serialize();
      	$.post('${base}/role_mgr/saveUserSelectRoles',data,function(reData){
      	  $('#btFind').trigger('click');
      	  var msg = "保存失败！";
      	  if(reData.json().success){
      	    msg = '保存成功！';
      	  }
      	  $('#msg').html(msg);
      	});
      }else{
        $('#msg').html('请选择要关联当前用户的角色！');
      }
    });
  });
</script>
</head>
<body>
  <div class="main">
    <div>当前处理的用户是：${selectUser.nickName }</div>
    <form id="rolesForm" action="${base }/role_mgr/listForUserSelect" method="post" class="form-horizontal">
      <div class="findCon">
      角色名:<input name="role.name" type="text" value="${role.name }"/> 
      <input name="userId" type="hidden" value="${userId }"/> 
      <input type="button" id="btFind" class="btn" value="查询" />   
      <input type="button" id="btSave" class="btn" value="保存" />      
      </div>
      <div id="msg"></div>
      <input type="hidden" name="page.pageNumber" />
      <input type="hidden" name="page.pageSize" value="10" />
      <c:if test="${fn:length(roles)>0}">
      <div id="Pagination2" class="jpager cf"></div>
      <table class="datatable">
        <thead>
          <tr>
            <td><input id="selectAll" type="checkbox"/>选择</td>
            <td>ID</td>
            <td>名称</td>
            <td>编码</td>
          </tr>
        </thead>
        <tbody>
        <c:forEach var="role2" items="${roles}" varStatus="stat">
          <tr>
            <td><input name="roleIds" value="${role2.id }" type="checkbox"/></td>
            <td>${role2.id }</td>
            <td>${role2.name }</td>
            <td>${role2.code }</td>
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
