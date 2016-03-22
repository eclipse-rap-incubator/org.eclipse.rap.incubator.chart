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
package org.eclipse.rap.addons.chart.demo;

import static org.eclipse.rap.addons.chart.Colors.CATEGORY10;

import org.eclipse.rap.addons.chart.basic.BarChart;
import org.eclipse.rap.addons.chart.basic.DataItem;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class BarChartSnippet extends AbstractEntryPoint {

  private BarChart barChart;

  @Override
  public void createContents( Composite parent ) {
    parent.setLayout( new GridLayout() );
    createBarChart( parent );
    createUpdateButton( parent );
    update();
  }

  private void createBarChart( Composite parent ) {
    barChart = new BarChart( parent, SWT.NONE );
    barChart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    barChart.addListener( SWT.Selection, new Listener() {
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
    barChart.setItems( createItems() );
  }

  private static DataItem[] createItems() {
    return new DataItem[] {
      new DataItem( Math.random() * 100, "Item 1", CATEGORY10[ 0 ] ),
      new DataItem( Math.random() * 100, "Item 2", CATEGORY10[ 1 ] ),
      new DataItem( Math.random() * 100, "Item 3", CATEGORY10[ 2 ] ),
      new DataItem( Math.random() * 100, "Item 4", CATEGORY10[ 3 ] ),
      new DataItem( Math.random() * 100, "Item 5", CATEGORY10[ 4 ] )
    };
  }

}
