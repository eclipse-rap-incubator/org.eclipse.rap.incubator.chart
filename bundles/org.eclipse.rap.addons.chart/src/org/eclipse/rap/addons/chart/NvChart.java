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


/**
 * A chart widget based on <a href="http://nvd3.org/">nvd3.js</a>.
 * <p>
 * Be default, the nvd3 JS library and CSS (<code>nv.d3.min.js</code> and
 * <code>nv.d3.min.css</code>) is loaded from a CDN. To change the URLs, you can set the system
 * properties <em>org.eclipse.rap.addons.chart.nvd3JsUrl</em> and
 * <em>org.eclipse.rap.addons.chart.nvd3CssUrl</em> to custom URLs.
 * </p>
 * <p>
 * Subclasses must provide a client implementation and refer to it using a renderer id in the
 * constructor. Have a look at the existing implementations of this class.
 * </p>
 *
 * @see "http://nvd3.org/"
 */
public abstract class NvChart extends Chart {

  private static final String PROP_NVD3_JS_URL = "org.eclipse.rap.addons.chart.nvd3JsUrl";
  private static final String PROP_NVD3_CSS_URL = "org.eclipse.rap.addons.chart.nvd3CssUrl";
  private static final String DEF_NVD3_JS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.2/nv.d3.min.js";
  private static final String DEF_NVD3_CSS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.2/nv.d3.min.css";

  /**
   * Create a chart instance that is implemented by the given <code>renderer</code>.
   */
  protected NvChart( Composite parent, int style, String renderer ) {
    super( parent, style, renderer );
    requireJs( System.getProperty( PROP_NVD3_JS_URL, DEF_NVD3_JS_URL ) );
    requireCss( System.getProperty( PROP_NVD3_CSS_URL, DEF_NVD3_CSS_URL ) );
    requireCss( registerResource( "resources/nv-chart.css" ) );
  }

}
