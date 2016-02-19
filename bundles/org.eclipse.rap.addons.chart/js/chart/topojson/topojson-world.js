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
  var _path;
  var _graticule = d3.geo.graticule();
  var _color = d3.scale.category20();

  var chart = function( svg ) {
    if( !_world ) {
      load( svg );
    } else {
      update( svg );
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

  function load( svg ) {
    d3.json( "rwt-resources/chart/topojson/world-50m.json", function( error, world ) {
      if( error ) {
        throw error;
      }
      _world = world;
      update( svg );
    });
  }

  function update( svg ) {
    updateProjection();
    svg.selectAll( "*" ).remove();
    svg.append( "path" )
      .datum( _graticule )
      .attr( "class", "graticule" )
      .attr( "d", _path );
    svg.append( "path" )
      .datum( _graticule.outline )
      .attr( "class", "graticule outline" )
      .attr( "d", _path );
    var countries = topojson.feature( _world, _world.objects.countries ).features;
    var neighbors = topojson.neighbors( _world.objects.countries.geometries );
    svg.selectAll( ".country" )
      .data( countries )
      .enter()
      .insert( "path", ".graticule" )
      .attr( "class", "country" )
      .attr( "d", _path )
      .style( "fill", function( d, i ) {
        var funct = function( n ) { return countries[ n ].color; };
        return _color( d.color = d3.max( neighbors[ i ], funct ) + 1 | 0 );
      });
  }

  function updateProjection() {
    var countries = topojson.feature( _world, _world.objects.countries );
    // create a first guess for the projection
    var scale = 150;
    var offset = [ _width / 2, _height / 2 ];
    var projection = d3.geo.kavrayskiy7()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    // create the path
    _path = d3.geo.path().projection( projection );
    // using the path determine the bounds of the current map and use
    // these to determine better values for the scale
    var bounds  = _path.bounds( countries );
    var hscale  = scale * _width  / ( bounds[ 1 ][ 0 ] - bounds[ 0 ][ 0 ] );
    var vscale  = scale * _height / ( bounds[ 1 ][ 1 ] - bounds[ 0 ][ 1 ] );
    scale   = ( hscale < vscale ) ? hscale - 10 : vscale;
    // new projection
    projection = d3.geo.kavrayskiy7()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    _path = _path.projection( projection );
  }

  widget._scheduleUpdate();

  return chart;

});
