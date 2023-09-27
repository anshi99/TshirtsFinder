$(document).ready(() => {
	$('#login_form').on('submit', e => {
		e.preventDefault();
		$('#username_error').html("");
		$('#password_error').html("");
		if (validateLoginForm()) {
			$('#login_loading').show();
			ajaxUserRequest();
		}
	});

	$('#tshirt_form').on('submit', e => {
		e.preventDefault();
		$('#no_tshirt_found').hide();
		$('#result_table').hide();
		$('#tshirt_loading').show();
		if (validateTShirtForm()) {
			ajaxFillTable();
		}
		$('#tshirt_loading').hide();
	});
});

let validateLoginForm = () => {
	// Check if username field is not empty
	if (!$('#username').val()) {
		$('#username_error').html("This field cannot be empty");
		return false;
	}

	// Check if username field is not empty
	if (!$('#password').val()) {
		$('#password_error').html("This field cannot be empty");
		return false;
	}

	return true;
}

let validateTShirtForm = () => {
	if (!$('#color').val()) {
		$('#color_error').html("This field cannot be empty");
		$('#color_error').show();
		return false;
	}

	return true;
}

let ajaxFillTable = () => {
	$.ajax({
		type: "POST",
		url: $('form').attr('action'),
		data: $('form').serialize(),
		success: (response) => {
			if (response.length != 0) {
				console.log(response);
				//Construct a table to display response
				var result = '';
				var count = 1;
				$.each(response, (i) => {
					result += '<tr>';
					result += '<td style="width: 5%;" >' + count++ + '</td>';
					result += '<td style="width: 15%">' + response[i].id + '</td>';
					result += '<td style="width: 20%">' + response[i].name + '</td>';
					result += '<td style="width: 10%">' + response[i].color + '</td>';
					result += '<td style="width: 8%">' + response[i].gender + '</td>';
					result += '<td style="width: 8%">' + response[i].size + '</td>';
					result += '<td style="width: 8%">$' + response[i].price + '</td>';
					result += '<td style="width: 8%">' + response[i].rating + '</td>';
					result += '<td style="width: 8%">' + response[i].avalibility + '</td>';
					result += '</tr >';
				});
				result += '';

				$('#result_body').html(result);
				$('#result_table').show();
			} else {
				$('#no_tshirt_found').show();
			}
		},
		error: (error) => {
			console.log("Some Error Occurred!");
			console.log(error);
		}
	}).done(() => {
		$('#color_error').hide();
		$('#tshirt_loading').hide();
	});
}

let ajaxUserRequest = () => {
	$.ajax({
		type: "POST",
		url: $('form').attr('action'),
		data: $('form').serialize(),
		success: (response) => {
			if (!response) {
				$(location).prop('href', './');
			} else {
				let data = $.parseJSON(response);
				if (data.username_error) {
					$('#username_error').html(data.username_error);
				}
				if (data.password_error) {
					$('#password_error').html(data.password_error);
				}
			}
		},
		error: (error) => {
			console.log(error);
		}
	}).done(() => {
		$('#login_loading').hide();
	});
}