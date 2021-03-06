package rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
public abstract class EndPoint {
    protected List<Map<String, Channel>> channels;

    protected List<Connection> connections;

    protected  Connection connection;

    protected Channel channel;

    protected String endPointName;

    public EndPoint(String endPointName) throws IOException,TimeoutException{
        this.endPointName = endPointName;

        //Create a connection factory
        ConnectionFactory factory = new ConnectionFactory();

        //hostname of your rabbitmq server
        factory.setHost("114.55.10.15");
        factory.setUsername("admin");
        factory.setPassword("123456");

        //getting a connection
        connection = factory.newConnection();

        //creating a channel
        channel = connection.createChannel();

        //declaring a queue for this channel. If queue does not exist,
        //it will be created on the server.
        channel.queueDeclare(endPointName,true,false,false,null);
    }

    public void close() throws IOException,TimeoutException{
        this.channel.close();
        this.connection.close();
    }
}
