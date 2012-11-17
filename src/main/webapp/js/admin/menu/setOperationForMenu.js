var optTree = null;
// 树的默认配置
var setting = {
  view : {
    selectedMulti : false
  },
  check: {
    enable: true,
    autoCheckTrigger: true
  },
  data : {
    simpleData : {
      enable : true
    }
  }
};
// 单击树节点的处理
function treeClick(event, treeId, treeNode) {
}

// 初始化树
function initTree() {
  $.getJSON(basePath + "/operation/operationJson?t=" + (new Date().getTime()), function(data) {
    optTree = $.fn.zTree.init($("#optTree"), setting, data);
    //set checkbox
    $.getJSON(basePath + "/operation/menuOperationsJson?menuId="+ menuId +"&t=" + (new Date().getTime()), function(menuOperationJson) {
      if(menuOperationJson&&menuOperationJson.length){
        for(var i=0;i<menuOperationJson.length;i++){
          var tmpOpt = menuOperationJson[i];
          var tmpNode = optTree.getNodesByFilter(function(node){return node.id==tmpOpt.operationId;},true);
          if(!tmpNode.children||!tmpNode.children.length){
            optTree.checkNode(tmpNode, true, true, true);
          }
        }
      }
    });
  });
}

$(function() {
  initTree();// 加载树
   //保存
  $("#btSave").click(function() {
    var nodes = optTree.getNodesByFilter(function(node){return node.checked&&!("" + node.id).startWith("class_");});
    var nodesJson = [];
    for ( var i = 0; i < nodes.length; i++) {
      nodesJson.push(nodes[i].id);
    }
    $.post(basePath + '/operation/saveMenuOperations', {
      optIds : JSON.stringify(nodesJson),
      menuId : menuId
    }, function(data) {
      var json = data.json();
      if (json.success) {
        initTree();// 加载树
        $('#msg').html('保存成功！');
      } else {
        $('#msg').html('保存失败！');
      }
    });
  });

  //刷新树
  $('#btRefresh').click(initTree);
});