/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.gora.orientdb.query;

import com.github.raymanrt.orientqb.query.Parameter;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import org.apache.gora.orientdb.store.OrientDBMapping;
import org.apache.gora.persistency.impl.PersistentBase;
import org.apache.gora.query.impl.QueryBase;
import org.apache.gora.store.DataStore;
import com.github.raymanrt.orientqb.query.Query;

import java.util.HashMap;
import java.util.Map;

import static com.github.raymanrt.orientqb.query.Projection.projection;

public class OrientDBQuery<K, T extends PersistentBase> extends QueryBase<K, T> {

  private OSQLSynchQuery<ODocument> dbQuery;
  private Map<String, Object> params;

  public OrientDBQuery() {
    super(null);
  }

  public OrientDBQuery(DataStore<K, T> dataStore) {
    super(dataStore);
  }

  public OSQLSynchQuery<ODocument> getOrientDBQuery() {
    return dbQuery;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public OSQLSynchQuery<ODocument> populateOrientDBQuery(final OrientDBMapping orientDBMapping,
                                                         final String[] fields) {
    params = new HashMap<String, Object>();
    Query selectQuery = new Query();
    selectQuery.from(orientDBMapping.getDocumentClass());
    if ((this.getStartKey() != null) && (this.getEndKey() != null)
            && this.getStartKey().equals(this.getEndKey())) {
      selectQuery.where(projection("_id").eq(Parameter.parameter("key")));
      params.put("key", this.getStartKey());
    } else if (this.getStartKey() != null || this.getEndKey() != null) {
      if (this.getStartKey() != null) {
        selectQuery.where(projection("_id").ge(Parameter.parameter("key_lower")));
        params.put("key_lower", this.getStartKey());
      }
      if (this.getEndKey() != null) {
        selectQuery.where(projection("_id").ge(Parameter.parameter("key_upper")));
        params.put("key_upper", this.getEndKey());
      }
    }

    for (String k : fields) {
      String dbFieldName = orientDBMapping.getDocumentField(k);
      if (dbFieldName != null && dbFieldName.length() > 0) {
        selectQuery.select(dbFieldName);
      }
    }

    dbQuery = new OSQLSynchQuery<ODocument>(selectQuery.toString());
    return dbQuery;
  }

}
