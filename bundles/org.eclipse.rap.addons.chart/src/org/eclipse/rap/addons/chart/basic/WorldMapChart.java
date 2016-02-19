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

import static org.eclipse.rap.addons.chart.internal.ColorUtil.toHtmlString;

import java.util.Map;

import org.eclipse.rap.addons.chart.MapChart;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.RGB;


public class WorldMapChart extends MapChart {

  public WorldMapChart( Composite parent, int style ) {
    super( parent, style, "topojson-world" );
    requireJs( registerResource( "chart/topojson/topojson-world.js" ) );
    requireCss( registerResource( "resources/topojson-world.css" ) );
    registerResource( "resources/world-110m.json" );
  }

  public void setCountryColors( Map<String, RGB> colors ) {
    checkWidget();
    JsonObject json = new JsonObject();
    for( String code : colors.keySet() ) {
      json.add( code, toHtmlString( colors.get( code ) ) );
    }
    setOption( "colors", json );
  }

}
