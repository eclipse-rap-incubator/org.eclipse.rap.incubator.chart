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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


@SuppressWarnings( "restriction" )
public class BarChart_Test {

  private Display display;
  private Shell shell;
  private RemoteObject remoteObject;
  private Connection connection;
  private BarChart chart;

  @Rule
  public TestContext context = new TestContext();

  @Before
  public void setUp() {
    display = new Display();
    shell = new Shell( display );
    remoteObject = mock( RemoteObject.class );
    connection = fakeConnection( remoteObject );
    chart = new BarChart( shell, SWT.NONE );
  }

  @Test
  public void testCreate_createsRemoteObject() {
    verify( connection ).createRemoteObject( eq( "rwt.chart.Chart" ) );
  }

  @Test
  public void testCreate_setsRenderer() {
    verify( remoteObject ).set( "renderer", "nv-bar" );
  }

  @Test
  public void testGetShowValues_defaultsToTrue() {
    assertTrue( chart.getShowValues() );
  }

 @Test
  public void testSetShowValues_changesValue() {
    chart.setShowValues( false );

    assertFalse( chart.getShowValues() );
  }

  @Test
  public void testSetShowValues_isRendered() {
    reset( remoteObject );

    chart.setShowValues( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showValues", false ) );
  }

  @Test
  public void testIsShowXAxis_defaultsToTrue() {
    assertTrue( chart.isShowXAxis() );
  }

  @Test
  public void testSetShowXAxis_changesValue() {
    chart.setShowXAxis( false );

    assertFalse( chart.isShowXAxis() );
  }

  @Test
  public void testSetShowXAxis_isRendered() {
    reset( remoteObject );

    chart.setShowXAxis( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showXAxis", false ) );
  }

  @Test
  public void testIsShowYAxis_defaultsToTrue() {
    assertTrue( chart.isShowYAxis() );
  }

  @Test
  public void testSetShowYAxis_changesValue() {
    chart.setShowYAxis( false );

    assertFalse( chart.isShowYAxis() );
  }

  @Test
  public void testSetShowYAxis_isRendered() {
    reset( remoteObject );

    chart.setShowYAxis( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showYAxis", false ) );
  }

  @Test
  public void testGetXAxisLabel_hasDefault() {
    assertEquals( "", chart.getXAxisLabel() );
  }

  @Test
  public void testSetXAxisLabel_changesValue() {
    chart.setXAxisLabel( "foo" );

    assertEquals( "foo", chart.getXAxisLabel() );
  }

  @Test
  public void testSetXAxisLabel_isRendered() {
    reset( remoteObject );

    chart.setXAxisLabel( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "xAxis.axisLabel", "foo" ) );
  }

  @Test
  public void testSetXAxisLabel_notRenderedIfUnchanged() {
    chart.setXAxisLabel( chart.getXAxisLabel() );

    verify( remoteObject, times( 0 ) ).set( eq( "barWidth" ), anyInt() );
  }

  @Test
  public void testGetYAxisLabel_hasDefault() {
    assertEquals( "", chart.getYAxisLabel() );
  }

  @Test
  public void testSetYAxisLabel_changesValue() {
    chart.setYAxisLabel( "foo" );

    assertEquals( "foo", chart.getYAxisLabel() );
  }

  @Test
  public void testSetYAxisLabel_isRendered() {
    reset( remoteObject );

    chart.setYAxisLabel( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "yAxis.axisLabel", "foo" ) );
  }

  @Test
  public void testSetYAxisLabel_notRenderedIfUnchanged() {
    chart.setYAxisLabel( chart.getYAxisLabel() );

    verify( remoteObject, times( 0 ) ).set( eq( "barWidth" ), anyInt() );
  }

  @Test
  public void testIsStaggerLabels_defaultsToFalse() {
    assertFalse( chart.isStaggerLabels() );
  }

  @Test
  public void testSetStaggerLabels_changesValue() {
    chart.setStaggerLabels( true );

    assertTrue( chart.isStaggerLabels() );
  }

  @Test
  public void testSetStaggerLabels_isRendered() {
    reset( remoteObject );

    chart.setStaggerLabels( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "staggerLabels", true ) );
  }

 @Test
  public void testSetItems() {
    reset( remoteObject );

    chart.setItems( new DataItem( 23, "foo" ), new DataItem( 42, "bar" ) );

    String expected = "[ { \"values\": [ { \"value\": 23, \"label\": \"foo\" },"
                                      + "{ \"value\": 42, \"label\": \"bar\" } ] } ]";
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
