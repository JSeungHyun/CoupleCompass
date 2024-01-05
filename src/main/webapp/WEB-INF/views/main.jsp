<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<div class="map" id="map">
</div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c0bb305bb3ec4d96d99cd810fb25caab"></script>
<script>
    let tmp = 0;
    let isNewMarker = true;
    let marker;
    let container = document.getElementById('map');
    let options = {
        center: new kakao.maps.LatLng(37.5760222, 126.9769000),
        level: 7
    };

    let map = new kakao.maps.Map(container, options);
    let zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);

    kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
        var latlng = mouseEvent.latLng; // 클릭 좌표
        if (isNewMarker){
            marker = new kakao.maps.Marker({
                position: latlng
            });
            marker.setMap(map);
            isNewMarker = false
        } else {
            marker.setPosition(latlng);
        }
    });
</script>
</body>
</html>