function changecol() {
    $("h2").css("background-color","#B2E0FF");}

Ext.onReady(function () {

    var toolbar=Ext.create('Ext.toolbar.Toolbar', {
        renderTo: 'toolbar',
        items: [
            {xtype: 'tbseparator'},
            {
                // xtype: 'button', // 默认的工具栏类型
                id:"button",
                text: '按钮'
            }

        ]
    });

    var gridstore=Ext.create('Ext.data.Store',{
        id:'namestore',
        autoLoad:false,
        fields:['id','name','password','content'],
        pageSize:25,
        proxy:{
            type:'ajax',
            url:'xxx.report?reportId=listdata',
            timeout:600000,
            reader:{
                type:'json',
                rootProperty:'records'
            }
        }
    });
    gridstore.load();
    var grid=Ext.create('Ext.grid.Panel',{
        id:'grid',
        store:gridstore,
        width:'100%',
        height:'100%',
        columnLines:true,

        columns:[
            {text: 'ID',  dataIndex:'id'},
            {text: '姓名',  dataIndex:'name',editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }},
            {text:'密码',dataIndex:'password'},
            {text:'内容',dataIndex:'content',flex:1}],
        selType: 'rowmodel',
        plugins: [
            Ext.create('Ext.grid.plugin.RowEditing', {
                clicksToEdit: 1
            })
        ],
        renderTo:'viewGird'
    });

    Ext.getCmp("button").on("click",function () {
        saveData();
    });

    function saveData() {
        var data=gridstore.getModifiedRecords();
        var params=[];
        // var id=new Array(data.length);
        // var name=new Array(data.length);
        // var password=new Array(data.length);
        // var content=new Array(data.length);
        for(var i=0;i<data.length;i++){
            params.push({"id":data[i].data.id,"name":data[i].data.name,"password":data[i].data.password,"content":data[i].data.content});
        }
        var mdata=Ext.encode(params);
       Ext.Ajax.request({
           url:'xxx.report?reportId=saveData',
           dataType:'json',
           params:{data:mdata},
           success:function (response) {
               console.log("yes");

           }
       })
    }



});