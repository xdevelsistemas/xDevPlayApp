/* <![CDATA[ */
/// Jquery validate newsletter
jQuery(document).ready(function(){

	$('#newsletter').submit(function(){

		var action = $(this).attr('action');

		$("#message-newsletter").slideUp(750,function() {
		$('#message-newsletter').hide();
		
		$('#submit-newsletter')
			.after('<i class="icon-spin3 animate-spin loader"></i>')
			.attr('disabled','disabled');

		$.post(action, {
			email_newsletter: $('#email_newsletter').val()
		},
			function(data){
				document.getElementById('message-newsletter').innerHTML = data;
				$('#message-newsletter').slideDown('slow');
				$('#newsletter .loader').fadeOut('slow',function(){$(this).remove()});
				$('#submit-newsletter').removeAttr('disabled');
				if(data.match('success') != null) $('#newsletter').slideUp('slow');

			}
		);

		});

		return false;

	});

});

// Jquery validate form contact
jQuery(document).ready(function(){

	$('#check_avail').submit(function(){

		var action = $(this).attr('action');

		$("#message-booking").slideUp(750,function() {
		$('#message-booking').hide();

 		$('#submit-check')
			.after(' <i class="icon-spin3 animate-spin loader"></i>')
			.attr('disabled','disabled');

		$.post(action, {
			check_in: $('#check_in').val(),
			check_out: $('#check_out').val(),
			name: $('#name').val(),
			email: $('#email').val(),
			quantity: $('#quantity').val()
	
		},
			function(data){
				document.getElementById('message-booking').innerHTML = data;
				$('#message-booking').slideDown('slow');
				$('#check_avail .loader').fadeOut('slow',function(){$(this).remove()});
				$('#submit-check').removeAttr('disabled');
				if(data.match('success') != null) $('#check_avail').slideUp('slow');

			}
		);

		});

		return false;

	});
		});
		

  /* ]]> */