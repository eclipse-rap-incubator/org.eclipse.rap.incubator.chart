/*******************************************************************************
 * Copyright (c) 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.addons.chart.basic;

import static org.eclipse.rap.addons.chart.internal.ColorUtil.toHtmlString;

import org.eclipse.rap.addons.chart.NvChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.swt.widgets.Composite;


/**
 * A basic historical bar chart widget.
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class HistoricalBarChart extends NvChart {

  private boolean showXAxis = true;
  private boolean showYAxis = true;
  private String xAxisLabel;
  private String yAxisLabel;
  private boolean useInteractiveGuideline = true;
  private boolean rightAlignYAxis;
  private boolean clipEdge = true;

  /**
   * Creates a new empty HistoricalBarChart.
   */
  public HistoricalBarChart( Composite parent, int style ) {
    super( parent, style, "nv-historical-bar" );
    requireJs( registerResource( "chart/nv/nv-historical-bar.js" ) );
  }

  /**
   * Whether the x-axis should be displayed in the chart. The default is <code>true</code>.
   *
   * @param showXAxis <code>true</code> to display x-axis
   */
  public void setShowXAxis( boolean showXAxis ) {
    checkWidget();
    if( this.showXAxis != showXAxis ) {
      this.showXAxis = showXAxis;
      setOption( "showXAxis", showXAxis );
    }
  }

  /**
   * Whether the y-axis should be displayed in the chart. The default is <code>true</code>.
   *
   * @param showYAxis <code>true</code> to display y-axis
   */
  public void setShowYAxis( boolean showYAxis ) {
    checkWidget();
    if( this.showYAxis != showYAxis ) {
      this.showYAxis = showYAxis;
      setOption( "showYAxis", showYAxis );
    }
  }

  /**
   * Sets the label to display on the x-axis.
   *
   * @param xAxisLabel the label for the x-axis
   */
  public void setXAxisLabel( String xAxisLabel ) {
    checkWidget();
    if( this.xAxisLabel != xAxisLabel ) {
      this.xAxisLabel = xAxisLabel;
      setOption( "xAxis.axisLabel", xAxisLabel );
    }
  }

  /**
   * Sets the label to display on the y-axis.
   *
   * @param yAxisLabel the label for the y-axis
   */
  public void setYAxisLabel( String yAxisLabel ) {
    checkWidget();
    if( this.yAxisLabel != yAxisLabel ) {
      this.yAxisLabel = yAxisLabel;
      setOption( "yAxis.axisLabel", yAxisLabel );
    }
  }

  /**
   * Sets the chart to use a guideline and floating tooltip instead of requiring the user to hover
   * over specific hotspots. Turning this on will set the 'interactive' and 'useVoronoi' options to
   * false to avoid conflicting.
   *
   * @param useInteractiveGuideline to use a guideline and floating tooltip
   */
  public void setUseInteractiveGuideline( boolean useInteractiveGuideline ) {
    checkWidget();
    if( this.useInteractiveGuideline != useInteractiveGuideline ) {
      this.useInteractiveGuideline = useInteractiveGuideline;
      setOption( "useInteractiveGuideline", useInteractiveGuideline );
    }
  }

  /**
   * When only one y-axis is used, this puts the y-axis on the right side instead of the left.
   *
   * @param rightAlignYAxis puts the y-axis on the right side
   */
  public void setRightAlignYAxis( boolean rightAlignYAxis ) {
    if( this.rightAlignYAxis != rightAlignYAxis ) {
      this.rightAlignYAxis = rightAlignYAxis;
      setOption( "rightAlignYAxis", rightAlignYAxis );
    }
  }

  /**
   * If true, masks lines within the X and Y scales using a clip-path.
   *
   * @param clipEdge masks lines within the X and Y scales
   */
  public void setClipEdge( boolean clipEdge ) {
    if( this.clipEdge != clipEdge ) {
      this.clipEdge = clipEdge;
      setOption( "clipEdge", clipEdge );
    }
  }

  /**
   * Returns whether y-axis are being displayed in the chart.
   *
   * @return <code>true</code> if y-axis are displayed
   */
  public boolean getShowYAxis() {
    checkWidget();
    return showYAxis;
  }

  /**
   * Returns whether x-axis are being displayed in the chart.
   *
   * @return <code>true</code> if x-axis are displayed
   */
  public boolean getShowXAxis() {
    checkWidget();
    return showXAxis;
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
   * Returns the label for the y-axis.
   *
   * @return the label for the y-axis
   */
  public String getYAxisLabel() {
    checkWidget();
    return yAxisLabel != null ? yAxisLabel : "";
  }

  /**
   * Returns whether guideline and floating tooltip used in the chart.
   *
   * @return <code>true</code> if guideline and floating tooltip used
   */
  public boolean getUseInteractiveGuideline() {
    checkWidget();
    return useInteractiveGuideline;
  }

  /**
   * Returns whether y-axis is displayed on the right side in the chart.
   *
   * @return <code>true</code> if y-axis is displayed on the right side
   */
  public boolean getRightAlignYAxis() {
    checkWidget();
    return rightAlignYAxis;
  }

  /**
   * Returns whether lines mask within the X and Y scales in the chart.
   *
   * @return <code>true</code> if lines mask within the X and Y scales
   */
  public boolean getClipEdge() {
    checkWidget();
    return clipEdge;
  }

  /**
   * Sets the data item to display. Later changes to this list won't be reflected. To change the
   * chart data, call this method with a new list of items.
   *
   * @param item the data items to display
   */
  public void setItems( DataGroup item ) {
    JsonArray values = new JsonArray();
    if( item != null ) {
      values.add( toJson( item ) );
    }
    setItems( values );
  }

  private static JsonObject toJson( DataGroup item ) {
    JsonObject json = new JsonObject();
    JsonArray jsonValues = new JsonArray();
    for( DataItem dataItem : item.items ) {
      if( dataItem instanceof DataItem2D ) {
        DataItem2D item2d = ( DataItem2D )dataItem;
        jsonValues.add( new JsonObject().add( "x", item2d.getX() ).add( "y", item2d.getY() ) );
      }
    }
    json.add( "values", jsonValues );
    if( item.text != null ) {
      json.add( "key", item.text );
    }
    if( item.color != null ) {
      json.add( "color", toHtmlString( item.color ) );
    }
    return json;
  }
}
