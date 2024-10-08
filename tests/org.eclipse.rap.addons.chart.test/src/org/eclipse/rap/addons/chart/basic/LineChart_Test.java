/*******************************************************************************
 * Copyright (c) 2013, 2024 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial API and implementation
 ******************************************************************************/
package org.eclipse.rap.addons.chart.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;
import org.eclipse.rap.rwt.internal.remote.ConnectionImpl;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.eclipse.rap.rwt.testfixture.TestContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


@SuppressWarnings( "restriction" )
public class LineChart_Test {

  private Display display;
  private Shell shell;
  private RemoteObject remoteObject;
  private Connection connection;
  private LineChart chart;

  @Rule
  public TestContext context = new TestContext();

  @Before
  public void setUp() {
    display = new Display();
    shell = new Shell( display );
    remoteObject = mock( RemoteObject.class );
    connection = fakeConnection( remoteObject );
    chart = new LineChart( shell, SWT.NONE );
  }

  @Test
  public void testCreate_createsRemoteObject() {
    verify( connection ).createRemoteObject( eq( "rwt.chart.Chart" ) );
  }

  @Test
  public void testCreate_setsRenderer() {
    verify( remoteObject ).set( "renderer", "nv-line" );
  }

  @Test
  public void testGetXAxisLabel_hasDefault() {
    assertEquals( "", chart.getXAxisLabel() );
  }

  @Test( expected = SWTException.class )
  public void testGetXAxisLabel_checksWidget() {
    chart.dispose();

    chart.getXAxisLabel();
  }

  @Test
  public void testSetXAxisLabel_changesValue() {
    chart.setXAxisLabel( "foo" );

    assertEquals( "foo", chart.getXAxisLabel() );
  }

  @Test( expected = SWTException.class )
  public void testSetXAxisLabel_checksWidget() {
    chart.dispose();

    chart.setXAxisLabel( "foo" );
  }

  @Test
  public void testSetXAxisLabel_isRendered() {
    chart.setXAxisLabel( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "xAxis.axisLabel", "foo" ) );
  }

  @Test
  public void testSetXAxisLabel_notRenderedIfUnchanged() {
    chart.setXAxisLabel( chart.getXAxisLabel() );

    verify( remoteObject, times( 0 ) ).set( eq( "barWidth" ), anyInt() );
  }

  @Test( expected = SWTException.class )
  public void testGetXAxisFormat_checksWidget() {
    chart.dispose();

    chart.getXAxisFormat();
  }

  @Test
  public void testSetXAxisFormat_changesValue() {
    chart.setXAxisFormat( "foo" );

    assertEquals( "foo", chart.getXAxisFormat() );
  }

  @Test( expected = SWTException.class )
  public void testSetXAxisFormat_checksWidget() {
    chart.dispose();

    chart.setXAxisFormat( "d" );
  }

  @Test
  public void testSetXAxisFormat_isRendered() {
    chart.setXAxisFormat( "x" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "xAxisFormat", "x" ) );
  }

  @Test
  public void testSetXAxisFormat_notRenderedIfUnchanged() {
    chart.setXAxisFormat( chart.getXAxisFormat() );

    verify( remoteObject, times( 0 ) ).set( eq( "spacing" ), anyInt() );
  }

  @Test
  public void testIsInteractive_defaultsToTrue() {
    assertTrue( chart.isInteractive() );
  }

  @Test
  public void testSetInteractive_changesValue() {
    chart.setInteractive( false );

    assertFalse( chart.isInteractive() );
  }

  @Test
  public void testSetInteractive_isRendered() {
    chart.setInteractive( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "interactive", false ) );
  }

  @Test
  public void testGetInterpolate_hasDefault() {
    assertEquals( "linear", chart.getInterpolate() );
  }

  @Test
  public void testSetInterpolate_changesValue() {
    chart.setInterpolate( "foo" );

    assertEquals( "foo", chart.getInterpolate() );
  }

  @Test
  public void testSetInterpolate_isRendered() {
    chart.setInterpolate( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "interpolate", "foo" ) );
  }

  @Test
  public void testGetArea_defaultsToFalse() {
    assertFalse( chart.getArea() );
  }

  @Test
  public void testSetArea_changesValue() {
    chart.setArea( true );

    assertTrue( chart.getArea() );
  }

  @Test
  public void testSetArea_isRendered() {
    chart.setArea( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "isArea", true ) );
  }

  @Test
  public void testGetUseInteractiveGuideline_defaultsToFalse() {
    assertFalse( chart.getUseInteractiveGuideline() );
  }

  @Test
  public void testSetUseInteractiveGuideline_changesValue() {
    chart.setUseInteractiveGuideline( true );

    assertTrue( chart.getUseInteractiveGuideline() );
  }

  @Test
  public void testSetUseInteractiveGuideline_isRendered() {
    chart.setUseInteractiveGuideline( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "useInteractiveGuideline", true ) );
  }

  @Test
  public void testGetUseVoronoi_defaultsToTrue() {
    assertTrue( chart.getUseVoronoi() );
  }

  @Test
  public void testSetUseVoronoi_changesValue() {
    chart.setUseVoronoi( false );

    assertFalse( chart.getUseVoronoi() );
  }

  @Test
  public void testSetUseVoronoi_isRendered() {
    chart.setUseVoronoi( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "useVoronoi", false ) );
  }

  @Test
  public void testGetPadData_defaultsToTrue() {
    assertTrue( chart.getPadData() );
  }

  @Test
  public void testSetPadData_changesValue() {
    chart.setPadData( false );

    assertFalse( chart.getPadData() );
  }

  @Test
  public void testSetPadData_isRendered() {
    chart.setPadData( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "padData", false ) );
  }

  @Test
  public void testSetItems() {
    chart.setItems( new DataGroup( new DataItem2D[] { new DataItem2D( 1, 2 ),
                                                      new DataItem2D( 3, 4 ) }, "foo" ),
                    new DataGroup( new DataItem2D[] { new DataItem2D( 2, 4 ),
                                                      new DataItem2D( 6, 8 ) }, "bar" ) );

    String expected = "[{ \"values\": [{ \"x\": 1, \"y\": 2 },"
                                    + "{ \"x\": 3, \"y\": 4 }], \"key\": \"foo\" },"
                     + "{ \"values\": [{ \"x\": 2, \"y\": 4 },"
                                    + "{ \"x\": 6, \"y\": 8 }], \"key\": \"bar\" }]";
    verify( remoteObject ).set( "items", JsonValue.readFrom( expected ) );
  }

  private Connection fakeConnection( RemoteObject remoteObject ) {
    ConnectionImpl connection = mock( ConnectionImpl.class );
    when( connection.createRemoteObject( anyString() ) ).thenReturn( remoteObject );
    when( connection.createServiceObject( anyString() ) ).thenReturn( mock( RemoteObject.class ) );
    context.replaceConnection( connection );
    return connection;
  }

}
