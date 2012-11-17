<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>角色列表</title>
<%@include file="../../common/admin_include.jsp"%>
<script type="text/javascript">
  $(function(){
    $('#btInit').click(function(){
      $.getJSON('${base}/system/init_operation?'+(new Date().getTime()),function(data){
        $('#msg').html("添加了"+data.insertCount +'条,更新了' + data.updateCount+ '条！');
      });
    });
  });
</script>
</head>
<body>
 <input id="btInit" type="button" value="初始化系统所有的action"/>
 <div id="msg" class="error">
  
 </div>
</body>
</html>
