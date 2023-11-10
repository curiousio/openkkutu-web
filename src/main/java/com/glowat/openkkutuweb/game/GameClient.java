package com.glowat.openkkutuweb.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    private val isSecure: Boolean,
    private val host: String,
    private val port: Int,
    private val key: String,
    private val id: Short
 */

public class GameClient extends WebSocketAdapter {
  private final Boolean isSecure;
  private final String host;
  private final int port;
  private final String key;
  private final short id;

  private final Logger logger = LoggerFactory.getLogger(GameClient.class);
  private WebSocket webSocket;
  private int players = 0;

  public GameClient(boolean isSecure, String host, int port, String key, short id) {
    this.isSecure = isSecure;
    this.host = host;
    this.port = port;
    this.key = key;
    this.id = id;
    connectWebSocket();
  }

  public void connectWebSocket() {
    try {
      String protocol = isSecure ? "wss" : "ws";
      String url = protocol + "://" + host + ":" + port + "/ws?k=" + key + "&i=" + id;
      webSocket = new WebSocketFactory().createSocket(new URI(url));
      webSocket.addListener(this);
      webSocket.connectAsynchronously();
    } catch (IOException | URISyntaxException e) {
      logger.warn(port + " @ 게임서버 연결에 실패했습니다. " + e.getMessage());
    }
  }
  @Override
  public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
    logger.info(port + " @ 게임서버#" + id + " 가 연결되었습니다.");
  }

  @Override
  public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
    if (closedByServer) {
      logger.info("서버에 의해 " + port + " @ 게임서버#" + id + " 의 연결이 끊어졌습니다.");
    } else {
      logger.info(port + " @ 게임서버#" + id + " 의 연결이 끊어졌습니다.");
    }
  }

  @Override
  public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
    logger.error(port + " @ 게임서버#" + id + " 에서 오류가 발생했습니다.", cause);
  }

  @Override
  public void onTextMessage(WebSocket websocket, String text) throws Exception {
    try {
      JsonNode jsonNode = new ObjectMapper().readTree(text);
      String type = jsonNode.get("type").textValue();

      if ("seek".equals(type)) {
        players = jsonNode.get("value").intValue();
      }
    } catch (IOException e) {
      logger.error(port + " @ 게임서버#" + id + " 에서 오류가 발생했습니다.", e);
    }
  }

  public void send(String data) {
    if (!webSocket.isOpen()) return;
    webSocket.sendText(data);
  }

  public boolean isConnected() {
    return webSocket != null && webSocket.isOpen();
  }
}
