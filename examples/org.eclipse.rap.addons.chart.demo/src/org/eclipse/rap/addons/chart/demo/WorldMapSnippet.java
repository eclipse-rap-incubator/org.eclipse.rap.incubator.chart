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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.addons.chart.basic.WorldMapChart;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class WorldMapSnippet extends AbstractEntryPoint {

  private WorldMapChart mapChart;

  @Override
  public void createContents( Composite parent ) {
    parent.setLayout( new GridLayout() );
    createMapChart( parent );
  }

  private void createMapChart( Composite parent ) {
    mapChart = new WorldMapChart( parent, SWT.NONE );
    mapChart.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true ) );
    mapChart.addListener( SWT.Selection, new Listener() {
      @Override
      public void handleEvent( Event event ) {
        System.out.println( "Selected country #" + event.index );
      }
    } );
    Map<String, RGB> colors = new HashMap<>();
    // TODO: Allow for three-letter country codes
    // See https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3
    colors.put( "100", new RGB( 200, 0, 0 ) ); // BGR
    colors.put( "124", new RGB( 200, 0, 0 ) ); // CDN
    colors.put( "276", new RGB( 200, 0, 0 ) ); // DEU
    colors.put( "616", new RGB( 200, 0, 0 ) ); // POL
    colors.put( "642", new RGB( 200, 0, 0 ) ); // ROU
    colors.put( "*", new RGB( 0, 100, 0 ) );
    mapChart.setCountryColors( colors );
  }

}
