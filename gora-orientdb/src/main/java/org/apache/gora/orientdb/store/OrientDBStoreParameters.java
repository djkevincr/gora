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

package org.apache.gora.orientdb.store;

import java.util.Properties;

public class OrientDBStoreParameters {

    public static final String ORIENT_DB_MAPPING_FILE = "gora.orientdb.mapping.file";
    public static final String ORIENT_DB_SERVER_HOST = "gora.orientdb.server.host";
    public static final String ORIENT_DB_SERVER_PORT = "gora.orientdb.server.port";
    public static final String ORIENT_DB_USER_USERNAME = "gora.orientdb.user.username";
    public static final String ORIENT_DB_USER_PASSWORD = "gora.orientdb.user.password";
    public static final String ORIENT_DB_DB_NAME = "gora.orientdb.database.name";
    public static final String ORIENT_DB_CONNECTION_POOL_SIZE = "gora.orientdb.con.pool.size";
    public static final String ORIENT_DB_STORAGE_TYPE = "gora.orientdb.storage.type";


    private String mappingFile;
    private String serverHost;
    private String serverPort;
    private String userName;
    private String userPassword;
    private String databaseName;
    private String connPoolSize;
    private String storageType;


    public String getMappingFile() {
        return this.mappingFile;
    }

    public String getServerHost() {
        return this.serverHost;
    }

    public String getServerPort() {
        return this.serverPort;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserPassword() {
        return this.userPassword;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public String getConnectionPoolSize() {
        return this.connPoolSize;
    }

    public String getStorageType() {
        return this.storageType;
    }

    public OrientDBStoreParameters(String mappingFile,
                                   String serverHost,
                                   String serverPort,
                                   String userName,
                                   String userPassword,
                                   String databaseName,
                                   String connPoolSize,
                                   String storageType) {
        this.mappingFile = mappingFile;
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.userName = userName;
        this.userPassword = userPassword;
        this.databaseName = databaseName;
        this.connPoolSize = connPoolSize;
        this.storageType = storageType;
    }

    public static OrientDBStoreParameters load(Properties properties) {
        String propMappingFile = properties.getProperty(ORIENT_DB_MAPPING_FILE,
                OrientDBStore.DEFAULT_MAPPING_FILE);
        String propServerHost = properties.getProperty(ORIENT_DB_SERVER_HOST);
        String propServerPort = properties.getProperty(ORIENT_DB_SERVER_PORT);
        String propUserName = properties.getProperty(ORIENT_DB_USER_USERNAME);
        String propUserPassword = properties.getProperty(ORIENT_DB_USER_PASSWORD);
        String propDatabaseName = properties.getProperty(ORIENT_DB_DB_NAME);
        String propConnPoolSize = properties.getProperty(ORIENT_DB_CONNECTION_POOL_SIZE);
        String propStorageType = properties.getProperty(ORIENT_DB_STORAGE_TYPE);
        return new OrientDBStoreParameters(propMappingFile,
                propServerHost, propServerPort, propUserName,
                propUserPassword, propDatabaseName, propConnPoolSize,propStorageType);
    }
}
