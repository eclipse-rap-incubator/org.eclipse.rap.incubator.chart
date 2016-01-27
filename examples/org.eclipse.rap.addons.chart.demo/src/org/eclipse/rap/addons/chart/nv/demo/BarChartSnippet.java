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
package org.eclipse.rap.addons.chart.nv.demo;

import org.eclipse.rap.addons.chart.basic.BarChart;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
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
    barChart.setShowValues( true );
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
    barChart.setChartData( createData() );
  }

  private static JsonArray createData() {
    return new JsonArray()
      .add( new JsonObject().add( "key", "foo" ).add( "values", createRandomValues() ) );
  }

  private static JsonArray createRandomValues() {
    return new JsonArray()
      .add( createItem( "Item 1", "#ff0000", Math.random() * 100 ) )
      .add( createItem( "Item 2", "#00ff00", Math.random() * 100 ) )
      .add( createItem( "Item 3", "#0000ff", Math.random() * 100 ) )
      .add( createItem( "Item 4", "#ffff00", Math.random() * 100 ) )
      .add( createItem( "Item 5", "#00ffff", Math.random() * 100 ) );
  }

  private static JsonObject createItem( String text, String color, double value ) {
    return new JsonObject()
      .add( "label", text )
      .add( "color", color )
      .add( "value", value );
  }

}
