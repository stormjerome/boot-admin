/**
 * 处理table中操作按钮隐藏时点击生效问题
 * @param layer
 * @param table
 */
function tableToolBarHide(layer, table) {
    //缓存当前操作的是哪个表格的哪个tr的哪个td
    $(document).off('mousedown','.layui-table-grid-down').on('mousedown','.layui-table-grid-down',function (event) {
        //直接记录td的jquery对象
        table._tableTrCurrr = $(this).closest('td');
    });

    //给弹出的详情里面的按钮添加监听级联的触发原始table的按钮的点击事件
    $(document).off('click','.layui-table-tips-main [lay-event]').on('click','.layui-table-tips-main [lay-event]',function (event) {
        var elem = $(this);
        var tableTrCurrr =  table._tableTrCurrr;
        if(!tableTrCurrr){
            return;
        }
        var layerIndex = elem.closest('.layui-table-tips').attr('times');
        layer.close(layerIndex);
        table._tableTrCurrr.find('[lay-event="' + elem.attr('lay-event') + '"]').first().click();
    });
}