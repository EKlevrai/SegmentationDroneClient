package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Interface regroupant toutes les fonctions de callback utiles lorsqu'on utilise socketIO client-side
 * @author Olivier
 *
 */
public interface CallbackAdapter {
	/**
	 * 
	 * @param data : json
	 * @throws JSONException
	 */
    public void callback(JSONArray data) throws JSONException;
    /**
     * La forme la plus standard des communication par socket : 
     * le  serveur emet sur la socket un message de la forme :::
     * @param event : string qui repr�sente la nature de l'evenement
     * @param data : JSONobject qui presente les potentielles donn�es pour mieux definir
     */
    public void on(String event, JSONObject data);
    /**
     * Le serveur communique par message(type string)
     * @param message : le message
     */
    public void onMessage(String message);
    /**
     * Le serveur communique par message(type JSONObject)
     * @param json : l'objet json composant le message
     */
    public void onMessage(JSONObject json);
    /**
     * callback � executer lorsque la connexion est �tablie
     */
    public void onConnect();
    /**
     * callback � executer apres une d�connexion
     */
    public void onDisconnect();
    /**
     * callback � executer lorsque la connexion a �chou�
     */
    public void onConnectFailure();
}
