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
public class DonutChart_Test {

  private Display display;
  private Shell shell;
  private RemoteObject remoteObject;
  private Connection connection;
  private DonutChart chart;

  @Rule
  public TestContext context = new TestContext();

  @Before
  public void setUp() {
    display = new Display();
    shell = new Shell( display );
    remoteObject = mock( RemoteObject.class );
    connection = fakeConnection( remoteObject );
    chart = new DonutChart( shell, SWT.NONE );
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
  public void testGetCornerRadius_hasDefault() {
    assertEquals( 0.0d, chart.getCornerRadius(), 0.0d );
  }

  @Test
  public void testSetCornerRadius_changesValue() {
    chart.setCornerRadius( 0.2d );

    assertEquals( 0.2d, chart.getCornerRadius(), 0.0d );
  }

  @Test
  public void testSetCornerRadius_isRendered() {
    chart.setCornerRadius( 0.2d );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "cornerRadius", 0.2d ) );
  }

  @Test
  public void testGetDonutRatio_hasDefault() {
    assertEquals( 0.5d, chart.getDonutRatio(), 0.0d );
  }

  @Test
  public void testSetDonutRatio_changesValue() {
    chart.setDonutRatio( 0.2d );

    assertEquals( 0.2d, chart.getDonutRatio(), 0.0d );
  }

  @Test
  public void testSetDonutRatio_isRendered() {
    chart.setDonutRatio( 0.2d );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "donutRatio", 0.2d ) );
  }

  @Test
  public void testGetPadAngle_hasDefault() {
    assertEquals( 0.0d, chart.getPadAngle(), 0.0d );
  }

  @Test
  public void testSetPadAngle_changesValue() {
    chart.setPadAngle( 0.2d );

    assertEquals( 0.2d, chart.getPadAngle(), 0.0d );
  }

  @Test
  public void testSetPadAngle_isRendered() {
    chart.setPadAngle( 0.2d );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "padAngle", 0.2d ) );
  }

  @Test
  public void testGetTitle_hasDefault() {
    assertEquals( "", chart.getTitle() );
  }

  @Test
  public void testSetTitle_changesValue() {
    chart.setTitle( "foo" );

    assertEquals( "foo", chart.getTitle() );
  }

  @Test
  public void testSetTitle_isRendered() {
    chart.setTitle( "foo" );

    verify( remoteObject ).call( "setOptions", new JsonObject().add( "title", "foo" ) );
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
