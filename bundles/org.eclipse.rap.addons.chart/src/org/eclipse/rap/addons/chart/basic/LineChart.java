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
 * A basic line chart widget that supports multiple lines.
 *
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class LineChart extends NvChart {

  private String xAxisLabel;
  private String yAxisLabel;
  private String xAxisFormat;
  private String yAxisFormat;

  /**
   * Creates a new empty LineChart.
   */
  public LineChart( Composite parent, int style ) {
   super( parent, style, "nv-line" );
    requireJs( registerResource( "chart/nv/nv-line.js" ) );
  }

  /**
   * Sets the label to display on the x-axis.
   *
   * @param label the label for the x-axis
   */
  public void setXAxisLabel( String label ) {
    checkWidget();
    if( this.xAxisLabel != label ) {
      xAxisLabel = label;
      setOption( "xAxis.axisLabel", label );
    }
  }

  /**
   * Returns the label for the x-axis.
   *
   * @return the label for the x-axis
   */
  public String getXAxisLabel() {
    checkWidget();
    return xAxisLabel != null ? xAxisLabel : "";
  }

  /**
   * Sets the label to display on the y-axis.
   *
   * @param label the label for the y-axis
   */
  public void setYAxisLabel( String label ) {
    checkWidget();
    if( this.yAxisLabel != label ) {
      yAxisLabel = label;
      setOption( "yAxis.axisLabel", label );
    }
  }

  /**
   * Returns the label for the y-axis.
   *
   * @return the label for the y-axis
   */
  public String getYAxisLabel() {
    checkWidget();
    return xAxisLabel != null ? xAxisLabel : "";
  }

  /**
   * Sets the format for the labels on the x-axis. The format string must be recognizable by the
   * <a href="https://github.com/mbostock/d3/wiki/Formatting#d3_format">d3.format()</a> function.
   *
   * @see "https://github.com/mbostock/d3/wiki/Formatting#d3_format"
   * @param format a d3 format string for the labels of the x-axis
   */
  public void setXAxisFormat( String format ) {
    checkWidget();
    if( this.xAxisFormat != format ) {
      xAxisFormat = format;
      setOption( "xAxisFormat", format );
    }
  }

  /**
   * Returns the format used for labels on the x-axis.
   *
   * @return the format for the labels on the x-axis
   */
  public String getXAxisFormat() {
    checkWidget();
    return xAxisFormat;
  }

  /**
   * Sets the format for the labels on the y-axis. The format string must be recognizable by the
   * <a href="https://github.com/mbostock/d3/wiki/Formatting#d3_format">d3.format()</a> function.
   *
   * @see "https://github.com/mbostock/d3/wiki/Formatting#d3_format"
   * @param format a d3 format string for the labels of the y-axis
   */
  public void setYAxisFormat( String format ) {
    checkWidget();
    if( this.yAxisFormat != format ) {
      yAxisFormat = format;
      setOption( "yAxisFormat", format );
    }
  }

  /**
   * Returns the format used for labels on the y-axis.
   *
   * @return the format for the labels on the y-axis
   */
  public String getYAxisFormat() {
    checkWidget();
    return yAxisFormat;
  }

  /**
   * Sets the data items to display. Every item represents a separate line. Later changes to this
   * list won't be reflected. To change the chart data, call this method with a new list of items.
   *
   * @param items the data items to display
   */
  public void setItems( DataGroup... items ) {
    JsonArray values = new JsonArray();
    for( DataGroup item : items ) {
      values.add( toJson( item ) );
    }
    setItems( values );
  }

  private static JsonObject toJson( DataGroup group ) {
    JsonArray values = new JsonArray();
    for( int i = 0; i < group.items.length; i++ ) {
      DataItem item = group.items[ i ];
      if( item instanceof DataItem2D ) {
        DataItem2D item2d = ( DataItem2D )item;
        values.add( new JsonObject().add( "x", item2d.getX() ).add( "y", item2d.getY() ) );
      } else {
        values.add( new JsonObject().add( "x", i ).add( "y", item.getValue() ) );
      }
    }
    JsonObject json = new JsonObject().add( "values", values );
    if( group.text != null ) {
      json.add( "key", group.text );
    }
    if( group.color != null ) {
      json.add( "color", toHtmlString( group.color ) );
    }
    return json;
  }

}
