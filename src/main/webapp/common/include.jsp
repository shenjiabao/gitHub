<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>

    
<!-- 引入 CSS -->
<!-- 主题 css -->
<link   style="text/css" href="<%=request.getContextPath() %>/js/easyui1.4.5/themes/default/easyui.css" rel="stylesheet"   />
<!-- 图标 css  -->
<link   style="text/css" rel="stylesheet"  href="<%=request.getContextPath() %>/js/easyui1.4.5/themes/icon.css"  />

<!-- css渲染页面效果  -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/uploadify/uploadify.css">

<!-- 引入JQuery -->
<!-- 引入JQuery 库-->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui1.4.5/jquery.min.js"></script>


<!-- uploadify库 -->
<script src="<%=request.getContextPath()%>/js/uploadify/jquery.uploadify.min.js"></script>

<!-- 引入my97 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/my97/WdatePicker.js"></script>



<!-- 引入EasyUI库-->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui1.4.5/jquery.easyui.min.js"></script>
<!-- 引入EasyUI语言库-->
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui1.4.5/locale/easyui-lang-zh_CN.js"></script>
    
    
<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="<%=contextPath%>/style/extIcon.css" type="text/css">

<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="<%=contextPath%>/style/extCss.css" type="text/css">
    




<script type="text/javascript">
var sys = sys || {};
sys.contextPath = '<%=contextPath%>';
sys.basePath = '<%=basePath%>';

sys.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

/**
 * 滚动条
 */
sys.progressBar = function(options) {
	if (typeof options == 'string') {
		if (options == 'close') {
			$('#progressBarDiv').dialog('destroy');
		}
	} else {
		if ($('#progressBarDiv').length < 1) {
			var opts = $.extend({
				title : '&nbsp;',
				closable : false,
				width : 300,
				height : 60,
				modal : true,
				content : '<div id="progressBar" class="easyui-progressbar" data-options="value:0"></div>'
			}, options);
			$('<div id="progressBarDiv"/>').dialog(opts);
			$.parser.parse('#progressBarDiv');
		} else {
			$('#progressBarDiv').dialog('open');
		}
		if (options.value) {
			$('#progressBar').progressbar('setValue', options.value);
		}
	}
};
/**
 * 通用错误提示
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
sys.onLoadError = {
	onLoadError : function(XMLHttpRequest) {
		if (parent.$ && parent.$.messager) {
			parent.$.messager.progress('close');
			parent.$.messager.alert('错误', XMLHttpRequest.responseText);
		} else {
			$.messager.progress('close');
			$.messager.alert('错误', XMLHttpRequest.responseText);
		}
	}
};
$.extend($.fn.datagrid.defaults, sys.onLoadError);
$.extend($.fn.treegrid.defaults, sys.onLoadError);
$.extend($.fn.tree.defaults, sys.onLoadError);
$.extend($.fn.combogrid.defaults, sys.onLoadError);
$.extend($.fn.combobox.defaults, sys.onLoadError);
$.extend($.fn.form.defaults, sys.onLoadError);


</script>

