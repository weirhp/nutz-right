/**
 * @author houpo 
 */

/** 反转字符串 */
String.prototype.reverse = function() {
  return this.split("").reverse().join("");
}

/** 判断字符串是否是整数字符串 */
String.prototype.isInt = function() {
  return /^\d+$/.test(this);
}

/** 判断字符串是否是整数字符串 */
String.prototype.trim = function() {
  return this.replace(/(^\s+)|(\s+$)/g, "");
}

/** 判断字符串是否是以某字符串开头 */
String.prototype.startWith = function(str, ignoreCase) {
  if (typeof str == 'undefined' || this.length < str.length) {
    return false;
  }
  if (this.substr(0, str.length) == str) {
    return true;
  }
  return false;
}

/** 判断字符串是否是以某字符串结尾 */
String.prototype.endWith = function(str) {
  if (typeof str == 'undefined' || this.length < str.length) {
    return false;
  }
  if (this.substring(this.length - str.length) == str) {
    return true;
  }
  return false;
}

/** 得到字节长度 */
String.prototype.byteLength = function() {
  return this.replace(/[^x00-xff]/g, "--").length;
}
/** 从左截取指定长度的字串 */
String.prototype.left = function(n) {
  return this.slice(0, n);
}
/** 从右截取指定长度的字串 */
String.prototype.right = function(n) {
  return this.slice(this.length - n);
}

/** 把字符串转换为json对象 */
String.prototype.json = function(){
  return eval('(' + this + ')');
}

function isArray(obj) {
  return Object.prototype.toString.call(obj) === '[object Array]';
} 
