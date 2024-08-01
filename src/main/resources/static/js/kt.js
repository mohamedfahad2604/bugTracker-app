$(document).ready(function() {
	const myInput = document.getElementById('ktTime');
	const timeInstance = M.Timepicker.init(myInput, {
	twelveHour: false,
    defaultTime: 'now'
});

// forces materialize time picker to display default time in input
timeInstance._updateTimeFromInput();
timeInstance.done();

});

