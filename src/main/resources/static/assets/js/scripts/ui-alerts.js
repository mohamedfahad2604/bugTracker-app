/*
 * UI - Alerts
 */

$(".card-alert .close").click(function () {
    $(this)
        .closest(".card-alert")
        .fadeOut("slow");
});
function hideDeleteDiv(){
	$('#deletedDiv').hide();
}