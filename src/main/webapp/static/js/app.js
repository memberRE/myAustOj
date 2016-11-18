
//获取当前项目名称
var pathName=window.document.location.pathname;
var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

//底部二维码展示
$(function () {
    $('[data-toggle=tooltip]').tooltip({delay:{show:0,hide:500}});
});
//菜单链接进入active
$(function () {
   var index = window.location.href.split("/").length-1;
    var href = window.location.href.split("/")[index].substr(0,4);
    if(href.length>0){
        //如果匹配开头成功则更改样式
        $(".navbar-nav li a[href^='/"+href+"']").parent().addClass('active')
        //[attribute^=value]：匹配给定的属性是以某些值开始的元素。
    }
});

$(function () {
    //写入侧边栏目录
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:projectPath + "/static/json/catelog.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.catename;
                $(".widget .catelog").append('<li><i class="fa fa-bookmark-o">&nbsp;&nbsp;<a href="'+projectPath+'/problem/tocatelog/'+item.id+'/'+content+'">'+content+'</a></i></li>');
            })
        }
    });
    //写入到标签栏
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:projectPath + "/static/json/tags.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.tagname;
                $(".widget .tags").append('<a href="'+projectPath+'/articles/search?search='+content+'" style="font-size: 12px;">'+content+'</a>');
            })
        }
    });
    //写入到文章
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:projectPath+"/static/json/article.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.title;
                if(content.length <17){
                $(".widget .article").append('<li><i class="fa fa-file-text-o"><a href="'+projectPath+'/articles/'+item.id+'" target="_blank">&nbsp;&nbsp;'+content+'</a></i></li>');
            	}else{
            		var content1 = content.substr(0,17) +"...";
            		$(".widget .article").append('<li><i class="fa fa-file-text-o"><a href="'+projectPath+'/articles/'+item.id+'" target="_blank"  data-placement="bottom"  data-content="'+content+'">&nbsp;&nbsp;'+content1+'</a></i></li>');
            		
            		//设置鼠标放置下方显示全部标题，无省略
            		$(".widget .article").find("a").each(function(){
            			$(this).mouseover(function(){
            				$(this).popover("show");
            			}).mouseout(function(){
            				$(this).popover("hide");
            			});
            		});
            	}
            })
            
        }
    });
    //写入到通知
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:projectPath + "/static/json/notify.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.notify_name;
                $(".tips .notify").append('<li><a href="#">'+content+'</a></li>');
            })
        }
    });
//    首页请求在index页面
});

//通知栏轮播
function jump(){
    $(function(){
        $('.tips-bulls li').eq(0).slideUp(2000,function(){
            $(this).clone().appendTo($(this).parent()).show();
            $(this).remove();
        });
    });
}
setInterval('jump()',2000);

