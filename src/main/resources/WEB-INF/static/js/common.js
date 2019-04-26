//var $ = jQuery = layui.jquery;
$.ajaxSetup({
    xhrFields:{withCredentials:true},
    crossDomain: true,
})
var util = {
	getJson:function(url,data){
		var result;
		util.getSync(url,data,function(res){
			result = res.data
		})
		return result;
	},
	postJson:function(url,data){
		var result;
		util.postSync(url,data,function(res){
			result = res.data
		})
		return result;
	},
	get:function(url,data,callback){
		this.ajax({url:url,data:data},callback);
	},
	getSync:function(url,data,callback){
		this.ajax({url:url,data:data,async:false},callback);
	},
	post:function(url,data,callback){
		this.ajax({url:url,data:data,type:"post"},callback);
	},
	postSync:function(url,data,callback){
		this.ajax({url:url,data:data,type:"post",async:false},callback);
	},
	delete:function(url,data,callback){
		this.ajax({url:url,data:data,type:"delete"},callback);
	},
	ajax:function(option,callback){
		var load;
		var _default = {
			type:"get",
	        url:"",
	        dataType: 'json',
	        traditional:true,
	        contentType:option.contentType || "application/x-www-form-urlencoded; charset=UTF-8",
	        timeout:100000,
	        beforeSend:function(){
	        	load = layer.load(2,{offset:['50%','50%']})
	        },
	        success: function(res){
	        	layer.close(load)
	        	if(res.code==2){
					callback(res);
				}else if(res.code==3){
					if(self != top){
						top.location.href='${ctxPath}/admin/login'
					}
					location.href='${ctxPath}/admin/login'
				}else{
					layer.alert(res.msg, {icon:2});
				}
	        },
	        error:function(xhr,status,error){
	        	layer.close(load)
	        	layer.alert('网络异常，请刷新后重试',{icon:2})
	        }
		}
		_default = $.extend(_default,option);
		$.ajax(_default);
	},
	keyup:function(el,callback){
		$(el).keyup(function(event){
			if(event.keyCode==13){
				callback()
			}
		})
	},
	getTree:function(arr,id){
		var result = [], temp;
        $.each(arr,function(key,item){
            if(arr[key].parentId==id){
                var obj = arr[key];
                temp = util.getTree(arr, arr[key].id);
                if (temp.length > 0) {
                    obj.children = temp;
                }
                result.push(obj);
            }
        });
    	return result;
	},
	refreshTabel:function(el,options){
		var id = el || 'table';
		var opts = {};
		if(options){
			options.page = options.page?options.page:{curr:1};
		}else{
			opts.page = {curr:1}
		}
		layui.table.reload(id,options?options:opts)
	},
	refreshCurrentPage:function(el,options){
		var id = el || 'table';
		layui.table.reload(id,options)
	},
	bindForm:function(options){
		$.each(options, function(i,o) {
			layui.form.on('submit('+o.event+')', function(data){
		   		if(o.callback){
		   			o.callback(data.field)
		   		}
			   	return false
			})
		})
	},
	table:function(url,cols,option,where,select,id){
		var ID = id || 'table';
		var table = layui.table.render({
			elem:ID,
			url:url,
			method:'post',
			where:where,
			page:{
				layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
				groups:5,
				first:false,
				last:false,
				curr:1,
			},
			cols:cols,
			request:{
				pageName: 'page',
				limitName: 'rows',
			},
			sort:true,
			response:{
				separator:true,
				statusName: 'code',
				statusCode: 2,
				msgName: 'msg',
				countName: 'total', //数据总数的字段名称，默认：count
				dataName:'content' //数据列表的字段名称，默认：data
			},
			skin: 'line',
			limits:[10,20,30,40,50],
			loading:true,
			limit:option.limit?option.limit:15
		})
		layui.table.on('tool('+ID+')', function(row){
		    option[row.event](row.data)
		})
		util.laydate('startTime')
		util.laydate('endTime')
		// util.region()
		util.rank()
		util.register()
		util.points()
		util.commissionType()
		util.shareType()
		layui.form.on('submit(select)', function(data){
			// data.field.push(where);
			var res = {};
			if(where){
				var res = Object.assign(where,data.field)
			}else{
				res = data.field
			}

	   		table.reload({
			  	where:res,
			  	page: {
			    	curr: 1 //重新从第 1 页开始
			  	},
			  	done:function(){
			  		if(select){
						select(res)
				   	}
			  	}
			})
		   	return false;
		})
		
		layui.form.on('submit(export)', function(data){
			var data = data.field;
			var param = '';
			for(key in data){
				if(typeof data[key] !='object' && data[key]!=''){
					param+= key+'='+data[key]+'&'
				}
			}
		   	window.open(url+'/export?'+param)
			return false;
		})
	},
	showShape:function(){
		var shape = $('<div>',{class:'shape-full'});
		shape.css({height:'100%',width:'100%',position:'relative','z-index':'9999'})
		$(window.top.document).find("#main-body").append(shape)
	},
	hideShape:function(){
		$(window.top.document).find('.shape-full').remove();
	},
	tables:function(option){ //url,cols,option,where,select,id,exportUrl
		util.showShape();
		layui.layer.load(2,{offset:['50%','50%']})
		var id = option.id || 'table',
			table = layui.table.render({
				elem:id,
				url:option.url,
				method:option.method || 'post',
				where:option.where,
				page:{
					layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
					groups:5,
					first:false,
					last:false,
					curr:1,
				},
				cols:option.cols,
				request:{
					pageName: 'page',
					limitName: 'rows',
				},
				sort:true,
				response:{
					separator:true,
					statusName: 'code',
					statusCode: 2,
					msgName: 'msg',
					countName: 'total',
					dataName:'content'
				},
				skin: 'line',
				limits:[15,20,30,40,50],
				loading:true,
				done:function(res, curr, count){
					util.hideShape();
					layui.layer.close(layui.layer.load(2,{offset:['50%','50%']}));
				},
				limit:15
			})
		util.laydate('startTime')
		util.laydate('endTime')

		// util.region()
		util.rank()
		util.register()
		util.points()
		util.commissionType()
		util.shareType()
		// util.paymentMethod()
		// util.profitType()
		layui.table.on('tool('+id+')', function(row){
		    option.event[row.event](row.data,function(){
		    	table.reload()
		    })
		})
		layui.form.on('submit(query)', function(data){
			var form = data.field
			table.reload({
			  	where:option.where?$.extend(option.where,form):form,
			  	page: {curr:1},
			  	done:function(res,curr,count){
			  		if(option.callback){
						option.callback(res,curr,count)
				   	}
			  	}
			})
			return false
		})
		layui.form.on('submit(export)', function(data){
			if(option.exportType=='post'){
			    var html = '<iframe id="down-file-iframe" style="display:none;"><form target="down-file-iframe" method="post" action="'+option.url+'/export" />'
			  	for(key in option.exportData){
			  		html += '<input name="'+key+'" value="'+option.exportData[key]+'"/>'
			  	}
			  	$('body').append(html)
			  	$('#down-file-iframe').submit().remove()
			}else{
				util.export(option.url,data)
			}
			return false
		})
		layui.table.on('sort(table)', function(obj){
			var where = $.extend($('.search-form').serializeObject(),{property:obj.field,direction:obj.type})
			table.reload({
		  		initSort:obj,
		  		page: {curr:1},
			    where: where
			})
		})

		setTimeout(()=>{
			if($('.shape-full')){
				$('.shape-full').hide();
			}
		},50000);
		return table
	},
	postExport:function(url,obj){
		this.downLoadFile({url:url,data:obj})
	},
	export:function(url,data){
		var data = data.field;
		var param = '';
		for(key in data){
			if(typeof data[key] !='object' && data[key]!=''){
				param+= key+'='+data[key]+'&'
			}
		}
		window.open(url+'/export?'+param)
	},
	downLoadFile:function(options) {
	    var config = $.extend(true, { method: 'post' }, options);
	    var $iframe = $('<iframe id="down-file-iframe" />');
	    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
	    $form.attr('action', config.url);
	    for (var key in config.data) {
	        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
	    }
	    $iframe.append($form);
	    $(document.body).append($iframe);
	    $form[0].submit();
	    $iframe.remove();
	},
	open:function(title,tpl,data,submit,cancel,callback,area,offset,userClose){
		var getTpl;
		layui.laytpl($(tpl).html()).render(data,function(html){
			getTpl = html;
		});
		var areaDefault = ['50%','70%'];
		layer.open({
			// id:'open'+getTpl.length,
			id:'open'+ Math.random()*1000,
			title:title,
		  	type: 1,
		  	content:getTpl,
		  	skin:'',//风格 class layui-layer-lan layui-layer-molv
		  	area:area || areaDefault,
		  	shade:[0.3, '#000'],
		  	closeBtn:1,
			offset:offset?offset:'100px',
			success: function(layero, index){
				layui.form.render(null,'openForm')
				if(callback){
					callback(layero,index)
				}

				layui.form.on('submit(submit)', function(data){
				    if(submit&&!userClose){
						submit(data.field,close(index))
				   	}else{
						submit(data.field)
				   	}

				   	return false;
				})
				layui.form.on('submit(cancel)', function(data){
				    if(cancel){
				    	cancel(data.field,close(index))
				    }
				    return false;
				})
				function close(index){
					layui.layer.close(index)
				}
			}
		})
	},
	openFull:function(title,url,closeBtn){
		var close = 1;
		if(closeBtn !='undefined'){
			close = closeBtn;
		}
		var index = layer.open({
			title:title,
		  	type: 2,
		 	content:url,
		 	closeBtn:close
		})
		layer.full(index)
	},
	getUrlParam:function(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)")
		var r = window.location.search.substr(1).match(reg)
		if(r!=null)return  unescape(r[2]); return null
	},
	alert:function(content,icon,title,callback){//1 成功   2错误   3。请问     4锁定   5 不开心   6开心 7警告
		layer.alert(content,{
			icon:icon,
			title:title || '提示'
		},
		function(index){
		  	layer.close(index);
		  	if(callback){
		  		callback()
		  	}
		})
	},
	confirm:function(content,yes,no){
		layer.confirm(content,{icon:3,title:'提示'},yes,no)
	},
	msg:function(content,icon,callback){
		layer.msg(content,{
		  icon:icon,
		  shade:'0.3'
		},callback)
	},
	iframe:function(url){
		$("#iframe").attr('src',url);
	},
	getStorage:function(key){
		return JSON.parse(localStorage.getItem(key))
	},
	setStorage:function(key,value){
		localStorage.setItem(key,JSON.stringify(value))
	},
	removeStorage:function(key){
		localStorage.removeItem(key)
	},
	region:function(el){
		var el = el || '.search-form [name="regionId"]';
		this.select(el,URL.regionList)
	},
	rank:function(el){
		var el = el || '.search-form [name="gradeId"]';
		this.select(el,URL.region.member.rank)
	},
	register:function(el){
		var el = el || '.search-form [name="registeredSource"]';
		this.select(el,URL.region.member.register)
	},
	points:function(el){
		var el = el || '.search-form [name="memberGradeSource"]';
		this.select(el,URL.region.member.points)
	},
	commissionType:function(el){
		var el = el || '.search-form [name="tradeType"]';
		// this.select(el,URL.region.happiness.list.type)
	},
	shareType:function(el){
		var el = el || '.search-form [name="shareType"]';
		// this.select(el,URL.region.supplier.record.shareType)
	},
	select:function(el,url){
		var el = $(el)
		if(el.length==0){
			return
		}
		var arr = util.getJson(url)
		var html = '<option value="">请选择</option>';
		$.each(arr,function(i,v){
			html+='<option value="'+v.value+'">'+v.key+'</option>'
		})
		el.append(html)
		layui.form.render()
	},
	laydate:function(id){
		layui.laydate.render({
		    elem:'#'+id
		})
	},
	unique:function(arr1,arr2){
		var arr = arr1.concat();
		for(var i=0;i<arr2.length;i++){
			arr.indexOf(arr2[i]) === -1 ? arr.push(arr2[i]) : 0;
		}
		return arr;
	},
	isType:function(data){
		var arr = {
			'[object Number]':'number',
			'[object String]':'string',
			'[object function]':'function',
			'[object Undefined]':'undefined',
			'[object Boolean]':'boolean',
			'[object Object]':'object',
			'[object Array]':'array',
			'[object Null]':'null',
		}
		return arr[Object.prototype.toString.call(data)];
	},
	isNull:function(data){
		return data.toString().replace(/\s/g, "").length > 0 ? false : true;
	},
	form:{
		isNull:function(data){
			for(d in data){
				if(util.isNull(data[d]))return true;
			}
			return false;
		},
		len:function(data,start,end){
			var len = data.toString().length;
			return len<start?true:(end?(len<end?false:true):false)
		}
	},
	tpl:function(id,data,el){
		for(i in data){
			if(Number(data[i])!=NaN){
				data[i] = data[i].toFixed(2)
			}
		}
		layui.laytpl($('#'+id).html()).render(data,function(html){
		  	$(el).html(html)
		})
	},
	cookie: function(e,o,t){e=e||"";var n,i,r,a,c,p,s,d,u;if("undefined"==typeof o){if(p=null,document.cookie&&""!=document.cookie)for(s=document.cookie.split(";"),d=0;d<s.length;d++)if(u=$.trim(s[d]),u.substring(0,e.length+1)==e+"="){p=decodeURIComponent(u.substring(e.length+1));break}return p}t=t||{},null===o&&(o="",t.expires=-1),n="",t.expires&&("number"==typeof t.expires||t.expires.toUTCString)&&("number"==typeof t.expires?(i=new Date,i.setTime(i.getTime()+864e5*t.expires)):i=t.expires,n="; expires="+i.toUTCString()),r=t.path?"; path="+t.path:"",a=t.domain?"; domain="+t.domain:"",c=t.secure?"; secure":"",document.cookie=[e,"=",encodeURIComponent(o),n,r,a,c].join("");},

	zTreeSelect:function(options){
		/*jquery.zTree*/
		var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};
		var elem = options.listElem;
		$('.expand-bar').remove();
		console.log('options:',options.zNodes);
		$(elem).before('<div class="expand-bar">'+
				'<span class="expand-all layui-btn layui-btn-xs">全部收起</span>'+
				'<span class="expand-all layui-btn layui-btn-xs">全部展开</span>'+
			'</div>')

		function beforeClick(treeId, treeNode) {
			hideMenu();
		}

		function onClick(e, treeId, treeNode) {
			if(!options.selectLastNode && treeNode.levels !=2){
				layui.layer.msg('请选择三级分类',{time:1000});
				$('#menuContent').css('display','block')
				return false;
			}

			if(options.createClassify && treeNode.levels >= 2){
				//新增分类，不能选择三级分类
				layui.layer.msg('不能选择三级分类',{time:1000});
				$('#menuContent').css('display','block')
				return false;
			}

			var str = options.listElem.split('#')[1];
			var zTree = $.fn.zTree.getZTreeObj(str),
			nodes = zTree.getSelectedNodes(),
			v = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $(options.triggerElem);
			cityObj.attr("value", v);

			if(options.selected){
				options.selected(treeNode);
			}
			// hideMenu();
		}
		$(options.triggerElem).on('click',function(){
			var cityObj = $(options.triggerElem);
			var cityOffset = $(options.triggerElem).offset();
			$(options.listElem).parent().slideDown("fast");

			// $("body").bind("mousedown", onBodyDown);
		})

		function hideMenu() {
			$(options.listElem).parent().fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents(".menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($(options.listElem), setting, options.zNodes);
		});

		//全部展开 全部收起
		$('.expand-bar span').on('click',function(){
			var str = options.listElem.split('#')[1];
			var zTree = $.fn.zTree.getZTreeObj(str);

			if($(this).index() == 0){
				zTree.expandAll(false);
			}else{
				zTree.expandAll(true);
			}
		})
	},
	fillFormData:function(option){
		//表单填充 ，通过元素name属性
		var elem = option.elem,
			data = option.data;
		for(var i in option.data){
			$(elem).find('input[type="text"]').each(function(){
				if($(this).attr('name') == i){
					$(this).val(option.data[i])
				}
			});
			$(elem).find('textarea').each(function(){
				if($(this).attr('name') == i){
					$(this).val(option.data[i])
				}
			})

			$(elem).find('input[type="checkbox"]').each(function(){
				if($(this).attr('name') == i){
					if(option.data[i] == 0){
						$(this).removeAttr('checked')
					}else{
						$(this).attr('checked','checked')
						if($(this).attr('lay-skin') == 'switch'){
							$(this).next().addClass('layui-form-onswitch');
						}
						if($(this).attr('lay-skin') == 'primary'){
							$(this).next().addClass('layui-form-checked');
						}
					}
				}
			})

			$(elem).find('select').each(function(){
				if($(this).attr('name') == i){
					$(this).val(option.data[i]);
					var _this = $(this);

					$(this).find('option').each(function(index,item){
						if($(this).val() == option.data[i]){
							_this.next('.layui-form-select').find('input').val($(this).text());
						}
					});
				}
			});

			//随时补充：
		}
	},
	copyToClipboard:function(id) {
      	var Url2=document.getElementById(id);
		// Url2.select(); // 选择对象
		document.execCommand("Copy"); // 执行浏览器复制命令
		// alert("已复制好，可贴粘。");
    },
    renderTable:function(options){
		layui.table.render({
			elem:options.elem,
			where:options.where,
			url:options.url,
			cols:options.cols,
			page:{
				layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'],
				groups:5,
				first:false,
				last:false,
				curr:1,
			},
			request:{
				pageName: 'page',
				limitName: 'rows',
			},
			method:options.method || 'post',
			sort:true,
			response:{
				separator:true,
				statusName: 'code',
				statusCode: 2,
				msgName: 'msg',
				countName: 'total', //数据总数的字段名称，默认：count
				dataName:'content' //数据列表的字段名称，默认：data
			},
			skin: 'line',
			limits:[10,20,30,40,50],
			loading:true,
			limit:options.limit?options.limit:12,
			done:options.done?options.done:function(){
				
			}
		});
	},
	tableWithNoPage:function(options){
		layui.table.render({
			elem:options.elem,
			where:options.where,
			url:options.url,
			cols:options.cols,
			method:options.method || 'post',
			sort:true,
			request:{
				pageName: 'page',
				limitName: 'rows',
			},
			response:{
				separator:false,
				statusName: 'code',
				statusCode: 2,
				msgName: 'msg',
				countName: 'total', //数据总数的字段名称，默认：count
				// dataName:'data' //数据列表的字段名称，默认：data
			},
			skin: 'line',
			limits:[10,20,30,40,50],
			loading:true,
			limit:options.limit?options.limit:15
		});
		layui.layer.close(layui.layer.load(2,{offset:['50%','50%']}));
	},
	getEditor:function(id,options){
		var elementId = id,option={};

        var opt = {
        	toolbars:[
              	[
                    'fullscreen',
                    'source',
                    'bold',
                    'undo',
                    'redo' ,
                    'simpleupload',
                    'italic',
                    'underline',
                    'removeformat',
                    'strikethrough',
                    'fontfamily',
                    'fontsize',
                    'justifyleft',
                    'justifyright',
                    'justifycenter',
                    'justifyjustify',
                    'forecolor',
                    'backcolor',
                    'link'
              	]
            ]
        }
        if(options){
        	opt = Object.assign(options,opt);
        }
		var ue = UE.getEditor(elementId,opt);
      	return ue;
	},
	getBase64Image:function(img){
		var canvas = document.createElement("canvas");
        canvas.width = img.width;
        canvas.height = img.height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, img.width, img.height);
        var ext = img.src.substring(img.src.lastIndexOf(".")+1).toLowerCase();
        var dataURL = canvas.toDataURL("image/"+ext);
        return dataURL;
	},
	fmoney:function(s, n){			
	    n = n >= 0 && n <= 20 ? n : 2;
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	    t = "";
	    for (i = 0; i < l.length; i++) {
	        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	    }
	    if(n==0){
	    	return t.split("").reverse().join("");
	    }else{
	    	return t.split("").reverse().join("") + "." + r;	    	
	    }
	},
	treeToPath:function(tree, path, currentPath) {
	    var currentPath = currentPath || [];
	    var path = path || [];

	    for(var i = 0; i < tree.length; i++) {
	        if(i !== 0) {
	            currentPath.pop();
	        }
	        var obj ={
	        	id:tree[i].id,
	        	text:tree[i].text,
	        	iconClas:tree[i].iconClas,
	        	hasChildren:tree[i].hasChildren
	        };
	        currentPath.push(obj);

	        if(tree[i].children.length) {
	            util.treeToPath(tree[i].children, path, currentPath);
	        }else {
	            path.push(currentPath.slice(0));
	        }
	    }

	    currentPath.pop();
	    return path;
	},
	getBirthdayFromIdCard : function(idCard) {  //根据身份证号码获取出生年月日
        var birthday = "";
        if(idCard != null && idCard != ""){
            if(idCard.length == 15){
                birthday = "19"+idCard.substr(6,6);
            } else if(idCard.length == 18){
                birthday = idCard.substr(6,8);
            }

            birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-");
        }

        return birthday;
    },
    getDate:function(timestamp){
    	var date = new Date(timestamp);
    	var year = date.getFullYear(),
    		month = date.getMonth()<10?('0'+date.getMonth()):date.getMonth(),
    		day = date.getDate()<10?('0'+date.getDate()):date.getDate(),
    		hour = date.getHours()<10?('0'+date.getHours()):date.getHours(),
    		min = date.getMinutes()<10?('0'+date.getMinutes()):date.getMinutes(),
    		sec = date.getSeconds()<10?('0'+date.getSeconds()):date.getSeconds();


    	return year+'-'+month+'-'+day+' '+hour+':'+min+':'+sec;
    },
    itemMove:function(options){
		var elem = $(options.elem);
		var dragList = elem.find('.dragList');
	    for (var i = 0; i < dragList.length; i++) {
	        dragList[i].addEventListener('dragstart', function(e) {
	            e.dataTransfer.effectAllowed = "move";
	            var indx = $($(e.target).parent()).index();
	            // 获得当前拖动的index
	            e.dataTransfer.setData("Text", indx.toString());
	        });
	    }

	    var dragbox = elem.find('.dragbox');
	    for (var i = 0; i < dragbox.length; i++) {
	        dragbox[i].addEventListener('dragover', function(ev) {
	            ev.preventDefault();
	            // $(this).css('border','2px dashed #53a1e4').siblings().css('border','none')
	        });

	        dragbox[i].addEventListener('drop', function(ev) {
	            ev.preventDefault();
	            var temporaryHTML, innerDom;
	            var data = ev.dataTransfer.getData("Text");

	            if ($(ev.target).attr('class') == 'dragList') {
	                temporaryHTML = $(ev.target).parent('li').find('.dragList');
	                innerDom = $(ev.target).parent('li');
	            } else if ($(ev.target).attr('class') == 'dragbox') {
	                temporaryHTML = $(ev.target).find('.dragList');
	                innerDom = $(ev.target);
	            }
	            $(innerDom).html($('.dragList').eq(data));
	            $(elem.find('.dragbox').eq(data)).html(temporaryHTML);
	        });
	    }
	},

	/**
	 * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
	 * 即谷歌、高德 转 百度
	 * @param lng
	 * @param lat
	 * @returns {*[]}
	 */
	gcj02tobd09:function(lng, lat) {
	    var z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_PI);
	    var theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_PI);
	    var bd_lng = z * Math.cos(theta) + 0.0065;
	    var bd_lat = z * Math.sin(theta) + 0.006;
	    return [bd_lng, bd_lat]
	},

	/**
	 * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换
	 * 即 百度 转 谷歌、高德
	 * @param bd_lon
	 * @param bd_lat
	 * @returns {*[]}
	 */
	bd09togcj02:function(bd_lon, bd_lat) {
	    var x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	    var x = bd_lon - 0.0065;
	    var y = bd_lat - 0.006;
	    var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
	    var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
	    var gg_lng = z * Math.cos(theta);
	    var gg_lat = z * Math.sin(theta);
	    return [gg_lng, gg_lat]
	},
	getTencentMapKey:function(){
		return 'C5BBZ-C3VK6-MRFSL-MMM2V-4SPF3-65BMJ'
	},
	abs:function(number){
		return Math.abs(number);
	},
	/*省市区选择*/ //(金谷覆盖区域)
	renderRegion:function(options){
		var selectors = options.selectors,
			filters = options.filters;	//select lay-filter, select id

		var province = $(selectors[0][1]).html(),
	        provinceView = $(selectors[0][0]),
	        city = $(selectors[1][1]).html(),
	        cityView = $(selectors[1][0]),
	        area = $(selectors[2][1]).html(),
	        areaView = $(selectors[2][0]);

	    var pId ='',
	    	cId = '',
	    	aId = '';

	    var provinceData,cityData,areaData;

		if(!options.fetch){
			provinceView.hide();
			cityView.hide();
			areaView.hide();
			options.selected('');
			return false;
		}else{
			provinceView.show();
			cityView.show();
			areaView.show();
		}

		var list = [];
		//获取省份
		util.post(URL.admin.area.jinguProvince,{},function(res){
		    if(res.code == 2){
		    	provinceData = res.data;
		    	list = res.data;
		        layui.laytpl(province).render(res.data,function(html){
		            provinceView.html(html);
		        });
		        if(options.defalut && options.defalut.length > 0){
	            	renderCity(options.defalut[0]);
	            	renderArea(options.defalut[1]);
	            	$('#'+filters[0]).val(options.defalut[0]);
	            	$('#'+filters[1]).val(options.defalut[1]);
	            	$('#'+filters[2]).val(options.defalut[2]);
	            }
				layui.form.render('select');
		        if(options.disabled){
					$('#'+filters[0]).find('option').attr('disabled','disabled');
		        	layui.form.render('select');
				}
		    }else{
		        util.msg(res.msg);
		    }
		});		

		//省份下拉事件
		layui.form.on('select('+filters[0]+')',function(data){
			pId = data.value;
		    if(data.value == ''){
		    	options.selected('');
		        return false;
		    }
		    renderCity(data.value);		    
		});

		 //城市下拉事件
		layui.form.on('select('+filters[1]+')',function(data){
			cId = data.value;
		    if(data.value == ''){
		    	options.selected(pId);
		        return;
		    }
		    renderArea(data.value);
		});

		//区下拉事件
		layui.form.on('select('+filters[2]+')',function(data){ 
			aId = data.value;       
		    if(data.value == ''){
		    	options.selected(cId);
		        return;
		    }
		    options.selected(data.value);
		});

		//渲染城市
		function renderCity(id){
			if(id){
				util.postSync(URL.admin.area.jinguSubs,{regionId:id},function(res){
			        if(res.code == 2){
			        	cityData = res.data;
			        	list = res.data;
			            layui.laytpl(city).render(res.data,function(html){
			                cityView.html(html);
			            })
			            $('#areaView').hide();
			            $('#areaView').hide();
			            $('#townView').hide(); 


			            layui.form.render('select');
			            if(options.disabled){
							$('#'+filters[1]).find('option').attr('disabled','disabled');
				        	layui.form.render('select');
						}
			            options.selected(id);
			        }else{
			            util.msg(res.msg);
			        }
			    });
			}
		}

		//渲染区域 
		function renderArea(id){			
			if(id){
				util.postSync(URL.admin.area.jinguSubs,{regionId:id},function(res){
			        if(res.code == 2){
			        	areaData = res.data;
			            layui.laytpl(area).render(res.data,function(html){
			                areaView.html(html);
			            });
			            $('#areaView').show();
			            $('#townView').hide();                
			            layui.form.render('select');

			            if(options.disabled){
							$('#'+filters[2]).find('option').attr('disabled','disabled');
				        	layui.form.render('select');
						}
			            
			            options.selected(id);		            
			        }else{
			            util.msg(res.msg);
			        }
			    })
			}
		}


	},
	isNumber:function(val){
		if(typeof val === 'number' && !isNaN(val)){
			return true;
		}else{
			return false;			
		}
	},
	//所有区域
	renderRegionAll:function(options){
		var selectors = options.selectors,
			filters = options.filters;	//select lay-filter, select id

		var province = $(selectors[0][1]).html(),
	        provinceView = $(selectors[0][0]),
	        city = $(selectors[1][1]).html(),
	        cityView = $(selectors[1][0]),
	        area = $(selectors[2][1]).html(),
	        areaView = $(selectors[2][0]);

	    var provinceData,cityData,areaData;

		if(!options.fetch){
			provinceView.hide();
			cityView.hide();
			areaView.hide();
			options.selected('');
			return false;
		}else{
			provinceView.show();
			cityView.show();
			areaView.show();
		}

		var list = [];
		//获取省份
		util.post(URL.admin.area.province,{},function(res){
		    if(res.code == 2){
		    	provinceData = res.data;
		    	list = res.data;
		        layui.laytpl(province).render(res.data,function(html){
		            provinceView.html(html);
		        });
		        if(options.defalut && options.defalut.length > 0){
	            	renderCity(options.defalut[0]);
	            	renderArea(options.defalut[1]);
	            	$('#'+filters[0]).val(options.defalut[0]);
	            	$('#'+filters[1]).val(options.defalut[1]);
	            	$('#'+filters[2]).val(options.defalut[2]);
	            }

		        layui.form.render('select');
		    }else{
		        util.msg(res.msg);
		    }
		});		

		//省份下拉事件
		layui.form.on('select('+filters[0]+')',function(data){
		    if(data.value == ''){
		        return;
		    }
		    renderCity(data.value);		    
		});

		 //城市下拉事件
		layui.form.on('select('+filters[1]+')',function(data){
		    if(data.value == ''){
		        return;
		    }
		    renderArea(data.value);
		});

		//区下拉事件
		layui.form.on('select('+filters[2]+')',function(data){        
		    if(data.value == ''){
		        return;
		    }
		    options.selected(data.value);
		});

		//渲染城市
		function renderCity(id){
			if(id){
				util.postSync(URL.admin.area.subs,{regionId:id},function(res){
			        if(res.code == 2){
			        	cityData = res.data;
			        	list = res.data;
			            layui.laytpl(city).render(res.data,function(html){
			                cityView.html(html);
			            })
			            $('#areaView').hide();
			            $('#areaView').hide();
			            $('#townView').hide(); 


			            layui.form.render('select');
			            options.selected(id);
			        }else{
			            util.msg(res.msg);
			        }
			    });
			}
		}

		//渲染区域
		function renderArea(id){			
			if(id){
				util.postSync(URL.admin.area.subs,{regionId:id},function(res){
			        if(res.code == 2){
			        	areaData = res.data;
			            layui.laytpl(area).render(res.data,function(html){
			                areaView.html(html);
			            });
			            $('#areaView').show();
			            $('#townView').hide();                
			            layui.form.render('select');
			            
			            options.selected(id);		            
			        }else{
			            util.msg(res.msg);
			        }
			    })
			}
		}
	},

	//select 模态框
	fakeSelect:function(options){
		$(document).on('click','.'+options.inputClass,function(){
			$(this).find('i').addClass('fake-icon-up');
			$('.'+options.inputClass).next('.select-wrap').css({height:'auto'});
			util.post(options.url,options.data,function(res){
				if(res.code == 2){
					var view = $('#'+options.selectId),
						list = $('#'+options.optionId).html();
					layui.laytpl(list).render(res.data.content,function(html){
						view.html(html);
					})
				}
			})			
		})

		$(document).on('click','body',function(e){
			if(e.target.className == 'fake-input'){
				if($('.'+options.inputClass).next('.select-wrap').css('height').split('px')[0] > 0){
					$('.'+options.inputClass).next('.select-wrap').css({'height':'0'})
				}else{
					$('.'+options.inputClass).next('.select-wrap').css({'height':'auto'})
				}
			}else if(e.target.className == 'option-list'){
				$('.'+options.inputClass).text(e.target.innerText).next('.select-wrap').css({height:'0'});
			}
		})
	}

}

$.fn.extend({
	serializeObject:function(){
		var obj = {}
	    var arr = this.serializeArray()
	    $.each(arr,function(i,v){
	    	var key = v.name;
	    	var value = v.value;
	    	if(obj[key]){
	    		if(util.isType(value)!='array'){
	    			obj[key] = [obj[key]]
	    		}
	    		obj[key].push(value)
	    	}else{
	    		obj[key] = value
	    	}
	    })
	    return obj
	},
	serializeJSON:function(){
		var obj = this.serializeObject()
    	return JSON.stringify(obj)
	}
})
String.prototype.trim = function() {
  return this.replace(/(^\s*)|(\s*$)/g, '')
}
/*
layui.form.verify({
	required:function(value,item){
		if(value.replace(/\s+/g,"") == ''){
			return '必填项不能为空'
		}
	},
	phone:function(value,item){
		if(value !=''){
			var reg =  /^1[3|4|5|6|7|8|9][0-9]{9}$/;
			var reg2 = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			if(!reg.test(value) && !reg2.test(value)){
				return '请输入正确的电话号码'
			}
		}
	},
	length255:function(value,item){
		if(value != ''){
			if(value.length > 255){
				return '不能超过255个字符';
			}
		}
	},
	length100:function(value,item){
		if(value != ''){
			if(value.length > 100){
				return '不能超过100个字符';
			}
		}
	},
	length200:function(value,item){
		if(value != ''){
			if(value.length > 200){
				return '不能超过200个字符';
			}
		}
	},
	length500:function(value,item){
		if(value != ''){
			if(value.length > 500){
				return '不能超过500个字符';
			}
		}
	},
	length10:function(value,item){
		if(value !=''){
			if(value.length>10){
				return '不能超过10个字符'
			}
		}
	},
	length30:function(value,item){
		if(value != ''){
			if(value.length > 30){
				return '不能超过30个字符';
			}
		}
	},
	length20:function(value,item){
		if(value != ''){
			if(value.length > 20){
				return '不能超过20个字符';
			}
		}
	},
	idCardVerify:function(value,item){
        var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        if(value !=''){
            if(!reg.test(value)){
                return '请输入有效的身份证号码'
            }
        }
    },
    realName:function(value,item){
		if(value != ''){
			var reg = /^([\u4e00-\u9fa5]{1,20}|[a-zA-Z\.\s]{1,20})$/;
			if(!reg.test(value)){
				return '真实姓名格式不正确';
			}
		}
	},
	isBankNo:function(value,item){
		if(value !=''){
			value = value.replace(/ /g,'');
			var reg = /^([1-9]{1})(\d{15}|\d{18})$/;
			if(!reg.test(value)){
				return '银行卡号格式不正确'
			}
		}
	},
	numberPositive:function(value,item){
		if(value != ''){
			var reg = /^[1-9]\d*$/;
			if(!reg.test(value)){
				return '请输入正整数'
			}
		}
	},
	numberInt:function(value){
		if(value != ''){
			var reg = /^[0-9]\d*$/;
			if(!reg.test(value)){
				return '请输入整数'
			}
		}
	}
});*/


$('input[type="password"]').on('paste',function(){
	//限制密码不能复制
	return false;
})

$('input[type="text"]').attr('autocomplete','off')
