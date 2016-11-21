/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2002-2016, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 */

package org.geotools.data.arcgisrest;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.Parameter;
import org.geotools.data.DataAccessFactory.Param;
import org.geotools.util.SimpleInternationalString;
import org.geotools.util.logging.Logging;

public class ArcGISRestDataStoreFactory implements DataStoreFactorySpi {

  /** Package's logger */
  protected static final Logger LOGGER = Logging
      .getLogger(ArcGISRestDataStoreFactory.class.getName());

  public static final String FACTORY_NAME = "ArcGIS ReST";
  public static final String FACTORY_DESCRIPTION = "ESRI(tm) ArcGIS ReST API data store";

  private static List<Param> paramMetadata = new ArrayList<Param>(10);

  public static final Param NAMESPACE_PARAM = new Param("namespace",
      String.class, "namespace associated to this data store", true);
  public static final Param URL_PARAM = new Param("url", String.class,
      "ednpoint of the ArcGSI ReST API", true);
  public static final Param USER_PARAM = new Param("user", String.class,
      new SimpleInternationalString("the username of the endpoint"), false,
      null);
  public static final Param PASSWORD_PARAM = new Param("password", String.class,
      new SimpleInternationalString(
          "the password associated with the username."),
      false, null,
      Collections.singletonMap(Parameter.IS_PASSWORD, Boolean.TRUE));

  static {
    paramMetadata.add(NAMESPACE_PARAM);
    paramMetadata.add(URL_PARAM);
    paramMetadata.add(USER_PARAM);
    paramMetadata.add(PASSWORD_PARAM);
  }

  public ArcGISRestDataStoreFactory() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public DataStore createNewDataStore(Map<String, Serializable> params)
      throws IOException {
    return createDataStore(params);
  }

  @Override
  public DataStore createDataStore(Map<String, Serializable> params)
      throws IOException {

    ArcGISRestDataStore dataStore = new ArcGISRestDataStore(
        (String) params.get(NAMESPACE_PARAM), (String) params.get(URL_PARAM),
        (String) params.get(USER_PARAM), (String) params.get(PASSWORD_PARAM));
    return dataStore;
  }

  @Override
  public String getDisplayName() {
    return FACTORY_NAME;
  }

  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return FACTORY_DESCRIPTION;
  }

  @Override
  public Param[] getParametersInfo() {
    // TODO Auto-generated method stub
    return (Param[]) paramMetadata.toArray();
  }

  @Override
  public boolean canProcess(Map<String, Serializable> params) {
    try {
      new URL((String) params.get(ArcGISRestDataStoreFactory.URL_PARAM.key));
    } catch (MalformedURLException e) {
      return false;
    }
    try {
      new URL((String) params.get(ArcGISRestDataStoreFactory.NAMESPACE_PARAM.key));
    } catch (MalformedURLException e) {
      return false;
    }

    return true;
  }

  @Override
  public boolean isAvailable() {
    return true;
  }

  @Override
  public Map<Key, ?> getImplementationHints() {
    return null;
  }

}