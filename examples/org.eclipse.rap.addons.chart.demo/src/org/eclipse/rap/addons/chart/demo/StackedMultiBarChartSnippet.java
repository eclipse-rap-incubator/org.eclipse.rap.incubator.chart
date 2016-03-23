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
package org.eclipse.rap.addons.chart.demo;

import static org.eclipse.rap.addons.chart.Colors.CATEGORY_10;

import org.eclipse.rap.addons.chart.basic.DataGroup;
import org.eclipse.rap.addons.chart.basic.DataItem2D;
import org.eclipse.rap.addons.chart.basic.StackedMultiBarChart;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class StackedMultiBarChartSnippet extends AbstractEntryPoint {

  private StackedMultiBarChart stackedMultiBarChart;

  @Override
  public void createContents( Composite parent ) {
    parent.setLayout( new GridLayout() );
    createStackedMultiBarChart( parent );
    createUpdateButton( parent );
    update();
  }

  private void createStackedMultiBarChart( Composite parent ) {
    stackedMultiBarChart = new StackedMultiBarChart( parent, SWT.NONE );
    stackedMultiBarChart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    stackedMultiBarChart.setShowControls( true );
    stackedMultiBarChart.setXAxisLabel( "X Axis ");
    stackedMultiBarChart.setYAxisLabel( "Y Axis" );
    stackedMultiBarChart.addListener( SWT.Selection, new Listener() {
      @Override
      public void handleEvent( Event event ) {
        System.out.println( "Selected bar item #" + event.index );
      }
    } );
  }

  private void createUpdateButton( Composite parent ) {
    Button button = new Button( parent, SWT.PUSH );
    button.setText( "Change data" );
    button.addListener( SWT.Selection, new Listener() {
      @Override
      public void handleEvent( Event event ) {
        update();
      }
    } );
  }

  private void update() {
    stackedMultiBarChart.setItems( createItems() );
  }

  private static DataGroup[] createItems() {
    DataGroup[] dataItemStackedBars = new DataGroup[ 2 ];
    DataItem2D[] values = new DataItem2D[ 70 ];
    for( int i = 0; i < 70; i++ ) {
      values[ i ] = new DataItem2D( i + 1, Math.random() * 100 );
    }
    dataItemStackedBars[ 0 ] = new DataGroup( values, "Stream 1", CATEGORY_10[ 2 ] );
    values = new DataItem2D[ 70 ];
    for( int i = 0; i < 70; i++ ) {
      values[ i ] = new DataItem2D( i + 1, Math.random() * 100 );
    }
    dataItemStackedBars[ 1 ] = new DataGroup( values, "Stream 2", CATEGORY_10[ 3 ] );
    return dataItemStackedBars;
  }

}
