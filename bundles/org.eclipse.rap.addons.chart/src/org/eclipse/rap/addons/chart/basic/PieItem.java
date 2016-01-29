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


public class PieItem {

  protected double value;
  protected String text;
  protected RGB color;

  public PieItem( double value, String text ) {
    this.value = value;
    this.text = text;
  }

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
