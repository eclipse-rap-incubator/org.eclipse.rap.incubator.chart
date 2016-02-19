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

/*global topojson:false */

rwt.chart.register( "topojson-world", function( widget ) {

  var _width;
  var _height;
  var _world;
  var _path;
  var _fill;
  var _showGraticule = true;

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

  chart.colors = function( colors ) {
    if( Array.isArray( colors ) ) {
      _fill = function( d ) {
        return colors[ d.col % colors.length ];
      };
    } else {
      _fill = function( d ) {
        return colors[ d.properties.code ] || colors[ "*" ];
      };
    }
    return chart;
  };

  chart.showGraticule = function( show ) {
    _showGraticule = show;
    return chart;
  };

  function load( svg ) {
    d3.json( "rwt-resources/resources/world-110m.json", function( error, world ) {
      if( error ) {
        throw error;
      }
      _world = world;
      update( svg );
    });
  }

  function update( svg ) {
    var countries = topojson.feature( _world, _world.objects.countries );
    calculateColorIndices( countries.features );
    updateProjection( countries );
    updateGraticule( svg );
    svg.selectAll( ".country" )
      .data( countries.features )
      .enter()
      .insert( "path", ".graticule" )
      .attr( "class", "country" )
      .attr( "id", function( d ) { return d.id; } )
      .attr( "title", function( d ) { return d.properties.name; } )
      .on( "click", function( d ) {
        widget.notifySelection( d.id, 0, d.properties.code + "," + d.properties.name );
      });
    svg.selectAll( ".country" )
      .attr( "d", _path )
      .style( "fill", _fill );
  }

  function updateProjection( countries ) {
    var scale = 150;
    var offset = [ _width / 2, _height / 2 ];
    var projection = d3.geo.kavrayskiy7()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    _path = d3.geo.path().projection( projection );
    var bounds  = _path.bounds( countries );
    var hscale  = scale * _width  / ( bounds[ 1 ][ 0 ] - bounds[ 0 ][ 0 ] );
    var vscale  = scale * _height / ( bounds[ 1 ][ 1 ] - bounds[ 0 ][ 1 ] );
    scale = ( hscale < vscale ) ? hscale - 10 : vscale - 20;
    projection = d3.geo.kavrayskiy7()
      .scale( scale )
      .translate( offset )
      .precision( 0.1 );
    _path = _path.projection( projection );
  }

  function updateGraticule( svg ) {
    if( _showGraticule ) {
      if( svg.selectAll( ".graticule" ).size() === 0 ) {
        var graticule = d3.geo.graticule();
        svg.append( "path" )
          .datum( graticule )
          .attr( "class", "graticule" );
        svg.append( "path" )
          .datum( graticule.outline )
          .attr( "class", "graticule outline" );
      }
      svg.selectAll( ".graticule" ).attr( "d", _path );
    } else {
      svg.selectAll( ".graticule" ).remove();
    }
  }

  function calculateColorIndices( countries ) {
    var neighbors = topojson.neighbors( _world.objects.countries.geometries );
    countries.forEach( function( d, i ) {
      var getCol = function( n ) {
        return countries[ n ].col;
      };
      d.col = d3.max( neighbors[ i ], getCol ) + 1 | 0;
    } );
  }

  widget._scheduleUpdate();

  return chart;

});
