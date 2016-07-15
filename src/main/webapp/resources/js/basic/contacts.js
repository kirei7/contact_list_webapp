 	function call(e) {
 	  var msg   = $(e.target).serialize();
        $.ajax({
          type: 'POST',
          url: 'contacts/',
          data: msg,
          success: function(data) {
            var obj = $.parseJSON(
              JSON.stringify(data)
            );
            $('#' + obj.id).remove();
          },
          error:  function(xhr, str){
	    alert('Error occurred: ' + xhr.responseCode);
          }
        });
    }
    /*function deleteContactDiv(data) {
        var obj = $.parseJSON(data);
        $("" + obj.id).remove();
    }*/