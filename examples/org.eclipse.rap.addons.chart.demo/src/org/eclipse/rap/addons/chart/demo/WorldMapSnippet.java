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

import org.eclipse.rap.addons.chart.basic.WorldMapChart;
import org.eclipse.rap.addons.chart.basic.DataItem;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class WorldMapSnippet extends AbstractEntryPoint {

  private WorldMapChart mapChart;

  @Override
  public void createContents( Composite parent ) {
    parent.setLayout( new GridLayout() );
    createMapChart( parent );
    createUpdateButton( parent );
    update();
  }

  private void createMapChart( Composite parent ) {
    mapChart = new WorldMapChart( parent, SWT.NONE );
    mapChart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    mapChart.addListener( SWT.Selection, new Listener() {
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
    mapChart.setItems( createItems() );
  }

  private static DataItem[] createItems() {
    return new DataItem[] {
    };
  }

}
