//能跳转进购物车肯定用户登陆了
//自己的JS代码
//刷新展示购物车商品
$.ajax({
	url:'http://www.wjian.top/shop/api_goods.php',
	type:'get',
	data:{'pagesize':3},
	dataType: 'json',
	success:function(result){
		console.log(result);
		//验证
		if(result.code!=0){
			console.log(result.message);
			return;
		};
		var obj=result.data;
		//遍历商品
		
		for(var i=0;i<obj.length;i++){
			var str=`
				<tr>
            	<td>
            		<img class="goods-img" 
            		src="${obj[i].goods_thumb}" />
            	</td>
            	<td>${obj[i].goods_name}</td>
            	<td class="come">
            		<span class="minus">-</span>
            		<span class="number">1</span>
            		<span class="add">+</span>
            	</td>
            	<td class='price'>100</td>
            	<td class='sum-price'>100</td>
            	<td class='duihuan'><a href="javascript:;" class="delete">立即兑换</a></td>
            </tr>
			`;
			//每运行一次把DOM节点添加到表格最后
			$('table').append(str);
		};
		//点击减号
			$('.minus').click(function(){
				//拿到当前点击元素的兄弟节点.number内容
				var beforeN=parseInt($(this).siblings('.number').html());
				if(beforeN<=1){
					return;
				}
				var afterN=beforeN-1;
				//console.log(afterN);
				$(this).siblings('.number').html(afterN);
				//设置小计
				var price=parseInt($(this).parent().siblings('.price').html());
				$(this).parent().siblings('.sum-price').html(price*afterN);
			});
			//点击加号
			$('.add').click(function(){
				//拿到当前点击元素的兄弟节点.number内容
				var beforeN=parseInt($(this).siblings('.number').html());
				if(beforeN>=10){
					return;
				}
				var afterN=beforeN+1;
				//console.log(afterN);
				$(this).siblings('.number').html(afterN);
				//设置小计
				var price=parseInt($(this).parent().siblings('.price').html());
				$(this).parent().siblings('.sum-price').html(price*afterN);
			});
			//点击全选
			$('.select-all').click(function(){
				//判断复选框是否被选中
				if($(this).attr('checked')=='checked'){
					//把所有商品复选框设置选中 并且加一个自定义标记
					$('.check').attr('checked',true);
					$('.check').attr('data-price','active');
				}else{
					//把所有商品复选框设置为不被选中
					$('.check').attr('checked',false);
					$('.check').attr('data-price','');
				};
				
			});
			//点击单选
			$('.check').click(function(){
				//点击 判断状态 如果选中则不选
				if($(this).attr('checked')){
					$(this).attr('data-price','active');
				}else{
					$(this).attr('data-price','');
					$('.select-all').attr('checked',false);
				}
				allPrice();
			});
			//点击删除
			$('.delete').click(function(){
				var beforeN=parseInt($(this).parent().siblings('.come'). children('.number').html());
				console.log(beforeN);
				var zhi=parseInt($(this).parent().siblings('.price').html())-beforeN;
				$(this).parent().siblings('.price').html(zhi);
				$(this).parent().siblings('.come'). children('.number').html(1);
				$(this).parent().siblings('.sum-price').html(100);
				alert("恭喜您，兑换成功！！！");
			});
	},
});


