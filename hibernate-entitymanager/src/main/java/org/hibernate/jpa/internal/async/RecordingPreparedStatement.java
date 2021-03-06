package org.hibernate.jpa.internal.async;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * PreparedStatement stub, which is not actually a real statement, but captures the sql, parameters, and types set on it.
 *
 * @author Jakob Korherr
 */
public class RecordingPreparedStatement implements PreparedStatement {

    private final List<Object> parameters;
    private final List<Integer> parameterTypes;
    private final String sql;

    public RecordingPreparedStatement(String sql) {
        this.sql = sql;
        parameters = new ArrayList<>();
        parameterTypes = new ArrayList<>();
    }

    public String getSql() {
        return sql;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public List<Integer> getParameterTypes() {
        return parameterTypes;
    }

    private void ensureCapacity(int index) {
        while (index >= parameters.size()) {
            parameters.add(null);
            parameterTypes.add(null);
        }
    }

    @Override
    public void clearParameters() throws SQLException {
        parameters.clear();
        parameterTypes.clear();
    }

    @Override
    public void close() throws SQLException {
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }


    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    // #### tracked parameter setters #################################################

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, null);
        parameterTypes.set(parameterIndex - 1, sqlType);
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.BOOLEAN);
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.TINYINT);
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.SMALLINT);
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.INTEGER);
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.BIGINT);
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.FLOAT);
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.DOUBLE);
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.NUMERIC);
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.VARCHAR);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.BINARY);
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.DATE);
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.TIME);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        ensureCapacity(parameterIndex - 1);
        parameters.set(parameterIndex - 1, x);
        parameterTypes.set(parameterIndex - 1, Types.TIMESTAMP);
    }

    // #### not implemented parameter setter ######################################################

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    // #### not implemented methods ###########################################################

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }
    
    @Override
    public boolean execute() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void addBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getMaxRows() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void cancel() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getUpdateCount() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getResultSetType() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void clearBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int[] executeBatch() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public Connection getConnection() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public int executeUpdate() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean isPoolable() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public void closeOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        throw new SQLFeatureNotSupportedException("not implemented");
    }

}
