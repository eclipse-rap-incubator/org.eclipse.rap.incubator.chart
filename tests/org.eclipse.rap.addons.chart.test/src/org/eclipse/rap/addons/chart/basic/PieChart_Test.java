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


@SuppressWarnings( "restriction" )
public class PieChart_Test {

  private Display display;
  private Shell shell;
  private RemoteObject remoteObject;
  private Connection connection;
  private PieChart chart;

  @Rule
  public TestContext context = new TestContext();

  @Before
  public void setUp() {
    display = new Display();
    shell = new Shell( display );
    remoteObject = mock( RemoteObject.class );
    connection = fakeConnection( remoteObject );
    chart = new PieChart( shell, SWT.NONE );
  }

  @Test
  public void testCreate_createsRemoteObject() {
    verify( connection ).createRemoteObject( eq( "rwt.chart.Chart" ) );
  }

  @Test
  public void testCreate_setsRenderer() {
    verify( remoteObject ).set( "renderer", "nv-pie" );
  }

  @Test
  public void testGetShowLabels_defaultsToTrue() {
    assertTrue( chart.getShowLabels() );
  }

  @Test
  public void testSetShowLabels_changesValue() {
    chart.setShowLabels( false );

    assertFalse( chart.getShowLabels() );
  }

  @Test
  public void testSetShowLabels_isRendered() {
    chart.setShowLabels( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "showLabels", false ) );
  }

  @Test
  public void testGetLabelsOutside_defaultsToFalse() {
    assertFalse( chart.getLabelsOutside() );
  }

  @Test
  public void testSetLabelsOutside_changesValue() {
    chart.setLabelsOutside( true );

    assertTrue( chart.getLabelsOutside() );
  }

  @Test
  public void testSetLabelsOutside_isRendered() {
    chart.setLabelsOutside( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "labelsOutside", true ) );
  }

  @Test
  public void testGetGrowOnHover_defaultsToTrue() {
    assertTrue( chart.getGrowOnHover() );
  }

  @Test
  public void testSetGrowOnHover_changesValue() {
    chart.setGrowOnHover( false );

    assertFalse( chart.getGrowOnHover() );
  }

  @Test
  public void testSetGrowOnHover_isRendered() {
    chart.setGrowOnHover( false );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "growOnHover", false ) );
  }

  @Test
  public void testGetLabelSunbeamLayout_defaultsToFalse() {
    assertFalse( chart.getLabelSunbeamLayout() );
  }

  @Test
  public void testSetLabelSunbeamLayout_changesValue() {
    chart.setLabelSunbeamLayout( true );

    assertTrue( chart.getLabelSunbeamLayout() );
  }

  @Test
  public void testSetLabelSunbeamLayout_isRendered() {
    chart.setLabelSunbeamLayout( true );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "labelSunbeamLayout", true ) );
  }

  @Test
  public void testGetLabelThreshold_hasDefault() {
    assertEquals( 0.02D, chart.getLabelThreshold(), 0.0 );
  }

  @Test
  public void testSetLabelThreshold_changesValue() {
    chart.setLabelThreshold( 0.0 );

    assertEquals( 0.0, chart.getLabelThreshold(), 0.0 );
  }

  @Test
  public void testSetLabelThreshold_isRendered() {
    chart.setLabelThreshold( 0.0 );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "labelThreshold", 0.0 ) );
  }

  @Test
  public void testGetLabelType_hasDefault() {
    assertEquals( "key", chart.getLabelType() );
  }

  @Test
  public void testSetLabelType_changesValue() {
    chart.setLabelType( "foo" );

    assertEquals( "foo", chart.getLabelType() );
  }

  @Test
  public void testSetLabelType_isRendered() {
    chart.setLabelType( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "labelType", "foo" ) );
  }

  @Test
  public void testGetLegendPosition_hasDefault() {
    assertEquals( "top", chart.getLegendPosition() );
  }

  @Test
  public void testSetLegendPosition_changesValue() {
    chart.setLegendPosition( "foo" );

    assertEquals( "foo", chart.getLegendPosition() );
  }

  @Test
  public void testSetLegendPosition_isRendered() {
    chart.setLegendPosition( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "legendPosition", "foo" ) );
  }

  @Test
  public void testSetItems() {
    chart.setItems( new DataItem( 23, "foo" ), new DataItem( 42, "bar" ) );

    String expected = "[{ \"value\": 23, \"label\": \"foo\" },"
                     + "{ \"value\": 42, \"label\": \"bar\" }]";
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
