 	//for info message displayed when
 	//contact have been deleted
 	function deleteContact(e) {
 	  var msg   = $(e.target).serialize();
        $.ajax({
          type: 'POST',
          url: 'contacts/',
          data: msg,
          success: function(data) {
            var obj = $.parseJSON(
              JSON.stringify(data)
            );
            var target = $('#delete-info');
            infoBlock(target, obj);
            $('#' + obj.id).remove();
            if ($("#contactListContainer").children().length < 1) {
            target = $("#contactListContainer");
                        loadData(target);
            }

          },
          error:  function(xhr, str){
	    alert('Error occurred: ' + xhr.responseCode);
          }
        });
    }
    //for info message displayed when
    //new contact have been added
    function addContact(e) {
 	  var msg   = $(e.target).serialize();
        $.ajax({
          type: 'POST',
          url: 'contacts/',
          data: msg,
          success: function(data) {
            var obj = $.parseJSON(
              JSON.stringify(data)
            );
            var target = $('#add-info');
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
    //helper function
    //used in: addContact()/deleteContect()
    //fade-in & fade-out #add-info/#delete-info
    //blocks
    function infoBlock(target, obj) {
        $("#info-msg-block>div").css('display', 'none');
        target.find('span').empty();
        target.find('span').append(obj.firstName + ' ' + obj.lastName);
        target.fadeIn(400);
        setTimeout(function() {
            target.fadeOut('slow');
            },
            2200
        );
    }
    //helper function
    //used in: addContact()
    //get all contacts & insert them
    //instead of current list
    function refreshContactList() {
        $.ajax({
          type: 'GET',
          url: 'contacts/',
          success: function(data) {
            fillContactList();
          },
          error:  function(xhr, str){
        alert('Error occurred: ' + xhr.responseCode);
          }
        });
    }
    //helper function
    //used in: refreshContactList()
    //actually remove old contacts from
    //div and fill it with given
    function fillContactList() {
        var target = $("#contactListContainer");
        target.empty();
        loadData(target);
    }

    //loads contacts content into
    //the 'target' element
    function loadData(target) {
        $.ajax({
              type: 'GET',
              url: 'util/preparedContactList',
              success: function(data) {
                target.append(data);
              },
              error:  function(xhr, str){
            alert('Error occurred: ' + xhr.responseCode);
              }
            });
    }

    //helper function
    //used in addContact()
    //resolves the error message by
    //its code
    function resolveAndDisplayMessage(code) {
        return $.ajax({
              type: 'GET',
              url: 'contacts/resolve-error',
              data:{code:code},
              success: function(data) {
                alert(data);
              },
              error:  function(xhr, str){
            alert('Error occurred: ' + xhr.responseCode);
              }
            });
    }
