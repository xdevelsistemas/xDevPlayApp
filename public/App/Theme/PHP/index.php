<!DOCTYPE html>

<?php
$system_path = dirname(__FILE__);
set_include_path(dirname(__FILE__));

// world simplest router
$uri = $_SERVER['REQUEST_URI'];
$uri = trim($uri, '/');

if (empty($uri)) {
    $include_file = '/pages/frontpage.php';
} else {
    $include_file = '/pages/' . $uri;
    if (is_dir($system_path . $include_file) && file_exists($system_path . $include_file . '/index.php')) {
        $include_file = $include_file . '/index.php';
    } else {
        if (file_exists($system_path . $include_file . '.php')) {
            $include_file = $include_file . '.php';
        } else {
            header('HTTP/1.1 404 Not Found');
            $include_file = '/pages/404.php';
        }
    }
}

?>


<!--[if IE 7]>
<html class="ie ie7" lang="en-US">
<![endif]-->
<!--[if IE 8]>
<html class="ie ie8" lang="en-US">
<![endif]-->
<!--[if !(IE 7) | !(IE 8)  ]><!-->
<html lang="en-US">
<!--<![endif]-->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Aviators, http://themes.byaviators.com">

    <link rel="shortcut icon" href="/assets/img/favicon.png" type="image/png">

    <!--[if lt IE 9]>
    <script src="/assets/js/html5.js" type="text/javascript"></script>
    <![endif]-->

    <meta name='robots' content='noindex,nofollow'/>

    <link rel='stylesheet' id='font-css'
          href='http://fonts.googleapis.com/css?family=Open+Sans%3A400%2C700%2C300&#038;subset=latin%2Clatin-ext&#038;ver=3.6'
          type='text/css' media='all'/>


    <link rel='stylesheet' id='revolution-fullwidth' href='/assets/libraries/rs-plugin/css/fullwidth.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='revolution-settings' href='/assets/libraries/rs-plugin/css/settings.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='bootstrap-css' href='/assets/libraries/bootstrap/css/bootstrap.min.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='bootstrap-responsive-css' href='/assets/libraries/bootstrap/css/bootstrap-responsive.min.css' type='text/css' media='all'/>

    <link rel='stylesheet' id='pictopro-normal-css' href='/assets/icons/pictopro-normal/style.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='justvector-web-font-css' href='/assets/icons/justvector-web-font/stylesheet.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='chosen-css' href='/assets/libraries/chosen/chosen.css' type='text/css' media='all'/>

    <link rel='stylesheet' id='aviators-css' href='/assets/css/jquery.bxslider.css' type='text/css' media='all'/>
    <link rel='stylesheet' id='properta-css' href='/assets/css/properta.css' type='text/css' media='all'/>

    <script type='text/javascript' src='http://code.jquery.com/jquery-1.7.2.min.js'></script>
    <script type='text/javascript' src='/assets/js/aviators-settings.js'></script>
    <script type='text/javascript' src='/assets/libraries/chosen/chosen.jquery.min.js'></script>
    <script type='text/javascript' src='/assets/libraries/rs-plugin/js/jquery.themepunch.revolution.min.js'></script>
    <script type='text/javascript' src='/assets/libraries/rs-plugin/js/jquery.themepunch.plugins.min.js'></script>

    <title>Properta | Real Estate Template</title>
</head>

<body class="home page page-template">

<?php include get_include_path() . '/templates/header/top.php'; ?>
<?php include get_include_path() . '/templates/header/navigation.php'; ?>


<div class="breadcrumb pull-left">
    <!-- Breadcrumb NavXT 4.4.0 -->
    <a title="Go to Properta." href="/" class="home">Properta</a> &gt; Page
</div>

<!-- /.breadcrumb -->
</div>
</div>
</div>

<?php include get_include_path() . $include_file; ?>

<!-- /#content -->
<?php include get_include_path()  . '/templates/footer/footer.php'; ?>

<script type='text/javascript' src='http://maps.googleapis.com/maps/api/js?v=3&#038;sensor=true&#038;ver=3.6'></script>
<script type='text/javascript' src='/assets/js/aviators-map.js'></script>
<script type='text/javascript' src='/assets/js/gmap3.infobox.min.js'></script>
<script type='text/javascript' src='/assets/js/bootstrap.min.js'></script>
<script type='text/javascript' src='/assets/js/retina.js'></script>
<script type='text/javascript' src='/assets/js/gmap3.clusterer.js'></script>
<script type='text/javascript' src='/assets/js/jquery.ezmark.js'></script>
<script type='text/javascript' src='/assets/js/carousel.js'></script>
<script type='text/javascript' src='/assets/js/jquery.bxslider.js'></script>
<script type='text/javascript' src='/assets/js/properta.js'></script>
<script type='text/javascript' src='/assets/js/jquery.bxslider.min.js'></script>
</body>
</html>
