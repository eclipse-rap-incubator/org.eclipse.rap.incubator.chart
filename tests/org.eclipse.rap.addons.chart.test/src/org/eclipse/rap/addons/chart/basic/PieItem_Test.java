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
import org.eclipse.rap.json.JsonValue;
import org.eclipse.swt.graphics.RGB;
import org.junit.Test;


public class PieItem_Test {

  @Test
  public void testToJson() {
    PieItem item = new PieItem( 23, "foo", new RGB( 0, 8, 128 ) );

    JsonObject json = item.toJson();

    JsonValue expected = parse( "{'value': 23, 'label': 'foo', 'color': '#000880'}" );
    assertEquals( expected, json );
  }

  @Test
  public void testToJson_skipsMissingColor() {
    PieItem item = new PieItem( 23, "foo" );

    JsonObject json = item.toJson();

    JsonValue expected = parse( "{'value': 23, 'label': 'foo'}" );
    assertEquals( expected, json );
  }

  @Test
  public void testToJson_skipsMissingText() {
    PieItem item = new PieItem( 23, null, new RGB( 0, 8, 128 ) );

    JsonObject json = item.toJson();

    JsonValue expected = parse( "{'value': 23, 'color': '#000880'}" );
    assertEquals( expected, json );
  }

  private static JsonValue parse( String json ) {
    return JsonValue.readFrom( json.replace( '\'', '"' ) );
  }

}
