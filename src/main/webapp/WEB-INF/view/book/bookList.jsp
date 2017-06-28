<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="/common/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>

<!-- ID唯一！！！  -->

<!-- 条件查询表单 -->

<div class="easyui-panel">
    <form id="form_book" method="post">
        <table>
            <tr>

                <td>书名:</td>
                <td><input type="text" id="title" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>价钱精确:</td>
                <td><input type="text" id="price" class="easyui-textbox"/></td>
            </tr>
          <%--  <tr>
                <td>级别名称:</td>
                <td> <select id="level"  style="width:200px;"><td>
            </tr>--%>
            <tr>

                <td>
                    <a id="wsearch" href="javascript:search()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
                </td>
                <td>
                    <a id="wreset" href="javascript:reset()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>


<!-- easyui渲染方式二  datagrid() -->
<table id="bookDateGrid_only"></table>

<%--toolbar  工具 条--%>
<div id="tb">
    <a href="javascript:addBook()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a href="javascript:editBookInline()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" >开始编辑</a>
    <a href="javascript:updateBookInline()" class="easyui-linkbutton" data-options="iconCls:'ext-icon-arrow_green',plain:true" >修改</a>
    <a href="javascript:cancleEditBookInline()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" >取消修改</a>

</div>


<script type="text/javascript">
    var bookDatagrid=null;
    /*开始编辑用户基本信息--行内编辑器*/
    function editBookInline() {
        //获取当前被选中的行
        var rowSelected =  bookDatagrid.datagrid('getSelected');
        //当选中行不为空时，当前行开启 行编辑
        if (rowSelected) {
            //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
            if (editRow != undefined) {
                //endEdit结束编辑方法
                bookDatagrid.datagrid("endEdit", editRow);
            }
            //当无编辑行时
            if (editRow == undefined) {
                //获取到当前选择行的下标
                var index = bookDatagrid.datagrid("getRowIndex", rowSelected);
                //开启编辑
                bookDatagrid.datagrid("beginEdit", index);
                //把当前开启编辑的行赋值给全局变量editRow
                editRow = index;
            }
        }
    }
    //将修改用户信息更新到数据库
    function updateBookInline() {
//            关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件，在事件中调用ajax更新后台

        bookDatagrid.datagrid("endEdit", editRow);
    }

    /*取消编辑用户基本信息*/
    function cancleEditBookInline(){
        editRow = undefined;
        //回滚所有从创建或者上一次调用acceptChanges函数后更改的数据。
        bookDatagrid.datagrid("rejectChanges");
    }

    <!--页面加载时 查询集合 -->
    $(function(){
        bookDatagrid=  $("#bookDateGrid_only").datagrid({
            url:'<%=request.getContextPath()%>/book/selectbookList.do',
            method:'post',
            pagination:true,
            rownumbers:true,
            pageNumber:1,
            pageSize:2,
            pageList:[2,4,6,8],
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'bookid',
            loadMsg:'候着。。。',
            toolbar: '#tb',
            //排序  默认 排序 字段 与 正倒序  后台 用 sort 和  order 接受
            sortName:'bookid',
            sortOrder:'desc',
            columns:[
                [
                    {field:'bookid',title:'ID',width:120,sortable:true,order:'desc'},
                    {field:'bookname',title:'名称',width:120,
                        //编辑器--文本框
                        editor:{
                            type:'validatebox',
                            options: {
                                required: 'minLength[4]',
                            }
                        }
                    },
                    {field:'bookprice',title:'价钱',width:120,sortable:true,order:'desc',
                        //编辑器--文本框
                        editor:{
                            type:'validatebox',
                            options: {
                                required: 'minLength[4]',
                            }
                        }
                    },
                    {field:'bookdate',title:'创建时间',width:120,
                        formatter: function (value, row, index) {

                            var date = new Date(value);

                            var year = date.getFullYear().toString();

                            var month = (date.getMonth() + 1);

                            var day = date.getDate().toString();

                            var hour = date.getHours().toString();

                            var minutes = date.getMinutes().toString();

                            var seconds = date.getSeconds().toString();

                            if (month < 10) {

                                month = "0" + month;

                            }

                            if (day < 10) {

                                day = "0" + day;

                            }

                            if (hour < 10) {

                                hour = "0" + hour;

                            }

                            if (minutes < 10) {

                                minutes = "0" + minutes;

                            }

                            if (seconds < 10) {

                                seconds = "0" + seconds;

                            }

                            return year + "/" + month + "/" + day + " " + hour + ":" + minutes + ":" + seconds;

                        }
                    }
                ]
            ]
        });
    });

    /*条件 查询  调用  load 方法*/
    function search(){

        bookDatagrid.datagrid('load',{
            "whereMap['title']":$("#title").val().trim(),
            "whereMap['price']":$("#price").val().trim(),
        })
    }

    //重置查询
    function reset(){
        $("#form_book").form('reset');
        search();
    }
</script>
</body>
</html>