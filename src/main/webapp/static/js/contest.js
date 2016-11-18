//获取当前项目名称
var pathName=window.document.location.pathname;
var projectPath=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

$(function () {
    $(".contesttitle").click(function () {
    	/*if($("#session").val() == 0){
            alert("请登陆后再操作");
            return false;
        }*/
    	var $con = $(this).next().text();
    	
    	var contest = $con.split("/")[0];
    	var type = $con.split("/")[1];
    	//alert($con+"  " + contest + "  " +type);
        
        var url = projectPath + "/contest/" + contest + "/" + type;
        
        //判断比赛时间与当前时间
        var nowTime = format();
        //获取比赛开始时间
        var startTime = $(this).parent().next().text();
        //获取比赛结束时间
        var endTime = $(this).parent().next().next().text();
        //alert("now:" + nowTime + "\nstartTime:" + startTime +"\nendTime:" + endTime);
        //判断时间
        if(nowTime > endTime){
        	//比赛已经结束
        	alert("比赛已经结束");
        }else if(nowTime < startTime){
        	//比赛还未开始
        	alert("比赛还未开始");
        }else{
        	$("#contestId").val(contest);
            $("#type").val(type);
            $('#myModal').modal('show');
        }
        
        //var url = this.href;
        //var index = this.href.split("/").length-1;
        //var contest = this.href.split("/")[index-1];
       // var type = this.href.split("/")[index];
       /* $.ajax({
            url: url,
            type:'post',
            dataType:'json',
            success:function (data) {
                if(data.error){
                    window.location.href = '/404';
                }
                if(data.time == 1){
                    alert("未到比赛时间,请稍后再打开");
                }
                if(data.public){//公开的比赛
                    window.location.href = url;
                }
                if(data.private){//私有的比赛(也就是设置了密码的比赛)
                    $("#contestId").val(contest);
                    $("#type").val(type);
                    $('#myModal').modal('show');
                }

            }
        });
        return false;*/
    })
});

$(function () {
   $("#contestComit").click(function () {
       $.ajax({
           url:projectPath+'/contest/piv',
           type:'post',
           data:$("#contestJudgeForm").serialize(),
           dataType:'html',
           success:function(data,status) {
        	   if(status == "success"){
                   var objs = jQuery.parseJSON(data);
                   //alert("正确" + data +"  " + status + "  " + objs.type);
                   if(objs.type == '1'){
                	   //alert("验证成功");
                	   window.location.href=projectPath+"/contest/contestdetail/"+objs.ContestId;
                   }else{
                	   alert("密码错误");
                   }
               }else{
            	   alert("请求失败");
               }
           },
           error:function(data){
        	   alert("发生未知错误");
           }
       })
   })
});

//获取当前格式化后的日期
function add0(m){return m<10?'0'+m:m }
function format(){
	//shijianchuo是整数，否则要parseInt转换
	var time = new Date();
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}