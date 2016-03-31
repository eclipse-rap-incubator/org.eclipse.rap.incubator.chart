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

import static org.eclipse.rap.addons.chart.demo.Colors.CAT10_COLORS;

import org.eclipse.rap.addons.chart.basic.DataGroup;
import org.eclipse.rap.addons.chart.basic.DataItem2D;
import org.eclipse.rap.addons.chart.basic.HistoricalBarChart;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class HistoricalBarChartSnippet extends AbstractEntryPoint {

  private HistoricalBarChart historicalBarChart;

  @Override
  public void createContents( Composite parent ) {
    parent.setLayout( new GridLayout() );
    createHistoricalBarChart( parent );
    createUpdateButton( parent );
    update();
  }

  private void createHistoricalBarChart( Composite parent ) {
    historicalBarChart = new HistoricalBarChart( parent, SWT.NONE );
    historicalBarChart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    historicalBarChart.setXAxisLabel( "I am X Label" );
    historicalBarChart.setYAxisLabel( "I am Y Label" );
    historicalBarChart.setClipEdge( false );
    historicalBarChart.addListener( SWT.Selection, new Listener() {
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
    historicalBarChart.setItems( createItems() );
  }

  private static DataGroup createItems() {
    DataItem2D[] values = new DataItem2D[ 100 ];
    for( int i = 0; i < 100; i++ ) {
      values[ i ] = new DataItem2D( i, Math.sin((double)i/10) * Math.random() * 100 );
    }
    return new DataGroup( values, "Stream", CAT10_COLORS[ 4 ] );
  }

}
