package login.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import login.client.GreetingService;
import login.shared.FieldVerifier;
import org.hibernate.exception.ConstraintViolationException;
import ua.kpi.its.desktopcloud.database.entity.Supplicant;
import ua.kpi.its.desktopcloud.database.dao.SupplicantDAO;

import java.io.IOException;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private SupplicantDAO dao;

    public String greetServer(String input) throws Exception {
        if (!FieldVerifier.isValidName(input)) {
            throw new IllegalArgumentException("Name must be at least 4 characters long");
        }

        String serverInfo = getServletContext().getServerInfo();
        String userAgent = getThreadLocalRequest().getHeader("User-Agent");

        input = escapeHtml(input);
        userAgent = escapeHtml(userAgent);

        return "Hello, " + input + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using:<br>" + userAgent;
    }

    @Override
    public String login(String name, String password) throws Exception {
        try {
            if (dao == null) {
                instantiateDAO();
            }
            dao.save(new Supplicant(name, password));
//            ActiveUserService.storeSupplicant(name, password);

        } catch (ConstraintViolationException e) {
            if (e.getCause().getMessage().toLowerCase().contains("duplicate")) {
                throw new Exception("Failed to save supplicant in DB: provide another user name", e);
            }
                throw e;
        } catch (IOException e){
            throw new Exception("Failed to instantiate DAO", e);
        }

        return "Login successful";
    }

    /**
     * Escape an html string. Escaping data received from the ua.kpi.its.desktopcloud.client helps to
     * prevent cross-site script vulnerabilities.
     *
     * @param html the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    private void instantiateDAO() throws IOException {
        dao = new SupplicantDAO();
    }
}
