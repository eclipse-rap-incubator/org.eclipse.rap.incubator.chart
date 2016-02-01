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


/**
 * Represents a data point in a Cartesian plane.
 */
public class DataPoint {

  /**
   * the x coordinate of the point
   */
  public final double x;

  /**
   * the y coordinate of the point
   */
  public final double y;

  /**
   * Creates a new data point with the given coordinates.
   *
   * @param x the x coordinate of this point
   * @param y the y coordinate of this point
   */
  public DataPoint( double x, double y ) {
    this.x = x;
    this.y = y;
  }

  protected JsonObject toJson() {
    return new JsonObject().add( "x", x ).add( "y", y );
  }

}
