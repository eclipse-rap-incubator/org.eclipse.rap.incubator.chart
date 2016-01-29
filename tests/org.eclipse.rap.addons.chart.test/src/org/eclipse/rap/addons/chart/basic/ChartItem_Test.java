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

import static org.junit.Assert.assertEquals;

import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.graphics.RGB;
import org.junit.Test;


public class ChartItem_Test {

  @Test
  public void testToHtmlString() {
    assertEquals( "#000880", ChartItem.toHtmlString( new RGB( 0, 8, 128 ) ) );
  }

  @Test
  public void testToJson() {
    ChartItem item = new ChartItem( 23, "foo", new RGB( 0, 8, 128 ) );

    JsonObject json = item.toJson();

    assertEquals( new JsonObject()
      .add( "value", 23 )
      .add( "label", "foo" )
      .add( "color", "#000880" ), json );
  }

  @Test
  public void testToJson_skipsMissingColor() {
    ChartItem item = new ChartItem( 23, "foo" );

    JsonObject json = item.toJson();

    assertEquals( new JsonObject().add( "value", 23 ).add( "label", "foo" ), json );
  }

  @Test
  public void testToJson_skipsMissingText() {
    ChartItem item = new ChartItem( 23, null, new RGB( 0, 8, 128 ) );

    JsonObject json = item.toJson();

    assertEquals( new JsonObject().add( "value", 23 ).add( "color", "#000880" ), json );
  }

}
