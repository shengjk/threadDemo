package xmht.threadDemo.connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author shengjk1
 * @date 6/18/2024
 */
public class ConnectionPool {
    private LinkedList<Connection> pool= new LinkedList<Connection>();

    public ConnectionPool(int initialSize) {
        if (initialSize>0){
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }
    public void reeleaseConnection(Connection connection){
        if (connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            if (mills <= 0) {

                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining=mills;
                // 超時操作
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining=future-System.currentTimeMillis();
                }
                Connection connection=null;
                if (!pool.isEmpty()){
                    connection=pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
