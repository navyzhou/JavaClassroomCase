$.fn.moveDiv=function(){
	var obj=$(this); //获取给定的对象
	var isDown=false; //记录鼠标有没有按下去
	var cx=0,cy=0; //鼠标按下去时的位置
	var nowx=0,nowy=0;
	
	//获取当前对象的位置
	var y=obj.css("top");
	var x=obj.css("left");
	
	if(y=="auto"){
		y=0;
	}
	
	if(x=="auto"){ //说明是水平居中
		x=($(window).width()-obj.outerWidth())/2;
	}
	
	//将此对象转为绝对定位方式
	obj.css({"position":"absolute","cursor":"move","top":y,"left":x});
	
	obj.bind({
		mousedown:function(e){
			isDown=true;
			//记录当前鼠标点击的位置
			cx=e.clientX;
			cy=e.clientY;
		},
		mouseup:function(){
			isDown=false;
			x=nowx;
			y=nowy;
		},
		mousemove:function(e){
			if(isDown){//如果鼠标时按下去的，则说明此对象要跟着鼠标移动
				nowx=x+e.clientX-cx;
				nowy=y+e.clientY-cy;
				$(this).css({left:nowx,top:nowy});
			}
		}
	});
}