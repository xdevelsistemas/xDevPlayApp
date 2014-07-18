<div id="content" class="clearfix">

<div class="container">
<div class="row">


<div id="main" class="span12">

<h1 class="page-header">Add submission</h1>

<div class="progressbar">
    <div class="progressbar-inner">
        <div class="row">
            <div class="item span4">
                <div class="number">1</div>
                <strong>Info</strong>
            </div>
            <!-- /.item  -->

            <div class="item span4">
                <div class="number">2</div>
                <strong>Amenities</strong>
            </div>
            <!-- /.item  -->

            <div class="item span4">
                <div class="number">3</div>
                <strong>Details</strong>
            </div>
            <!-- /.item  -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.progressbar-inner -->
</div>
<!-- /.progressbar -->
<form method="post" action="javascript:void(0)" enctype="multipart/form-data" class="submission-form form-vertical">
<div class="row">
<div class="span4">
    <input type="hidden" name="post_type" value="">

    <div class="control-group">
        <label class="control-label" for="address">
            Image
        </label>

        <div class="controls">
            <img width="80" height="59" src="/assets/img/property/13.jpg"
                 class="attachment-admin-thumb" alt="13"/>
            <a href="#">Remove</a>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="address">
            Address
        </label>

        <div class="controls">
            <input type="text" name="post_title" size="30" value="2566 Quincy St NE" id="address" autocomplete="off"
                   required="required">
        </div>
    </div>

    <div class="control-group">
        <label class="control-label" for="description">
            Description
        </label>

        <div class="controls">
            <textarea id="description" class="wp-editor-area" rows="10" name="content" required="required">Quisque non
                dictum eros. Praesent porta vehicula arcu eu ornare. Donec id egestas arcu. Suspendisse auctor
                condimentum ligula ultricies cursus. Vestibulum vel orci vel lacus rhoncus sagittis sed vitae mi.
                Pellentesque molestie elit bibendum tincidunt semper. Aliquam ac volutpat risus. In felis felis, posuere
                commodo aliquet eget, sagittis sed turpis. Phasellus commodo turpis non nunc egestas, et egestas felis
                pretium. Pellentesque dignissim libero vitae tincidunt semper.

                Nam id ante nisi. Nam sollicitudin, dolor non suscipit feugiat, nibh lacus commodo metus, nec tempus dui
                velit sagittis velit. Pellentesque elementum risus rhoncus justo porta, at varius odio consequat. Duis
                non augue adipiscing, posuere quam non, tempus urna.</textarea>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            Type
        </label>

        <div class="controls">
            <select name='property_types' id='property_types' class='postform'>
                <option class="level-0" value="6">Apartment</option>
                <option class="level-0" value="40">Single Home</option>
                <option class="level-0" value="41" selected="selected">Family House</option>
                <option class="level-0" value="42">Building Area</option>
                <option class="level-0" value="43">Condo</option>
                <option class="level-0" value="44">Cottage</option>
                <option class="level-0" value="45">Villa</option>
            </select>

        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            Location
        </label>

        <div class="controls">
            <select name='property_locations' id='property_locations' class='postform'>
                <option class="level-0" value="7">Barney Circle</option>
                <option class="level-0" value="47">Capitol Hill</option>
                <option class="level-0" value="48">Judiciary Square</option>
                <option class="level-0" value="49">Kingman Park</option>
                <option class="level-0" value="50">Navy Yard</option>
                <option class="level-0" value="51">Near Northeast</option>
                <option class="level-0" value="52" selected="selected">Sursum Corda</option>
                <option class="level-0" value="53">Swampoodle</option>
                <option class="level-0" value="54">Benning Heights</option>
                <option class="level-0" value="55">Benning Ridge</option>
                <option class="level-0" value="56">Benning</option>
                <option class="level-0" value="57">Burrville</option>
                <option class="level-0" value="58">Capitol View</option>
                <option class="level-0" value="59">Central Northeast</option>
                <option class="level-0" value="60">Civic Betterment</option>
                <option class="level-0" value="65">Spring Valley</option>
            </select>

        </div>
    </div>
</div>

<div class="span4">
    <div class="control-group">
        <label class="control-label">
            Amenities
        </label>

        <div class="controls">
            <ul class="unstyled">

                <li id='amenities-8' class="popular-category"><label class="selectit"><input value="8" type="checkbox"
                                                                                             name="tax_input[amenities][]"
                                                                                             id="in-amenities-8"
                                                                                             checked='checked'/> Air
                        conditioning</label></li>

                <li id='amenities-9' class="popular-category"><label class="selectit"><input value="9" type="checkbox"
                                                                                             name="tax_input[amenities][]"
                                                                                             id="in-amenities-9"
                                                                                             checked='checked'/> Balcony</label>
                </li>

                <li id='amenities-12'><label class="selectit"><input value="12" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-12"
                                                                     checked='checked'/> Cleaning after exit</label>
                </li>

                <li id='amenities-13'><label class="selectit"><input value="13" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-13"
                                                                     checked='checked'/> Cofee pot</label></li>

                <li id='amenities-15'><label class="selectit"><input value="15" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-15"
                                                                     checked='checked'/> Cot</label></li>

                <li id='amenities-17'><label class="selectit"><input value="17" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-17"
                                                                     checked='checked'/> DVD</label></li>

                <li id='amenities-18' class="popular-category"><label class="selectit"><input value="18" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-18"
                                                                                              checked='checked'/>
                        Fan</label></li>

                <li id='amenities-19' class="popular-category"><label class="selectit"><input value="19" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-19"
                                                                                              checked='checked'/> Fridge</label>
                </li>

                <li id='amenities-22'><label class="selectit"><input value="22" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-22"
                                                                     checked='checked'/> Heating</label></li>

                <li id='amenities-23'><label class="selectit"><input value="23" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-23"
                                                                     checked='checked'/> Hi-fi</label></li>

                <li id='amenities-25'><label class="selectit"><input value="25" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-25"
                                                                     checked='checked'/> Iron</label></li>

                <li id='amenities-27'><label class="selectit"><input value="27" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-27"
                                                                     checked='checked'/> Lift</label></li>

                <li id='amenities-31'><label class="selectit"><input value="31" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-31"
                                                                     checked='checked'/> Parquet</label></li>

                <li id='amenities-33'><label class="selectit"><input value="33" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-33"
                                                                     checked='checked'/> Roof terrace</label></li>

                <li id='amenities-38'><label class="selectit"><input value="38" type="checkbox"
                                                                     name="tax_input[amenities][]" id="in-amenities-38"
                                                                     checked='checked'/> Use of pool</label></li>

                <li id='amenities-39' class="popular-category"><label class="selectit"><input value="39" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-39"
                                                                                              checked='checked'/> Video</label>
                </li>

                <li id='amenities-10'><label class="selectit"><input value="10" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-10"/> Bedding</label></li>

                <li id='amenities-11' class="popular-category"><label class="selectit"><input value="11" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-11"/>
                        Cable TV</label></li>

                <li id='amenities-14'><label class="selectit"><input value="14" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-14"/> Computer</label></li>

                <li id='amenities-16'><label class="selectit"><input value="16" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-16"/> Dishwasher</label></li>

                <li id='amenities-20'><label class="selectit"><input value="20" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-20"/> Grill</label></li>

                <li id='amenities-21'><label class="selectit"><input value="21" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-21"/> Hairdryer</label></li>

                <li id='amenities-24' class="popular-category"><label class="selectit"><input value="24" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-24"/>
                        Internet</label></li>

                <li id='amenities-26'><label class="selectit"><input value="26" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-26"/> Juicer</label></li>

                <li id='amenities-28'><label class="selectit"><input value="28" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-28"/> Microwave</label></li>

                <li id='amenities-29'><label class="selectit"><input value="29" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-29"/> Oven</label></li>

                <li id='amenities-30' class="popular-category"><label class="selectit"><input value="30" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-30"/>
                        Parking</label></li>

                <li id='amenities-32'><label class="selectit"><input value="32" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-32"/> Radio</label></li>

                <li id='amenities-34'><label class="selectit"><input value="34" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-34"/> Smoking allowed</label></li>

                <li id='amenities-35' class="popular-category"><label class="selectit"><input value="35" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-35"/>
                        Terrace</label></li>

                <li id='amenities-36'><label class="selectit"><input value="36" type="checkbox"
                                                                     name="tax_input[amenities][]"
                                                                     id="in-amenities-36"/> Toaster</label></li>

                <li id='amenities-37' class="popular-category"><label class="selectit"><input value="37" type="checkbox"
                                                                                              name="tax_input[amenities][]"
                                                                                              id="in-amenities-37"/>
                        Towelwes</label></li>

            </ul>
        </div>
    </div>
</div>


<div class="span4">
    <div class="control-group">
        <label class="control-label">
            Price </label>

        <div class="controls">
            <div class="input-append">
                <input type="number" name="_property_meta[price]" value="59900">
                <span class="add-on">€</span>
            </div>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            Bathrooms </label>

        <div class="controls">
            <input type="number" name="_property_meta[bathrooms]" value="4">
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            Bedrooms </label>

        <div class="controls">
            <input type="number" name="_property_meta[bedrooms]" value="4">
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            Area </label>

        <div class="controls">
            <div class="input-append">
                <input type="number" name="_property_meta[area]" value="900">
                <span class="add-on">m<sup>2</sup></span>
            </div>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            GPS </label>

        <div class="controls">
            <input class="latitude" type="text" name="_property_meta[latitude]" value="38.936813"
                   placeholder="Latitude"/>

            <input class="longitude" type="text" name="_property_meta[longitude]" value="-76.987173"
                   placeholder="Longitude"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">
            Contract type </label>

        <div class="controls">

            <ul class="unstyled">
                <li>
                    <label>
                        <input type="radio" name="_property_meta[contract_type]" value="rent"/>
                        &nbsp;&nbsp;For rent </label>
                </li>

                <li>
                    <label>
                        <input type="radio" name="_property_meta[contract_type]" value="sale" checked="checked"/>
                        &nbsp;&nbsp;Sale </label>
                </li>
            </ul>
        </div>
    </div>
</div>
</div>

<div class="form-actions">
    <input type="submit" class="btn btn-primary" value="Save">
</div>
</form>

</div>
<!-- /#main -->
</div>
<!-- /.row -->
</div>
<!-- /.container -->

</div><!-- /#content -->
