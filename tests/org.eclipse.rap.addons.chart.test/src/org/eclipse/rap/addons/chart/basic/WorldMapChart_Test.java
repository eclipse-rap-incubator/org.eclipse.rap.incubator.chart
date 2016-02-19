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
package org.eclipse.rap.addons.chart.basic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.client.Client;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.internal.remote.ConnectionImpl;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.testfixture.TestContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@SuppressWarnings( { "restriction", "deprecation" } )
public class WorldMapChart_Test {

  private static final String PROP_TOPOJSON_JS_URL = "org.eclipse.rap.addons.chart.topojsonJsUrl";
  private static final String DEF_TOPOJSON_JS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/topojson/1.6.20/topojson.min.js";

  private Display display;
  private Shell shell;
  private RemoteObject remoteObject;
  private Connection connection;
  private WorldMapChart chart;

  @Rule
  public TestContext context = new TestContext();

  @Before
  public void setUp() {
    display = new Display();
    shell = new Shell( display );
    remoteObject = mock( RemoteObject.class );
    connection = fakeConnection( remoteObject );
    chart = new WorldMapChart( shell, SWT.NONE );
  }

  @Test
  public void testCreate_requiresTopojsonJS() {
    JavaScriptLoader loader = mock( JavaScriptLoader.class );
    fakeLoader( loader );

    new WorldMapChart( shell, SWT.NONE );

    verify( loader ).require( DEF_TOPOJSON_JS_URL );
  }

  @Test
  public void testCreate_requiresTopojsonJS_fromSystemProperty() {
    JavaScriptLoader loader = mock( JavaScriptLoader.class );
    fakeLoader( loader );

    System.setProperty( PROP_TOPOJSON_JS_URL, "custom://url" );
    new WorldMapChart( shell, SWT.NONE );
    System.clearProperty( PROP_TOPOJSON_JS_URL );

    verify( loader, never() ).require( DEF_TOPOJSON_JS_URL );
    verify( loader ).require( "custom://url" );
  }

  @Test
  public void testCreate_registeresJavaScriptResource() {
    assertTrue( RWT.getResourceManager().isRegistered( "chart/topojson/topojson-world.js" ) );
  }


  @Test
  public void testCreate_registeresCssResource() {
    assertTrue( RWT.getResourceManager().isRegistered( "resources/topojson-world.css" ) );
  }

  @Test
  public void testCreate_registeresJsonResource() {
    assertTrue( RWT.getResourceManager().isRegistered( "resources/world-110m.json" ) );
  }

  @Test
  public void testCreate_createsRemoteObject() {
    verify( connection ).createRemoteObject( eq( "rwt.chart.Chart" ) );
  }

  @Test
  public void testCreate_setsRenderer() {
    verify( remoteObject ).set( "renderer", "topojson-world" );
  }

  @Test
  public void testGetGraticule_defaultsToFalse() {
    assertFalse( chart.getShowGraticule() );
  }

  @Test
  public void testSetGraticule_changesValue() {
    chart.setShowGraticule( true );

    assertTrue( chart.getShowGraticule() );
  }

  @Test
  public void testSetGraticule_isRendered() {
    chart.setShowGraticule( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showGraticule", true ) );
  }

  @Test
  public void testSetColors_byArray_isRendered() {
    RGB[] colors = {
      new RGB( 0x1f, 0x77, 0xb4 ),
      new RGB( 0xff, 0x7f, 0x0e ),
      new RGB( 0x2c, 0xa0, 0x2c )
    };

    chart.setColors( colors );

    JsonArray expectedColors = new JsonArray()
        .add( "#1f77b4" )
        .add( "#ff7f0e" )
        .add( "#2ca02c" );
    verify( remoteObject ).call( "setOptions", new JsonObject().add( "colors", expectedColors ) );
  }

  private void fakeLoader( JavaScriptLoader loader ) {
    Client client = mock( Client.class );
    when( client.getService( JavaScriptLoader.class ) ).thenReturn( loader );
    context.replaceClient( client );
  }

  private Connection fakeConnection( RemoteObject remoteObject ) {
    ConnectionImpl connection = mock( ConnectionImpl.class );
    when( connection.createRemoteObject( anyString() ) ).thenReturn( remoteObject );
    when( connection.createServiceObject( anyString() ) ).thenReturn( mock( RemoteObject.class ) );
    context.replaceConnection( connection );
    return connection;
  }

}
