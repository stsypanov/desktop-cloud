package login.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface GreetingServiceAsync {
    void greetServer(String name, AsyncCallback<String> async);

    void login(String name, String password, AsyncCallback<String> async);
}
