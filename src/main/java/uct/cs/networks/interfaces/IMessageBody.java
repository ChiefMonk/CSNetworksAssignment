package uct.cs.networks.interfaces;

import java.security.Key;

import java.io.Serializable;

/**
 *
 * @author Chipo Hamayobe (chipo@cs.uct.ac.za)
 */
public interface IMessageBody extends Serializable {

    public String getData();

    public void setData(String data);

    public String getInfo();

    public void setInfo(String info);

    public Key getKey();

    public void setKey(Key key);
}
