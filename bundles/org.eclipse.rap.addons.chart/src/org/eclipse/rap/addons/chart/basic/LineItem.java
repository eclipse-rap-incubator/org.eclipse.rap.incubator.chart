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

public class LineItem {

  private DataPoint[] points;
  private String text;
  private RGB color;

  public LineItem( DataPoint[] points, String text ) {
    this( points, text, null );
  }

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
