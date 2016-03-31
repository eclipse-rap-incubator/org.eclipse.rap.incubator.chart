/*******************************************************************************
 * Copyright (c) 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Elshad Seyidmammadov - initial API and implementation
 ******************************************************************************/

rwt.chart.register( "nv-historical-bar", function( widget ) {
  var chart = nv.models.historicalBarChart()
    .useInteractiveGuideline( true );
  chart.yAxis
    .tickFormat( d3.format( ',.2f' ) );
  chart.bars.interactive( true );
  chart.bars.dispatch.on( "elementClick.rap", function( item ) {
    widget.notifySelection( item.index );
  });
  return chart;
});
