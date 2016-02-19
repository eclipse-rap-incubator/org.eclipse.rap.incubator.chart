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

  var projection = d3.geo.cylindricalEqualArea()
    .parallel( 40 )
    //.scale( 200 )
    .precision( 0.1 );
  var path = d3.geo.path().projection( projection );
  var graticule = d3.geo.graticule();

  var chart = function( svg ) {
    svg.selectAll("*").remove();
    svg.append( "path" )
      .datum( graticule )
      .attr( "class", "graticule" )
      .attr( "d", path );
    if( !_world ) {
      load( svg );
    } else {
      update( svg );
    }
  };

  chart.width = function( width ) {
    _width = width;
    projection.translate( [ _width / 2, _height / 2 ] );
    projection.scale( ( _width + 1 ) / 2 / Math.PI );
    return chart;
  };

  chart.height = function( height ) {
    _height = height;
    projection.translate( [ _width / 2, _height / 2 ] );
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
    svg.insert( "path", ".graticule" )
      .datum( topojson.feature( _world, _world.objects.land ) )
      .attr( "class", "land" )
      .attr( "d", path );
    svg.insert( "path", ".graticule" )
      .datum( topojson.mesh( _world, _world.objects.countries, function( a, b ) { return a !== b; }))
      .attr( "class", "boundary" )
      .attr( "d", path );
  }

  widget._scheduleUpdate();

  return chart;

});
