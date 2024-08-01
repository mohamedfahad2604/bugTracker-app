function getHistory(e) {
	var ktId = e.getAttribute('ktId');
	console.log(ktId);


	var formData = new FormData();
	formData.append('ktId', ktId);
	$("#kthistorytable tr").remove();
	
	$
		.ajax({
			type: "GET",
			url: "/getKtHistory?issueId=" + ktId,
			data: formData,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success: function(res) {
				var len = res.length;
				for (var i = 0; i < len; i++) {


					$("#kthistorytable").append(
						'<tr><td>'
						+ res[i].ktTo
						+ '</td><td>' + res[i].status
						+ '</td><td>' + moment(new Date(res[i].ktDate)).format('DD/MM/YYYY HH:mm:ss')
						/*+ '</td><td>' + res[i].ktDate*/
						+ '</td><td>' + res[i].ktTimeTaken
						+ '</td></tr>');
				}

				$('#myModal').modal('show');
			},
			error: function(e) {
				console.log(e);
			}
		});
}

