(function($){
    $(document).ready(function(){
        refreshListCustomer();
    });
})(AJS.$ || jQuery);

let datatable;
function refreshListCustomer() {
    let subsidiaryElt = document.getElementById("subsidiary");
    let subsidiary = subsidiaryElt!=null? subsidiaryElt.value : null;

    $("thead>tr.filters").remove();
    if(subsidiary!==undefined && subsidiary!=null && subsidiary.length>2){
        $('#controls-table thead tr').clone(true).removeClass('head-title')
        .addClass('filters').appendTo('#controls-table thead');
         datatable = new DataTable('#controls-table', {
            orderCellsTop: true,
            fixedHeader: true,
            processing: true,
            serverSide: true,
            jQueryUI: true,
            pagingType: "full_numbers",
            length: 50,
            lengthMenu: [10, 25, 50, 100, 500],
            colReorder: true,
            ajax: {
                url: AJS.contextPath() + `/rest/cokpitv4/1.0/customer/${subsidiary}/filter`,
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: function(data) { return data = JSON.stringify(data);}
            },
            initComplete: function () {
                var api = this.api();
                api.columns().eq(0)
                    .each(function (colIdx) {
                        var cell = $('.filters th').eq(
                            $(api.column(colIdx).header()).index()
                        );
                        var title = $(cell).text();
                        $(cell).html('<input type="text" placeholder="' + title + '" />');
                        $('input', $('.filters th').eq($(api.column(colIdx).header()).index()))
                            .off('keyup change')
                            .on('keyup change', function (e) {
                                e.stopPropagation();
                                $(this).attr('value', $(this).val());
                                var cursorPosition = this.selectionStart;
                                api.column(colIdx)
                                    .search(this.value, false, this.value == '').draw();
                                $(this)
                                    .focus()[0]
                                    .setSelectionRange(cursorPosition, cursorPosition);
                            });
                    });
            },
            columns: [
                {"data" : "codeClient", searchable: true, orderable: true},
                {"data" : "displayName", searchable: true, orderable: true},
                {"data" : "libelleTypeClient", searchable: true, orderable: true},
                {"data" : "statutKYC", searchable: true, orderable: true},
                {"data" : "segmentClient", searchable: true, orderable: true},
                {"data" : "classificationRisque", searchable: true, orderable: true},
                {"data" : "avisRCOFatca", searchable: true, orderable: true},
                {"data" : "flagPEP", searchable: true, orderable: true}
            ],
            columnDefs : [
                {
                    "render": function ( data, type, row ) { return '<a href="/plugins/servlet/cokpitv4/kyc?subsidiary='+subsidiary+'&key='+row.codeClient+'"><span class="aui-icon aui-icon-small aui-iconfont-new-watch"></span>'+row.codeClient+'</a>' },
                    "targets": 0
                },
                { "visible": false,  "targets": [] }
            ],
            language: {
                processing:     WRM.I18n.getText('cokpit.frc.datatable.processing'),
                search:         WRM.I18n.getText('cokpit.frc.datatable.search'),
                lengthMenu:     WRM.I18n.getText('cokpit.frc.datatable.lengthMenu'),
                info:           WRM.I18n.getText('cokpit.frc.datatable.info'),
                infoEmpty:      WRM.I18n.getText('cokpit.frc.datatable.infoEmpty'),
                infoFiltered:   WRM.I18n.getText('cokpit.frc.datatable.infoFiltered'),
                infoPostFix:    WRM.I18n.getText('cokpit.frc.datatable.infoPostFix'),
                loadingRecords: WRM.I18n.getText('cokpit.frc.datatable.loadingRecords'),
                zeroRecords:    WRM.I18n.getText('cokpit.frc.datatable.zeroRecords'),
                emptyTable:     WRM.I18n.getText('cokpit.frc.datatable.emptyTable'),
                paginate: {
                    first:      WRM.I18n.getText('cokpit.frc.datatable.paginate.first'),
                    previous:   WRM.I18n.getText('cokpit.frc.datatable.paginate.previous'),
                    next:       WRM.I18n.getText('cokpit.frc.datatable.paginate.next'),
                    last:       WRM.I18n.getText('cokpit.frc.datatable.paginate.last'),
                },
                aria: {
                    sortAscending:  WRM.I18n.getText('cokpit.frc.datatable.area.sortAscending'),
                    sortDescending: WRM.I18n.getText('cokpit.frc.datatable.area.sortDescending'),
                }
            }
        });
    }
}