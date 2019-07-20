/**
 * @author Rongxin Zhu
 * @Student_id 938816
 */

package com;

import com.jfoenix.controls.JFXButton;
import com.network.Message;
import common.ClientListener;
import common.LobbyController;
import common.LoginController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class PlayerItem extends AnchorPane {
    private int prefWidth = 120;
    private int prefHeight = 30;

    public PlayerItem(String playerName) {
        this.setPrefSize(prefWidth, prefHeight);
        // name lable and invite button
        Label lName = new Label(playerName);
        lName.setStyle("-fx-text-fill: #B2B2B2; -fx-font-size: 14.0; -fx-font-family: Arial Rounded MT Bold");
        JFXButton btnInvite = new JFXButton("Invite");
        btnInvite.getStyleClass().add("login-button");

        btnInvite.setOnAction(e -> {
            // show information dialog if the user hasn't join the table
            if (LobbyController.getInstance().inTable()) {
                Message inviteMsg = new Message(Message.Status.INVITE);
                inviteMsg.setUserName(LoginController.getInstance().getPlayerName());
                inviteMsg.setInvitedUsername(playerName);
                ClientListener.getInstance().sendMsg(inviteMsg);
            } else {
                LobbyController.getInstance().showDialog("Please join a table first.");
            }
        });

        lName.relocate(20, 7);
        btnInvite.relocate(90, 2);

        this.getChildren().addAll(lName, btnInvite);
    }
}

