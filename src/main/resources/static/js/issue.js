function getHistory(e) {
	var issueId = e.getAttribute('issueId');
	var formData = new FormData();
	formData.append('issueId', issueId);
	$("#issuehistorytable tr").remove();
	$.ajax({
			type: "GET",
			url: "/getIssueStatusHistory?issueId=" + issueId,
			data: formData,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			success: function(res) {
				var len = res.length;
				for (var i = 0; i < len; i++) {
					$("#issuehistorytable").append(
						'<tr><td>'
						+ res[i].staff
						+ '</td><td>' + res[i].status
						+ '</td><td>' + moment(new Date(res[i].createDate)).format('DD/MM/YYYY HH:mm:ss')
						+ '</td></tr>');
				}

				$('#myModal').modal('show');
			},
			error: function(e) {
				console.log(e);
			}
		});
}

