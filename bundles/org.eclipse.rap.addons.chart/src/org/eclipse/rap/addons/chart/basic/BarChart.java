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

import org.eclipse.rap.addons.chart.NvChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.widgets.Composite;


/**
 * A basic bar chart widget.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class BarChart extends NvChart {

  private boolean showValues = true;

  /**
   * Creates a new empty BarChart.
   */
  public BarChart( Composite parent, int style ) {
    super( parent, style, "nv-bar" );
    requireJs( registerResource( "chart/nv/nv-bar.js" ) );
  }

  /**
   * Whether the y values should be displayed in the chart. The default is <code>true</code>.
   *
   * @param show <code>true</code> to display y values
   */
  public void setShowValues( boolean show ) {
    checkWidget();
    if( show != showValues ) {
      showValues = show;
      setOption( "showValues", show );
    }
  }

  /**
   * Returns whether y values are being displayed in the chart.
   *
   * @return <code>true</code> if y values are displayed
   */
  public boolean getShowValues() {
    checkWidget();
    return showValues;
  }

  /**
   * Sets the data items to display. Later changes to this list won't be reflected. To change the
   * chart data, call this method with a new list of items.
   *
   * @param items the data items to display
   */
  public void setItems( DataItem... items ) {
    JsonArray values = new JsonArray();
    for( DataItem item : items ) {
      values.add( toJson( item ) );
    }
    setItems( new JsonArray().add( new JsonObject().add( "values", values ) ) );
  }

  private static JsonObject toJson( DataItem item ) {
    JsonObject json = new JsonObject().add( "value", item.value );
    if( item.text != null ) {
      json.add( "label", item.text );
    }
    if( item.color != null ) {
      json.add( "color", toHtmlString( item.color ) );
    }
    return json;
  }

}
