function onCalled(e){window.location.href=e}function loadTinyMCE(){tinymce.init({selector:"textarea",theme:"modern",resize:"vertical",plugins:["advlist autolink lists link image charmap  preview hr ","searchreplace wordcount visualblocks visualchars code ","insertdatetime  nonbreaking save table contextmenu directionality","paste textcolor colorpicker textpattern"],menubar:!1,toolbar:"styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent"})}function onShowAlertDiv(e,t,a){var n="";"info"==a&&(n='<div class="alert alert-info alert-dismissable" '),"success"!=a&&""!=allTrim(a)||(n='<div class="alert alert-success alert-dismissable" '),"warning"==a&&(n='<div class="alert alert-warning alert-dismissable" '),"danger"==a&&(n='<div class="alert alert-danger alert-dismissable" '),""!=allTrim(t)&&(n=n+' style="margin-bottom:5px;"> <button aria-hidden="true" data-dismiss="alert" class="close" type="button"></button>'+t+"</div>",$("#"+e).html(n))}function onShowTextAreaLengthSpan(e){var t=$("#"+e).attr("maxlength");$("#"+e).after("<div><span id='"+e+"LengthSpanId'>"+t+"</span> Characters Remaining</div>"),onCheckTextAreaMaxLength(e,t),$("#"+e).bind("keyup change",function(){onCheckTextAreaMaxLength(this.id,t)})}function onCheckTextAreaMaxLength(e,t){var a=$("#"+e).val().length;$("#"+e+"LengthSpanId").text(parseInt(t)-parseInt(a)),a>t&&($("#"+e).val($("#"+e).val().slice(0,t)),$("#"+e+"LengthSpanId").text(0))}