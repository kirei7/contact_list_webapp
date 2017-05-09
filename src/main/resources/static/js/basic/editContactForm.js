$(document).ready(function(){
    $("#edit-modal").html(
        getForm()
    );
    $("#edit-modal form").attr("id", "edit-contact");
});

//this button opens a modal window and this particular
//function adds an 'id' attribute to modal form
//and fills input fields with values
function modalButtonClick(btn) {
    console.log("button clicked");
    var contact = $(btn).closest(".contact-wrapper"),
        id = contact.attr("id");
    console.log(id);
    $("#edit-contact :input[name=id]").attr(
            "value",
            id
        );
    $("#edit-contact :input[name=firstName]").val(
        contact.find(".firstName").html()
    );
    $("#edit-contact :input[name=lastName]").val(
        contact.find(".lastName").html()
    );
    $("#edit-contact :input[name=email]").val(
        contact.find(".email").html()
    );
    $("#edit-contact :input[name=mobileNum]").val(
        contact.find(".mobileNum").html()
    );
    $("#edit-contact :input[name=workNum]").val(
        contact.find(".workNum").html()
    );

}

function editContact(e) {
    var msg   = $(e.target).serialize();
    $.ajax({
              type: 'POST',
              url: 'contacts/',
              data: msg,
              success: function(data) {
                var obj = $.parseJSON(
                  JSON.stringify(data)
                );
                var target = $('#edit-info');
                //here we using functions defined in contacts.js
                infoBlock(target, obj);
                refreshContactList();
              },
              error:  function(xhr, str){
              //here we pass the message code from response error to
              //resolver, which returns 'up-to-locale', readable message
              //example code: contact.firstName.required
                resolveAndDisplayMessage(
                        xhr.responseJSON.message
                    );
              }
            });
}

//prepare and return a copy of the addContact form
//but with proper "edit" attributes and fields
function getForm() {
    var form = $("#add-contact").clone();
    form.attr("onsubmit", "editContact(event)");
    form.removeAttr("id");
    form.append('<input type="hidden" name="id" />');
    form.append('<input type="hidden" value="put" name="_method">');
    form.find('input[type=submit]').remove();
    $("#edit-contact :input").val('');
    return form;
}

function submitEditForm() {
    $("#edit-contact").submit();
}