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
import org.junit.Test;


public class DataPoint_Test {

  @Test
  public void testToJson() {
    DataPoint dataPoint = new DataPoint( 1.41, 3.14 );

    JsonObject json = dataPoint.toJson();

    assertEquals( new JsonObject().add( "x", 1.41 ).add( "y",  3.14 ), json );
  }

}
