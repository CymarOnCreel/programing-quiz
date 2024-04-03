function filterTable(status) {
	var rows = document.getElementsByClassName('question-row');
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		var rowStatus = row.classList[1].split('-')[1];
		row.style.display = (status === 'all' || rowStatus === status) ? ''
				: 'none';
	}
}

function deleteQuestion(element) {
	var questionId = element.getAttribute('data-question-id');
	var questionText = element.getAttribute('data-question-text');

	if (confirm('Are you sure you want to delete this question?\n'
			+ questionText)) {
		$.ajax({
			url : '/questionManagement/' + questionId,
			type : 'DELETE',
			success : function(data) {
				alert(data);
				location.reload();
			},
			error : function(error) {
				alert('Error deleting question');
			}
		});
	}
}

function updateQuestion(element) {

	var isConfirmed = confirm('Are you sure you want to update?');
	if (isConfirmed) {
		var form = $(element).closest('form');
		$.ajax({
			url : form.attr('action'),
			type : form.attr('method'),
			data : form.serialize(),
			success : function(data) {
				alert('Update Succesfull :)');
			},
			error : function(error) {
				alert('Error updateting:)');
			}
		});
	}
}
