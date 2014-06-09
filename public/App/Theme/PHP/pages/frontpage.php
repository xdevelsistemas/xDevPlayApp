<div id="content" class="clearfix">
    <div class="map-wrapper">
        <div class="map">


            <script type="text/javascript">

                var infoBoxes = [];

                var property1 = '<div class="infobox clearfix"><div class="close"><img src="/assets/img/close.png" alt=""></div><div class="image"><a href="/properties/property-detail" ><img src="/assets/img/property/17.jpg" alt="677 Cottage Terrace" width="130px"></a><div class="contract-type">For sale</div></div><div class="info"><div class="title"><a href="/properties/property-detail">677 Cottage Terrace</a></div><div class="location">Spring Valley</div><div class="property-info clearfix"><div class="area"><i class="icon icon-normal-cursor-scale-up"></i>650m<sup>2</sup></div><div class="bedrooms"><i class="icon icon-normal-bed"></i>1</div><div class="bathrooms"><i class="icon icon-normal-shower"></i>1</div></div><div class="price">59,600 €</div><div class="link"><a href="/properties/property-detail">View more</a></div></div></div>';
                var property2 = '<div class="infobox clearfix"><div class="close"><img src="/assets/img/close.png" alt=""></div><div class="image"><a href="/properties/property-detail"><img src="/assets/img/property/19.jpg" alt="643 37th Ave" width="130px"></a><div class="contract-type">For sale</div></div><div class="info"><div class="title"><a href="/properties/property-detail">643 37th Ave</a></div><div class="location">Burrville</div><div class="property-info clearfix"><div class="area"><i class="icon icon-normal-cursor-scale-up"></i>800m<sup>2</sup></div><div class="bedrooms"><i class="icon icon-normal-bed"></i>2</div><div class="bathrooms"><i class="icon icon-normal-shower"></i>2</div></div><div class="price">Contact us</div><div class="link"><a href="/properties/property-detail">View more</a></div></div></div>';
                var property3 = '<div class="infobox clearfix"><div class="close"><img src="/assets/img/close.png" alt=""></div><div class="image"><a href="/properties/property-detail" ><img src="/assets/img/property/17.jpg" alt="677 Cottage Terrace" width="130px"></a>                  <div class="contract-type">For sale</div></div><div class="info"><div class="title"><a href="/properties/property-detail">677 Cottage Terrace</a></div><div class="location">Spring Valley</div><div class="property-info clearfix"><div class="area"><i class="icon icon-normal-cursor-scale-up"></i>650m<sup>2</sup></div><div class="bedrooms"><i class="icon icon-normal-bed"></i>1</div><div class="bathrooms"><i class="icon icon-normal-shower"></i>1</div></div><div class="price">59,600 €</div><div class="link"><a href="/properties/property-detail">View more</a></div></div></div>';

                for (var i=0;i< 15 ;i++)
                {
                    infoBoxes.push(property1);
                    infoBoxes.push(property2);
                    infoBoxes.push(property3);
                }

                jQuery(document).ready(function ($) {
                    var map = $('#map').aviators_map({
                        locations: new Array([38.951399, -76.958463], [38.942855, -76.959149], [38.935945, -76.954085], [38.924194, -76.962497], [38.929335, -76.966402], [38.950131, -76.975286], [38.941386, -76.976745], [38.912975, -76.973269], [38.927266, -76.985156], [38.936813, -76.987173], [38.941653, -76.995885], [38.929235, -76.995627], [38.922024, -77.001378], [38.920788, -77.020304], [38.926531, -77.007558], [38.939384, -77.018115], [38.939217, -77.070257], [38.931539, -77.103517], [38.942454, -77.05352], [38.930571, -77.086007], [38.947194, -77.109696], [38.949864, -77.094762], [38.940685, -77.095964], [38.932474, -77.026441], [38.932941, -77.034165], [38.932641, -77.044079], [38.932908, -77.061674], [38.931372, -77.07781], [38.926665, -77.101457], [38.929135, -77.101671], [38.919086, -77.108538], [38.910103, -77.104504], [38.920221, -77.084033], [38.915513, -77.089741], [38.918752, -77.095105], [38.912073, -77.00597], [38.90486, -77.024724], [38.918418, -77.010605], [38.928868, -77.021377], [38.920154, -77.010562], [38.915847, -77.069699], [38.926164, -77.056739], [38.925045, -77.040063], [38.922591, -77.034291]),
                        types: new Array('family-house', 'villa', 'cottage', 'single-home', 'family-house', 'cottage', 'apartment', 'building-area', 'apartment', 'family-house', 'villa', 'family-house', 'villa', 'single-home', 'cottage', 'villa', 'condo', 'apartment', 'single-home', 'cottage', 'family-house', 'villa', 'apartment', 'apartment', 'villa', 'villa', 'apartment', 'cottage', 'villa', 'family-house', 'building-area', 'family-house', 'family-house', 'cottage', 'apartment', 'cottage', 'family-house', 'villa', 'cottage', 'condo', 'building-area', 'family-house', 'single-home', 'apartment'),
                        contents: infoBoxes,
                        transparentMarkerImage: '/assets/img/marker-transparent.png',
                        transparentClusterImage: '/assets/img/markers/cluster-transparent.png',
                        zoom: 14,
                        center: {
                            latitude: 38.932307,
                            longitude: -77.034258
                        },
                        filterForm: '.map-filtering',
                        enableGeolocation: '',
                        pixelOffsetX: -75,
                        pixelOffsetY: -200
                    });
                });
            </script>

            <div id="map" class="map-inner" style="height: 700px"></div>
            <!-- /.map-inner -->

            <?php include get_include_path() . '/templates/header/block-map-filter-horizontal.php'; ?>
        </div>
        <!-- /.map -->
    </div>

    <!-- /.map-wrapper -->
    <div class="container">
        <div class="row">

            <div class="sidebar span3">
                <?php include get_include_path() . '/templates/sidebar/block-quick-search.php'; ?>
                <?php include get_include_path() . '/templates/sidebar/block-partners.php'; ?>
                <?php include get_include_path() . '/templates/sidebar/block-most-recent.php'; ?>
                <?php include get_include_path() . '/templates/sidebar/block-agencies.php'; ?>
            </div>
            <!-- /#sidebar -->

            <div id="main" class="span9">

                <h2>Featured Properties</h2>
                <?php include get_include_path() . '/templates/main/block-featured-properties.php'; ?>
                <hr>

                <h1 class="page-header">Recent Properties</h1>
                <?php include get_include_path() . '/templates/main/block-recent-properties.php'; ?>
                <hr>

                <?php include get_include_path() . '/templates/main/block-features.php'; ?>

                <?php include get_include_path() . '/templates/main/block-carousel-properties.php'; ?>

                <div id="partners_widget-3" class="widget partners">

                    <h2>Partners</h2>

                    <div class="partners">
                        <div class="row">
                            <div class="span3">
                                <div class="partner">
                                    <a href="http://www.themeforest.net">
                                        <img width="270" height="70" src="/assets/img/partners/themeforest.png"
                                             class="thumbnail-image" alt="themeforest"/>
                                    </a>
                                </div>
                            </div>
                            <!-- /.span3 -->
                            <div class="span3">
                                <div class="partner">
                                    <a href="http://www.activeden.net">
                                        <img width="270" height="70" src="/assets/img/partners/activeden.png"
                                             class="thumbnail-image" alt="activeden"/>
                                    </a>
                                </div>
                            </div>
                            <!-- /.span3 -->
                            <div class="span3">
                                <div class="partner">
                                    <a href="http://www.3docean.net">
                                        <img width="270" height="70" src="/assets/img/partners/3docean.png"
                                             class="thumbnail-image" alt="3docean"/>
                                    </a>
                                </div>
                            </div>
                            <!-- /.span3 -->
                            <div class="span3">
                                <div class="partner">
                                    <a href="http://www.codecanyon.net">
                                        <img width="270" height="70" src="/assets/img/partners/code-canyon.png"
                                             class="thumbnail-image" alt="code-canyon"/>
                                    </a>
                                </div>
                            </div>
                            <!-- /.span3 -->
                            <div class="span3">
                                <div class="partner">
                                    <a href="http://www.photodune.net">
                                        <img width="270" height="70" src="/assets/img/partners/photo-dune.png"
                                             class="thumbnail-image" alt="photo-dune"/>
                                    </a>
                                </div>
                            </div>
                            <!-- /.span3 -->
                            <div class="span3">
                                <div class="partner">
                                    <a href="http://www.graphicriver.net">
                                        <img width="270" height="70" src="/assets/img/partners/graphic-river.png"
                                             class="thumbnail-image" alt="graphic-river"/>
                                    </a>
                                </div>
                            </div>
                            <!-- /.span3 -->
                        </div>
                        <!-- /.row -->
                    </div>
                    <!-- /.partners -->
                </div>
            </div>
            <!-- /#main -->

        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->

</div>