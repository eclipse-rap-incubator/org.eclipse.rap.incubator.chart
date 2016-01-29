/*******************************************************************************
 * Copyright (c) 2013, 2016 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.addons.chart.nv.demo;

import static org.eclipse.rap.addons.chart.nv.demo.Colors.CAT10_COLORS;

import org.eclipse.rap.addons.chart.basic.DataPoint;
import org.eclipse.rap.addons.chart.basic.LineChart;
import org.eclipse.rap.addons.chart.basic.LineItem;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class LineChartSnippet extends AbstractEntryPoint {

  private LineChart lineChart;

  @Override
  public void createContents( Composite parent ) {
    parent.setLayout( new GridLayout() );
    createLineChart( parent );
    createUpdateButton( parent );
    update();
  }

  private void createLineChart( Composite parent ) {
    lineChart = new LineChart( parent, SWT.NONE );
    lineChart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    lineChart.setXAxisLabel( "Time" );
    lineChart.setYAxisLabel( "Radiation" );
    lineChart.setYAxisFormat( "d" );
    lineChart.addListener( SWT.Selection, new Listener() {
      @Override
      public void handleEvent( Event event ) {
        System.out.println( "Selected line item #" + event.index + ", point #" + event.detail );
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
    lineChart.setItems( createItems() );
  }

  private static LineItem[] createItems() {
    return new LineItem[] {
      new LineItem( createRandomPoints(), "Series 1", CAT10_COLORS[ 0 ] ),
      new LineItem( createRandomPoints(), "Series 2", CAT10_COLORS[ 1 ] )
    };
  }

  private static DataPoint[] createRandomPoints() {
    DataPoint[] values = new DataPoint[100];
    for( int i = 0; i < values.length; i++ ) {
      values[i] = new DataPoint( i, Math.random() * 100 );
    }
    return values;
  }

}
