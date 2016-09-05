$(function(){
	$.post("../../adminInfoServlet",{op:"getLoginInfo"},function(data){
		if(data=="" || data==null){
			location.href="../login.html";
		}else{
			$("#index_loginuser").text(data.aname);
			$("#index_loginphoto").attr("src","../../"+data.photos);
		}
	},"json");
	
	$('#index_content_info').tabs('add',{   
	    title:'源辰信息',   
	    closable:true,
	    fit:true,
	    href:"yc.html"
	});
	
	//当点击菜单的时候，自动创建一个选项卡
	$('#index_menu_tree').tree({
		onClick: function(node){
			var tabs=$('#index_content_info'); //获取选项卡对象
			var title="源辰信息";
			var href="yc.html";
			
			if(node.id=="index_role"){
				if(tabs.tabs("exists","角色管理")){ //如果这个面板在选项卡中存在，则选中
					tabs.tabs("select","角色管理");
					return;
				}else{
					title="角色管理";
					href="roles.html";
				}
			}else if(node.id=="index_admin"){
				if(tabs.tabs("exists","管理员信息")){ //如果这个面板在选项卡中存在，则选中
					tabs.tabs("select","管理员信息");
					return;
				}else{
					title='管理员信息';  
					href="admin.html";
				}
			}else if(node.id=="index_shopping3"){
				if(tabs.tabs("exists","店铺信息管理")){ //如果这个面板在选项卡中存在，则选中
					tabs.tabs("select","店铺信息管理");
					return;
				}else{
					title='店铺信息管理';  
					href="managershopping.html";
				}
			}
			
			tabs.tabs("add",{
				title:title,   
			    closable:true,
			    fit:true,
			    href:href
			});
		}
	});


});