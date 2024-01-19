/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import static java.sql.JDBCType.BLOB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACHARD
 */
public class BlobGen implements AutoCloseable {

    private final Connection connection;
    private final List<Blob> blobs;
    private final List<Clob> clobs;

    public BlobGen(Connection connection) {
        this.connection = connection;
        this.blobs = new ArrayList<>();
        this.clobs = new ArrayList<>();
    }

    public final Blob blob(byte[] bytes)
            throws SQLException {
        Blob blob;
        blob = connection.createBlob();
        blob.setBytes(1, bytes);
        blobs.add(blob);
        return blob;
    }

    public final Clob clob(String string)
            throws SQLException {
        Clob clob;
        clob = connection.createClob();
        clob.setString(1, string);
        clobs.add(clob);
        return clob;
    }

    @Override
    public final void close() throws Exception {
        blobs.clear();
        clobs.clear();

    }
}
