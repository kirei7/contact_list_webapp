 	//for info message displayed when
 	//contact have been deleted
 	function deleteContact(e) {
                $("#info-msg-block>div").css('display', 'none');
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
          },
          error:  function(xhr, str){
	    alert('Error occurred: ' + xhr.responseCode);
          }
        });
    }
    //for info message displayed when
    //new contact have been added
    function addContact(e) {
                    $("#info-msg-block>div").css('display', 'none');
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
	    alert('Error occurred: ' + xhr.responseCode);
          }
        });
    }
    //helper function
    //used in: addContact()/deleteContect()
    //fade-in & fade-out #add-info/#delete-info
    //blocks
    function infoBlock(target, obj) {
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
