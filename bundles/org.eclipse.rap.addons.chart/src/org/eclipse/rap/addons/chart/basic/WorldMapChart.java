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
package org.eclipse.rap.addons.chart.basic;

import org.eclipse.rap.addons.chart.MapChart;
import org.eclipse.swt.widgets.Composite;


public class WorldMapChart extends MapChart {

  public WorldMapChart( Composite parent, int style ) {
    super( parent, style, "topojson-world" );
    requireJs( registerResource( "chart/topojson/topojson-world.js" ) );
    requireCss( registerResource( "resources/topojson-world.css" ) );
    registerResource( "resources/world-50m.json" );
  }

}
