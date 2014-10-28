var wsocket;
var serviceLocation = "wss://localhost:8443/web_jatrik/chat/";
var $nickName;
var $message;
var $chatWindow;
var room = '';

function enterGral(e){
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 13){
		messageGral = $(PrimeFaces.escapeClientId('chatForm:tabChat:enviarGral'));
		messageGralText = messageGral.val();
		messageGral.val('').focus();
		list = $(PrimeFaces.escapeClientId('chatForm:tabChat:mensajesGral'));
		list.scrollTop = list[0].scrollHeight;		
		gralRC([{name: 'mensajeGral', value: messageGralText}]);
		
	}
}

function onMessageReceived(evt) {
	//var msg = eval('(' + evt.data + ')');
	var msg = JSON.parse(evt.data); // native API
	var $messageLine = $('<tr><td class="received">' + msg.received
			+ '</td><td class="user label label-info">' + msg.sender
			+ '</td><td class="message badge">' + msg.message
			+ '</td></tr>');
	$chatWindow.append($messageLine);
}

function sendMessage() {
	var msg = '{"message":"' + $message.val() + '", "sender":"'
	+ $nickName.val() + '", "received":""}';
	wsocket.send(msg);
	$message.val('').focus();
}

function connectToChatserver() {
	room = $('#chatroom option:selected').val();
	username = $(PrimeFaces.escapeClientId('chatForm:hiddenUsername')).val();
	wsocket = new WebSocket(serviceLocation + room + '/' + username);
	wsocket.onmessage = onMessageReceived;
}

function leaveRoom() {
	wsocket.close();
	$chatWindow.empty();
	$('.chat-wrapper').hide();
	$('.chat-signin').show();
	$nickName.focus();
}

$(document).ready(function() {
	
	connectToChatserver();
	
	$nickName = $('#nickname');
	$message = $('#message');
	$chatWindow = $('#response');
	$('.chat-wrapper').hide();
	$nickName.focus();

	$('#enterRoom').click(function(evt) {
		evt.preventDefault();
		connectToChatserver();
		$('.chat-wrapper h2').text('Chat # '+$nickName.val() + "@" + room);
		$('.chat-signin').hide();
		$('.chat-wrapper').show();
		$message.focus();
	});
	$('#do-chat').submit(function(evt) {
		evt.preventDefault();
		sendMessage()
	});

	$('#leave-room').click(function(){
		leaveRoom();
	});
});