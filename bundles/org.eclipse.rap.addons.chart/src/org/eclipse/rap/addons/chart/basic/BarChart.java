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

import org.eclipse.rap.addons.chart.NvChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.widgets.Composite;


public class BarChart extends NvChart {

  private boolean showValues = true;

  public BarChart( Composite parent, int style ) {
    super( parent, style, "nv-bar" );
    requireJs( registerResource( "chart/nv/nv-bar.js" ) );
  }

  public void setShowValues( boolean show ) {
    checkWidget();
    if( show != showValues ) {
      showValues = show;
      setOption( "showValues", show );
    }
  }

  public boolean getShowValues() {
    checkWidget();
    return showValues;
  }

  public void setItems( BarItem... items ) {
    JsonArray values = new JsonArray();
    for( BarItem item : items ) {
      values.add( item.toJson() );
    }
    setItems( new JsonArray().add( new JsonObject().add( "values", values ) ) );
  }

}
