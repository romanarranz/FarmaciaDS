$('#mapa').locationpicker({
	location: {
		latitude: 46.15242437752303,
        longitude: 2.7470703125
	},
	radius: 300,
		inputBinding: {
		latitudeInput: $('#us3-lat'),
		longitudeInput: $('#us3-lon'),
		radiusInput: $('#us3-radius'),
		locationNameInput: $('#us3-address')
	},
	enableAutocomplete: true,
	onchanged: function (currentLocation, radius, isMarkerDropped) {
		// Uncomment line below to show alert on each Location Changed event
        //alert("Location changed. New location (" + currentLocation.latitude + ", " + currentLocation.longitude + ")");
	}
});