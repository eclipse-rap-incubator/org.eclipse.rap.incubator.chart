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

public class DataPoint {

  public double x;
  public double y;

  public DataPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  protected JsonObject toJson() {
    return new JsonObject().add( "x", x ).add( "y", y );
  }

}
