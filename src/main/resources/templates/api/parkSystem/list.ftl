<!DOCTYPE html>
<html lang="zh">
<head>
<title>停车场系统管理</title>
	<#include "../../common/head.ftl"/>
	<#include "../../common/datatable.ftl"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
		<section class="content-header">
			<ol class="breadcrumb">
				<li><a href="javascript:void(0)" onclick="top.location.href='${ctx}/'"><i class="fa fa-dashboard"></i> 主页</a></li>
				<li><a href="#">停车场系统管理</a></li>
				<li class="active">系统列表</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="col-xs-12">
					<div class="box">
						<div class="box-header">
							<h3 class="box-title">停车场系统列表</h3>
							<div class="box-tools">
							</div>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<table id="contentTable" class="table table-bordered table-striped table-hover">
								<thead>
									<tr>
										<th>编号</th>
										<th>停车场名称</th>
										<th>停车场编号</th>
										<th>原停车场ID</th>
										<th>URL</th>
										<th>品牌</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
						<a href="#" class="btn btn-primary" data-bind='click: add'><i class="fa fa-edit"></i>  接入停车场系统</a>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</section>
</body>
<script type="text/javascript">
	let viewModel;
	$(document).ready(function() {
		viewModel = {
			initTable: function() {
				const options = {
					divId : "contentTable",
					url : "${ctx}/api/park/search",
					columns:[{
						'data':'id'
					},{
						'data':'name',
						'orderable': false
					},{
						'data':'parkId'
					},{
						'data':'oldId'
					},{
						'data':'url',
						'orderable': false
					},{
						'data':function(row, type, val, meta) {
							/*if (row.brandName === "D") {
								return "<span class='label label-danger'>禁用</span>";
							}*/
							return row.parkBrand;
						}
					},{
						'data':function(row, type, val, meta) {
							var html = "";
							html += "<button class='btn btn-default btn-xs' onclick='viewModel.edit(" + row.id + ")' title='编辑'> <i class='fa fa-edit fa-lg'></i> </button> ";
							html += "<button class='btn btn-default btn-xs' onclick='viewModel.delete(\"" + row.id + "\")' title='删除'> <i class='fa fa-trash-o fa-lg'></i> </button> ";
							/*	html += "<a href='javascript:void(0)' onclick='addRole(\"" + data.id + "\")' title=''> <i class='fa fa-tag fa-lg'></i> </a>";*/
							return html;
						},
						'orderable': false
					}]
				};
				createTable(options);
			},
			add: function() {
				let url = "${ctx}/api/park/add";
				window.location.href = url;

			},
			reset: function() {
				$(".datatable_query").val('');
			},
			query: function() {
				refreshTable();
			},
			edit: function(id) {
				//top.showMyModel(url,'编辑用户', '70%', '80%', callBackAction);
				window.location.href = "${ctx}/api/park/edit/" + id;
			},
			delete: function(id) {
				if (id == null || id === "") {
					console.log("ID不能为空");
				} else {
					layer.confirm('确认删除?', {
						btn: ['确认','取消'] //按钮
					}, function(){
						const ids = [id];
						$.post({
							url:'${ctx}/api/park/delete/'+ids,
							success:function(data) {
								if (data.flag) {
									layer.alert(data.content, function(index){
										callBackAction(data);
										layer.close(index);
									});
								} else {
									layer.alert(data.content);
								}
							}
						});
					}, function(){
					});
				}
			}
		};
		ko.applyBindings(viewModel);
		viewModel.initTable();
	});

	function callBackAction(data) {
		refreshTable();
	}
</script>
</html>
