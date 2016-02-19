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

rwt.chart.register( "datamap-world", function( widget ) {
  var map;
  var element;
  var chart = function(selection, widget) {
    if (!map) {
      element = selection.node().parentNode;
      element.removeChild(selection.node());
      map = new Datamap({element: element});
    }
  };

  function resize() {
    if (element) {
      var newsize = element.clientWidth,
          oldsize = d3.select(element).select('svg').attr('data-width');
      d3.select(element).select('svg').selectAll('g').attr('transform', 'scale(' + (newsize / oldsize) + ')');
    }
  }

  chart.width = function(width) {
    if (element) {
      d3.select(element).attr('width', width);
    }
    resize();
    return chart;
  };
  chart.height = function(height) {
    if (element) {
      d3.select(element).attr('height', height);
    }
    resize();
    return chart;
  };
  return chart;
});
