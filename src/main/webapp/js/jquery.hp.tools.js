(function($) {
  // 全局方法扩展使用方式$.extendClass(child,parent)
  $.extend({
    // 类继承
    extendClass : function(child, parent) {
      child.prototype = $.extend(true, {}, parent.prototype, child.prototype);
      child.prototype.contractor = child;
      // child.prototype.super = parent.prototype;
    },

    // 创建一个二维数据
    create2Array : function(len) {
      var arr = new Array();
      for ( var i = 0; i < len; i++) {
        arr[i] = new Array();
      }
      return arr;
    },
    /**
     * 截取字符串，两个英文字符长度相当一个汉字的长度 author hp.
     * 
     * @param str
     * @param len
     *          返回的字符串的长度
     * @param suffix
     *          当超长时要显示的后缀
     * @returns
     */
    cutStrForShow : function(str, len, suffix) {
      if (str.length <= len) {
        return str;
      }
      var nowLen = 0.0;
      var reStr = [];
      var sf = suffix || '...';
      var sfLen = 0;
      for ( var j = 0; j < sf.length; j++) {
        sfLen += (sf.charCodeAt(i) <= 255 ? 0.5 : 1.0);
      }
      for ( var i = 0; i < str.length; i++) {
        nowLen += (str.charCodeAt(i) <= 255 ? 0.5 : 1.0);
        reStr.push(str.substr(i, 1));
        if (nowLen >= len && i != str.length && sfLen != 0) {
          for ( var j = i; j >= 0; j--) {
            nowLen -= (str.charCodeAt(i) <= 255 ? 0.5 : 1.0);
            reStr.pop();
            if (nowLen + sfLen <= len) {
              break;
            }
          }
          reStr.push(sf);
          break;
        }
      }
      return reStr.join('');
    },
    /**
     * 简单实现字符串的格式化 使用方法： tmp = $.hformat("sdfh{0},sdfsdf{1}");
     * alert(tmp(11,22));
     */
    hformat : function(source, params) {
      if (arguments.length === 1) {
        return function() {
          var args = $.makeArray(arguments);
          args.unshift(source);
          return $.format.apply(this, args);
        };
      }
      if (arguments.length > 2 && params.constructor !== Array) {
        params = $.makeArray(arguments).slice(1);
      }
      if (params.constructor !== Array) {
        params = [ params ];
      }
      $.each(params, function(i, n) {
        source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
      });
      return source;
    }

  });

  // 对象级别扩展
  $.fn.extend({
    //让文本框只能输入数字
    isNum : function() {
      this.each(function() {
        if ($(this).is(":text")) {
          $(this).keyup(function() {
            this.value = this.value.replace(/\D/g, '');
          });
        }
      })
    }
  });
  
  //添加属性的匹配规则支持正则表达式
  (function(_ATTR) {
    /***************************************************************************
     * https://github.com/padolsey/jQuery-plus/blob/master/jquery.plus.js
     * Making attribute selectors support regex syntax. E.g.
     * $('div[id%=/^foo/]')
     */
    var expr = $.expr;
    expr.filter.ATTR = function(elem, match) {
      if (match[2] === '%=') {
        var attrName = match[1], attrValue = expr.attrHandle[attrName] ? expr.attrHandle[attrName](elem)
            : elem[attrName] !== null ? elem[attrName] : elem.getAttribute(attrName), operator = match[2], regex = regexRegex
            .exec(match[4]);
        return regex && RegExp(regex[1], regex[2]).test(attrValue);
      }
      return _ATTR.apply(this, arguments);
    };
  })($.expr.filter.ATTR);

})(jQuery)