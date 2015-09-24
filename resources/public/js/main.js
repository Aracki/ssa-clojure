/**
 * Created by Raca420 on 09-Sep-15.
 */
function aud_play(){
    var audio = document.getElementById("myAudio");
    if(audio.paused){
        audio.play();
    } else{
        audio.pause();
    }
}



function dodajE(){
  $.confirm({
    title: 'Add new employee!',
    content: '<form action="/model/employees/insert" method="post">' +
        '<div class="form-group">'+
        '<label>Ime:</label>'+
        ' <input type="text" class="form-control" name="firstName" placeholder="Ime">'+
        ' </div>'+
        '<div class="form-group">'+
    '<label>Prezime:</label>' +
    '<input type="text" class="form-control" name="lastName" placeholder="Prezime">' +
    '</div>' +
    '<div class="form-group">' +
    ' <label>Email:</label>' +
    '<input type="text" class="form-control" name="email" placeholder="mail@gmail.com">' +
    '</div>'+'<div class="form-group">'+'<label>Office code:</label><br/>'+
    '<select name="" class="form-control" id="manufracturerId">' +
        '{{#offices}}'+
        ' <option value="{{id}}">{{city}}</option>'+
        '{{/offices}}'+
    ' </select>' +
    ' </div>' +
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