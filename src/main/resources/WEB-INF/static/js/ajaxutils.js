$packageAjax = function (ajaxrequire,callback) {
    url = ajaxrequire.urls;
    dataType = ajaxrequire.dataType;
    async = ajaxrequire.async
    method = ajaxrequire.method;
    data = ajaxrequire.data;
    //data.token = token;
    //data.device = 'weixin';

    $.ajax({
        url: url,
        dataType: dataType,
        cache: false,
        async: true,
        type: method,
        data: data,
        timeout:10000,
        success:function(data){
            if (data.code != 2) {
                layer.msg(data.msg);
            }
            callback(data);
        },
        error:function(data){
            layer.msg(data.msg);
        }
    });
}