/*******************************************************************************
 * Copyright (c) 2015, 2016 EclipseSource and others.
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
 * A basic pie or donut chart widget.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class PieChart extends NvChart {

  private boolean showLabels = true;
  private boolean donut;

  /**
   * Creates a new empty Pie chart.
   */
  public PieChart( Composite parent, int style ) {
    super( parent, style, "nv-pie" );
    requireJs( registerResource( "chart/nv/nv-pie.js" ) );
  }

  /**
   * Set whether labels should be displayed for each segment. The default is <code>true</code>.
   *
   * @param show <code>true</code> to display labels for each segment
   */
  public void setShowLabels( boolean show ) {
    checkWidget();
    if( show != showLabels ) {
      showLabels = show;
      setOption( "showLabels", show );
    }
  }

  /**
   * Returns whether labels are displayed for each segment.
   *
   * @return <code>true</code> if labels are displayed for each segment.
   */
  public boolean getShowLabels() {
    checkWidget();
    return showLabels;
  }

  /**
   * Sets whether this chart should be displayed as a donut chart. The default is
   * <code>false</code>.
   *
   * @param donut <code>true</code> to display as a donut chart
   */
  public void setDonut( boolean donut ) {
    if( this.donut != donut ) {
      this.donut = donut;
      setOption( "donut", donut );
    }
  }

  /**
   * Returns whether this chart is displayed as a donut chart.
   *
   * @return <code>true</code> if displayed as a donut chart
   */
  public boolean getDonut() {
    return donut;
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
    setItems( values );
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
