package login.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import login.shared.FieldVerifier;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Main implements EntryPoint {

    private static final String SERVER_ERROR = "При обращении к серверу произошла ошибка. Проверьте соединение.";

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    public void onModuleLoad() {
        final Button sendButton = new Button("регистрация");
        final TextBox nameField = new TextBox(),
                passwordField = new TextBox();

        nameField.getElement().setPropertyString("placeholder", "ваше имя");
        passwordField.getElement().setPropertyString("placeholder", "ваш пароль");

        final Label errorLabel = new Label();

        sendButton.addStyleName("sendButton");

        RootPanel.get("nameFieldContainer").add(nameField);
        RootPanel.get("passwordFielContainer").add(passwordField);

        RootPanel.get("sendButtonContainer").add(sendButton);
        RootPanel.get("errorLabelContainer").add(errorLabel);

        nameField.setFocus(true);
        nameField.selectAll();

        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Регистрация");
        dialogBox.setAnimationEnabled(true);

        final Button closeButton = new Button("Закрыть");
        closeButton.getElement().setId("closeButton");
        closeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
                sendButton.setEnabled(true);
                sendButton.setFocus(true);
            }
        });

        final Label textToServerLabel = new Label();
        final HTML serverResponseLabel = new HTML();
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Введённое имя:</b>"));
        dialogVPanel.add(textToServerLabel);
        dialogVPanel.add(new HTML("<br><b>Ответ сервера:</b>"));
        dialogVPanel.add(serverResponseLabel);
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);


        class MyHandler implements ClickHandler, KeyUpHandler {

            public void onClick(ClickEvent event) {
                sendNameToServer();
            }

            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
                    sendNameToServer();
                }
            }

            private void sendNameToServer() {
                errorLabel.setText("");
                String name = nameField.getText(),
                        password = passwordField.getText();

                if (!FieldVerifier.isValidName(name)) {
                    errorLabel.setText("имя должно включать не менее 4 знаков");
                    return;
                }

                if (!FieldVerifier.isValidPassword(password)){
                    errorLabel.setText("пароль должен включать не менее 8 знаков");
                    return;
                }

                sendButton.setEnabled(false);
                textToServerLabel.setText(name);
                serverResponseLabel.setText("");

                greetingService.login(name, password, new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        dialogBox.setText("Remote Procedure Call - Failure");
                        serverResponseLabel.addStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(throwable.getMessage());
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }

                    @Override
                    public void onSuccess(String response) {
                        dialogBox.setText("Remote Procedure Call");
                        serverResponseLabel.removeStyleName("serverResponseLabelError");
                        serverResponseLabel.setHTML(response);
                        dialogBox.center();
                        closeButton.setFocus(true);
                    }
                });

            }
        }

        MyHandler handler = new MyHandler();
        sendButton.addClickHandler(handler);
        nameField.addKeyUpHandler(handler);
    }
}
