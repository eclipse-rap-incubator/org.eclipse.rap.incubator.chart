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


public abstract class NvChart extends Chart {

  private static final String PROP_NVD3_JS_URL = "org.eclipse.rap.addons.chart.nvd3JsUrl";
  private static final String PROP_NVD3_CSS_URL = "org.eclipse.rap.addons.chart.nvd3CssUrl";
  private static final String DEF_NVD3_JS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.js";
  private static final String DEF_NVD3_CSS_URL
    = "https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.css";


  protected NvChart( Composite parent, int style, String renderer ) {
    super( parent, style, renderer );
    requireJs( System.getProperty( PROP_NVD3_JS_URL, DEF_NVD3_JS_URL ) );
    requireCss( System.getProperty( PROP_NVD3_CSS_URL, DEF_NVD3_CSS_URL ) );
    requireCss( registerResource( "resources/nv-chart.css" ) );
  }

}
