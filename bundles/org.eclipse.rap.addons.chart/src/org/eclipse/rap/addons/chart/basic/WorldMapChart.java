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

import org.eclipse.rap.addons.chart.MapChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;


/**
 * A basic world map chart widget.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class WorldMapChart extends MapChart {

  private boolean showGraticule;

  /**
   * Creates a new empty WorldMap chart.
   */
  public WorldMapChart( Composite parent, int style ) {
    super( parent, style, "topojson-world" );
    requireJs( registerResource( "chart/topojson/topojson-world.js" ) );
    requireCss( registerResource( "resources/topojson-world.css" ) );
    registerResource( "resources/world-110m.json" );
  }

  /**
   * Set array of colors, which will be used to fill the countries with.
   *
   * @param colors The array with country fill colors.
   */
  public void setColors( RGB[] colors ) {
    checkWidget();
    JsonArray json = new JsonArray();
    for( RGB color : colors ) {
      json.add( toHtmlString( color ) );
    }
    setOption( "colors", json );
  }

  /**
   * Set whether graticule should be displayed or not. The default is <code>false</code>.
   *
   * @param show <code>true</code> to display graticule.
   */
  public void setShowGraticule( boolean show ) {
    checkWidget();
    if( show != showGraticule ) {
      showGraticule = show;
      setOption( "showGraticule", show );
    }
  }

  /**
   * Returns whether graticule is displayed.
   *
   * @return <code>true</code> if graticule is displayed.
   */
  public boolean getShowGraticule() {
    checkWidget();
    return showGraticule;
  }

  /**
   * Sets the map data items to display. Later changes to this list won't be reflected. To change
   * the chart data, call this method with a new list of items.
   *
   * @param items the map data items to display
   */
  public void setItems( MapDataItem... items ) {
    JsonObject values = new JsonObject();
    for( MapDataItem item : items ) {
      values.add( item.getCountry(), toJson( item ) );
    }
    setOption( "countries", values );
  }

  private static JsonObject toJson( MapDataItem item ) {
    JsonObject json = new JsonObject();
    if( item.text != null ) {
      json.add( "label", item.text );
    }
    if( item.color != null ) {
      json.add( "color", toHtmlString( item.color ) );
    }
    return json;
  }

}
