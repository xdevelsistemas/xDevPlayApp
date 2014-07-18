<div id="content" class="clearfix">
<!-- /.map-wrapper -->
<div class="container">
<div class="row">

<div class="sidebar span3">
    <?php include get_include_path() . '/templates/sidebar/block-enquire-now.php'; ?>
    <?php include get_include_path() . '/templates/sidebar/block-extended-search.php'; ?>
    <?php include get_include_path() . '/templates/sidebar/block-partners.php'; ?>
    <?php include get_include_path() . '/templates/sidebar/block-most-recent.php'; ?>
    <?php include get_include_path() . '/templates/sidebar/block-agencies.php'; ?>
</div>
<!-- /#sidebar -->

<div id="main" class="span9 single-property">

<h1 class="page-header fl">643 37th Ave,</h1>
<span>Burrville</span>

<div class="property-detail">

<div class="row">
    <div class="span6 gallery">
        <div class="preview">
            <img src="/assets/img/property/19.jpg" alt="">
        </div>

        <div class="content">
            <ul>
                <li class="active">
                    <div class="thumb">
                        <a href="#"><img src="/assets/img/property/19.jpg" alt=""></a>
                    </div>
                </li>
                <li>
                    <div class="thumb">
                        <a href="#"><img src="/assets/img/property/16.jpg" alt=""></a>
                    </div>
                </li>
                <li>
                    <div class="thumb">
                        <a href="#"><img src="/assets/img/property/17.jpg" alt=""></a>
                    </div>
                </li>
                <li>
                    <div class="thumb">
                        <a href="#"><img src="/assets/img/property/18.jpg" alt=""></a>
                    </div>
                </li>
                <li>
                    <div class="thumb">
                        <a href="#"><img src="/assets/img/property/10.jpg" alt=""></a>
                    </div>
                </li>

            </ul>
        </div>
        <!-- /.content -->
    </div>

    <div class="overview">
        <div class="pull-right overview">
            <div class="row">
                <div class="span3">
                    <!-- <h2>Overview</h2> -->

                    <table>
                        <tbody>
                        <tr>
                            <th>Price:</th>
                            <td class="price">
                                Contact us
                            </td>
                        </tr>

                        <tr>
                            <th>ID:</th>
                            <td><strong>#358</strong></td>
                        </tr>

                        <tr>
                            <th>Contract type:</th>
                            <td>
                        <span class="contract-type">
                                                            For sale
                                                    </span>
                            </td>
                        </tr>

                        <tr>
                            <th>Type:</th>
                            <td>Family House</td>
                        </tr>

                        <tr>
                            <th>Location:</th>
                            <td>Burrville</td>
                        </tr>


                        <tr>
                            <th>Bedrooms:</th>
                            <td>2</td>
                        </tr>

                        <tr>
                            <th>Area:</th>
                            <td>800m<sup>2</sup></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.span2 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /.overview -->        </div>
</div>

<h2>About Property</h2>

<p>Quisque non dictum eros. Praesent porta vehicula arcu eu ornare. Donec id egestas arcu. Suspendisse auctor
    condimentum ligula ultricies cursus. Vestibulum vel orci vel lacus rhoncus sagittis sed vitae mi. Pellentesque
    molestie elit bibendum tincidunt semper. Aliquam ac volutpat risus. In felis felis, posuere commodo aliquet eget,
    sagittis sed turpis. Phasellus commodo turpis non nunc egestas, et egestas felis pretium. Pellentesque dignissim
    libero vitae tincidunt semper. Nam id ante nisi. Nam sollicitudin, dolor non suscipit feugiat, nibh lacus commodo
    metus, nec tempus dui velit sagittis velit. Pellentesque elementum risus rhoncus justo porta, at varius odio
    consequat. Duis non augue adipiscing, posuere quam non, tempus urna.</p>


<div class="row">
    <div class="span6">
        <div class="row">
            <div class="span6">
                <h2>General amenities</h2>

                <div class="row">
                    <ul class="span2">
                        <li class="checked">Air conditioning</li>
                        <li class="plain">Balcony</li>
                        <li class="plain">Bedding</li>
                        <li class="checked">Cable TV</li>
                        <li class="plain">Cleaning after exit</li>
                        <li class="plain">Cofee pot</li>
                        <li class="checked">Computer</li>
                        <li class="checked">Cot</li>
                        <li class="checked">Dishwasher</li>
                        <li class="checked">DVD</li>
                        <li class="plain">Fan</li>
                    </ul>
                    <!-- /.span2 -->
                    <ul class="span2">
                        <li class="plain">Fridge</li>
                        <li class="checked">Grill</li>
                        <li class="plain">Hairdryer</li>
                        <li class="plain">Heating</li>
                        <li class="plain">Hi-fi</li>
                        <li class="plain">Internet</li>
                        <li class="plain">Iron</li>
                        <li class="checked">Juicer</li>
                        <li class="plain">Lift</li>
                        <li class="checked">Microwave</li>
                        <li class="plain">Oven</li>
                    </ul>
                    <!-- /.span2 -->
                    <ul class="span2">
                        <li class="plain">Parking</li>
                        <li class="checked">Parquet</li>
                        <li class="checked">Radio</li>
                        <li class="checked">Roof terrace</li>
                        <li class="plain">Smoking allowed</li>
                        <li class="plain">Terrace</li>
                        <li class="plain">Toaster</li>
                        <li class="checked">Towelwes</li>
                        <li class="checked">Use of pool</li>
                        <li class="plain">Video</li>
                    </ul>
                    <!-- /.span2 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.span12 -->
        </div>
        <!-- /.row -->
    </div>

    <div class="span3">
        <h2>Map</h2>

        <div id="property-map"
             style="position: relative; background-color: rgb(229, 227, 223); overflow: hidden; -webkit-transform: translateZ(0);">
        </div>

        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                function LoadMapProperty() {
                    var locations = new Array(
                      [38.951399, -76.958463]
                    );
                    var types = new Array(
                      'family-house'
                    );
                    var markers = new Array();
                    var plainMarkers = new Array();

                    var mapOptions = {
                        center: new google.maps.LatLng(38.951399, -76.958463),
                        zoom: 14,
                        mapTypeId: google.maps.MapTypeId.ROADMAP,
                        scrollwheel: false
                    };

                    var map = new google.maps.Map(document.getElementById('property-map'), mapOptions);

                    $.each(locations, function (index, location) {
                        var marker = new google.maps.Marker({
                            position: new google.maps.LatLng(location[0], location[1]),
                            map: map,
                            icon: '/assets/img/marker-transparent.png'
                        });

                        var myOptions = {
                            draggable: true,
                            content: '<div class="marker ' + types[index] + '"><div class="marker-inner"></div></div>',
                            disableAutoPan: true,
                            pixelOffset: new google.maps.Size(-21, -58),
                            position: new google.maps.LatLng(location[0], location[1]),
                            closeBoxURL: "",
                            isHidden: false,
                            // pane: "mapPane",
                            enableEventPropagation: true
                        };
                        marker.marker = new InfoBox(myOptions);
                        marker.marker.isHidden = false;
                        marker.marker.open(map, marker);
                        markers.push(marker);
                    });

                    google.maps.event.addListener(map, 'zoom_changed', function () {
                        $.each(markers, function (index, marker) {
                            marker.infobox.close();
                        });
                    });
                }

                google.maps.event.addDomListener(window, 'load', LoadMapProperty);

                var dragFlag = false;
                var start = 0, end = 0;

                function thisTouchStart(e) {
                    dragFlag = true;
                    start = e.touches[0].pageY;
                }

                function thisTouchEnd() {
                    dragFlag = false;
                }

                function thisTouchMove(e) {
                    if (!dragFlag) return;
                    end = e.touches[0].pageY;
                    window.scrollBy(0, ( start - end ));
                }

                document.getElementById("property-map").addEventListener("touchstart", thisTouchStart, true);
                document.getElementById("property-map").addEventListener("touchend", thisTouchEnd, true);
                document.getElementById("property-map").addEventListener("touchmove", thisTouchMove, true);
            });

        </script>
    </div>

</div>
</div>

</div>
<!-- /#main -->

</div>
<!-- /.row -->
</div>
<!-- /.container -->

</div>