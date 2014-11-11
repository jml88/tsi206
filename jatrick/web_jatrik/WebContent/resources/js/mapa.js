var currentMarker = null;
var otroMarker = null;

function handlePointClick(event) {
	if (currentMarker === null) {
		document.getElementById('lat').value = event.latLng.lat();
		document.getElementById('lng').value = event.latLng.lng();
		currentMarker = new google.maps.Marker({position : new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()), draggable: true});
		PF('map').addOverlay(currentMarker);
	} else {
		document.getElementById('lat').value = event.latLng.lat();
		document.getElementById('lng').value = event.latLng.lng();
		otroMarker = new google.maps.Marker({position : new google.maps.LatLng(event.latLng.lat(), event.latLng.lng()), draggable: true});
		currentMarker.setMap(null);
		PF('map').addOverlay(otroMarker);
		currentMarker = otroMarker;
		otroMarker = null;
	}
}
