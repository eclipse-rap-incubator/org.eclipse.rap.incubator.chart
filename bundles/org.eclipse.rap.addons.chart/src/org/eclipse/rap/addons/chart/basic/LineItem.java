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

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;


/**
 * Represents a line in a line chart.
 *
 * @see LineChart
 */
public class LineItem {

  protected final DataPoint[] points;
  protected final String text;
  protected final RGB color;

  /**
   * Creates a line with the given data points and text. Later changes to the data points won't be
   * reflected in the chart.
   */
  public LineItem( DataPoint[] points, String text ) {
    this( points, text, null );
  }

  /**
   * Creates a line with the given data points, text, and color. Later changes to the data points
   * won't be reflected in the chart.
   */
  public LineItem( DataPoint[] points, String text, RGB color ) {
    this.points = points;
    this.text = text;
    this.color = color;
  }

  protected JsonObject toJson() {
    JsonArray values = new JsonArray();
    for( DataPoint point : points ) {
      values.add( point.toJson() );
    }
    JsonObject json = new JsonObject().add( "values", values );
    if( text != null ) {
      json.add( "key", text );
    }
    if( color != null ) {
      json.add( "color", toHtmlString( color ) );
    }
    return json;
  }

}
