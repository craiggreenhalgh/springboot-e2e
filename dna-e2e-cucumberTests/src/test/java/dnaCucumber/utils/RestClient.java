package dnaCucumber.utils;

//import com.anatwine.test.constants.Constant;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vikram103069 on 11/10/2016.
 */
public class RestClient {

    Client client;
    WebResource resource;
    DefaultClientConfig config;

    public RestClient(String url, Map<String, String> properties){
        config = new DefaultClientConfig();
        config.getProperties()
                .put(URLConnectionClientHandler.PROPERTY_HTTP_URL_CONNECTION_SET_METHOD_WORKAROUND,
                        true);
        client = Client.create(config);
        resource = client.resource(url);
        resource.accept("MediaType.APPLICATION_JSON_TYPE");
        for(Map.Entry<String, String> entry: properties.entrySet()) {
            resource.setProperty(entry.getKey(), entry.getValue());
        }
    }

    public void setAuthentication(String user, String password){
        client.addFilter(new HTTPBasicAuthFilter(user, password));
    }

//    public void setAuthentication(String basicAuthCode)

    /**
     * invokes the get operation
     */
    public ClientResponse get(){
        return resource.get(ClientResponse.class);
    }

    /**
     * invokes the post operation
     */
    public ClientResponse post(){
        return resource.post(ClientResponse.class);
    }

    /**
     * invokes post operation
     */
    public ClientResponse post(String input){
        return resource.post(ClientResponse.class, input);
    }

    /**
     * invokes put operation
     */
    public ClientResponse put(String input){
        return resource.put(ClientResponse.class, input);
    }

    /**
     * invokes patch operation
     */
    public ClientResponse patch(String input){
        return resource.method("PATCH", ClientResponse.class, input);
    }

    public static int getStatus(ClientResponse response){return response.getStatus(); }

//    public static String getMessage(ClientResponse res){
//        Map<String,String> response = new HashMap<String,String>();
//        return response.put(Constant.DATA,res.getEntity(String.class));
//    }

    public static String getCorrelationId(ClientResponse response){return response.getHeaders().getFirst("anatwineCorrelationId");}

}
