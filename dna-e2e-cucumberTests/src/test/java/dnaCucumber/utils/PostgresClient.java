package dnaCucumber.utils;

import dnaCucumber.config.Config;
import org.apache.commons.lang.UnhandledException;
import org.postgresql.jdbc42.Jdbc42Connection;
import org.postgresql.util.HostSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Vikram103069 on 14/10/2016.
 */
public class PostgresClient {
    private static final Logger LOG = LoggerFactory.getLogger(PostgresClient.class);
    private Jdbc42Connection con = null;

    private int pollTime;
    private Config config = Config.getInstance();

    public PostgresClient(){
        initialize();
    }

    private void initialize(){
        try{
            HostSpec spec = new HostSpec(config.getPostGresHost(), Integer.parseInt(config.getPostgresPortNumber()));
            HostSpec[] specs = {spec};
            Properties pro = new Properties();
            pro.setProperty("password", config.getPostgresPassword());
            con = new Jdbc42Connection(specs, config.getPostgresuserName(),
                    config.getPostgresDbName(), pro, config.getPostgresUrl());
        }
        catch (Exception ignore){
            throw  new UnhandledException(ignore);
        }
    }


    public ResultSet executeQuery(String query){
        try {
            return con.execSQLQuery(query);
        }
        catch(SQLException e){
            throw new UnhandledException(e);
        }
    }

    public boolean executeUpdateQuery(String query){
        try {
            con.execSQLUpdate(query);
            return true;
        }
        catch(SQLException e){
            return false;
        }
    }

    public int getResultSetCount(ResultSet set){
        int count = 0;
        try {
            while (set.next()) {
                count++;
            }
        }
        catch (SQLException e){
            throw new UnhandledException(e);
        }
        return count;
    }

    public List<Map<String, String>> executeQueryAsList(String query) throws SQLException, UnhandledException {
        try{
            return transformResultSetToHash(executeQuery(query));
        }
        catch (SQLException e){
            throw new UnhandledException(e);
        }
    }
    public boolean pollCondition(Predicate<PostgresClient> predicate, PostgresClient adapter){
        try {
            pollTime = 240;
            while (!predicate.test(adapter)) {
                Thread.sleep(1000);
                pollTime--;
                if(pollTime==0){
                    return false;
                }
            }
            return true;
        }
        catch (Exception ignore){
            return false;
        }
    }

    public boolean pollConditionForTime(Predicate<PostgresClient> predicate,
                                        PostgresClient adapter, int pollTime){
        try {
            while (!predicate.test(adapter)) {
                Thread.sleep(1000);
                pollTime--;
                if(pollTime==0){
                    return false;
                }
            }
            return true;
        }
        catch (Exception ignore){
            return false;
        }
    }


    public void closeConnection(){
        try {
            if (!con.isClosed())
                con.close();
        }
        catch (Exception ignore){
            LOG.info("Can not close the existing connection");
        }
    }


    private List<Map<String, String>> transformResultSetToHash(ResultSet set) throws SQLException {
        List<Map<String, String>> maps = new ArrayList<>();
        try {
            ResultSetMetaData metaData = set.getMetaData();
            int columns = metaData.getColumnCount();
            while (set.next()) {
                Map<String, String> map = new HashMap<>();
                if (!set.isAfterLast()) {
                    for (int i = 1; i <= columns; i++) {
                        String column = set.getMetaData().getColumnName(i);
                        map.put(column, set.getString(i));
                    }
                }
                maps.add(map);
            }
            return maps;
        }
        catch(SQLException e){
            throw e;
        }
    }
}
