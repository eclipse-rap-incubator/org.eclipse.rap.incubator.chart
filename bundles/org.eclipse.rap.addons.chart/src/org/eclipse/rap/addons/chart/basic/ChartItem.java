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

import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;


public class ChartItem {

  protected double value;
  protected String text;
  protected RGB color;

  public ChartItem( double value, String text ) {
    this.value = value;
    this.text = text;
  }

  public ChartItem( double value, String text, RGB color ) {
    this.value = value;
    this.text = text;
    this.color = color;
  }

  protected JsonObject toJson() {
    JsonObject json = new JsonObject().add( "value", value );
    if( text != null ) {
      json.add( "label", text );
    }
    if( color != null ) {
      json.add( "color", toHtmlString( color ) );
    }
    return json;
  }

  protected static String toHtmlString( RGB color ) {
    StringBuilder result = new StringBuilder( 7 ).append( '#' );
    append2x( result, color.red );
    append2x( result, color.green );
    append2x( result, color.blue );
    return result.toString();
  }

  private static void append2x( StringBuilder result, int red ) {
    if( red < 16 ) {
      result.append( '0' );
    }
    result.append( Integer.toHexString( red ) );
  }

}
