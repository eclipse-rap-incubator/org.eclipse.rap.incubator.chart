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
package org.eclipse.rap.addons.chart;

import org.eclipse.rap.json.JsonObject;

/**
 * Holds the margins of chart.
 */
public class NvMargin {

  /**
   * Left margin of chart.
   */
  public int left = 0;
  
  /**
   * Top margin of chart.
   */
  public int top = 0;
  
  /**
   * Right margin of chart.
   */
  public int right = 0;

  /**
   * Bottom margin of chart.
   */
  public int bottom = 0;

  public NvMargin( int left, int top, int right, int bottom ) {
    this.left = left;
    this.top = top;
    this.right = right;
    this.bottom = bottom;
  }

  /**
   * Returns the margins as Json object.
   * @return The margis as Json
   */
  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    json.add( "top", this.top );
    json.add( "right", this.right);
    json.add( "bottom", this.bottom );
    json.add( "left", this.left);
    return json;
  }

  @Override
  public boolean equals( Object obj ) {
    if( this == obj ) {
      return true;
    }
    if( obj == null ) {
      return false;
    }
    if( !( obj instanceof NvMargin ) ) {
      return false;
    }
    NvMargin other = ( NvMargin )obj;
    if( bottom != other.bottom ) {
      return false;
    }
    if( left != other.left ) {
      return false;
    }
    if( right != other.right ) {
      return false;
    }
    if( top != other.top ) {
      return false;
    }
    return true;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + bottom;
    result = prime * result + left;
    result = prime * result + right;
    result = prime * result + top;
    return result;
  }

}