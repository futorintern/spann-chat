<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script>
$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
       return null;
    }
    else{
       return results[1] || 0;
    }
}
$(function(){
	
	var myTopic = $.urlParam('email');
	var email = $.urlParam('email'); 
	$.ajax({
		url : "users",
		success: function(data) {
			data = $.parseJSON(data);
			data.forEach(function(user) {
				alert(user)
			})
		}
	})
	
	function sendMessage() {
		var msg = $("div#chatBox input[type='text']").val();
	}
	
	$("div#chatBox input[type='text']").keyup(function(event) {
		var code = event.which;
		if(code == 13) {
			event.preventDefault();
			sendMessage();
		}
		
	});
});
</script>
</head>
<body>
	<table vertical-align=top>
		<tr>
			<th>Online Users</th>
			<th colspan=2>
				<div id="chatBox">
					<textarea rows="20" cols="39"></textarea>
					<br></br>
					<input type="text"><input type="submit" value="send">
				</div>
			</th>
		</tr>
		<tr>
			<td id="onlineUsers"></td>
		</tr>
	</table>
</body>
</html>