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
 * A basic stackable multi bar chart widget.
 * <dl>
 * <dt><b>Styles:</b></dt>
 * <dd>none</dd>
 * <dt><b>Events:</b></dt>
 * <dd>Selection</dd>
 * </dl>
 */
public class StackedMultiBarChart extends NvChart {

  private boolean showControls;
  private boolean stacked;
  private boolean reduceXTicks = true;
  private int rotateLabels = 0;
  private float groupSpacing = 0.1f;
  private boolean showYAxis = true;
  private boolean showXAxis = true;
  private String xAxisLabel;
  private String yAxisLabel;
  private DataGroup[] items = new DataGroup[ 0 ];

  /**
   * Creates a new empty StackedMultiBarChart.
   */
  public StackedMultiBarChart( Composite parent, int style ) {
    super( parent, style, "nv-stacked-multi-bar" );
    requireJs( registerResource( "chart/nv/nv-stacked-multi-bar.js" ) );
  }

  /**
   * Whether to show extra controls or not in the chart. Extra controls include things like making
   * mulitBar charts stacked or side by side. The default is <code>false</code>.
   *
   * @param showControls <code>true</code> to show extra controls
   */
  public void setShowControls( boolean showControls ) {
    checkWidget();
    if( showControls != this.showControls ) {
      this.showControls = showControls;
      setOption( "showControls", showControls );
    }
  }

  /**
   * Whether the bars should be displayed as stacked in the chart. The default is <code>false</code>
   * .
   *
   * @param stacked <code>true</code> to display bars as stacked
   */
  public void setStacked( boolean stacked ) {
    checkWidget();
    if( stacked != this.stacked ) {
      this.stacked = stacked;
      setOption( "stacked", stacked );
      updateItems();
    }
  }

  /**
   * Whether reduce the displayed X axis parameter ticks in the chart. The default is
   * <code>true</code>.
   *
   * @param reduceXTicks <code>false</code> to display all X axis parameter ticks
   */
  public void setReduceXTicks( boolean reduceXTicks ) {
    checkWidget();
    if( reduceXTicks != this.reduceXTicks ) {
      this.reduceXTicks = reduceXTicks;
      setOption( "reduceXTicks", reduceXTicks );
    }
  }

  /**
   * Rotates the X axis labels by the specified degree. The default is 0.
   *
   * @param rotateLabels to display label values rotation in degree
   */
  public void setRotateLabels( int rotateLabels ) {
    checkWidget();
    if( rotateLabels != this.rotateLabels ) {
      this.rotateLabels = rotateLabels;
      setOption( "rotateLabels", rotateLabels );
    }
  }

  /**
   * The padding between bar groups in the chart. The default is 0.1f.
   *
   * @param groupSpacing to put padding between bar groups
   */
  public void setGroupSpacing( float groupSpacing ) {
    checkWidget();
    if( groupSpacing != this.groupSpacing ) {
      this.groupSpacing = groupSpacing;
      setOption( "groupSpacing", groupSpacing );
    }
  }

  /**
   * Whether the y axis should be displayed in the chart. The default is <code>true</code>.
   *
   * @param showYAxis <code>true</code> to display y axis
   */
  public void setShowYAxis( boolean showYAxis ) {
    checkWidget();
    if( showYAxis != this.showYAxis ) {
      this.showYAxis = showYAxis;
      setOption( "showYAxis", showYAxis );
    }
  }

  /**
   * Whether the x axis should be displayed in the chart. The default is <code>true</code>.
   *
   * @param showXAxis <code>true</code> to display x axis
   */
  public void setShowXAxis( boolean showXAxis ) {
    checkWidget();
    if( showXAxis != this.showXAxis ) {
      this.showXAxis = showXAxis;
      setOption( "showXAxis", showXAxis );
    }
  }

  /**
   * Sets the label to display on the x-axis.
   *
   * @param label the label for the x-axis
   */
  public void setXAxisLabel( String label ) {
    checkWidget();
    if( this.xAxisLabel != label ) {
      this.xAxisLabel = label;
      setOption( "xAxis.axisLabel", label );
    }
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
   * Returns whether to show extra controls or not in the chart.
   *
   * @return <code>true</code> if extra controls displayed
   */
  public boolean getShowControls() {
    checkWidget();
    return showControls;
  }

  /**
   * Returns whether the bars should be displayed as stacked in the chart.
   *
   * @return <code>true</code> if bars displayed as stacked
   */
  public boolean getStacked() {
    checkWidget();
    return stacked;
  }

  /**
   * Returns whether reduce the displayed x parameter ticks in the chart.
   *
   * @return <code>false</code> if all x parameter ticks displayed
   */
  public boolean getReduceXTicks() {
    checkWidget();
    return reduceXTicks;
  }

  /**
   * Returns specified rotated degree of X axis labels in the chart.
   *
   * @return rotated specified degree of X axis labels.
   */
  public int getRotateLabels() {
    checkWidget();
    return rotateLabels;
  }

  /**
   * Returns the padding between bar groups in the chart.
   *
   * @return the padding between bar groups.
   */
  public float getGroupSpacing() {
    checkWidget();
    return groupSpacing;
  }

  /**
   * Returns whether y axis are being displayed in the chart.
   *
   * @return <code>true</code> if y axis are displayed
   */
  public boolean getShowYAxis() {
    checkWidget();
    return showYAxis;
  }

  /**
   * Returns whether x axis are being displayed in the chart.
   *
   * @return <code>true</code> if x axis are displayed
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
    return this.xAxisLabel != null ? this.xAxisLabel : "";
  }

  /**
   * Returns the label for the y-axis.
   *
   * @return the label for the y-axis
   */
  public String getYAxisLabel() {
    checkWidget();
    return this.yAxisLabel != null ? this.yAxisLabel : "";
  }

  /**
   * Sets the data items to display. Later changes to this list won't be reflected. To change the
   * chart data, call this method with a new list of items.
   *
   * @param items the data items to display
   */
  public void setItems( final DataGroup... items ) {
    checkWidget();
    this.items = items;
    updateItems();
  }

  private void updateItems() {
    JsonArray values = new JsonArray();
    for( int i = 0; i < items.length; i++ ) {
      values.add( toJson( items[ i ], stacked ) );
    }
    setItems( values );
  }

  private static JsonObject toJson( DataGroup item, boolean isStackable ) {
    JsonObject json = new JsonObject()
      .add( "key", item.text )
      .add( "nonStackable", isStackable );
    JsonArray jsonValues = new JsonArray();
    for( DataItem dataItem : item.items ) {
      if( dataItem instanceof DataItem2D ) {
        DataItem2D item2d = ( DataItem2D )dataItem;
        jsonValues.add( new JsonObject().add( "x", item2d.getX() ).add( "y", item2d.getY() ) );
      }
    }
    json.add( "values", jsonValues );
    if( item.color != null ) {
      json.add( "color", toHtmlString( item.color ) );
    }
    return json;
  }

}
