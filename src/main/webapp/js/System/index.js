function changecol() {
    $("h2").css("background-color","#B2E0FF");}

Ext.onReady(function () {


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
            {text: '姓名',  dataIndex:'name'},
            {text:'密码',dataIndex:'password'},
            {text:'内容',dataIndex:'content',flex:1}],
        renderTo:'viewGird'
    });

});