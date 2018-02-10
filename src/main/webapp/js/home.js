
// start---form validation          
$.validate({
    lang: 'en'
});
// end---form validation

// start---ajax request for dropdown list
$(document).ready(function () {

    $('#company').change(function (event) {

        // clean info field
        $('#newOrder').text('');

        var company = $("select#company").val();
        $.get('dropDownAjax', {
            companyValue: company
        }, function (response) {

            var select = $('#fillingStation');
            select.find('option').remove();
            $.each(response, function (key, entry) {
                
                if (entry.address === undefined) {
                    select.append($('<option></option>').attr('value', entry.id)
                            .text(entry.code));
                } else {
                    // limitting the result string and display it in select item
                    var comboString = entry.code.concat(" : ").concat(entry.address);
                    if (comboString.length > 30) {
                        comboString = comboString.substring(0, 30).concat('...');
                    }
                    select.append($('<option></option>').attr('value', entry.id)
                            .text(comboString));
                }
            });
        });
    });
});
// end---ajax request for dropdown list

// start---form submit
$(document).ready(function () {
    $('#orderForm').on('submit', function (e) {
        e.preventDefault();
        var message;
        // if no proper choises in drop down lists
        if ($('select#company').val() === null) {
            showErrorMessage('company');
            return false;
        } else if ($('select#fillingStation').val() === null) {
            showErrorMessage('filling station');
            return false;
        } else if ($("select#fuelType").val() === null) {
            showErrorMessage('fuel type');
            return false;
        } else {
            $("#tableOut").empty();
            $.ajax({
                url: $(this).attr('action') || window.location.pathname,
                type: "POST",
                data: $(this).serialize(),
                success: function (data) {
                    $("#newOrder").html("Order with <b>id=" + data.id +
                            "</b> was sucessfully added to the database.")
                            .css('color', 'green');
                    updateTable();
                },
                error: function (jXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                }
            });
        }
    });
});

function showErrorMessage(selectorName) {
    $("#newOrder").text('Denied! Select proper ' + selectorName +
            ' in drop down list.').css('color', 'red');
}
// end---form submit

// start---show table
$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: 'showOrdersAjax',
        dataType: 'json',
        success: function (obj, textstatus) {
            console.log(obj);
            $('#ordersTable').DataTable({
                data: obj,
                columns: [
                    {data: "id"},
                    {data: "companyName"},
                    {data: "fillingStationCode"},
                    {data: "fillingStationAddress"},
                    {data: "orderDateHr"},
                    {data: "fuelType"},
                    {data: "costPerLiter"},
                    {data: "discount"},
                    {data: "amountLiters"},
                    {data: "totalCost"}
                ]
            });
        },
        error: function (obj, textstatus) {
            alert(obj.msg);
        }
    });
});
//end---show table

//start---update table
$(document).ready(function () {
    $('#updateTableBtn').click(function () {
        updateTable();
    });
});

function updateTable() {
    $.get('showOrdersAjax', function (newDataArray) {
        $('#ordersTable').DataTable().clear();
        $('#ordersTable').DataTable().rows.add(newDataArray);
        $('#ordersTable').DataTable().draw();
    });
}
//end---update table
