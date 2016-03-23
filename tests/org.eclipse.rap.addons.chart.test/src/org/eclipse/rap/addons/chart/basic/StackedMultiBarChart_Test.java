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
package org.eclipse.rap.addons.chart.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
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
import org.mockito.ArgumentCaptor;


@SuppressWarnings( "restriction" )
public class StackedMultiBarChart_Test {

  private Display display;
  private Shell shell;
  private RemoteObject remoteObject;
  private Connection connection;
  private StackedMultiBarChart chart;

  @Rule
  public TestContext context = new TestContext();

  @Before
  public void setUp() {
    display = new Display();
    shell = new Shell( display );
    remoteObject = mock( RemoteObject.class );
    connection = fakeConnection( remoteObject );
    chart = new StackedMultiBarChart( shell, SWT.NONE );
  }

  @Test
  public void testCreate_createsRemoteObject() {
    verify( connection ).createRemoteObject( eq( "rwt.chart.Chart" ) );
  }

  @Test
  public void testCreate_setsRenderer() {
    verify( remoteObject ).set( "renderer", "nv-stacked-multi-bar" );
  }

  @Test
  public void testIsShowControls_defaultsToFalse() {
    assertFalse( chart.getShowControls() );
  }

  @Test
  public void testIsStacked_defaultsToFalse() {
    assertFalse( chart.getStacked() );
  }

  @Test
  public void testIsReduceXTicks_defaultsToTrue() {
    assertTrue( chart.getReduceXTicks() );
  }

  @Test
  public void testGetRotateLabels_hasDefault() {
    assertEquals( 0, chart.getRotateLabels() );
  }

  @Test
  public void testGroupSpacing_hasDefault() {
    assertEquals( 0.1f, chart.getGroupSpacing(), 0.0f );
  }

  @Test
  public void testGetShowYAxis_defaultsToTrue() {
    assertTrue( chart.getShowYAxis() );
  }

  @Test
  public void testGetShowXAxis_defaultsToTrue() {
    assertTrue( chart.getShowXAxis() );
  }

  @Test
  public void testGetXAxisLabel_hasDefault() {
    assertEquals( "", chart.getXAxisLabel() );
  }

  @Test
  public void testGetYAxisLabel_hasDefault() {
    assertEquals( "", chart.getYAxisLabel() );
  }

  @Test
  public void testShowControls_changesValue() {
    chart.setShowControls( true );

    assertTrue( chart.getShowControls() );
  }

  @Test
  public void testStacked_changesValue() {
    chart.setStacked( true );

    assertTrue( chart.getStacked() );
  }

  @Test
  public void testSetReduceXTicks_changesValue() {
    chart.setReduceXTicks( false );

    assertFalse( chart.getReduceXTicks() );
  }

  @Test
  public void testSetRotateLabels_changesValue() {
    chart.setRotateLabels( 30 );

    assertEquals( 30, chart.getRotateLabels() );
  }

  @Test
  public void testSetGroupSpacing_changesValue() {
    chart.setGroupSpacing( 0.3f );

    assertEquals( 0.3f, chart.getGroupSpacing(), 0.0f );
  }

  @Test
  public void testSetShowYAxis_changesValue() {
    chart.setShowYAxis( false );

    assertFalse( chart.getShowYAxis() );
  }

  @Test
  public void testSetShowXAxis_changesValue() {
    chart.setShowXAxis( false );

    assertFalse( chart.getShowXAxis() );
  }

  @Test
  public void testSetXAxisLabel_changesValue() {
    chart.setXAxisLabel( "foox" );
    assertEquals( "foox", chart.getXAxisLabel() );
  }

  @Test
  public void testSetYAxisLabel_changesValue() {
    chart.setYAxisLabel( "fooy" );
    assertEquals( "fooy", chart.getYAxisLabel() );
  }

  @Test
  public void testSetShowControls_isRendered() {
    chart.setShowControls( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showControls", true ) );
  }

  @Test
  public void testSetStacked_isRendered() {
    chart.setStacked( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "stacked", true ) );
  }

  @Test
  public void testSetReduceXTicks_isRendered() {
    chart.setReduceXTicks( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "reduceXTicks", false ) );
  }

  @Test
  public void testSetRotateLabels_isRendered() {
    chart.setRotateLabels( 30 );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "rotateLabels", 30 ) );
  }

  @Test
  public void testSetGroupSpacing_isRendered() {
    chart.setGroupSpacing( 1.3f );

    ArgumentCaptor<JsonObject> captor = ArgumentCaptor.forClass( JsonObject.class );
    verify( remoteObject ).call( eq( "setOptions" ), captor.capture() );
    assertEquals( 1.3f, captor.getValue().get( "groupSpacing" ).asFloat(), 0.0f );
  }

  @Test
  public void testSetShowYAxis_isRendered() {
    chart.setShowYAxis( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showYAxis", false ) );
  }

  @Test
  public void testSetShowXAxis_isRendered() {
    chart.setShowXAxis( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showXAxis", false ) );
  }

  @Test
  public void testSetXAxisLabel_isRendered() {
    chart.setXAxisLabel( "foox" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "xAxis.axisLabel", "foox" ) );
  }

  @Test
  public void testSetYAxisLabel_isRendered() {
    chart.setYAxisLabel( "fooy" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "yAxis.axisLabel", "fooy" ) );
  }

  @Test
  public void testSetItems() {
    DataItem2D[] values = new DataItem2D[ 3 ];
    values[ 0 ] = new DataItem2D( 1, 25.986570 );
    values[ 1 ] = new DataItem2D( 2, 5.986570 );
    values[ 2 ] = new DataItem2D( 3, 15.86570 );
    DataGroup streamOne = new DataGroup( values, "Stream 1" );
    chart.setStacked( true );

    chart.setItems( streamOne );

    String expected = "[{\"key\":\"Stream 1\","
        + "\"nonStackable\":true,"
        + "\"values\":[{\"x\":1,\"y\":25.98657},"
        + "{\"x\":2,\"y\":5.98657},"
        + "{\"x\":3,\"y\":15.8657}]}]";

    verify( remoteObject ).set( "items", JsonValue.readFrom( expected ) );
  }

  @Test
  public void testSetItems_withoutSettingStacked() {
    DataItem2D[] values = new DataItem2D[ 3 ];
    values[ 0 ] = new DataItem2D( 1, 25.986570 );
    values[ 1 ] = new DataItem2D( 2, 5.986570 );
    values[ 2 ] = new DataItem2D( 3, 15.86570 );
    DataGroup streamOne = new DataGroup( values, "Stream 1" );

    chart.setItems( streamOne );

    String expected = "[{\"key\":\"Stream 1\","
        + "\"nonStackable\":false,"
        + "\"values\":[{\"x\":1,\"y\":25.98657},"
        + "{\"x\":2,\"y\":5.98657},"
        + "{\"x\":3,\"y\":15.8657}]}]";

    verify( remoteObject ).set( "items", JsonValue.readFrom( expected ) );
  }

  @Test
  public void testSetItems_beforeStacked() {
    DataItem2D[] values = new DataItem2D[ 3 ];
    values[ 0 ] = new DataItem2D( 1, 25.986570 );
    values[ 1 ] = new DataItem2D( 2, 5.986570 );
    values[ 2 ] = new DataItem2D( 3, 15.86570 );
    DataGroup streamOne = new DataGroup( values, "Stream 1" );

    chart.setItems( streamOne );
    chart.setStacked( true );

    String expected = "[{\"key\":\"Stream 1\","
        + "\"nonStackable\":true,"
        + "\"values\":[{\"x\":1,\"y\":25.98657},"
        + "{\"x\":2,\"y\":5.98657},"
        + "{\"x\":3,\"y\":15.8657}]}]";

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
