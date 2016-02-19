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
package org.eclipse.rap.addons.chart;

import org.eclipse.swt.widgets.Composite;


public abstract class MapChart extends Chart {

  private static final String PROP_TOPOJSON_JS_URL = "org.eclipse.rap.addons.chart.topojsonJsUrl";
  private static final String DEF_TOPOJSON_JS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/topojson/1.6.20/topojson.min.js";

  /**
   * Create a chart instance that is implemented by the given <code>renderer</code>.
   */
  protected MapChart( Composite parent, int style, String renderer ) {
    super( parent, style, renderer );
    requireJs( System.getProperty( PROP_TOPOJSON_JS_URL, DEF_TOPOJSON_JS_URL ) );
  }

}
