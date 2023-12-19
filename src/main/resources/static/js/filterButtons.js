function filterTable(status) {
    var rows = document.getElementsByClassName('question-row');
    for (var i = 0; i < rows.length; i++) {
        var row = rows[i];
        var rowStatus = row.classList[1].split('-')[1]; 
        row.style.display = (status === 'all' || rowStatus === status) ? '' : 'none';
    }
}