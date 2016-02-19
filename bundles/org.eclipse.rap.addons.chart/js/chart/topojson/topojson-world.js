/*******************************************************************************
 * Copyright (c) 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial API and implementation
 ******************************************************************************/

rwt.chart.register( "topojson-world", function( widget ) {

  var _width;
  var _height;

  var _world;
  var _svg;
  var _path;
  var _graticule = d3.geo.graticule();

  var chart = function( svg ) {
    _svg = svg;
    if( !_world ) {
      load();
    } else {
      update();
    }
  };

  chart.width = function( width ) {
    _width = width;
    return chart;
  };

  chart.height = function( height ) {
    _height = height;
    return chart;
  };

  function load() {
    d3.json( "rwt-resources/chart/topojson/world-50m.json", function( error, world ) {
      if( error ) {
        throw error;
      }
      _world = world;
      update();
    });
  }

  function update() {
    updateProjection();
    _svg.selectAll( "*" ).remove();
    _svg.append( "path" )
      .datum( _graticule )
      .attr( "class", "graticule" )
      .attr( "d", _path );
    _svg.insert( "path", ".graticule" )
      .datum( topojson.feature( _world, _world.objects.land ) )
      .attr( "class", "land" )
      .attr( "d", _path );
    _svg.insert( "path", ".graticule" )
      .datum( topojson.mesh( _world, _world.objects.countries, function( a, b ) { return a !== b; }))
      .attr( "class", "boundary" )
      .attr( "d", _path );
  }

  function updateProjection() {
    var land = topojson.feature( _world, _world.objects.land );
    // create a first guess for the projection
    var scale = 150;
    var offset = [ _width / 2, _height / 2 ];
    var projection = d3.geo.mercator()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    // create the path
    _path = d3.geo.path().projection( projection );
    // using the path determine the bounds of the current map and use
    // these to determine better values for the scale and translation
    var bounds  = _path.bounds( land );
    var hscale  = 150 * _width  / ( bounds[ 1 ][ 0 ] - bounds[ 0 ][ 0 ] );
    var vscale  = 150 * _height / ( bounds[ 1 ][ 1 ] - bounds[ 0 ][ 1 ] );
    scale   = ( hscale < vscale ) ? hscale : vscale;
    // new projection
    projection = d3.geo.mercator()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    _path = _path.projection( projection );
  }

  widget._scheduleUpdate();

  return chart;

});
