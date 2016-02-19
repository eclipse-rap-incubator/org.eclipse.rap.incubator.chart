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
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;


public class WorldMapChart extends MapChart {

  private boolean showGraticule = true;

  public WorldMapChart( Composite parent, int style ) {
    super( parent, style, "topojson-world" );
    requireJs( registerResource( "chart/topojson/topojson-world.js" ) );
    requireCss( registerResource( "resources/topojson-world.css" ) );
    registerResource( "resources/world-110m.json" );
  }

  public void setColors( Map<String, RGB> colors ) {
    checkWidget();
    JsonObject json = new JsonObject();
    for( String code : colors.keySet() ) {
      json.add( code, toHtmlString( colors.get( code ) ) );
    }
    setOption( "colors", json );
  }

  public void setColors( RGB[] colors ) {
    checkWidget();
    JsonArray json = new JsonArray();
    for( RGB color : colors ) {
      json.add( toHtmlString( color ) );
    }
    setOption( "colors", json );
  }

  public void setShowGraticule( boolean show ) {
    checkWidget();
    if( show != showGraticule ) {
      showGraticule = show;
      setOption( "showGraticule", show );
    }
  }

  public boolean getShowGraticule() {
    checkWidget();
    return showGraticule;
  }

}
