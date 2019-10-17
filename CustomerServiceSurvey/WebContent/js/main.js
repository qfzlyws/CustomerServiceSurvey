/**
 * 
 */
function checkNull()
{
	var checked=true;
	
	$(".keyIndicator").each(function(){
		var keyItem = $(this).attr("id");		
		var isChecked = $('input:radio[name="' +  keyItem + '"]:checked').val()?true:false;
		
		if(!isChecked)
		{
			$('div[name="' +  keyItem + '"]').addClass("hightlightborder");
			$([document.documentElement, document.body]).animate({
		        scrollTop: $('div[name="' +  keyItem + '"]').offset().top
		    }, 2000);
			checked=false;
			return false;
		}
		else
		{
			$('div[name="' +  keyItem + '"]').removeClass("hightlightborder");
			return true;
		}
		
	});
	
	if(!checked)
	{
		return checked;
	}
	
	$("span:contains(其他)").each(function(){
		var spanId = $(this).attr("id");
		var spanText = $(this).text();
		
		var isChecked = $("input[value='" +  spanId + "']:checked").val()?true:false;
		
		if(isChecked){
			var userKeyValue = $("input[type='text'][id='"+spanId+"']").val();
			
			if(userKeyValue==null || userKeyValue==""){
				$("input[type='text'][id='"+spanId+"']").addClass("hightlightborder");
				$([document.documentElement, document.body]).animate({
			        scrollTop: $("input[type='text'][id='"+spanId+"']").offset().top
			    }, 2000);
				checked = false;
				return false;
			}
			else
			{
				$("input[type='text'][id='"+spanId+"']").removeClass("hightlightborder");
				return true;
			}
		}
		else
		{
			$("input[type='text'][id='"+spanId+"']").removeClass("hightlightborder");
			return true;
		}
	});
	
	return checked;
}