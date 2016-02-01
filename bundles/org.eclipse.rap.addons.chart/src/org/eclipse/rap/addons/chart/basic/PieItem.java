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

import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;


/**
 * Represents a segment of a pie/donut chart.
 *
 * @see PieChart
 */
public class PieItem {

  protected final double value;
  protected final String text;
  protected final RGB color;

  /**
   * Create a new pie item with the given value and text.
   *
   * @param value the value of the item, affects the size of the section, should not be negative
   * @param text the label text for the item, or <code>null</code> to omit the label
   */
  public PieItem( double value, String text ) {
    this( value, text, null );
  }

  /**
   * Create a new pie item with the given value, text, and color.
   *
   * @param value the value of the item, affects the size of the section, should not be negative
   * @param text the label text for the item, or <code>null</code> to omit the label
   * @param color the color of this section, or <code>null</code> to use the default color
   */
  public PieItem( double value, String text, RGB color ) {
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

}
