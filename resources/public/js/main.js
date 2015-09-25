/**
 * Created by Raca420 on 09-Sep-15.
 */



function dodajE(){
  $.confirm({
    title: 'Add new employee!',
    content: '<form action="/model/employees/insert" method="post">' +
        '<div class="form-group">'+
        '<label>First name:</label>'+
        ' <input type="text" class="form-control" name="firstName" placeholder="First name">'+
        ' </div>'+
        '<div class="form-group">'+
    '<label>Last name:</label>' +
    '<input type="text" class="form-control" name="lastName" placeholder="Last name">' +
    '</div>' +
    '<div class="form-group">' +
    ' <label>Email:</label>' +
    '<input type="text" class="form-control" name="email" placeholder="mail@gmail.com">' +
    '</div>'+
    '<button style="float: left" type="submit" class="btn btn-success" style="float:right">Add employee</button>' +
    '</form>',

    cancel: function(){

    }
});
}

function dodajC(){
    $.confirm({
        title: 'Add new customer!',
        content: '<form action="/model/customers/insert" method="post">' +
        '<div class="form-group">'+
        '<label>Customer name</label>'+
        ' <input type="text" class="form-control" name="customerName" placeholder="Name">'+
        ' </div>'+
        '<div class="form-group">'+
        '<label>Phone</label>' +
        '<input type="number" class="form-control" name="phone" placeholder="Phone">' +
        '</div>' +
        '<div class="form-group">' +
        ' <label>City:</label>' +
        '<input type="text" class="form-control" name="city" placeholder="City">' +
        '</div>'+
        '<button style="float: left" type="submit" class="btn btn-success" style="float:right">Add customer</button>' +
        '</form>',

        cancel: function(){

        }
    });
}