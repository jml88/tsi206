var currentMarker = null;
var otroMarker = null;
var elevator = null;

function handlePointClick(event) {

	var locations = [];
	elevator = new google.maps.ElevationService();
	locations.push(event.latLng);
	var positionalRequest = {
		'locations' : locations
	}
	elevator.getElevationForLocations(positionalRequest, function(results, status) {
		if (status == google.maps.ElevationStatus.OK) {
			if (results[0]) {
				document.getElementById('altura').value = results[0].elevation;
			} else {
				alert('No results found');
			}
		} else {
			alert('Elevation service failed due to: ' + status);
		}
	});

	if (currentMarker === null) {
		document.getElementById('lat').value = event.latLng.lat();
		document.getElementById('lng').value = event.latLng.lng();
		currentMarker = new google.maps.Marker({
			position : new google.maps.LatLng(event.latLng.lat(), event.latLng
					.lng()),
			draggable : true
		});
		PF('map').addOverlay(currentMarker);
	} else {
		document.getElementById('lat').value = event.latLng.lat();
		document.getElementById('lng').value = event.latLng.lng();
		otroMarker = new google.maps.Marker({
			position : new google.maps.LatLng(event.latLng.lat(), event.latLng
					.lng()),
			draggable : true
		});
		currentMarker.setMap(null);
		PF('map').addOverlay(otroMarker);
		currentMarker = otroMarker;
		otroMarker = null;
	}
}
